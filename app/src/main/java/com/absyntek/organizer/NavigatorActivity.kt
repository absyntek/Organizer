package com.absyntek.organizer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.absyntek.organizer.utils.Destination

class NavigatorActivity : AppCompatActivity(){
    companion object {
        const val DESTINATION = "DESTINATION"
        @JvmStatic
        fun newIntent(context: Context, destination: Destination) =
            Intent(context, NavigatorActivity::class.java)
                .putExtra(DESTINATION, destination)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                finish()
            }
        }
        if (savedInstanceState == null) {
            val destination = intent.getSerializableExtra(DESTINATION) as? Destination

            if (destination == null) {
                Toast.makeText(this, "Ooops", Toast.LENGTH_LONG).show()
                finish()
                return
            }

            val fragment = destination.toFragment()
            if (fragment != null) {
                setContentView(R.layout.activity_navigator)
                supportFragmentManager.commit {
                    replace(R.id.fragment_navigator, fragment)
                    addToBackStack(fragment::class.qualifiedName)
                }
                return
            }

            val intent = destination.toIntent(this)
            if (intent != null) {
                startActivity(intent)
                finish()
            }
        }
    }
}
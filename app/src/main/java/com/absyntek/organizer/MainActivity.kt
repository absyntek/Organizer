package com.absyntek.organizer

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.absyntek.organizer.database.DataBase
import com.absyntek.organizer.databinding.ActivityMainBinding
import com.absyntek.organizer.utils.Destination

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val navController = findNavController(R.id.nav_host_fragment)
        bind.navView.setupWithNavController(navController)
        setUpListeners()
    }

    private fun setUpListeners(){
        bind.btnMenu.setOnClickListener {
            val popUp = PopupMenu(this, bind.btnMenu)
            popUp.menuInflater.inflate(R.menu.app_bar_menu, popUp.menu)
            popUp.setOnMenuItemClickListener {
                when (it.itemId){
                    R.id.menu_new_box -> {
                        startActivity(NavigatorActivity.newIntent(this, Destination.NewBox))
                        true
                    }
                    R.id.menu_new_system -> {
                        true
                    }
                    R.id.menu_setting -> {
                        true
                    }
                    else -> {false}
                }
            }
            popUp.show()
        }
        bind.navView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navigation_home -> {
                    bind.tvTitle.text = "Home"
                    true
                }
                R.id.navigation_dashboard -> {
                    bind.tvTitle.text = "DashBoard"
                    true
                }
                R.id.navigation_notifications -> {
                    bind.tvTitle.text = "Notif"
                    true
                }
                R.id.navigation_nutriments -> {
                    bind.tvTitle.text = "Nutriments"
                    true
                }
                else -> false
            }
        }
    }
}
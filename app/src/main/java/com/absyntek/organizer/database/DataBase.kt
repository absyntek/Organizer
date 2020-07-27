package com.absyntek.organizer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.absyntek.organizer.database.dao.BoxDao
import com.absyntek.organizer.database.dao.SystemDao
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.models.materials.SystemKind
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        SystemKind::class,
        BoxModel::class
    ], version = 1, exportSchema = true
)
abstract class DataBase : RoomDatabase(){
    abstract fun systemDao(): SystemDao
    abstract fun boxDao(): BoxDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var systemDao = database.systemDao()
                    var boxDao = database.boxDao()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            val tmpInstance = INSTANCE
            if (tmpInstance != null){
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "organizer_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
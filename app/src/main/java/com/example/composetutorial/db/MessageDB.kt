package com.example.composetutorial.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.composetutorial.Message
import com.example.composetutorial.SampleData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//This tutorial was used for basics of RoomDB: https://www.youtube.com/watch?v=sWOmlDvz_3U&t=404s

@Database(entities = [Message::class], version = 1)
abstract class MessageDB : RoomDatabase() {

    abstract fun getMessageDao(): MessageDao

    companion object {
        const val NAME = "Message_DB"

        // Code for initializing database using databaCallback generated using ChatGPT 5 model
        @Volatile
        var INSTANCE: MessageDB? = null

        fun getDatabase(context: Context): MessageDB {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MessageDB::class.java,
                    NAME
                )
                    .addCallback(DatabaseCallback())
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }


    private class DatabaseCallback : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->

                CoroutineScope(Dispatchers.IO).launch {
                    database.getMessageDao()
                        .addMessages(SampleData.conversationSample)
                }
            }
        }
    }
}
package com.example.composetutorial.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composetutorial.Message

//This tutorial was used for basics of RoomDB: https://www.youtube.com/watch?v=sWOmlDvz_3U&t=404s

@Dao
interface MessageDao {
    @Query("SELECT * FROM MESSAGE")
    fun getAllMessages() : LiveData<List<Message>>

    @Insert
    suspend fun addMessage(message: Message)

    @Query("SELECT author FROM Message LIMIT 1")
    fun getName(): LiveData<String>

    @Query("UPDATE Message SET author = :newAuthor")
    suspend fun updateAllAuthors(newAuthor: String)


    @Insert
    suspend fun addMessages(messages: List<Message>)

    @Query("SELECT COUNT(*) FROM Message")
    suspend fun getMessageCount(): Int

}
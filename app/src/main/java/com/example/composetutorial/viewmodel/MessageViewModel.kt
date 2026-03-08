package com.example.composetutorial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.composetutorial.Message
import com.example.composetutorial.db.MessageDB
import kotlinx.coroutines.launch

class MessageViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MessageDB.Companion.getDatabase(application).getMessageDao()

    val messages: LiveData<List<Message>> = dao.getAllMessages()

    fun updateUsername(name: String){
        viewModelScope.launch {
            dao.updateAllAuthors(name)
        }
    }

    val username: LiveData<String> = dao.getName()
}
package com.example.dependencyinjectionexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependencyinjectionexample.entity.NoteEntity
import com.example.dependencyinjectionexample.repository.DatabaseRepository
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: DatabaseRepository) : ViewModel() {

    private val _noteList: MutableLiveData<List<NoteEntity>> = MutableLiveData()
    val noteList: LiveData<List<NoteEntity>> get() = _noteList

    fun getAllNotes() = viewModelScope.launch {
        repository.getAllNotes().collect {
            _noteList.postValue(it)
        }
    }

    fun saveNote(note: NoteEntity) = viewModelScope.launch {
        repository.saveNote(note)
    }

}
package com.example.dependencyinjectionexample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.InvalidationTracker
import com.example.dependencyinjectionexample.R
import com.example.dependencyinjectionexample.adapter.NoteAdapter
import com.example.dependencyinjectionexample.databinding.ActivityMainBinding
import com.example.dependencyinjectionexample.viewmodel.DatabaseViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val noteAdapter by lazy { NoteAdapter() }
    private val viewModel: DatabaseViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnAddNote.setOnClickListener { addNoteFragment() }

        initializeRV()

        viewModel.getAllNotes()

        viewModel.noteList.observe(this, Observer {
            if (it.isEmpty()) {
                showEmpty(false)
            } else {
                showEmpty(true)
                noteAdapter.differ.submitList(it)
            }
        })
    }

    private fun initializeRV() {
        binding.rvNoteList.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun addNoteFragment() {
        AddNoteFragment().show(supportFragmentManager, AddNoteFragment().tag)
    }

    private fun showEmpty(isShown: Boolean) {
        binding.apply {
            if (isShown) {
                rvNoteList.visibility = View.VISIBLE
                emptyTxt.visibility = View.GONE
            } else {
                rvNoteList.visibility = View.GONE
                emptyTxt.visibility = View.VISIBLE
            }
        }
    }
}
package com.example.dependencyinjectionexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dependencyinjectionexample.R
import com.example.dependencyinjectionexample.databinding.FragmentAddNoteBinding
import com.example.dependencyinjectionexample.entity.NoteEntity
import com.example.dependencyinjectionexample.viewmodel.DatabaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject

class AddNoteFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel: DatabaseViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dialog as? BottomSheetDialog)?.behavior?.state = STATE_EXPANDED
        binding.btnSave.setOnClickListener {
            if (checkFields()) {
                val note = NoteEntity(
                    noteTitle = binding.edtTitle.text.toString(),
                    noteDesc = binding.edtDesc.text.toString()
                )
                saveNoteIntoDB(note)

                clearFields()
            }
        }
        binding.imgClose.setOnClickListener { dismiss() }
    }

    private fun clearFields() {
        binding.apply {
            edtTitle.setText("")
            edtDesc.setText("")
            dismiss()
        }
    }

    private fun checkFields(): Boolean {
        if (binding.edtDesc.text.isEmpty() || binding.edtTitle.text.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter data", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveNoteIntoDB(noteEntity: NoteEntity) {
        viewModel.saveNote(noteEntity)
    }
}
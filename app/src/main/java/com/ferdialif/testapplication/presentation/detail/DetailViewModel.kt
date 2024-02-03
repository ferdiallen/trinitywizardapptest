package com.ferdialif.testapplication.presentation.detail

import android.view.View
import androidx.lifecycle.ViewModel
import com.ferdialif.testapplication.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

}
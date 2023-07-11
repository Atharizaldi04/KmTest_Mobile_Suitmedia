package com.athar.suitmediatestathar

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getStory(): LiveData<PagingData<DataItem>> =
        userRepository.getStory().cachedIn(viewModelScope)

}

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.provideRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
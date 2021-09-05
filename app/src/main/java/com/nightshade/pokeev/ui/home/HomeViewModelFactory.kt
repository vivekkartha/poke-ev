package com.nightshade.pokeev.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightshade.pokeev.data.providers.DataProvider

class HomeViewModelFactory(private val dataProvider: DataProvider) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(dataProvider) as T

}
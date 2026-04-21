package com.example.app26_InfiniteScroll_Pro.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    // Llista d'elements carregats
    var items by mutableStateOf<List<String>>(emptyList())

    // Control de càrrega
    var isLoading by mutableStateOf(false)
    private var currentPage = 1

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            // Simulem una crida a l'API
            val newItems = fetchItemsFromApi(currentPage)
            items = items + newItems
            currentPage++
            isLoading = false
        }
    }

    // Simulació de crida a API
    private suspend fun fetchItemsFromApi(page: Int): List<String> {
        delay(1500) // Simulem latència de xarxa
        return (1..20).map { "Element #$it (Pàgina $page)" }
    }
}
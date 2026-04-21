package com.example.app26_InfiniteScroll_Pro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.app26_InfiniteScroll_Pro.data.paging.MyPagingSource

class Pantalla1ViewModel : ViewModel() {
    val itemsFlow = Pager(
        config = PagingConfig(
            pageSize = 20,          // Tamany de cada pàgina
            prefetchDistance = 5,   // A 5 registre del final, demanarà la següent pàgina
            maxSize = 100, // Important: Allibera memòria quan passa de 100
            enablePlaceholders = false
        ),
        pagingSourceFactory = { MyPagingSource() }
    ).flow.cachedIn(viewModelScope) // Manté el flux viu durant el cicle de vida
}
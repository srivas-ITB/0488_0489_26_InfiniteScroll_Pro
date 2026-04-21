package com.example.app26_InfiniteScroll_Pro.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.app26_InfiniteScroll_Pro.ui.viewmodel.Pantalla1ViewModel

@Composable
fun Pantalla1() {
    // Convertim el Flow a un estat que Compose entengui
    val vm : Pantalla1ViewModel = viewModel()
    val lazyPagingItems = vm.itemsFlow.collectAsLazyPagingItems()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text("Quantitat de registes carregats: ${lazyPagingItems.itemCount}")

        Spacer(modifier = Modifier.height(20.dp))
        InfiniteScrollScreen(vm, lazyPagingItems)
    }
}

@Composable
fun InfiniteScrollScreen(vm: Pantalla1ViewModel, lazyPagingItems: LazyPagingItems<String>) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(lazyPagingItems.itemCount) { index ->
            val item = lazyPagingItems[index]
            if (item != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = item, modifier = Modifier.padding(24.dp))
                }
            }
        }

        // Gestió de l'estat de càrrega al final
        when (val state = lazyPagingItems.loadState.append) {
            is LoadState.Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                    )
                }
            }

            is LoadState.Error -> {
                item { Text("Error en carregar més dades") }
            }

            else -> {}
        }
    }
}

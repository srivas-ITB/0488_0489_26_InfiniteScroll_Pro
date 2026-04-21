package com.example.app26_InfiniteScroll_Pro.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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


@Composable
fun Pantalla1() {

    val vm: PostViewModel = viewModel()

    LaunchedEffect(Unit) {
            vm.loadNextPage()
        }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))

        Text("Quantitat de registes carregats: ${vm.items.size}" )

        Spacer(modifier = Modifier.height(20.dp))
        InfiniteScrollScreen(vm)
    }


}

@Composable
fun InfiniteScrollScreen(viewModel: PostViewModel = viewModel()) {
    val listState = rememberLazyListState()

    // Detectem si l'usuari s'apropa al final de la llista
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            // Si l'últim element visible és un dels 5 últims de la llista, carreguem més
            lastVisibleItem?.index != null && lastVisibleItem.index >= listState.layoutInfo.totalItemsCount - 5
        }
    }

    // Llançarem la càrrega quan el 'derivedStateOf' ens ho indiqui
    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value && !viewModel.isLoading) {
            viewModel.loadNextPage()
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(viewModel.items) { item ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(text = item, modifier = Modifier.padding(24.dp))
            }
        }

        // Mostrem un indicador de càrrega al final de tot
        if (viewModel.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}




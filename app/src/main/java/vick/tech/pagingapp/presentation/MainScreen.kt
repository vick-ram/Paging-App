package vick.tech.pagingapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import vick.tech.pagingapp.domain.model.Quote
import vick.tech.pagingapp.domain.viewmodel.QuotesViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    quotesViewModel: QuotesViewModel,
    paddingValues: PaddingValues
) {
    val quotes = quotesViewModel.quotes.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = paddingValues
    ) {
        items(quotes.itemCount) { index ->
            val uiQuotes = quotes[index]
            uiQuotes?.let {
                QuoteItem(quote = it)
            }
        }

        quotes.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = quotes.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage ?: "Unknown error",
                            modifier = Modifier.fillMaxSize(),
                            onRetryClick = { quotes.retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = quotes.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage ?: "Unknown error",
                            onRetryClick = { quotes.retry() }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun QuoteItem(modifier: Modifier = Modifier, quote: Quote) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = quote.quote,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                modifier = modifier.align(Alignment.End),
                text = quote.author,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.error),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetryClick) {
                Text(text = "Retry")
            }
        }
    }
}

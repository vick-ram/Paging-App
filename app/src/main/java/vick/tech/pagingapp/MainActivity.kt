package vick.tech.pagingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import vick.tech.pagingapp.domain.viewmodel.QuotesViewModel
import vick.tech.pagingapp.presentation.MainScreen
import vick.tech.pagingapp.ui.theme.PagingAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val quotesViewModel by viewModels<QuotesViewModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagingAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = { Text(text = "Quotes") })
                    }
                ) { innerPadding ->
                    MainScreen(
                        quotesViewModel = quotesViewModel,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}

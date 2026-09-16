package com.example.pertemuan1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pertemuan1.ui.screen.BasicInfoScreen
import com.example.pertemuan1.ui.screen.HubungiKamiScreen
import com.example.pertemuan1.ui.theme.Pertemuan1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pertemuan1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), // 1. Tambahkan koma di sini
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "basic_info") {
                        composable("basic_info") {
                            BasicInfoScreen(
                                onNavigateToContact = { navController.navigate("form_screen") }
                            )
                        }
                        composable("form_screen") {
                            // 2. Hapus koma menggantung dan tambahkan parameter showSnackbar (bisa berupa lambda kosong jika tidak dipakai langsung dari sini)
                            HubungiKamiScreen (
                                navController = navController,
                                showSnackbar = { pesan -> /* Handle snackbar jika perlu */ }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun LayoutTentangJualan() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pertemuan1Theme {
        Greeting("Android")
    }
}
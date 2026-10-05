package com.example.pertemuan1.ui.screen

import android.adservices.adid.AdId
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pertemuan1.Greeting
import com.example.pertemuan1.R
//import com.example.pertemuan1.data.dummy.DummyData
import com.example.pertemuan1.data.model.Product
import com.example.pertemuan1.ui.theme.Pertemuan1Theme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.pertemuan1.util.JualanConstants.BASE_URL
import com.example.pertemuan1.viewmodel.ProductUiState
import com.example.pertemuan1.viewmodel.ProductViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProductScreen(productId: Int, navController: NavController?, viewModel: ProductViewModel) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }
    var product by remember { mutableStateOf<Product?>(null) }
    var quantity by rememberSaveable { mutableStateOf(1) }

    val uiState by viewModel.uiState.collectAsState()

//    var selectedCategoryId by rememberSaveable { mutableStateOf<Int?>(null) }

    when (val state = uiState) {
        is ProductUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProductUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Produk tidak ditemukan.")
            }
        }
        is ProductUiState.Success -> {
            val product = state.products.find { it.id == productId }

            if (product == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Produk tidak ditemukan.")
                }
            } else {
                StatelessDetailProduct(
                    product = product,
                    quantity = quantity,
                    onQuantityChange = { newQuantity ->
                        quantity = newQuantity
                    },
                    onBackClick = { navController?.popBackStack() },
                    onAddToCartClick = { Toast.makeText(context, "Dimasukkan: $quantity", Toast.LENGTH_SHORT).show() }
                )
            }
        }
    }

//    LaunchedEffect(productId) {
//        isLoading = true
//        delay(1000)
////        product = DummyData.products.find { it.id == productId }
//        isLoading = false
//    }

    StatelessDetailProduct(
        product = product,
//        isLoading = isLoading,
        quantity = quantity,
        onQuantityChange = { quantity = it },
        onBackClick = { navController?.popBackStack() },
        onAddToCartClick = { Toast.makeText(context, "Dimasukkan: $quantity", Toast.LENGTH_SHORT).show() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessDetailProduct(
    product: Product?,
//    isLoading: Boolean,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Produk") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(painterResource(id = R.drawable.back_icon), "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (product != null) {
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(rememberScrollState())) {
//                val imageRes = if (product.img == "dummy_product") R.drawable.dummy_product else R.drawable.dummy_product
//                Image(painterResource(id = imageRes), contentDescription = null, modifier = Modifier.fillMaxWidth().height(280.dp))
//
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text(product.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
//                    Text("Rp ${product.price}", style = MaterialTheme.typography.titleLarge)
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text("Deskripsi", fontWeight = FontWeight.Bold)
//                    Text(product.description ?: "")
//                    Text("Stok: ${product.stock}")
//                }

                val imageModel: Any = if(product.img == "dummy_product") {
                    R.drawable.dummy_product
                } else {
                    "$BASE_URL/img/${product.img}"
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = imageModel,
                        contentDescription = product.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(androidx.compose.ui.graphics.Color.White),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Jumlah Beli")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        FilledTonalIconButton(
                            onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                            enabled = quantity > 1
                        ) { Text("-") }

                        Text(quantity.toString(), modifier = Modifier.padding(horizontal = 16.dp))

                        FilledTonalIconButton(
                            onClick = { if (quantity < product.stock) onQuantityChange(quantity + 1) },
                            enabled = quantity < product.stock
                        ) { Text("+") }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onAddToCartClick,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = product.stock > 0 && quantity > 0
                ) { Text("Tambah ke Keranjang") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    Pertemuan1Theme {
//        StatelessDetailProduct(
//            product = DummyData.products.firstOrNull(), // Ambil data pertama dari DummyData
//            isLoading = false, // Set false agar bisa melihat tampilan datanya
//            quantity = 1,
//            onQuantityChange = {},
//            onBackClick = {},
//            onAddToCartClick = {}
//        )
//        DetailProductScreen(
//            productId = 1,
//            navController = null
//        )
    }
}
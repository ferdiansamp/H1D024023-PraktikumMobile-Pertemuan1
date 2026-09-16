package com.example.pertemuan1.data.dummy

import com.example.pertemuan1.data.model.Category
import com.example.pertemuan1.data.model.Product

object DummyData {
    val categories = listOf(
        Category(id = 1, name = "Makanan", description = "Aneka Makanan Lokal", product_count = 5),
        Category(id = 2, name = "Minuman", description = "Minuman Segar", product_count = 5),
        Category(id = 3, name = "Kerajinan", description = "Kerajinan Tangan", product_count = 5)
    )
    val products = listOf(
        // Makanan
        Product(1, 1, categories[0], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(2, 1, categories[0], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(3, 1, categories[0], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(4, 1, categories[0], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(5, 1, categories[0], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        
        // Minuman
        Product(6, 2, categories[1], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(7, 2, categories[1], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),Product(1, 1, categories[0], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(8, 2, categories[1], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(9, 2, categories[1], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(10, 2, categories[1], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        
        // Kerajinan
        Product(11, 2, categories[2], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(12, 2, categories[2], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(13, 2, categories[2], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(14, 2, categories[2], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(15, 2, categories[2], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
    )
}
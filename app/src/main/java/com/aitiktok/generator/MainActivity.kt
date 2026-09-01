package com.aitiktok.generator

import android.app.Activity
import android.os.Bundle
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etProduct = findViewById<EditText>(R.id.etProduct)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)
        val btnCopy = findViewById<Button>(R.id.btnCopy)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnGenerate.setOnClickListener {

            val product = etProduct.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (product.isEmpty()) {
                etProduct.error = "Masukkan nama produk"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                etDescription.error = "Masukkan deskripsi produk"
                return@setOnClickListener
            }

            val result = """
HOOK:
"Stop scroll! Kalau kamu lagi cari $product, wajib lihat ini!"

NARASI:
"Kalau kamu sedang mencari $product, produk ini menarik banget untuk dipertimbangkan.
${description}.
Cocok buat kamu yang ingin produk praktis dan mudah digunakan.
Kalau penasaran, cek produknya sekarang!"

CAPTION:
"$product yang praktis dan menarik untuk dipertimbangkan! 
${description}. 
Cek produknya sekarang sebelum kelewatan."

HASHTAG:
#TikTokAffiliate #TikTokShop #FYP #ProdukViral #RekomendasiProduk #${product.replace(" ", "")}
            """.trimIndent()

            tvResult.text = result
        }

        btnCopy.setOnClickListener {

            val text = tvResult.text.toString()

            if (text == "Hasil akan muncul di sini..." || text.isEmpty()) {
                Toast.makeText(
                    this,
                    "Generate konten terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val clipboard =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            clipboard.setPrimaryClip(
                ClipData.newPlainText("TikTok Content", text)
            )

            Toast.makeText(
                this,
                "Hasil berhasil disalin",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

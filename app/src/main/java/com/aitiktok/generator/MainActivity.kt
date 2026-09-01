package com.aitiktok.generator

import android.app.Activity
import android.os.Bundle
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val PICK_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etProduct = findViewById<EditText>(R.id.etProduct)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val etScene = findViewById<EditText>(R.id.etScene)

        val btnReference = findViewById<Button>(R.id.btnReference)
        val btnPrompt = findViewById<Button>(R.id.btnPrompt)
        val btnImage = findViewById<Button>(R.id.btnImage)
        val btnVideo = findViewById<Button>(R.id.btnVideo)
        val btnCopy = findViewById<Button>(R.id.btnCopy)

        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Upload gambar referensi
        btnReference.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, PICK_IMAGE)
        }

        // Generate prompt
        btnPrompt.setOnClickListener {

            val product = etProduct.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val scene = etScene.text.toString().trim()

            if (product.isEmpty()) {
                etProduct.error = "Masukkan nama produk"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                etDescription.error = "Masukkan deskripsi produk"
                return@setOnClickListener
            }

            val prompt = """
BUAT GAMBAR UGC TIKTOK AFFILIATE

Produk: $product

Deskripsi:
$description

Scene:
$scene

FORMAT:
Vertikal 9:16, realistis, natural, kualitas tinggi.

ATURAN PENTING:
Pertahankan produk dan referensi asli.
Jangan mengubah bentuk, warna, desain, logo, detail,
atau karakteristik produk.
Jangan menambahkan teks atau tulisan ke gambar.
Jangan menambahkan watermark.
Gunakan pencahayaan natural.
Tampilan seperti konten UGC TikTok asli.

VIDEO:
Jika digunakan untuk video, hanya kamera yang bergerak
secara halus. Produk, orang, dan semua elemen utama
tetap sama. Jangan mengubah referensi.
            """.trimIndent()

            tvResult.text = prompt
        }

        // Generate gambar
        btnImage.setOnClickListener {
            Toast.makeText(
                this,
                "Fitur Generate Gambar akan disambungkan ke AI pada tahap berikutnya.",
                Toast.LENGTH_LONG
            ).show()
        }

        // Generate video
        btnVideo.setOnClickListener {
            Toast.makeText(
                this,
                "Fitur Generate Video akan disambungkan ke AI pada tahap berikutnya.",
                Toast.LENGTH_LONG
            ).show()
        }

        // Copy hasil
        btnCopy.setOnClickListener {

            val text = tvResult.text.toString()

            if (text == "Hasil akan muncul di sini..." || text.isEmpty()) {
                Toast.makeText(
                    this,
                    "Generate prompt terlebih dahulu.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val clipboard =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            clipboard.setPrimaryClip(
                ClipData.newPlainText("AI TikTok Generator", text)
            )

            Toast.makeText(
                this,
                "Prompt berhasil disalin.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Toast.makeText(
                this,
                "Gambar referensi berhasil dipilih.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}                return@setOnClickListener
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

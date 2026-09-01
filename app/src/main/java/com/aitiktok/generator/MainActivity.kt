package com.aitiktok.generator

import android.app.Activity
import android.os.Bundle
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var promptInput: EditText
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(32, 40, 32, 32)
        root.setBackgroundColor(Color.rgb(15, 15, 20))

        val title = TextView(this)
        title.text = "AI TikTok Generator"
        title.textSize = 24f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER
        title.setPadding(0, 0, 0, 30)

        promptInput = EditText(this)
        promptInput.hint = "Masukkan prompt gambar / video..."
        promptInput.setTextColor(Color.WHITE)
        promptInput.setHintTextColor(Color.GRAY)
        promptInput.setPadding(20, 20, 20, 20)

        val generateButton = Button(this)
        generateButton.text = "GENERATE PROMPT"

        val copyButton = Button(this)
        copyButton.text = "COPY PROMPT"

        val imageButton = Button(this)
        imageButton.text = "PILIH GAMBAR REFERENSI"

        val videoButton = Button(this)
        videoButton.text = "BUKA VIDEO GENERATOR"

        resultText = TextView(this)
        resultText.text = "Hasil prompt akan muncul di sini."
        resultText.textSize = 16f
        resultText.setTextColor(Color.WHITE)
        resultText.setPadding(10, 30, 10, 30)

        root.addView(title)
        root.addView(promptInput)
        root.addView(generateButton)
        root.addView(copyButton)
        root.addView(imageButton)
        root.addView(videoButton)
        root.addView(resultText)

        setContentView(root)

        generateButton.setOnClickListener {
            generatePrompt()
        }

        copyButton.setOnClickListener {
            copyPrompt()
        }

        imageButton.setOnClickListener {
            pickImage()
        }

        videoButton.setOnClickListener {
            openVideoGenerator()
        }
    }

    private fun generatePrompt() {

        val input = promptInput.text.toString().trim()

        if (input.isEmpty()) {
            Toast.makeText(
                this,
                "Masukkan prompt terlebih dahulu",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val prompt = """
            UGC TikTok affiliate, vertical 9:16.
            Pertahankan produk dan referensi asli.
            Jangan mengubah bentuk, warna, detail, logo,
            tekstur atau desain produk.
            Tidak ada teks tambahan.
            Tampilan realistis dan natural.
            Kamera bergerak perlahan dan stabil.
            
            $input
        """.trimIndent()

        resultText.text = prompt
    }

    private fun copyPrompt() {

        val text = resultText.text.toString()

        val clipboard =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clip = ClipData.newPlainText(
            "AI Prompt",
            text
        )

        clipboard.setPrimaryClip(clip)

        Toast.makeText(
            this,
            "Prompt berhasil disalin",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun pickImage() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        startActivityForResult(intent, 100)
    }

    private fun openVideoGenerator() {

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com")
        )

        startActivity(intent)
    }
}Produk: $product

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

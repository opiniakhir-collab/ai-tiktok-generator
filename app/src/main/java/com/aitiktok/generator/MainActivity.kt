package com.aitiktok.generator

import android.app.Activity
import android.os.Bundle
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private lateinit var productInput: EditText
    private lateinit var presetSpinner: Spinner
    private lateinit var resolutionSpinner: Spinner
    private lateinit var result: TextView

    private val presets = arrayOf(
        "UGC Story Selling",
        "Premium Product",
        "TikTok Affiliate",
        "Kamera Bergerak Saja",
        "Bengkel / Workshop",
        "Product Showcase",
        "UGC Human",
        "Viral Hook"
    )

    private val resolutions = arrayOf(
        "HD 720p",
        "Full HD 1080p",
        "4K UHD 2160p"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(24, 30, 24, 24)
        root.setBackgroundColor(Color.rgb(15, 15, 20))

        val title = TextView(this)
        title.text = "AI TIKTOK GENERATOR"
        title.textSize = 24f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER
        title.setPadding(0, 0, 0, 20)

        productInput = EditText(this)
        productInput.hint = "Nama / deskripsi produk"
        productInput.setTextColor(Color.WHITE)
        productInput.setHintTextColor(Color.GRAY)

        val presetLabel = TextView(this)
        presetLabel.text = "PRESET"
        presetLabel.setTextColor(Color.WHITE)
        presetLabel.setPadding(0, 15, 0, 5)

        presetSpinner = Spinner(this)

        val presetAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            presets
        )

        presetSpinner.adapter = presetAdapter

        val resolutionLabel = TextView(this)
        resolutionLabel.text = "RESOLUSI OUTPUT"
        resolutionLabel.setTextColor(Color.WHITE)
        resolutionLabel.setPadding(0, 15, 0, 5)

        resolutionSpinner = Spinner(this)

        val resolutionAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            resolutions
        )

        resolutionSpinner.adapter = resolutionAdapter

        val imageButton = Button(this)
        imageButton.text = "PILIH GAMBAR REFERENSI"

        val generateButton = Button(this)
        generateButton.text = "GENERATE VIDEO"

        val copyButton = Button(this)
        copyButton.text = "COPY HASIL"

        result = TextView(this)
        result.text = "Hasil akan muncul di sini."
        result.textSize = 15f
        result.setTextColor(Color.WHITE)
        result.setPadding(5, 20, 5, 20)

        root.addView(title)
        root.addView(productInput)
        root.addView(presetLabel)
        root.addView(presetSpinner)
        root.addView(resolutionLabel)
        root.addView(resolutionSpinner)
        root.addView(imageButton)
        root.addView(generateButton)
        root.addView(copyButton)
        root.addView(result)

        setContentView(root)

        imageButton.setOnClickListener {
            pilihGambar()
        }

        generateButton.setOnClickListener {
            generatePrompt()
        }

        copyButton.setOnClickListener {
            copyHasil()
        }
    }

    private fun pilihGambar() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)

        intent.type = "image/*"

        intent.addCategory(Intent.CATEGORY_OPENABLE)

        startActivityForResult(intent, 100)
    }

    private fun generatePrompt() {

        val produk = productInput.text.toString().trim()

        if (produk.isEmpty()) {
            Toast.makeText(
                this,
                "Masukkan produk terlebih dahulu",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val preset = presetSpinner.selectedItem.toString()
        val resolution = resolutionSpinner.selectedItem.toString()

        val outputResolution = when (resolution) {
            "HD 720p" -> "1280x720"
            "Full HD 1080p" -> "1920x1080"
            "4K UHD 2160p" -> "2160x3840"
            else -> "1920x1080"
        }

        val motion = when (preset) {

            "UGC Story Selling" ->
                "slow push-in natural menuju produk dan close-up pada akhir video."

            "Premium Product" ->
                "cinematic slow push-in dengan subtle orbit mengelilingi produk."

            "TikTok Affiliate" ->
                "handheld smartphone movement yang natural seperti konten TikTok."

            "Kamera Bergerak Saja" ->
                "HANYA KAMERA YANG BERGERAK. Produk dan seluruh detail referensi tetap."

            "Bengkel / Workshop" ->
                "slow push-in menuju produk dengan aktivitas workshop tetap natural."

            "Product Showcase" ->
                "slow cinematic slide kemudian close-up pada detail produk."

            "UGC Human" ->
                "kamera handheld mengikuti tangan yang memegang produk secara natural."

            "Viral Hook" ->
                "quick push-in halus dari close-up menuju fokus utama produk."

            else ->
                "kamera bergerak perlahan dan stabil."
        }

        val prompt = """

RUNWAY IMAGE-TO-VIDEO

PRESET:
$preset

OUTPUT:
Resolution target: $outputResolution
Aspect Ratio: 9:16
Duration: 10 seconds
Quality: Maximum
Style: realistic, cinematic, premium

PRODUCT:
$produk

REFERENCE LOCK:
Gunakan gambar referensi sebagai sumber utama.
Pertahankan bentuk produk.
Pertahankan warna produk.
Pertahankan logo.
Pertahankan tulisan.
Pertahankan tekstur.
Pertahankan material.
Pertahankan desain.

Jangan melakukan redesign.
Jangan melakukan morphing.
Jangan mengubah identitas produk.

CAMERA:
$motion

VISUAL:
Natural lighting.
Sharp details.
Realistic material texture.
Smooth motion.
Premium commercial quality.
Clean composition.

NEGATIVE:
No product deformation.
No logo changes.
No color changes.
No extra products.
No duplicated objects.
No distorted hands.
No text overlay.
No watermark.

VOICE OVER INDONESIA:

"Kalau kamu lagi cari produk yang praktis
dan worth it, ini wajib banget kamu lihat.
Link-nya sudah aku sematkan."

CTA:

"Link sudah aku sematkan."

CAPTION:

Produk yang wajib kamu lihat kalau lagi cari
sesuatu yang praktis dan worth it 🔥

HASHTAG:

#TikTokShop
#TikTokAffiliate
#AffiliateIndonesia
#RacunTikTok
#ProdukViral
#FYP
#FYPIndonesia

4K PROCESSING:

Jika provider menghasilkan resolusi lebih rendah,
gunakan AI upscale sebagai tahap akhir menuju
2160x3840 vertical 4K UHD.

""".trimIndent()

        result.text = prompt
    }

    private fun copyHasil() {

        val text = result.text.toString()

        if (text == "Hasil akan muncul di sini.") {

            Toast.makeText(
                this,
                "Generate hasil terlebih dahulu",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val clipboard =
            getSystemService(Context.CLIPBOARD_SERVICE)
                    as ClipboardManager

        val clip = ClipData.newPlainText(
            "AI TikTok Prompt",
            text
        )

        clipboard.setPrimaryClip(clip)

        Toast.makeText(
            this,
            "Berhasil disalin",
            Toast.LENGTH_SHORT
        ).show()
    }
}

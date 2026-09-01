package com.aitiktok.generator

import android.app.Activity
import android.os.Bundle
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var productInput: EditText
    private lateinit var result: TextView

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
        productInput.hint = "Masukkan nama / deskripsi produk"
        productInput.setTextColor(Color.WHITE)
        productInput.setHintTextColor(Color.GRAY)

        val imageButton = Button(this)
        imageButton.text = "PILIH REFERENSI"

        val generateButton = Button(this)
        generateButton.text = "GENERATE VIDEO + CONTENT"

        val copyButton = Button(this)
        copyButton.text = "COPY HASIL"

        result = TextView(this)
        result.text = "Hasil akan muncul di sini."
        result.textSize = 15f
        result.setTextColor(Color.WHITE)
        result.setPadding(5, 20, 5, 20)

        root.addView(title)
        root.addView(productInput)
        root.addView(imageButton)
        root.addView(generateButton)
        root.addView(copyButton)
        root.addView(result)

        setContentView(root)

        imageButton.setOnClickListener {
            pilihGambar()
        }

        generateButton.setOnClickListener {
            generateContent()
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

    private fun generateContent() {

        val produk = productInput.text.toString().trim()

        if (produk.isEmpty()) {
            Toast.makeText(
                this,
                "Masukkan produk terlebih dahulu",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val hasil = """
            
            🎬 VIDEO PROMPT
            
            Produk: $produk
            
            Format vertical 9:16, durasi 10 detik,
            gaya UGC TikTok realistis dan premium.
            
            PERTAHANKAN REFERENSI PRODUK 100%.
            Jangan mengubah bentuk, warna, ukuran,
            logo, tulisan, tekstur, detail dan desain produk.
            
            Hanya kamera yang bergerak secara natural.
            Gerakan kamera slow push-in,
            sedikit handheld cinematic,
            fokus tetap pada produk.
            
            Background realistis dan elegan.
            Lighting natural, detail tajam,
            realistic skin and material texture.
            Tidak ada objek tambahan yang mengganggu.
            Tidak ada teks tambahan di layar.
            
            
            🎞️ STORYBOARD
            
            0-3 detik:
            Kamera mulai dari medium shot,
            perlahan mendekati produk.
            
            3-7 detik:
            Kamera melakukan slow push-in
            sambil mempertahankan produk sebagai fokus utama.
            
            7-10 detik:
            Kamera berhenti pada close-up premium
            yang memperlihatkan detail produk.
            
            
            🗣️ VOICE OVER
            
            "Kalau kamu lagi cari produk yang praktis
            dan bikin aktivitas jadi lebih mudah,
            ini wajib banget kamu lihat.
            Link-nya sudah aku sematkan."
            
            
            📣 CTA
            
            "Link sudah aku sematkan."
            
            
            📝 CAPTION
            
            Lagi cari produk yang praktis dan worth it?
            Coba lihat yang satu ini.
            Detail produknya bisa langsung kamu cek
            lewat link yang sudah aku sematkan 🔥
            
            
            #️⃣ HASHTAG
            
            #TikTokShop #TikTokAffiliate #AffiliateIndonesia
            #RekomendasiProduk #ProdukViral #RacunTikTok
            #FYP #FYPIndonesia #BelanjaOnline
            
        """.trimIndent()

        result.text = hasil
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
            "AI TikTok Content",
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

package com.deep.vlcapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            VideoPlayerActivity.start(
                this,
                uri = "https://itv1.broadcastcdn.net/KUK-ITV-107/tracks-v2a1/mono.m3u8"
            )
        }
    }
}
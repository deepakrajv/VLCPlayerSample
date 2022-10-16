package com.deep.vlcapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button0 = findViewById<Button>(R.id.button_0)
        button0.setOnClickListener {
            VideoPlayerActivity.start(
                this,
                uri = "https://itv1.broadcastcdn.net/KUK-ITV-107/tracks-v2a1/mono.m3u8"
            )
        }
        val button1 = findViewById<Button>(R.id.button_1)
        button1.setOnClickListener {
            VideoPlayerActivity.start(
                this,
                uri = "https://dfn.kalalay.com/DW_ENGLISH/index.m3u8"
            )
        }
        val button2 = findViewById<Button>(R.id.button_2)
        button2.setOnClickListener {
            VideoPlayerActivity.start(
                this,
                uri = "https://dfn.kalalay.com/B4U_MOVIES/index.m3u8"
            )
        }
    }
}
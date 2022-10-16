package com.deep.vlcapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer

class VideoPlayerActivity : AppCompatActivity() {
    companion object {

        private const val USE_TEXTURE_VIEW = false
        private const val ENABLE_SUBTITLES = true
        private const val KEY_FILE_PATH = "KEY_FILE_PATH"

        @JvmStatic
        fun start(activity: Activity, uri: String? = null) {
            val intent = Intent(activity, VideoPlayerActivity::class.java)
            intent.putExtra(KEY_FILE_PATH, uri)
            activity.startActivity(intent)
        }
    }

    private var mLibVLC: LibVLC? = null

    private var mMediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        initPlayerView()
    }

    private fun initPlayerView() {

        val uri = Uri.parse(intent.getStringExtra(KEY_FILE_PATH))
        mLibVLC = LibVLC(this, ArrayList<String>().apply {
            add("--no-drop-late-frames")
            add("--no-skip-frames")
            add("--rtsp-tcp")
            add("-vvv")
        })
        mMediaPlayer = MediaPlayer(mLibVLC)
        mMediaPlayer?.attachViews(
            findViewById(R.id.view_vlc_layout),
            null,
            ENABLE_SUBTITLES,
            USE_TEXTURE_VIEW
        )
        try {
            Media(mLibVLC, uri).apply {
                setHWDecoderEnabled(true, false)
                // addOption(":network-caching=150");
                // addOption(":clock-jitter=0");
                // addOption(":clock-synchro=0");
                mMediaPlayer?.media = this
            }.release()
            mMediaPlayer?.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        mMediaPlayer?.stop()
        mMediaPlayer?.detachViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer?.release()
        mLibVLC?.release()
    }
}
package com.servinuevafm.tutorialradio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Switch;
import android.widget.Toast;


import com.chibde.visualizer.BarVisualizer;
import com.chibde.visualizer.LineVisualizer;
import com.chibde.visualizer.SquareBarVisualizer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;


public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ExoPlayer exoPlayer;
    private Uri streamUri;

    public MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://radiofelima.radio12345.com/");

        exoPlayer = new ExoPlayer.Builder(this).build();
        Switch switchStreaming = findViewById(R.id.switchStreaming);


//playing media
        //mediaPlayer.start();



        switchStreaming.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startStreaming();
            } else {
                stopStreaming();
            }
        });

    }

    public void squareBarVisualization(View view) {
        SquareBarVisualizer squareBarVisualizer = findViewById(R.id.visualizerSquareBar);
        squareBarVisualizer.setVisibility(View.VISIBLE);
        // set custom color to the line.
        squareBarVisualizer.setColor(ContextCompat.getColor(this, R.color.white));
        // define a custom number of bars you want in the visualizer between (10 - 256).
        squareBarVisualizer.setDensity(200);
        // Set Spacing
        squareBarVisualizer.setGap(1);
        // Set the media player to the visualizer.
        //squareBarVisualizer.setPlayer(mediaPlayer.getAudioSessionId());
        squareBarVisualizer.setPlayer(exoPlayer.getAudioSessionId());
    }







    private void startStreaming() {
        long currentTimeMillis = System.currentTimeMillis();
        Toast.makeText(this,"Activado",Toast.LENGTH_LONG).show();
        MediaItem mediaItem = MediaItem.fromUri("https://us1freenew.listen2myradio.com/live.mp3?typeportmount=s1_14690_stream_"+currentTimeMillis);
        //MediaItem mediaItem = MediaItem.fromUri("http://162.210.192.98:14690");
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();
    }

    private void stopStreaming() {
        exoPlayer.stop();
        exoPlayer.clearMediaItems();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();

    }
}


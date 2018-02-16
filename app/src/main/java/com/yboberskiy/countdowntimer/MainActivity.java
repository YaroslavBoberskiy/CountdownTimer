package com.yboberskiy.countdowntimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CountDownTimer timer;
    TextView timeTextView;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBarView = findViewById(R.id.timerSeekBar);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        ImageView eggImageView = (ImageView) findViewById(R.id.eggImageView);
        Button startButtonView = (Button) findViewById(R.id.startButton);
        mp = MediaPlayer.create(this, R.raw.fanfara);


        seekBarView.setMax(40);
        seekBarView.setProgress(0);
        timeTextView.setText(Integer.toString(seekBarView.getProgress()));
        startButtonView.setOnClickListener(this);

        seekBarView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                timeTextView.setText(Integer.toString(progress));
                initializeTimer (progress*1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void initializeTimer (long millisUntilFinished) {
        timer = new CountDownTimer(millisUntilFinished, 1000) {

            public void onTick(long millisUntilFinished) {
                timeTextView.setText(Long.toString(millisUntilFinished/1000));
            }

            public void onFinish() {
                timeTextView.setText("Done!");
                mp.start();
            }
        };
    }

    @Override
    public void onClick(View view) {
        timer.start();
    }
}

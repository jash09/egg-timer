package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Boolean counteractive= false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resettimer(){

        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counteractive=false;




    }

    public void buttonclicked(View view){
        if (counteractive){
          resettimer();

        }else{

        counteractive=true;
        timerSeekBar.setEnabled(false);
        goButton.setText("STOP!");


         countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                upadtetimerfunc((int) (millisUntilFinished)/1000);
            }

            @Override
            public void onFinish() {

                MediaPlayer mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mediaPlayer.start();
                resettimer();

            }
        }.start();
    }}

    public void upadtetimerfunc(int secondsleft){

        int minutes = secondsleft / 60;
        int seconds= secondsleft  - (minutes * 60);

        String secondsString = Integer.toString(seconds);
        if(seconds <=9){

            secondsString = "0" + secondsString;

        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        goButton=findViewById(R.id.goButton);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                upadtetimerfunc(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

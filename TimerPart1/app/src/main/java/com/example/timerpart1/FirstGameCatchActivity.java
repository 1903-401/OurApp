package com.example.timerpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class FirstGameCatchActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;

    // Size
    private int frameheight;
    private int boxSize;
    private int screenWidth;
    private int screenHeight;

    // position
    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;



    // score
    private int score = 0;


    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlayer sound;

    // Status check
    private boolean action_flg = false;
    private boolean start_flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_game_catch);

        sound = new SoundPlayer(this);


        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);


        // get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        // move to out of screen.
        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);

        scoreLabel.setText("Score : 0");
    }

    public void changePos() {

        hitCheck();

        // Orange
        orangeX -= 12;
        if (orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameheight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        // Black
        blackX -= 16;
        if (blackX < 0) {
            blackX = screenWidth + 10;
            blackY = (int) Math.floor(Math.random() * (frameheight - black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);


        // pink
        pinkX -= 20;
        if (pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (int) Math.floor(Math.random() * (frameheight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);


        // Move Box
        if (action_flg == true) {
            // Touching
            boxY -= 20;
        } else {
            // Realising
            boxY += 20;
        }
        // check box position
        if (boxY < 0) boxY = 0;

        if (boxY > frameheight - boxSize) boxY = frameheight - boxSize;

        box.setY(boxY);

        scoreLabel.setText("Score : " + score);

    }

    public void hitCheck() {
        // if the center of the ball is in the box, it counts as a hit.

        //orange
        int orangeCenterX = orangeX + orange.getWidth() / 2;
        int orangeCenterY = orangeY + orange.getHeight() / 2;

        // 0 <= orangeCenterX <= boxWidth
        // boxY <= orangeCenterY <= boxY + boxHeight

        if (0 <= orangeCenterX && orangeCenterX <= boxSize && boxY <= orangeCenterY &&
                orangeCenterY <= boxY + boxSize) {


            score += 10;
            orangeX = -10;
            sound.playHitSound();
        }


        // pink
        int pinkCenterX = pinkX + pink.getWidth() / 2;
        int pinkCenterY = pinkY + pink.getHeight() / 2;

        if(0<=pinkCenterX &&pinkCenterX <=boxSize &&boxY <=pinkCenterY &&
                pinkCenterY <=boxY +boxSize){

            score += 30;
            pinkX = -10;
            sound.playHitSound();
        }

        // Black
        int blackCenterX = blackX + black.getWidth() / 2;
        int blackCenterY = blackY + black.getHeight() / 2;

        if(0 <= blackCenterX && blackCenterX <= boxSize && boxY <=  blackCenterY &&
                blackCenterY <= boxY + boxSize){

            // Stop timer
            timer.cancel();
            timer = null;

            sound.playOverSound();

            // Show result.
            Intent intent = new Intent(getApplicationContext(), CatchResultActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }

    }

    public boolean onTouchEvent(MotionEvent me) {

        if (start_flg == false){

            start_flg = true;


            FrameLayout frame = (FrameLayout)  findViewById(R.id.frame);
            frameheight = frame.getHeight();

            boxY = (int) box.getY();

            boxSize = box.getHeight();


            startLabel.setVisibility(View.INVISIBLE);
            scoreLabel.setVisibility(View.VISIBLE);


            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN){
                action_flg = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP){
                action_flg = false;
            }

        }
        return true;
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }



}

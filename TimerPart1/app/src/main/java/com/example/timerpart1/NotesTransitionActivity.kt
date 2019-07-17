package com.example.timerpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils

import com.example.timerpart1.NotesMainActivity1;
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notes_transition.*

class NotesTransitionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_transition)

        ic_logo5.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_in))
        Handler().postDelayed({
            ic_logo5.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_out))
            Handler().postDelayed({
                ic_logo5.visibility = View.GONE
                startActivity(Intent(this, NotesMainActivity1::class.java))
                finish()
            },500)
        },1500)
    }
    }

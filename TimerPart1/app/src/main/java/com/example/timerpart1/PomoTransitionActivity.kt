package com.example.timerpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pomo_transition.*
import kotlinx.android.synthetic.main.activity_timer_transition.*

class PomoTransitionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pomo_transition)

        ic_logo3.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_in))
        Handler().postDelayed({
            ic_logo3.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_out))
            Handler().postDelayed({
                ic_logo3.visibility = View.GONE
                startActivity(Intent(this,PomodoroActivity::class.java))
                finish()
            },500)
        },1500)
    }
    }

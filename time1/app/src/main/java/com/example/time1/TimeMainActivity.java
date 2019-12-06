package com.example.time1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class TimeMainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText text_biaoti,text_beizhu;
    private Button bt_riqi, bt_chongfu, bt_tupian,bt_zhiding;
    private TextView time;
    private int i = 0;
    private Timer timer = null;
    private TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        text_biaoti = findViewById(R.id.editText_biaoti);
        text_beizhu = findViewById(R.id.editText_beizhu);
        bt_riqi = findViewById(R.id.button);
        bt_chongfu = findViewById(R.id.button2);
        bt_tupian = findViewById(R.id.button3);
        bt_zhiding = findViewById(R.id.button4);

        time = findViewById(R.id.time);
        bt_riqi.setOnClickListener(this);
        bt_chongfu.setOnClickListener(this);
        bt_tupian.setOnClickListener(this);
        bt_zhiding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }



    public void stopTime(){
        timer.cancel();
    }
}
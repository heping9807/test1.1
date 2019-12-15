package com.example.time1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class TimeMainActivity extends AppCompatActivity  {
    private EditText text_biaoti,text_beizhu,editText2;
    private Button bt_riqi, bt_chongfu, bt_tupian,bt_zhiding;
    private TextView time;
    private int i = 0;
    private Timer timer = null;
    private TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_main);
        initView();
    }

    private void initView() {
        text_biaoti = findViewById(R.id.editText_biaoti);
        text_beizhu = findViewById(R.id.editText_beizhu);

        bt_riqi = findViewById(R.id.button);
        bt_chongfu = (Button) findViewById(R.id.button2);
        bt_tupian = (Button) findViewById(R.id.button3);
        bt_zhiding = (Button) findViewById(R.id.button4);

        //time = findViewById(R.id.time);
        /*
        bt_riqi.setOnClickListener(this);
        bt_chongfu.setOnClickListener(this);
        bt_tupian.setOnClickListener(this);
        bt_zhiding.setOnClickListener(this);
        */
        bt_riqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TimeMainActivity.this, riliMainActivity.class);
                startActivity(intent);
            }
        });



    }


}
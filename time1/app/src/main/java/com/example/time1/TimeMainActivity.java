package com.example.time1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.time1.ui.shike.HomeFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TimeMainActivity extends AppCompatActivity  {
    public static final int changliang = 901;
    public static final int REQUEST_CODE_get_time = 902;
    private EditText text_biaoti,text_beizhu;
    private Button bt_riqi, bt_chongfu, bt_tupian,bt_zhiding,bt_queren;
    private TextView time=null;
    private int i = 0;
    Date Now_time,endTime;
    long diff,days=10,hours,minutes;
    public String time_change;
    int nian,yue,ri;        //年月日
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_get_time:
                if(resultCode==RESULT_OK){
                    //取得启动该Activity的Intent对象
                    nian = data.getIntExtra("nian",0);
                    yue = data.getIntExtra("yue",0);
                    ri = data.getIntExtra("ri",0);
                    String All=nian+"年"+yue+"月"+ri+"日";
                    time.setText(All);
                    Toast.makeText(this, "获取时间成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_main);
        initView();

        bt_queren.setOnClickListener(new View.OnClickListener() {   //传参
            @Override
            public void onClick(View v) {       //传值给HomeFragment
                /*取得输入框中的内容*/
                String Biaoti=text_biaoti.getText().toString();
                String Beizhu=text_beizhu.getText().toString();
                //传值
                Intent intent = new Intent(TimeMainActivity.this, HomeFragment.class);
                intent.putExtra("nian", nian);
                intent.putExtra("yue", yue);
                intent.putExtra("ri", ri);
                intent.putExtra("biaoti", Biaoti);
                intent.putExtra("beizhu", Beizhu);
                try {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    Now_time = new Date(System.currentTimeMillis());
                    endTime = df.parse("nian"+"-"+"yue"+"-"+"ri"+" "+"00-00-00");
                    diff = Now_time.getTime() - endTime.getTime();
                    days = diff / (1000 * 60 * 60 * 24);
                    hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                    minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
                    diff = Now_time.getTime() - endTime.getTime();
                    days = diff / (1000 * 60 * 60 * 24);
                    time_change=days+"天"+hours+"小时"+minutes+"分钟";
                }catch (Exception e) { }
                intent.putExtra("Time", time_change);
                setResult(RESULT_OK,intent);
                TimeMainActivity.this.finish();

            }
        });



    }

    private void initView() {
        text_biaoti = findViewById(R.id.editText_biaoti);
        text_beizhu = findViewById(R.id.editText_beizhu);
        time=findViewById(R.id.textView6);

        bt_riqi = findViewById(R.id.button);
        bt_chongfu = (Button) findViewById(R.id.button2);
        bt_tupian = (Button) findViewById(R.id.button3);
        bt_zhiding = (Button) findViewById(R.id.button4);
        bt_queren=findViewById(R.id.button5);

        bt_riqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TimeMainActivity.this, riliMainActivity.class);
                startActivityForResult(intent, REQUEST_CODE_get_time);
            }
        });
    }

    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.leftmenu_new_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
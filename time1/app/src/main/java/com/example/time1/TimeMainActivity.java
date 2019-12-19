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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.text.format.Time;


public class TimeMainActivity extends AppCompatActivity  {

    public static final int REQUEST_CODE_get_time = 902;
    private EditText text_biaoti,text_beizhu;
    private Button bt_riqi, bt_chongfu, bt_tupian,bt_zhiding,bt_queren;
    private TextView time=null;
    String start_time,end_time;
    int nian,yue,ri;        //年月日
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_get_time:
                if(resultCode==RESULT_OK){
                    //取得启动该Activity的Intent对象
                    nian = data.getIntExtra("nian",0);
                    yue = data.getIntExtra("yue",0)+1;
                    ri = data.getIntExtra("ri",0);
                    String All=nian+"年"+yue+"月"+ri+"日";
                    time.setText(All);
                    end_time=nian+"-"+yue+"-"+ri+" "+"00:00:00";
                    start_time=getNowTime();
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
                String time_change=getTimeDifference(start_time, end_time);

                //传值
                Intent intent = new Intent(TimeMainActivity.this, HomeFragment.class);
                intent.putExtra("nian", nian);
                intent.putExtra("yue", yue);
                intent.putExtra("ri", ri);
                intent.putExtra("biaoti", Biaoti);
                intent.putExtra("beizhu", Beizhu);
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
    /**
     * 获取当前时间
     * @return
     */
    public String getNowTime() {
        String timeString = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        timeString = simpleDateFormat.format(date);
        // System.out.println("-------timeString----------" + timeString);
        return timeString;
    }
    /**
     * 计算时间差
     * @param starTime
     *            开始时间
     * @param endTime
     *            结束时间
     *            返回类型 ==1----天，时，分。 ==2----时
     * @return 返回时间差
     */
    public String getTimeDifference(String starTime, String endTime) {
        String timeString = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = df.parse(starTime);
            Date parse1 = df.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();

            long day = diff / (1000 * 60 * 60 * 24);
            long hour = (diff-day*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long min = (diff-day*(1000 * 60 * 60 * 24)-hour*(1000* 60 * 60))/(1000* 60);

            timeString = day+"天"+hour + "小时" + min + "分";
            // System.out.println(day + "天" + hour + "小时" + min + "分" + s +
            // "秒");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;
    }
}
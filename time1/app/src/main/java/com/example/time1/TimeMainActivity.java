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
    private int i = 0;
    private Calendar calendar=null;
    private SimpleDateFormat sf = null;
    String start_time,end_time;

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
                time_change=getTimeDifference(start_time, end_time);

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
    public int calculate(int year, int month) {
        boolean yearleap = judge(year);
        int day;
        if (yearleap && month == 2) {
            day = 29;
        } else if (!yearleap && month == 2) {
            day = 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = 30;
        } else {
            day = 31;
        }
        return day;
    }
    public boolean judge(int year) {
        boolean yearleap = (year % 400 == 0) || (year % 4 == 0)
                && (year % 100 != 0);// 采用布尔数据计算判断是否能整除
        return yearleap;
    }
    /**
     * 十一下加零
     * @param str
     * @return
     */
    public String thanTen(int str) {

        String string = null;

        if (str < 10) {
            string = "0" + str;
        } else {
            string = "" + str;
        }
        return string;
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
    /**
     * 计算相差的小时
     * @param starTime
     * @param endTime
     * @return
     */
    public String getTimeDifferenceHour(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();
            String string = Long.toString(diff);

            float parseFloat = Float.parseFloat(string);

            float hour1 = parseFloat / (60 * 60 * 1000);

            timeString = Float.toString(hour1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;
    }

    /**
     * 获取时间中的某一个时间点
     * @return
     */

    /**
     * Sring变int
     * @param str
     * @return
     */
    public int strToInt(String str) {
        int value = 0;
        value = Integer.parseInt(str);
        return value;
    }

    /**
     * 与当前时间比较早晚
     *
     * @param time
     *            需要比较的时间
     * @return 输入的时间比现在时间晚则返回true
     */
    public boolean compareNowTime(String time) {
        boolean isDayu = false;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date parse = dateFormat.parse(time);
            Date parse1 = dateFormat.parse(getNowTime());

            long diff = parse1.getTime() - parse.getTime();
            if (diff <= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isDayu;
    }

    /**
     * 把时间戳变yyyy-MM-dd HH:mm格式时间
     *
     * @param time
     * @return
     */
    public String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(d);
    }

    /**
     * 返回时间戳
     *
     * @param time
     * @return
     */
    public long dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                Locale.CHINA);
        Date date;
        long l = 0;
        try {
            date = sdr.parse(time);
            l = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 比较两个时间
     *
     * @param starTime
     *            开始时间
     * @param endString
     *            结束时间
     * @return 结束时间大于开始时间返回true，否则反之֮
     */
    public boolean compareTwoTime(String starTime, String endString) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endString);

            long diff = parse1.getTime() - parse.getTime();
            if (diff >= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isDayu;

    }

    public boolean compareTwoTime2(String starTime, String endString) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endString);

            long diff = parse1.getTime() - parse.getTime();
            if (diff >= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isDayu;

    }
    /**
     * 获取年
     * @param time
     * @return
     */
    public String getTimeYear(String time) {

        String substring = time.substring(0, time.lastIndexOf(" "));
        return substring;

    }
    /**
     * 换算小时，0.5小时-->0小时30分
     * @param hour
     * @return
     */
    private String convertTime(String hour) {

        String substring = hour.substring(0, hour.lastIndexOf("."));
        String substring2 = hour.substring(hour.lastIndexOf(".") + 1,
                hour.length());
        substring2 = "0." + substring2;
        float f2 = Float.parseFloat(substring2);
        f2 = f2 * 60;
        String string = Float.toString(f2);
        String min = string.substring(0, string.lastIndexOf("."));
        return substring + "小时" + min + "分";

    }

}
package com.example.time1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailMainActivity extends AppCompatActivity {
    public static final int RESULT_CODE_DELETE = 907;
    public EditText text_biaoti,text_shijian,text_beizhu;
    public TextView textView_show;
    public Button button_delete,button_change;
    private int editPosition;
    CountDownTimer Timer;
    String Nowtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);

        button_change=findViewById(R.id.button_change);
        button_delete=findViewById(R.id.button_delete);
        text_biaoti=findViewById(R.id.editText_biaoti);
        text_shijian=findViewById(R.id.editText_time);
        text_beizhu=findViewById(R.id.editText_beizhu);
        textView_show=findViewById(R.id.textView_show);

        editPosition=getIntent().getIntExtra("edit_position",0);
        String goodName= getIntent().getStringExtra("title");
        String goodTime= getIntent().getStringExtra("time");
        String goodBeizhu= getIntent().getStringExtra("beizhu");
        Nowtime= getIntent().getStringExtra("Nowtime");

        if(goodName!=null) {
            text_biaoti.setText(goodName);
            text_shijian.setText(goodTime+"");
            text_beizhu.setText(goodBeizhu);
        }
        try {
            initData();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final ListView listViewTime = findViewById(R.id.listview);
        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("edit_position", editPosition);
                intent.putExtra("biaoti", text_biaoti.getText().toString().trim());
                intent.putExtra("shijian",text_shijian.getText().toString().trim());
                intent.putExtra("beizhu",text_beizhu.getText().toString().trim());
                setResult(RESULT_OK, intent);
                DetailMainActivity.this.finish();
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(DetailMainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("询问")
                        .setMessage("你确定要删除这条吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.putExtra("edit_position", editPosition);
                                setResult(RESULT_CODE_DELETE, intent);
                                DetailMainActivity.this.finish();
                                Toast.makeText(DetailMainActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create().show();
            }
        });
    }
    public void initData() throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date =sdf.parse(Nowtime);
        Calendar calendar = Calendar.getInstance();
        assert date != null;
        calendar.setTime(date);
        long timeSave=calendar.getTimeInMillis()-System.currentTimeMillis();

        if(Timer!=null)
            Timer.cancel();
        Timer = new CountDownTimer(timeSave, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                    long day = millisUntilFinished / (1000 * 24 * 60 * 60); //单位天

                    long hour = (millisUntilFinished - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60);
                    //单位时
                    long minute = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60);
                    //单位分
                    long second = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;
                    //单位秒
                    textView_show.setText(day+"天"+hour + "小时" + minute + "分钟" + second + "秒");
            }
            @Override
            public void onFinish() {
            }
        };
        Timer.start();
    }
}

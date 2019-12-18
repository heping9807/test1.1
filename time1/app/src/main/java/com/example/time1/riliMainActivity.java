package com.example.time1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class riliMainActivity extends AppCompatActivity {
    private DatePicker datePicker=null;
    private Calendar calendar=null;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rili_main);


        //获取日历
        calendar=Calendar.getInstance();
        mYear=calendar.get(Calendar.YEAR);
        mMonth=calendar.get(Calendar.MONTH);
        mDay=calendar.get(Calendar.DAY_OF_MONTH);

        datePicker = (DatePicker) findViewById(R.id.calendarView) ;

        datePicker.init( mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {

            public void onDateChanged(DatePicker view, int year, int month0fYear, int day0fMonth) {
                mYear=year;
                mMonth=month0fYear;
                mDay=day0fMonth;
                Toast.makeText(riliMainActivity.this,
                        year + "年" + (month0fYear + 1) + "月" + day0fMonth + "日",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button=findViewById(R.id.button6);      //确认按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传值
                Intent intent = new Intent(riliMainActivity.this, TimeMainActivity.class);
                intent.putExtra("nian", mYear);
                intent.putExtra("yue", mMonth);
                intent.putExtra("ri", mDay);
                setResult(RESULT_OK,intent);
                riliMainActivity.this.finish();
            }
        });
    }
}

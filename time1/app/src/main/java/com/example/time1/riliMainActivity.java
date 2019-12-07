package com.example.time1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class riliMainActivity extends AppCompatActivity {
    private DatePicker datePicker=null;
    private Calendar calendar=null;
    private int mYear;
    private int mMonth;
    private int mDay;

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
                Toast.makeText(riliMainActivity.this,
                        year + "年" + (month0fYear + 1) + "月" + day0fMonth + "日",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}

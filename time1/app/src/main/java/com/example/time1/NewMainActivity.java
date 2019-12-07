package com.example.time1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewMainActivity extends AppCompatActivity {

    public Button New_button;
    private List<Time> listTimes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        New_button = this.findViewById(R.id.New_button);
        New_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewMainActivity.this, TimeMainActivity.class);
                startActivity(intent);
            }
        });

        init();
        ListView listViewTime=this.findViewById(R.id.listview);
        TimeAdapter adapter=new TimeAdapter(NewMainActivity.this ,android.R.layout.activity_list_item ,listTimes);
        listViewTime.setAdapter(adapter);
    }

    private void init() {
        listTimes.add(new Time("1月纪念日","100 days",R.drawable.time_1));
        listTimes.add(new Time("2月纪念日","100 days",R.drawable.time_2));
    }

    public List<Time> getListTimes(){
        return listTimes;
    }
    class TimeAdapter extends ArrayAdapter<Time> {
        private int resourceId;

        public TimeAdapter(Context context, int resource, List<Time> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Time time = getItem(position);//获取当前项的实例
            View view = LayoutInflater.from(this.getContext()).inflate(this.resourceId, null);

            ImageView imageView=(ImageView)view.findViewById(R.id.image_view_time_cover);
            TextView textView_title=(TextView)view.findViewById(R.id.text_view_time_title);
            TextView textView_shijian=(TextView)view.findViewById(R.id.text_view_time_shijian);
            return view;
        }
    }
}

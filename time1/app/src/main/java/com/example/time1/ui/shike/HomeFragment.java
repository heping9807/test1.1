package com.example.time1.ui.shike;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.time1.DetailMainActivity;
import com.example.time1.NewMainActivity;
import com.example.time1.R;
import com.example.time1.Time;
import com.example.time1.TimeMainActivity;
import com.example.time1.ui.shezhi.SendViewModel;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends Fragment {
    public static final int REQUEST_CODE_GET_ALL = 903;
    public static final int REQUEST_CODE_GET_COLOR = 904;
    private SendViewModel sendViewModel;
    public Button New_button;
    public List<Time> listTimes=new ArrayList<>();
    TimeAdapter timeadapter;
    int nian,yue,ri;        //年月日

    Date Now_time,endTime;
    long diff,days=10,hours,minutes;

    @Override       //传值
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_GET_ALL:
                if(resultCode==RESULT_OK){
                    //取得启动该Activity的Intent对象

                    nian = data.getIntExtra("nian",0);
                    yue = data.getIntExtra("yue",0);
                    ri = data.getIntExtra("ri",0);
                    String biaoti=data.getStringExtra("biaoti");
                    String beizhu=data.getStringExtra("beizhu");
                    String time=data.getStringExtra("Time");
/*
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
                        String time=days+"天"+hours+"小时"+minutes+"分钟";
                        listTimes.add(new Time(biaoti,time,R.drawable.time_1));
                        timeadapter.notifyDataSetChanged();
                        Toast.makeText(this.getActivity(), "新建ListView成功", Toast.LENGTH_SHORT).show();
                    }catch (Exception e) { }
 */
                    listTimes.add(new Time(biaoti,time,R.drawable.time_1));
                    timeadapter.notifyDataSetChanged();
                    Toast.makeText(this.getActivity(), "新建ListView成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_GET_COLOR:
                if(resultCode==RESULT_OK){


                }
                break;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_2_shike,container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        New_button = root.findViewById(R.id.New_button);
        initTime();

        timeadapter = new TimeAdapter(HomeFragment.this.getActivity(), R.layout.list_view_item_time, listTimes);
        ListView listViewTime = root.findViewById(R.id.listview);
        listViewTime.setAdapter(timeadapter);
        /*
        //取得启动该Fragment的对象
        Bundle arguments = getArguments();
        String biaoti = arguments.getString("biaoti");
        String beizhu = arguments.getString("beizhu");
        listTimes.add(new Time(biaoti,beizhu,R.drawable.time_1));
        timeadapter.notifyDataSetChanged();
        */



        /////////listview的点击响应函数，由position判断点击的是哪个子项
        listViewTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Time list_time = listTimes.get(position);
                Toast.makeText(HomeFragment.this.getActivity(), list_time.getTitle(),
                        Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent();
                intent1.setClass(HomeFragment.this.getActivity(), DetailMainActivity.class);
                startActivity(intent1);
            }
        });

        New_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomeFragment.this.getActivity(), TimeMainActivity.class);
                startActivityForResult(intent, REQUEST_CODE_GET_ALL);
            }
        });
        return root;
    }

    private void initTime() {
        listTimes.add(new Time("1月纪念日",100+"days",R.drawable.time_1));
        listTimes.add(new Time("2月纪念日",100+"days",R.drawable.time_2));
    }

    public List<Time> getListTimes(){
        return listTimes;
    }
    class TimeAdapter extends ArrayAdapter<Time> {
        private int resourceId;

        TimeAdapter(Context context, int resource, List<Time> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Time time = getItem(position);//获取当前项的实例
            LayoutInflater mInflater=LayoutInflater.from(this.getContext());
            View view = mInflater.inflate(this.resourceId, null);

            ImageView imageView=(ImageView)view.findViewById(R.id.image_view_time_cover);//图片
            imageView.setImageResource(time.getCoverResourceId());
            TextView textView_title=(TextView)view.findViewById(R.id.text_view_time_title);//标题
            textView_title.setText(time.getTitle());
            TextView textView_shijian=(TextView)view.findViewById(R.id.text_view_time_shijian);//时间
            textView_shijian.setText(time.getTime());

            return view;
        }
    }

}


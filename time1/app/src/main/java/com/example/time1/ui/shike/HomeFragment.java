package com.example.time1.ui.shike;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.time1.NewMainActivity;
import com.example.time1.R;
import com.example.time1.Time;
import com.example.time1.TimeMainActivity;
import com.example.time1.ui.shezhi.SendViewModel;


public class HomeFragment extends Fragment {
    private SendViewModel sendViewModel;
    public Button New_button;
    private List<Time> listTimes=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_2_shike,container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        New_button = root.findViewById(R.id.New_button);
        New_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomeFragment.this.getActivity(), TimeMainActivity.class);
                startActivity(intent);
            }
        });

        initTime();

        TimeAdapter adapter = new TimeAdapter(HomeFragment.this.getActivity(), R.layout.list_view_item_time, listTimes);
        ListView listViewTime = root.findViewById(R.id.listview);
        listViewTime.setAdapter(adapter);

        /////////listview的点击响应函数，由position判断点击的是哪个子项
        listViewTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Time list_time = listTimes.get(position);
                Toast.makeText(HomeFragment.this.getActivity(), list_time.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    private void initTime() {
        listTimes.add(new Time("1月纪念日","100 days",R.drawable.time_1));
        listTimes.add(new Time("2月纪念日","100 days",R.drawable.time_2));
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
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_2_shike);

        New_button = this.findViewById(R.id.New_button);
        New_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomeFragment.this, TimeMainActivity.class);
                startActivity(intent);
            }
        });

        initTime();

        TimeAdapter adapter=new TimeAdapter(HomeFragment.this ,R.layout.list_view_item_time ,listTimes);
        ListView listViewTime=this.findViewById(R.id.listview);
        listViewTime.setAdapter(adapter);
        /////////listview的点击响应函数，由position判断点击的是哪个子项
        listViewTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Time list_time = listTimes.get(position);
                Toast.makeText(HomeFragment.this, list_time.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTime() {
        listTimes.add(new Time("1月纪念日","100 days",R.drawable.time_1));
        listTimes.add(new Time("2月纪念日","100 days",R.drawable.time_2));
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
    }*/

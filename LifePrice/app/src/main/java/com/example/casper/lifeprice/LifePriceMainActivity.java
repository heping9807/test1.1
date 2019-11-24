package com.example.casper.lifeprice;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LifePriceMainActivity extends AppCompatActivity {


    private ArrayList<Good> theGoods;
    private ListView listViewSuper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_price_main);

        InitData();

        listViewSuper= (ListView) this.findViewById(R.id.list_view_goods);
        GoodsArrayAdapter theAdaper=new GoodsArrayAdapter(this,R.layout.list_item_good,theGoods);
        listViewSuper.setAdapter(theAdaper);
    }

    private void InitData() {
        theGoods=new ArrayList<Good>();
        theGoods.add(new Good("fish",1, R.drawable.a1));
        theGoods.add(new Good("fish 2",2,R.drawable.a2));
        theGoods.add(new Good("fish 3",3,R.drawable.a3));
    }

    protected class GoodsArrayAdapter extends ArrayAdapter<Good>
    {
        private  int resourceId;
        public GoodsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Good> objects) {
            super(context, resource, objects);
            resourceId=resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater mInflater= LayoutInflater.from(this.getContext());
            View item = mInflater.inflate(this.resourceId,null);

            ImageView img = (ImageView)item.findViewById(R.id.image_view_good);
            TextView name = (TextView)item.findViewById(R.id.text_view_name);
            TextView price = (TextView)item.findViewById(R.id.text_view_price);

            Good good_item= this.getItem(position);
            img.setImageResource(good_item.getPictureId());
            name.setText("商品："+good_item.getName());
            price.setText("价格："+good_item.getPrice()+"元");

            return item;
        }
    }
}

package com.jnu.taobao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Market extends AppCompatActivity {
      ListView listViewthings;
    private  List<Book> listBooks=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        init();
        listViewthings=this.findViewById(R.id.List_View_Things);

        ArrayAdapter adapter = new BookAdapter(
                Market.this, R.layout.list_view_item_book, listBooks);
        listViewthings.setAdapter(adapter);
    }


    private void init() {
        listBooks.add(new Book("个百度啊回复hi",R.drawable.a4));
        listBooks.add(new Book("俄罗斯蓝猫", R.drawable.a3));
        listBooks.add(new Book("美国短毛猫", R.drawable.a1));
    }
    class BookAdapter extends ArrayAdapter<Book> {

        private int resourceId;

        public BookAdapter(Context context, int resource, List<Book> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Book book = getItem(position);//获取当前项的实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            ((ImageView) view.findViewById(R.id.ImageView_Name)).setImageResource(book.getCoverId());
            ((TextView) view.findViewById(R.id.TextView_Name)).setText(book.getNanme());
            return view;
        }
    }
}

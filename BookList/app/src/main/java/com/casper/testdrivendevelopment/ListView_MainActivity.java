package com.casper.testdrivendevelopment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class ListView_MainActivity extends AppCompatActivity {

    ListView listViewBooks;
    private List<Book> listBooks=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_main);

        listViewBooks=this.findViewById(R.id.list_view_books);

        ArrayAdapter adapter=new BookAdapter(
                ListView_MainActivity.this,R.layout.list_view_item_book,listBooks);

        listViewBooks.setAdapter(adapter);
    }
    private void init(){
        listBooks.add(new Book("软件项目",R.drawable.book_1));
        listBooks.add(new Book("创新工程",R.drawable.book_2));
        listBooks.add(new Book("信息安全",R.drawable.book_no_name));
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
            ((ImageView) view.findViewById(R.id.image_view_book_cover)).setImageResource(book.getCoverResourceId());
            ((TextView) view.findViewById(R.id.name)).setText(book.getTitle());
            return view;
        }
    }



}

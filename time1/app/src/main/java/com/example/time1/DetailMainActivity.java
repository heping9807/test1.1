package com.example.time1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class DetailMainActivity extends AppCompatActivity {
    public EditText text_biaoti,text_shijian;
    public Button button_delete,button_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);

        button_change=findViewById(R.id.button_change);
        button_delete=findViewById(R.id.button_delete);
        final ListView listViewTime = findViewById(R.id.listview);
        private class ButtonListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button_change:
                        break;
                    case R.id.button_delete:
                        
                        break;
                }
            }
        }
    }
}

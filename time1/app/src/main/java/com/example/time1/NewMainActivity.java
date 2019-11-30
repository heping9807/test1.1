package com.example.time1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewMainActivity extends AppCompatActivity {

    public Button New_button;

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





    }
}

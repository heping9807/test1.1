package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BottonMainActivity extends AppCompatActivity {

    private Button button_En,button_Cn;
    private TextView textViewHelloworld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botton_main);

        button_Cn=this.findViewById(R.id.button_Cn);
        button_En=this.findViewById(R.id.button_En);

        textViewHelloworld=this.findViewById(R.id.textViewHelloWorld);

        button_Cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewHelloworld.setText(button_Cn.getText());
            }
        });

        button_En.setOnClickListener(new Button_CnOnclick());


    }

    private class Button_CnOnclick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            textViewHelloworld.setText(((Button)view).getText());
        }
    }
}

package com.example.time1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class DetailMainActivity extends AppCompatActivity {
    public static final int RESULT_CODE_DELETE = 907;
    public EditText text_biaoti,text_shijian;
    public Button button_delete,button_change;
    private int editPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);

        button_change=findViewById(R.id.button_change);
        button_delete=findViewById(R.id.button_delete);
        text_biaoti=findViewById(R.id.editText_biaoti);
        text_shijian=findViewById(R.id.editText_time);

        editPosition=getIntent().getIntExtra("edit_position",0);
        String goodName= getIntent().getStringExtra("title");
        String goodTime= getIntent().getStringExtra("time");
        if(goodName!=null) {
            text_biaoti.setText(goodName);
            text_shijian.setText(goodTime+"");
        }

        final ListView listViewTime = findViewById(R.id.listview);
        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("edit_position", editPosition);
                intent.putExtra("biaoti", text_biaoti.getText().toString().trim());
                intent.putExtra("shijian",text_shijian.getText().toString());
                setResult(RESULT_OK, intent);
                DetailMainActivity.this.finish();
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(DetailMainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("询问")
                        .setMessage("你确定要删除这条吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.putExtra("edit_position", editPosition);
                                setResult(RESULT_CODE_DELETE, intent);
                                DetailMainActivity.this.finish();
                                Toast.makeText(DetailMainActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create().show();
            }
        });
    }
}

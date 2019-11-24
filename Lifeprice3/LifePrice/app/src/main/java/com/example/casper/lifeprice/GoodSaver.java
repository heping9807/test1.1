package com.example.casper.lifeprice;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GoodSaver {
    public GoodSaver(Context context) {
        this.context = context;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    Context context;
    ArrayList<Good> goods=new ArrayList<Good>();

    public void save()
    {
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput("Serializable.txt",Context.MODE_PRIVATE));
            outputStream.writeObject(goods);
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Good> load() {

        try {
            ObjectInputStream inputStream = new ObjectInputStream(
                    context.openFileInput("Serializable.txt"));
            goods = (ArrayList<Good>) inputStream.readObject();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            }
        return goods;
    }
}

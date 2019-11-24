package com.example.casper.lifeprice.data;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.support.test.InstrumentationRegistry;

import com.example.casper.lifeprice.R;
import com.example.casper.lifeprice.data.model.Good;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jsjzx on 2019/11/11.
 */
public class FileDataSourceTest {
    private FileDataSource keeper;
    @Before
    public void setUp() throws Exception {
        keeper=new FileDataSource(InstrumentationRegistry.getTargetContext());
        keeper.load();
    }

    @After
    public void tearDown() throws Exception {
        keeper.save();
    }

    @Test
    public void getGoods() throws Exception {
        FileDataSource fileDataSource=new FileDataSource(InstrumentationRegistry.getTargetContext());
        Assert.assertEquals(0,fileDataSource.getGoods().size());
    }

    @Test
    public void saveThenLoad() throws Exception {
        FileDataSource fileDataSource=new FileDataSource(InstrumentationRegistry.getTargetContext());
        Assert.assertEquals(0,fileDataSource.getGoods().size());
        fileDataSource.getGoods().add(new Good("测试1",1.23, R.drawable.a4));
        fileDataSource.getGoods().add(new Good("测试2",2.22, R.drawable.a3));
        fileDataSource.save();
        FileDataSource fileLoader=new FileDataSource(InstrumentationRegistry.getTargetContext());
        fileLoader.load();

        Assert.assertNotEquals(fileDataSource.getGoods(),fileLoader.getGoods());
        Assert.assertEquals(fileDataSource.getGoods().size(),fileLoader.getGoods().size());

        for(int i=0;i<fileDataSource.getGoods().size();i++)
        {
            Good goodThis=fileDataSource.getGoods().get(i);
            Good goodThat=fileLoader.getGoods().get(i);

            Assert.assertNotEquals(goodThat,goodThis);
            Assert.assertEquals(goodThat.getName(),goodThis.getName());
            Assert.assertEquals(goodThat.getPrice(),goodThis.getPrice(),1e-4);
            Assert.assertEquals(goodThat.getPictureId(),goodThis.getPictureId());
        }

    }

    @Test
    public void saveEmptyThenLoad() throws Exception {
        FileDataSource fileDataSource=new FileDataSource(InstrumentationRegistry.getTargetContext());
        Assert.assertEquals(0,fileDataSource.getGoods().size());
        fileDataSource.save();
        FileDataSource fileLoader=new FileDataSource(InstrumentationRegistry.getTargetContext());
        fileLoader.load();

        Assert.assertEquals(fileDataSource.getGoods().size(),fileLoader.getGoods().size());
        Assert.assertEquals(0,fileDataSource.getGoods().size());
    }

}
package com.example.casper.lifeprice.data;

import com.example.casper.lifeprice.data.model.Shop;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jsjzx on 2019/11/11.
 */
public class ShopLoaderTest {
    @Test
    public void getShops() throws Exception {
        ShopLoader shopLoader = new ShopLoader();
        Assert.assertEquals(0, shopLoader.getShops().size());
    }

    @Test
    public void download() throws Exception {
        ShopLoader shopLoader = new ShopLoader();
        String content=shopLoader.download("http://file.nidama.net/class/mobile_develop/data/bookstore.json");
        Assert.assertTrue(content.length()>300);
        Assert.assertTrue(content.contains("\"memo\": \"珠海二城广场\"") && content.contains("\"longitude\": \"113.526421\","));
    }

    @Test
    public void parseJson() throws Exception {
        ShopLoader shopLoader = new ShopLoader();
        String content=shopLoader.download("http://file.nidama.net/class/mobile_develop/data/bookstore.json");
        Assert.assertEquals(0,shopLoader.getShops().size());
        shopLoader.parseJson(content);
        Assert.assertEquals(3,shopLoader.getShops().size());
        Shop shop=shopLoader.getShops().get(2);
        Assert.assertEquals("明珠商业广场",shop.getName());
        Assert.assertEquals("珠海二城广场",shop.getMemo());
        Assert.assertEquals(22.251953,shop.getLatitude(),1e-6);
        Assert.assertEquals(113.526421,shop.getLongitude(),1e-6);
    }

}
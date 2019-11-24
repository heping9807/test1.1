package com.example.casper.lifeprice.data;

import com.example.casper.lifeprice.data.model.Shop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShopLoaderTest {
    ShopLoader shopLoader;

    @Before
    public void setUp() throws Exception {
        shopLoader=new ShopLoader();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getShops() {
        assertNotNull(shopLoader.getShops());
        assertEquals(0,shopLoader);
    }

    @Test
    public void download() {
        String content =shopLoader.download("http://file.nidama.net/class/mobile_develop/data/bookstore.json");
        assertTrue(content.length()>=300);
        assertTrue(content.contains("\"longitude\":\113.526421\","));

    }

    @Test
    public void parseJson() {
        shopLoader.parseJson(content);
        assertEquals(2,shopLoader.getShops().size());
        Shop shop=shopLoader.getShops().get(1);
        assertEquals("明珠商业广场",shop.getName());
        assertEquals("珠海二城广场",shop.getMemo());
        assertEquals(22.251523,shop.getLatitude(),1e-6);
        assertEquals("113.526421",shop.getLongitude(),1e-6);

    }
}
package com.example.casper.lifeprice;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.casper.lifeprice.data.ShopLoader;
import com.example.casper.lifeprice.data.model.Shop;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) view.findViewById(R.id.bmapView);

        BaiduMap baiduMap = mMapView.getMap();

        //修改百度地图的初始位置
        LatLng centerPoint = new LatLng(22.2559, 113.541112);
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(centerPoint).zoom(17).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        baiduMap.setMapStatus(mMapStatusUpdate);

        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.a1);
        MarkerOptions markerOption = new MarkerOptions().icon(bitmap).position(centerPoint);
        Marker marker = (Marker) baiduMap.addOverlay(markerOption);
        //添加文字
        OverlayOptions textOption = new TextOptions().bgColor(0xAAFFFF00).fontSize(50)
                .fontColor(0xFFFF00FF).text("我的学校").rotate(0).position(centerPoint);
        baiduMap.addOverlay(textOption);

        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getContext(), "Marker"+marker.getTitle()+"被点击了！", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        downloadAndDrawShops(baiduMap);

        return view;
    }

    private void downloadAndDrawShops(final BaiduMap baiduMap) {
        final ShopLoader shopLoader=new ShopLoader();
        final Handler handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                for(int i=0;i<shopLoader.getShops().size();++i)
                {
                    Shop shop=shopLoader.getShops().get(i);
                    LatLng point = new LatLng(shop.getLatitude(), shop.getLongitude());

                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.a1);
                    MarkerOptions markerOption = new MarkerOptions().icon(bitmap).position(point);
                    Marker marker = (Marker) baiduMap.addOverlay(markerOption);
                    marker.setTitle(shop.getName());
                    //添加文字
                    OverlayOptions textOption = new TextOptions().bgColor(0xAAFFFF00).fontSize(50)
                            .fontColor(0xFFFF00FF).text(shop.getName()).rotate(0).position(point);
                    baiduMap.addOverlay(textOption);
                }

            }
        };
        Runnable run=new Runnable() {
            @Override
            public void run() {
                String data=shopLoader.download("http://file.nidama.net/class/mobile_develop/data/bookstore.json");
                shopLoader.parseJson(data);
                handler.sendEmptyMessage(1);
            }
        };
        new Thread(run).start();
    }

    private MapView mMapView = null;

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        if (mMapView != null) mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        if (mMapView != null) mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        if (mMapView != null) mMapView.onDestroy();
    }

}

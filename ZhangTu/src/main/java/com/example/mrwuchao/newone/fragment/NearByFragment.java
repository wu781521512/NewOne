package com.example.mrwuchao.newone.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.SupportMapFragment;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.mrwuchao.newone.R;

/**
 * 地图页面的fragment
 */
public class NearByFragment extends SupportMapFragment implements AMapLocationListener {
    MapView mapView ;
    AMap aMap;
    private AMapLocationClient locationClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby,null);
        mapView = (MapView) view.findViewById(R.id.fragment_nearby_map);
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();
        startLocation();
        return view;
    }

    private void startLocation() {
        locationClient = new AMapLocationClient(getActivity());
        //初始化定位参数
        AMapLocationClientOption clientOption = new AMapLocationClientOption();
        //设置定位监听
        locationClient.setLocationListener(this);
        clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位时间间隔
        clientOption.setOnceLocation(true);
        clientOption.setNeedAddress(true);
        locationClient.setLocationOption(clientOption);
        //开始定位
        locationClient.startLocation();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
        locationClient.unRegisterLocationListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        mapView.onSaveInstanceState(bundle);
    }

    //定位发生变化回调该方法
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        double weidu = aMapLocation.getLatitude();//获取纬度
        double jingdu = aMapLocation.getLongitude();//获取经度
        Log.i("location","纬度"+weidu + "   经度"+jingdu);
        //获得城市编码
        String cityCode = aMapLocation.getCityCode();
        LatLng latLng = new LatLng(weidu,jingdu);
        //给定位到的地方设置标记点
        MarkerOptions marker = new MarkerOptions().
                            position(latLng).
                            icon(BitmapDescriptorFactory.
                                    fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.location_marker_2)));
        aMap.addMarker(marker);
        //改变地图视角，放大定位到的地方
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                latLng,//新的中心点坐标
                14, //新的缩放级别
                30, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        ));
        aMap.animateCamera(cameraUpdate,300,null);
    }
}

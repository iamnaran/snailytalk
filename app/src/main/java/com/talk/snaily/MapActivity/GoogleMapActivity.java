package com.talk.snaily.MapActivity;

import android.graphics.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.talk.snaily.R;

/**
 * Created by Administrator on 16/09/2016.
 */
public class GoogleMapActivity extends AppCompatActivity {


    static final LatLng ST_XAVIER =   new LatLng(27.693105,85.321326);
    static final LatLng MY_PLACE =   new LatLng(27.690103,85.312378);
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_google_map);


        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        Marker st_xavier = map.addMarker(new MarkerOptions().position(ST_XAVIER).title("St Xaviers College").snippet("College of BSCCSIT"));



        Marker my_place = map.addMarker(new MarkerOptions().position(MY_PLACE).title("My Place").snippet("Where you live ?")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_face_black_18dp)));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ST_XAVIER,15));


        map.animateCamera(CameraUpdateFactory.zoomTo(14),2000,null);

    }
}

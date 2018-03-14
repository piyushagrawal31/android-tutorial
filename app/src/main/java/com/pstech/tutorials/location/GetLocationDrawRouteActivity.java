package com.pstech.tutorials.location;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pstech.tutorials.R;
import com.pstech.tutorials.models.LineType;
import com.pstech.tutorials.models.Station;
import com.pstech.tutorials.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pagrawal on 14-03-2018.
 */

public class GetLocationDrawRouteActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;

    @BindView(R.id.mapview) MapView mMapView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private GoogleMap mGoogleMap;
    private String TAG = "DrawLocation";
    private LocationListener mLocationListener;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_drawer_layout);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentLocation();
            }
        });
        initLocationListener();

        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        initMap();
        AppUtils.drawRoute(createDummyRoute(), mGoogleMap);
    }

    private List<Station> createDummyRoute() {
        List<Station> stations = new ArrayList<>();
        Station.Builder builder = new Station.Builder();
        builder.setId(1).setLineType(LineType.RED.toString()).setLattitude(17.4986732)
        .setLongitude(78.3866581);
        stations.add(builder.build("JNTU"));

        builder.setId(2).setLineType(LineType.RED.toString()).setLattitude(17.4937607)
        .setLongitude(78.3997035);
        stations.add(builder.build("KPHB"));

        builder.setId(3).setLineType(LineType.RED.toString()).setLattitude(17.4852508)
                .setLongitude(78.4093863);
        stations.add(builder.build("Kukatpally"));

        return stations;
    }

    private void initLocationListener() {
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    Log.d(TAG, String.format("%f, %f", location.getLatitude(), location.getLongitude()));
                    drawMarker(location);
                    mLocationManager.removeUpdates(mLocationListener);
                } else {
                    Log.d(TAG,"Location is null");
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        getCurrentLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        mLocationManager.removeUpdates(mLocationListener);
    }

    private void initMap() {
        int googlePlayStatus = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (googlePlayStatus != ConnectionResult.SUCCESS) {
            GooglePlayServicesUtil.getErrorDialog(googlePlayStatus, this, -1).show();
            finish();
        } else {
            if (mGoogleMap != null) {
                if (!checkLocationPermission())
                    return;
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
                mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
            }
        }
    }

    private void getCurrentLocation() {
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled))
            Snackbar.make(mMapView, R.string.error_location_provider, Snackbar.LENGTH_INDEFINITE).show();
        else {
            if (isNetworkEnabled) {
                if (!checkLocationPermission())
                    return;
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (isGPSEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        if (location != null) {
            Log.d(TAG, String.format("getCurrentLocation(%f, %f)", location.getLatitude(),
                    location.getLongitude()));
            drawMarker(location);
        }
    }

    private void drawMarker(Location location) {
        if (mGoogleMap != null) {
            mGoogleMap.clear();
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
            mGoogleMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title("Current Position"));

            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));

        }

    }

    private boolean checkLocationPermission() {

        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return false;

        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }
}

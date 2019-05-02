package com.example.TravelCounsellingSystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.config.GoogleDirectionConfiguration;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import multiview.Interest;
import multiview.InterestAdapter;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionCallback, NavigationView.OnNavigationItemSelectedListener, GoogleMap.OnMarkerClickListener, LocationListener {
    private GoogleMap googleMap;
    private String serverKey = "AIzaSyDv2646zqbHsnpEx2t5T95A7hoXKQczG5w";       // enter the mapAPI key


    private ArrayList<LatLng> cities = new ArrayList<>();   // list of cities

    private double theMostWestBound = 0;
    private double theMostNorthBound = 0;
    private double theMostEastBound = 0;                   // useful in setting camera
    private double theMostSouthBound = 0;

    public DBManager dbHelper;
    public DBManager dbHelpertwo;
    public SQLiteDatabase database;
    private Log LogUtils;
    public SQLiteDatabase databasetwo;
    public Context context2;
    ImageView imageview;
    String[] path;
    private List<Interest> interestList = new ArrayList<Interest>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_up);


        Intent intent=getIntent();
        path=intent.getStringArrayExtra("city");
        DBManager dbHelper = new DBManager(this);           // get city name from db
        dbHelper.openDatabase();
        SQLiteDatabase databasetwo = dbHelper.getDatabase();
        for(int i=0; i<  path.length; i++){
            Cursor cursor = databasetwo.rawQuery("select location from travelcity where cityname ="+"'"+path[i]+"'", null);
            while (cursor.moveToNext()){
                String location = (cursor.getString(cursor.getColumnIndex("location")));
                String[] temp = location.split(",");
                LatLng cityTemp = new LatLng(Double.valueOf(temp[0]),Double.valueOf(temp[1]));
                cities.add(cityTemp);            // add to List<LatLng>
            }
        }





        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        initInterest(path[0]);
        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setPadding(0,0,0,0);
        SparseBooleanArray clciked = new SparseBooleanArray(interestList.size());
        InterestAdapter adapter = new InterestAdapter(interestList,clciked );
        recyclerView.setAdapter(adapter);

        FloatingActionButton feedback = findViewById(R.id.feedback);                      // get attractions name from db
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feed=new Intent("android.intent.action.FEEDBACK");
                feed.putExtra("activityname","main");
                startActivity(feed);

            }
        });

        FloatingActionButton backtoMain = findViewById(R.id.backmain);
        backtoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent("android.intent.action.MAINPAGE");
                startActivity(back);
            }
        });
        final Activity dd=this;
        FloatingActionButton save = (FloatingActionButton) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBitmap(activityShot(dd));
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if(cities.size()==0){
            System.out.println("No city in the list!");
        }
        else if(cities.size()==1){
            System.out.println(cities.get(0));
            googleMap.addMarker(new MarkerOptions().position(cities.get(0)).title(path[0]));       // if only one city in the list, just pin a single point without drawing route
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cities.get(0), 10.5f));
        }
        else{
            for(int i = 0; i< cities.size(); i++){
                googleMap.addMarker(new MarkerOptions().position(cities.get(i)).title(path[i]));     // draw multiple points
            }
            requestDirection();
        }

        this.googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                initInterest(marker.getTitle());

                RecyclerView recyclerView = findViewById(R.id.recycler_view1);
                recyclerView.clearOnChildAttachStateChangeListeners();
                StaggeredGridLayoutManager layoutManager = new
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setPadding(0,0,0,0);                       // when click a redmarker, return the city name
                SparseBooleanArray clciked = new SparseBooleanArray(interestList.size());
                InterestAdapter adapter = new InterestAdapter(interestList,clciked );
                recyclerView.setAdapter(adapter);
                return true;
            }
        });

    }

    public void requestDirection() {
        GoogleDirectionConfiguration.getInstance().setLogEnabled(true);

        for(int i=0; i<cities.size()-1; i++){
            GoogleDirection.withServerKey(serverKey)
                    .from(cities.get(i))                               // using loop to draw all the route
                    .to(cities.get(i+1))
                    .transportMode(TransportMode.DRIVING)         // just considering driving as transportmode at current version.
                    .execute(this);
        }
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            System.out.println("Getting direction succeeded.");
            Route route = direction.getRouteList().get(0);
            Leg leg = route.getLegList().get(0);
            // addPolyline as route

            ArrayList<LatLng> directionPositionList = leg.getDirectionPoint();
            this.googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));

            if(theMostWestBound == 0){
                theMostWestBound = route.getBound().getSouthwestCoordination().getCoordination().longitude;
            }
            if(theMostNorthBound == 0){
                theMostNorthBound = route.getBound().getNortheastCoordination().getCoordination().latitude;
            }
            if(theMostEastBound == 0){
                theMostEastBound = route.getBound().getNortheastCoordination().getCoordination().longitude;
            }
            if(theMostSouthBound == 0){
                theMostSouthBound = route.getBound().getSouthwestCoordination().getCoordination().latitude;
            }
            setCameraWithCoordinationBounds(route);           // set proper camera parameters
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        System.out.println("Failed to get direction!");
    }

    private void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = route.getBound().getSouthwestCoordination().getCoordination();
        LatLng northeast = route.getBound().getNortheastCoordination().getCoordination();

        if(southwest.latitude < theMostSouthBound){
            theMostSouthBound = southwest.latitude;
        }
        if(southwest.longitude < theMostWestBound){
            theMostWestBound = southwest.longitude;
        }
        if(northeast.latitude > theMostNorthBound){
            theMostNorthBound = northeast.latitude;
        }
        if(northeast.longitude > theMostEastBound){
            theMostEastBound = northeast.longitude;
        }
        LatLng theMostSouthWest = new LatLng(theMostSouthBound, theMostWestBound);
        LatLng theMostNorthEast = new LatLng(theMostNorthBound, theMostEastBound);
        LatLngBounds bounds = new LatLngBounds(theMostSouthWest, theMostNorthEast);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));     // set zoom level
    }


    private void initInterest(String cityName) {
        interestList.clear();
        DBManager2 dbHelper = new DBManager2(this);
        dbHelper.openDatabase();
        database = dbHelper.getDatabase();
        Cursor cursor = database.rawQuery("select figure from "+cityName, null);
        Cursor cursor1= database.rawQuery("select attractions from "+cityName,null);
        ImageView ivv=new ImageView(this);
        ArrayList<Bitmap> Bitmaps = getDrawable(cursor);
        ArrayList<String> cityNames=getCityName(cursor1);                  // add attraction to the list
        //ivv.setImageDrawable(drawables.get(0));
        //ivv.setImageResource(R.drawable.a1);
        //setContentView(ivv);
        System.out.println("hoh"+cityNames.size());

        for(int i=0;i<cityNames.size();i++){
            Interest a1=new Interest(cityNames.get(i),Bitmaps.get(i), cityName);
            interestList.add(a1);
        }

    }

    private ArrayList<Bitmap> getDrawable(Cursor cursor){
        ArrayList<Bitmap> Bitmaps = new ArrayList<Bitmap>();
        if(cursor != null && cursor.getCount() != 0) {
            while(cursor.moveToNext()) {
                byte[] img = cursor.getBlob(cursor.getColumnIndex("figure"));
                Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length, null);
//                BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
//                Drawable drawable = bitmapDrawable;
                Bitmaps.add(bitmap);
            }
        }
        return Bitmaps;
    }

    private ArrayList<String> getCityName(Cursor cursor){
        ArrayList<String> cityNames=new ArrayList<String>();
        if(cursor != null && cursor.getCount() != 0) {
            while(cursor.moveToNext()) {
                String name=cursor.getString(cursor.getColumnIndex("attractions"));
                cityNames.add(name);
            }
        }
        return cityNames;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public static Bitmap activityShot(Activity activity) {
        Toast.makeText(activity,"The Result has been saved",Toast.LENGTH_SHORT).show();
        View cv = activity.getWindow().getDecorView();

        cv.setDrawingCacheEnabled(true);
        cv.buildDrawingCache();
        Bitmap bmp = cv.getDrawingCache();
        if (bmp == null) {
            return null;
        }

        bmp.setHasAlpha(false);
        bmp.prepareToDraw();
        return bmp;

    }


    public void saveBitmap(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "image1" + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);                   // save route screenshot to mobile phone
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Uri uri = Uri.fromFile(file);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent("android.intent.action.FEEDBACK");
            intent.putExtra("activityname","select1");
            startActivity(intent);
        } else if (id == R.id.nav_manage) {                       // set navigation bar

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}

package com.example.TravelCounsellingSystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.maps.android.SphericalUtil;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.text.*;
import multiview.City;
import multiview.CityAdapter;

public class Select2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //Initialize the city list
    private List<City> cityList = new ArrayList<City>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_for_select2);
        Intent intent=getIntent();
        final String[] Select1Info=intent.getStringArrayExtra("SelectInfo");
        Intent intent1=getIntent();
        final String prefer=intent1.getStringExtra("prefer");
        //Initialize the city name for choose
        final String[] cities= {"London", "Liverpool", "Manchester", "Birmingham", "Leeds", "Bristol", "Plymouth", "Brighton", "York", "Sheffield", "Newcastle", "Nottingham", "Oxford", "Bath", "Norwich", "Isle of Wight", "Cambridge", "Southampton", "Canterbury", "Leicester"};
        final SparseBooleanArray clicked;

        //Add the tool bar to the view
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        System.out.println("prefertest "+prefer);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setPadding(0,0,0,0);
        clicked = new SparseBooleanArray(cityList.size());
        CityAdapter adapter = new CityAdapter(cityList,clicked);
        Button button= findViewById(R.id.submit);

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = 0;
                String[] temp;
                for(int i =0;i<cities.length;i++){
                    if(clicked.get(i)){
                        num++;
                    }
                }
                if(num!=0){
                    String[] Select2Info = new String[num];
                    num = 0;
                    for(int i =0;i<cities.length;i++) {
                        if (clicked.get(i)) {
                            Select2Info[num] = cities[i];
                            num++;
                        }
                    }
                    System.out.println("situation1 "+sort(prefer, Select1Info,Select2Info)[0]);
                    temp = sort(prefer, Select1Info,Select2Info);
                }
                else{
                    String[] Select2Info = {"None"};
                    System.out.println("situation2 "+sort(prefer, Select1Info,Select2Info)[0]);
                    temp = sort(prefer, Select1Info,Select2Info);
                    System.out.println("situation er");
                }
                String[] path = pathRoute(temp,Select1Info[1],Select1Info[2]);
                for(String s:path){
                    System.out.println("city "+s);
                }
                Intent intent=new Intent("android.intent.action.MAP");
                intent.putExtra("city",path);
                view.getContext().startActivity(intent);
            }
        });
    }

    //Initialize the cities object
    private void initFruits() {
        City London = new City(getRandomLengthName("London"), R.drawable.a1);
        cityList.add(London);
        City Liverpool = new City(getRandomLengthName("Liverpool"), R.drawable.a2);
        cityList.add(Liverpool);
        City Manchester = new City(getRandomLengthName("Manchester"), R.drawable.a3);
        cityList.add(Manchester);
        City Birmingham = new City(getRandomLengthName("Birmingham"), R.drawable.a4);
        cityList.add(Birmingham);
        City Leeds = new City(getRandomLengthName("Leeds"), R.drawable.a5);
        cityList.add(Leeds);
        City Bristol = new City(getRandomLengthName("Bristol"), R.drawable.a6);
        cityList.add(Bristol);
        City Brighton = new City(getRandomLengthName("Brighton"), R.drawable.a7);
        cityList.add(Brighton);
        City Plymouth = new City(getRandomLengthName("Plymouth"), R.drawable.a8);
        cityList.add(Plymouth);
        City York = new City(getRandomLengthName("York"), R.drawable.a9);
        cityList.add(York);
        City Sheffield = new City(getRandomLengthName("Sheffield"), R.drawable.a10);
        cityList.add(Sheffield);
        City Newcastle = new City(getRandomLengthName("Newcastle"), R.drawable.a11);
        cityList.add(Newcastle);
        City Nottingham = new City(getRandomLengthName("Nottingham"), R.drawable.a12);
        cityList.add(Nottingham);
        City Oxford = new City(getRandomLengthName("Oxford"), R.drawable.a13);
        cityList.add(Oxford);
        City Bath = new City(getRandomLengthName("Bath"), R.drawable.a14);
        cityList.add(Bath);
        City Norwich = new City(getRandomLengthName("Norwich"), R.drawable.a15);
        cityList.add(Norwich);
        City Isle = new City(getRandomLengthName("Isle of Wight"), R.drawable.a16);
        cityList.add(Isle);
        City Cambridge = new City(getRandomLengthName("Cambridge"), R.drawable.a17);
        cityList.add(Cambridge);
        City Southampton = new City(getRandomLengthName("Southampton"), R.drawable.a18);
        cityList.add(Southampton);
        City Canterbury = new City(getRandomLengthName("Canterbury"), R.drawable.a19);
        cityList.add(Canterbury);
        City Leicester = new City(getRandomLengthName("Leicester"), R.drawable.a20);
        cityList.add(Leicester);
    }

    private String getRandomLengthName(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        return builder.toString();
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

    //Add navigation bar chose
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent("android.intent.action.FEEDBACK");
            intent.putExtra("activityname","select2");
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //This is the algorithmn of sort all the cities for users according to the preferrence
    public String[] sort(String prefer,String[] Select1Info,String[] Select2Info){
        double travelDayPrefer=0;
        if(prefer.charAt(0)=='N'){
            travelDayPrefer=2.5;
        }else {
            travelDayPrefer=1.6;
        }

        int Days=0;

        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date d1=new Date();
        Date d2=new Date();
        d1.getTime();
        d2.getTime();
        try{
            d1=df.parse(Select1Info[3]);
            d2=df.parse(Select1Info[4]);
        }catch (Exception e){

        }

        Days=(int)(d2.getTime()-d1.getTime())/(60*60*1000*24);

        int NumOfCity = (int)Math.ceil(Days/travelDayPrefer);
        System.out.println("Days "+Days);
        System.out.println("NumofCity"+NumOfCity);
        if(Select2Info.length>=NumOfCity){
            System.out.println("special situation");
            return Select2Info;
        }
        int sortCity=0;
        String[] selectcities;
        int cityCounter;
        if(!Select2Info[0].equals("None")){
            sortCity=NumOfCity+Select2Info.length;
            selectcities=new String[sortCity];
            for(int i=0;i<Select2Info.length;i++){
                selectcities[i]=Select2Info[i];
            }
            cityCounter=Select2Info.length;
        }else {
            sortCity=NumOfCity;
            selectcities=new String[sortCity];
            cityCounter=0;
        }


        System.out.println("cities:"+selectcities.length);
        String[][] allInfo=new String[20][2];
        int[] allMatch=new int[20];
        //init database
        DBManager dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        SQLiteDatabase databasetwo = dbHelper.getDatabase();
        Cursor cursor = databasetwo.rawQuery("select cityname,scenery,historical,entertaining from travelcity", null);

        for(int i=0;cursor.moveToNext();i++){
            allInfo[i][0]=cursor.getString(cursor.getColumnIndex("cityname"));
            allInfo[i][1]=cursor.getString(cursor.getColumnIndex("scenery"))+cursor.getString(cursor.getColumnIndex("historical"))+cursor.getString(cursor.getColumnIndex("entertaining"));
            allMatch[i]=0;
        }
        for(int i=0;i<20;i++){
            if(allInfo[i][0].equals(Select1Info[1])||allInfo[i][0].equals(Select1Info[2])){
                allInfo[i][0]="Exclude";
            }
        }

        for(int i=0;i<20;i++){
            if(!allInfo[i][0].equals("Exclude")){
                if(prefer.charAt(0)==allInfo[i][1].charAt(0))allMatch[i]++;
                if(prefer.charAt(1)==allInfo[i][1].charAt(1))allMatch[i]++;
                if(prefer.charAt(2)==allInfo[i][1].charAt(2))allMatch[i]++;
            }

        }
        for(int i=0;i<20;i++){
            if(allMatch[i]==3){
                selectcities[cityCounter]=allInfo[i][0];
                cityCounter++;
                if(cityCounter==selectcities.length)return selectcities;
            }
        }
        for(int i=0;i<20;i++){
            if(allMatch[i]==2){
                selectcities[cityCounter]=allInfo[i][0];
                cityCounter++;
                if(cityCounter==selectcities.length)return selectcities;
            }
        }
        for(int i=0;i<20;i++){
            if(allMatch[i]==1){
                selectcities[cityCounter]=allInfo[i][0];
                cityCounter++;
                if(cityCounter==selectcities.length)return selectcities;
            }
        }
        for(int i=0;i<20;i++){
            if(allMatch[i]==0){
                selectcities[cityCounter]=allInfo[i][0];
                cityCounter++;
                if(cityCounter==selectcities.length)
                    return selectcities;
            }
        }
        return selectcities;
    }


    //This is the algorithmn to find out the best route for the travelling plan
    String[] pathRoute(String[] cities,String start, String end){
        String[] route = new String[cities.length];
        route[0] = start;
        ArrayList<Double> longitude = new ArrayList<>();
        ArrayList<Double> latitude = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        DBManager dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        SQLiteDatabase databasetwo = dbHelper.getDatabase();
        for(int i=0; i<  cities.length; i++){
            Cursor cursor = databasetwo.rawQuery("select location from travelcity where cityname ="+"'"+cities[i]+"'", null);
            while (cursor.moveToNext()){
                String location = (cursor.getString(cursor.getColumnIndex("location")));
                String[] temp = location.split(",");
                longitude.add(Double.valueOf(temp[1]));
                latitude.add(Double.valueOf(temp[0]));
                name.add(cities[i]);
            }
        }
        for(int j=0;j<name.size();j++){
            System.out.println("input city "+name.get(j));
        }
        int runtime = name.size();
        for(int a=0;a<runtime;a++){
            for(int i=0;i<name.size();i++){
                if(start.equals(name.get(i))){
                    name.remove(i);
                    latitude.remove(i);
                    longitude.remove(i);
                    break;
                }
                if(end.equals(name.get(i))){
                    name.remove(i);
                    latitude.remove(i);
                    longitude.remove(i);
                    break;
                }
            }
        }
        for(int j=0;j<name.size();j++) {
            System.out.println("input deleted city " + name.get(j));
        }

        Cursor cursor = databasetwo.rawQuery("select location from travelcity where cityname ="+"'"+start+"'", null);
        String location = "";
        while (cursor.moveToNext()){
            location = (cursor.getString(cursor.getColumnIndex("location")));
        }
        String[] temp = location.split(",");
        double startLatitude =  Double.valueOf(temp[0]);
        double startLongitude =  Double.valueOf(temp[1]);
        double tempA1 = 0;
        double tempA2 = 0;
        for(int a=0;a<route.length-1;a++){
            int tempB = 0;
            double max = 1000000000;
            for(int j=0;j<longitude.size();j++){
                if(tempA1==0){
                    tempA1 = startLatitude;
                }
                if(tempA2==0){
                    tempA2 = startLongitude;
                }
                System.out.println("la1 "+tempA1);
                System.out.println("lO1 "+tempA2);
                System.out.println("la2 "+latitude.get(j));
                System.out.println("lO2 "+longitude.get(j));
                if(getDistance(tempA1,tempA2,latitude.get(j),longitude.get(j))<max){
                    System.out.println("max1 is "+max);
                    max = getDistance(tempA1,tempA2,latitude.get(j),longitude.get(j));
                    System.out.println("max1 is "+max);
                    System.out.println("updateCity is "+name.get(j));
                    tempB = j;
                }

            }
            if(name.size()!=0){
                System.out.println("city!! "+name.get(tempB));
                System.out.println("A's value "+a);
                route[a+1] = name.get(tempB);
                name.remove(tempB);
                latitude.remove(tempB);
                longitude.remove(tempB);
            }
        }
        if(!end.equals("None")&&!end.equals("Select a city")){
            String[] result = Arrays.copyOf(route,route.length+1);
            result[route.length] = end;
            return result;
        }
        return route;
    }

    //Calculate the distence between cities
    public double getDistance(double latPoint1, double lngPoint1, double latPoint2, double lngPoint2){
        LatLng p1 = new LatLng(latPoint1, lngPoint1);
        LatLng p2 = new LatLng(latPoint2, lngPoint2);
        double distance = SphericalUtil.computeDistanceBetween(p1, p2);
        return distance;
    }

}
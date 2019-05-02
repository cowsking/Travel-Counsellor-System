package com.example.TravelCounsellingSystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Select1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;

    private TextView country;
    private TextView cityStart;
    private TextView cityEnd;
    private TextView dateStartTitle;
    private TextView dateStart;
    private TextView dateEndTitle;
    private TextView dateEnd;
    private int provinceIndex;
    private String[] province = {"England", "Others    "};

    ArrayAdapter<String> aa3;
    ArrayAdapter<String> aa2;
    ArrayAdapter<String> aa1;
    //Initialize the Selected Informatiion array with "None" value
    public String[] InfoSelect = {"None", "None", "None", "None", "None"};

    String prefer;
    private Calendar cal;
    private int year, month, day;
    //Initialize the all the city names to choose
    private String[][] city = {{"Select a city","London", "Liverpool", "Manchester", "Birmingham", "Leeds", "Bristol", "Plymouth", "Brighton", "York", "Sheffield", "Newcastle", "Nottingham", "Oxford", "Bath", "Norwich", "Isle of Wight", "Cambridge", "Southampton", "Canterbury", "Leicester"}, {"Others"}};
    private String[][] city2={{"Select a city","London", "Liverpool", "Manchester", "Birmingham", "Leeds", "Bristol", "Plymouth", "Brighton", "York", "Sheffield", "Newcastle", "Nottingham", "Oxford", "Bath", "Norwich", "Isle of Wight", "Cambridge", "Southampton", "Canterbury", "Leicester"}, {"Others"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //get the information from the last activity
        Intent intent=getIntent();
        prefer=intent.getStringExtra("prefer");
        System.out.println("prefer q1 "+prefer);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        //Add the tool bar in the view
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Add the Textview and select to the view
        country = findViewById(R.id.country);
        cityStart = findViewById(R.id.cityStart);
        cityEnd = findViewById(R.id.cityEnd);
        dateStartTitle = findViewById(R.id.dateStartTitle);
        dateStart = findViewById(R.id.dateStart);
        dateEndTitle = findViewById(R.id.dateEndTitle);
        dateEnd = findViewById(R.id.dateEnd);
        spinner1 = findViewById(R.id.sp1);
        spinner2 = findViewById(R.id.sp2);
        spinner3 = findViewById(R.id.sp3);

        country.setText("Country");
        cityStart.setText("Start Place");
        cityEnd.setText("End Place (Optional)");
        dateStartTitle.setText("Start Date");
        dateStart.setText("Choose Your Start Date");
        dateEndTitle.setText("End Date");
        dateEnd.setText("Choose Your End Date");

        //Get the date value for today
        getDate();
        //Set the date choose view and listenner
        dateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.dateStart:
                        OnDateSetListener listener = new OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                                dateStart.setText(year + "-" + (++month) + "-" + day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                                InfoSelect[3] = year + "-" + (++month) + "-" + day;
                            }
                        };
                        DatePickerDialog dialog = new DatePickerDialog(Select1.this, DatePickerDialog.THEME_HOLO_LIGHT, listener, year, month, day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                        dialog.show();
                        break;

                    default:
                        break;
                }
            }
        });

        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.dateEnd:
                        OnDateSetListener listener = new OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                                dateEnd.setText(year + "-" + (++month) + "-" + day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                                InfoSelect[4] = year + "-" + (++month) + "-" + day;
                            }
                        };
                        DatePickerDialog dialog = new DatePickerDialog(Select1.this, DatePickerDialog.THEME_HOLO_LIGHT, listener, year, month, day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                        dialog.show();
                        break;

                    default:
                        break;
                }
            }
        });

        //Add the
        aa1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, province);
        spinner1.setAdapter(aa1);

        aa2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, city[0]);
        spinner2.setAdapter(aa2);


        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                InfoSelect[0] = province[position];
                provinceIndex = position;
                aa2 = new ArrayAdapter<>(Select1.this, android.R.layout.simple_dropdown_item_1line, city[position]);
                spinner2.setAdapter(aa2);
                aa3 = new ArrayAdapter<>(Select1.this, android.R.layout.simple_dropdown_item_1line, city[position]);
                spinner3.setAdapter(aa3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InfoSelect[1] = aa2.getItem(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InfoSelect[2] = aa3.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new submitOnClickListener());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDate() {
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);       //Get year
        Log.i("wxy", "year" + year);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
    }

    //Set submit listenner
    class submitOnClickListener implements View.OnClickListener {
        public submitOnClickListener() {
        }

        @Override
        public void onClick(View arg0) {
            boolean AllFinish = true;
            for (int i = 0; i < 5; i++) {
                if (InfoSelect[i].equals("None")) {
                    AllFinish = false;
                }
            }
            if(InfoSelect[1].equals("Select a city")){
                AllFinish=false;
            }
            if (AllFinish) {
                System.out.println(InfoSelect);
                Toast.makeText(getApplicationContext(), "Select Success", Toast.LENGTH_LONG).show();
                Intent intent=new Intent("android.intent.action.QUERY_FOLLOW");
                intent.putExtra("SelectInfo",InfoSelect);
                intent.putExtra("prefer",prefer);
                System.out.println("prefer q1l "+prefer);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "You should choose all the information!", Toast.LENGTH_LONG).show();
            }
        }
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
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

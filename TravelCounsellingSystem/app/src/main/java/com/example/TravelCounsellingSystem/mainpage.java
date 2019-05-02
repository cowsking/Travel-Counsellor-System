package com.example.TravelCounsellingSystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.TravelCounsellingSystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class mainpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        String prefer;
        int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get the input value from user of the last activity
        Intent intent=getIntent();
        prefer=intent.getStringExtra("prefer");
        userId= intent.getIntExtra("userId", 0);
        System.out.println("mainPage userIdValue test "+prefer+" of "+""+userId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add the navigation bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Add the submit button
        Button submit = findViewById(R.id.submit3);
        submit.setOnClickListener(new submitOnClickListener());
    }

    //Set the submit listener
    class submitOnClickListener implements View.OnClickListener {
        //private Page page;
        public submitOnClickListener() {
        }

        @Override
        public void onClick(View arg0) {
            Intent intent1=new Intent("android.intent.action.QUERY_START");
            System.out.println("mainpage prefer "+prefer);
            intent1.putExtra("prefer",prefer);
            uploadValue();
            startActivity(intent1);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent("android.intent.action.FEEDBACK");
            intent.putExtra("activityname","main");
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    OkHttpClient client = new OkHttpClient();

    Call post(Callback callback) {
        RequestBody post = new FormBody.Builder()
                .add("userId", String.valueOf(userId))
                .add("preference", prefer)
                .build();
        Request request = new Request.Builder()
                .url("https://student.csc.liv.ac.uk/~sgwche11/208/record.php")
                .post(post)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    //Upload the preferrence information to the server
    private void uploadValue() {
        post(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Something went wrong
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    // Do what you want to do with the response.
                } else {
                    // Request not successful
                }
            }
        });
    }

}

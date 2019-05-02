package com.example.TravelCounsellingSystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TravelCounsellingSystem.R;

public class picShow extends AppCompatActivity
         {
    String city;
    String interest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get the value from the last activity
        Intent intent=getIntent();
        city=intent.getStringExtra("CityName");
        interest=intent.getStringExtra("AttractionName");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_show_layout);

        //Initialize the DBManager to get value from Database
        DBManager2 dbHelper = new DBManager2(this);
        dbHelper.openDatabase();
        SQLiteDatabase databasetwo = dbHelper.getDatabase();
        //Get the pictures of the interest places from database
        Cursor cursor=databasetwo.rawQuery("select figure from "+city+" where attractions="+"'"+interest+"'",null);
        while(cursor.moveToNext()) {
            byte[] img = cursor.getBlob(cursor.getColumnIndex("figure"));

            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length, null);

            ImageView pic = findViewById(R.id.pic);
            pic.setImageBitmap(bitmap);
        }
        //Get the name of interest places from the database
        Cursor cursor1=databasetwo.rawQuery("select about from "+city+" where attractions="+"'"+interest+"'",null);
        while(cursor1.moveToNext()) {
            String about=cursor1.getString(cursor1.getColumnIndex("about"));
            TextView aabout=findViewById(R.id.about);
            System.out.println("uhuh"+about);

            aabout.setText(about);
        }

    }


}

package com.example.TravelCounsellingSystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class DBManager {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "cityi.db"; //assign database name to variable DB_NAME
    public static final String PACKAGE_NAME = "com.example.TravelCounsellingSystem";  //assign package name to variable package name
    public static  String DB_PATH = "/data";         //assign db path 
    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {             //constructing method for class DBManager
        this.context = context;
        DB_PATH=getSDPath();                        //return SDPath from method getSDPath
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) { //constructing setDatabase(SQLiteDatabase database) method
        this.database = database;                   
    }

    public void openDatabase() {                       //constructing openDatabase() method in order to openDatabase according to DB_PATH and DB_NAME    
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }
    private SQLiteDatabase openDatabase(String dbfile) {     //The database file has been put into raw directory. In order to get database file, use openRawResource() method to get database resources.
        try {                                                //make judgement abot whethe database file has been put into database directory,if not, create directory
            if (!(new File(dbfile).exists())) {
                InputStream is = this.context.getResources().openRawResource(R.raw.cityi); //create InputStream     
                FileOutputStream fos = new FileOutputStream(dbfile);   //create fileoutstream and write content of database to specified file
                byte[] buffer = new byte[BUFFER_SIZE];                 //use buffer to contain content of inputstream
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);                       //write inputstream content output file
                }
                fos.close();                                           //close the outputstream
                is.close();                                            //close the inputstream
            }

            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);  //open SQLiteDatabase according to database file
            return db;        //return SQLiteDatabase

        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }
    public void closeDatabase() {
        this.database.close();            //close database
    }

    public String getSDPath(){            //get SD path of database file 
        File sdDir = null;                                     
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);//make judgement about whether sdCard exist
        if(sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//if sdCard exists, then get storage directory which contains file
        }
        return sdDir.toString();
    }

}

package com.example.guestbook;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Const {

    public static Connection con;
    public void setConnection(){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ip = "192.168.1.6";
            String ConnUrl = "jdbc:jtds:sqlserver://"+ip+"/instance=SQLServer:user=sa:password=sapassword:databasename=API_BukuAbsen";

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            con = DriverManager.getConnection(ConnUrl);
            Log.e("ASK","Connection Called");
        }catch (Exception e){
            Log.e("ASK","EXCEPTION" + e.getMessage());

        }
    }

   //Splash Screen
   public static final int DELAY_SPLASH_SCREEN = 3000;

   public static final String URL_PROVINSI = "https://dev.farizdotid.com/api/daerahindonesia/provinsi/";
   public static final String URL_KOTA = "https://dev.farizdotid.com/api/daerahindonesia/kota?id_provinsi=";
   public static final String URL_KECAMATAN = "https://dev.farizdotid.com/api/daerahindonesia/kecamatan?id_kota=";
   public static final String URL_KELURAHAN = "https://dev.farizdotid.com/api/daerahindonesia/kelurahan?id_kecamatan=";
   //API SQL SERVER
    public  static final String URL_DATA = "http://localhost:8085/api/visitor";
}

package com.example.guestbook;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Const {

    public static Connection con;
   //Splash Screen
   public static final int DELAY_SPLASH_SCREEN = 3000;

   public static final String URL_PROVINSI = "https://dev.farizdotid.com/api/daerahindonesia/provinsi/";
   public static final String URL_KOTA = "https://dev.farizdotid.com/api/daerahindonesia/kota?id_provinsi=";
   public static final String URL_KECAMATAN = "https://dev.farizdotid.com/api/daerahindonesia/kecamatan?id_kota=";
   public static final String URL_KELURAHAN = "https://dev.farizdotid.com/api/daerahindonesia/kelurahan?id_kecamatan=";
   //API SQL SERVER
    public  static final String URL_DATA = "http://192.168.1.6:8085/api/visitor";
    //http://192.168.1.5/api/visitor
}

package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.guestbook.databinding.ActivityHomeBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private ActivityHomeBinding binding;
    private static final String TAG = MainActivity.class.getSimpleName();

    ArrayList<String> listNamaProvinsi = new ArrayList<>();
    ArrayList<String> listNamaKota = new ArrayList<>();
    ArrayList<String> listNamaKecamatan = new ArrayList<>();
    ArrayList<String> listNamaKelurahan = new ArrayList<>();

    ArrayList<String> listIdProvinsi = new ArrayList<>();
    ArrayList<String> listIdKota = new ArrayList<>();
    ArrayList<String> listIdKecamatan = new ArrayList<>();
    ArrayList<String> listIdKelurahan = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        binding.toolbar.btnDocument.setOnClickListener(this);

        getAllProvinsi();
    }

    private void getAllProvinsi() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Const.URL_PROVINSI;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String result = new String(responseBody);
                Log.d(TAG, result);
                listIdProvinsi.clear();
                listNamaProvinsi.clear();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("provinsi");

                    for (int i=0; i < jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String Id = object.getString("id");
                        String provinsi = object.getString("nama");
                        listIdProvinsi.add(Id);
                        listNamaProvinsi.add(provinsi);
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listNamaProvinsi);
                    binding.dropdownProvinsi.setAdapter(arrayAdapter);

                    binding.dropdownProvinsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            showDataKota(listIdProvinsi.get(position));
                        }
                    });

                }catch (Exception e){
                    Toast.makeText(Home.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage =  statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(Home.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDataKota(String id) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Const.URL_KOTA + id;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d(TAG, result);
                listNamaKota.clear();
                listIdKota.clear();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("kota_kabupaten");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String idKota = object.getString("id");
                        String namaKota = object.getString("nama");
                        listIdKota.add(idKota);
                        listNamaKota.add(namaKota);
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,
                            listNamaKota);
                    binding.dropdownKotaKabupaten.setAdapter(arrayAdapter);

                    binding.dropdownKotaKabupaten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            showDataKecamatan(listIdKota.get(position));
                        }
                    });

                }catch (Exception e){
                    Toast.makeText(Home.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void showDataKecamatan(String id) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Const.URL_KECAMATAN + id;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                listIdKecamatan.clear();
                listNamaKecamatan.clear();

                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("kecamatan");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String idKecamatan = object.getString("id");
                        String namaKecamatan = object.getString("nama");

                        listIdKecamatan.add(idKecamatan);
                        listNamaKecamatan.add(namaKecamatan);
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,
                            listNamaKecamatan);
                    binding.dropdownKecamatan.setAdapter(arrayAdapter);

                    binding.dropdownKecamatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            showDataKelurahan(listIdKecamatan.get(position));
                        }
                    });

                }catch (Exception e){
                    Toast.makeText(Home.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void showDataKelurahan(String id) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Const.URL_KELURAHAN + id;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                listIdKelurahan.clear();
                listNamaKelurahan.clear();

                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("kelurahan");

                        for (int i=0; i< jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String idKelurahan = object.getString("id");
                            String namaKelurahan = object.getString("nama");

                            listIdKelurahan.add(idKelurahan);
                            listNamaKelurahan.add(namaKelurahan);
                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,
                                listNamaKelurahan);
                        binding.dropdownKelurahan.setAdapter(arrayAdapter);
                }catch (Exception e){
                    Toast.makeText(Home.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }



    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Home.this, DataActivity.class);
        startActivity(intent);
    }
}
package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.guestbook.databinding.ActivityDataBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DataActivity extends AppCompatActivity {

    public static final String TAG = DataActivity.class.getSimpleName();
    private ActivityDataBinding binding;
    private VisitorsAdapter adapter;
    private ArrayList<Visitors> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        binding = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvAllData.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvAllData.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        binding.rvAllData.addItemDecoration(itemDecoration);

        showLoading(true);
        getListVisitor();

        binding.lytToolbarData.back.setOnClickListener(view -> {
            finish();
        });

    }

    private void getListVisitor() {
        showLoading(true);
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://dev.farizdotid.com/api/daerahindonesia/kota?id_provinsi=31";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showLoading(false);

                ArrayList<Visitors> listData = new ArrayList<>();

                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("kota_kabupaten");

                    for (int i=0; i< jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String nama = object.getString("nama");
                        //String provinsi = object.getString("id_kota");
                        //String kehadiran = object.getString("kehadiran");

                        Visitors visitors = new Visitors();
                        visitors.setNama(nama);
                        listData.add(visitors);
                    }
                    adapter = new VisitorsAdapter(listData);
                    binding.rvAllData.setAdapter(adapter);

                }catch (Exception e){
                    Toast.makeText(DataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showLoading(false);
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
                Toast.makeText(DataActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showLoading(Boolean state) {
        if (state){
            binding.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
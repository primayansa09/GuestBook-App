package com.example.guestbook;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.guestbook.API.APIConfigProvinsi;
import com.example.guestbook.model.ProvinsiItem;
import com.example.guestbook.model.ProvinsiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProvinsiViewModel extends ViewModel {

    private final MutableLiveData<List<ProvinsiItem>> _listProvinsi = new MutableLiveData<>();

    public LiveData<List<ProvinsiItem>> getProvinsi(){
        return _listProvinsi;
    }
    private static final String TAG = "ProvinsiViewModel";
    
    public ProvinsiViewModel(){
        findProvinsi();
    }

    private void findProvinsi() {
        Call<ProvinsiResponse> client = APIConfigProvinsi.getApiService().getProvinsi();
        client.enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, retrofit2.Response<ProvinsiResponse> response) {
                if (response.isSuccessful()){
                    if(response.body() != null){
//                        setProvinsi(response.body().getProvinsi());
                        _listProvinsi.setValue(response.body().getProvinsi());
                    }
                }else {
                    if (response.body() != null){
                        Log.e(TAG, "onFailure: " + response.body().getProvinsi());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}

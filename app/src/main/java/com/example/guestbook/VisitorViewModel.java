package com.example.guestbook;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.guestbook.API.ApiConfig;
import com.example.guestbook.model.VisitorResponse;
import com.example.guestbook.model.VisitorResultsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorViewModel extends ViewModel {

    private final MutableLiveData<List<VisitorResultsItem>> _listVisitor = new MutableLiveData<>();
//    private MutableLiveData<Visitors> detailModel = new MutableLiveData<>();
    public LiveData<List<VisitorResultsItem>> getVisitor(){
        return _listVisitor;
    }

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading(){
        return _isLoading;
    }

    private final MutableLiveData<Boolean> _isNoData = new MutableLiveData<>();
    public LiveData<Boolean> isNoData(){
        return _isNoData;
    }

    private static final String TAG = "VisitorViewModel";

    public VisitorViewModel(){
        findVisitor();
    }

    private void findVisitor() {
        Call<VisitorResponse> client = ApiConfig.getApiService().getVisitors();
        client.enqueue(new Callback<VisitorResponse>() {
            @Override
            public void onResponse(Call<VisitorResponse> call, Response<VisitorResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                       _listVisitor.setValue(response.body().getData());
                    }
                }else {
                    if (response.body() != null){
                        Log.e(TAG, "onFailure: " + response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<VisitorResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}

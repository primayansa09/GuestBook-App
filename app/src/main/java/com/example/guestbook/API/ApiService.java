package com.example.guestbook.API;

import com.example.guestbook.model.KecamatanResponse;
import com.example.guestbook.model.KelurahanResponse;
import com.example.guestbook.model.KotaResponse;
import com.example.guestbook.model.ProvinsiResponse;
import com.example.guestbook.model.VisitorResponse;
import com.example.guestbook.model.VisitorResultsItem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/visitor")
    Call<VisitorResponse> getVisitors();

    @POST("api/visitor")
    Call<VisitorResponse> postVisitors(@Body VisitorResultsItem visitorResultsItem);

    @GET("provinsi")
    Call<ProvinsiResponse> getProvinsi();

    @GET("kota")
    Call<KotaResponse> getKota_Kabupaten(@Query("id_provinsi") int id_provinsi);

    @GET("kecamatan")
    Call<KecamatanResponse> getKecamatan(@Query("id_kota") int id_kota);

    @GET("kelurahan")
    Call<KelurahanResponse> getKelurahan(@Query("id_kecamatan") int id_kecamatan);
}

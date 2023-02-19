package com.example.guestbook.model;

import com.google.gson.annotations.SerializedName;

public class KotaKabupatenItem{

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private int id;

	@SerializedName("id_provinsi")
	private String id_Provinsi;

	public String getNama(){
		return nama;
	}

	public int getId(){
		return id;
	}

	public String getIdProvinsi(){
		return id_Provinsi;
	}
}
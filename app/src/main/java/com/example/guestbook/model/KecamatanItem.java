package com.example.guestbook.model;

import com.google.gson.annotations.SerializedName;

public class KecamatanItem{

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_kota")
	private String id_Kota;

	@SerializedName("id")
	private int id;

	public String getNama(){
		return nama;
	}

	public String getIdKota(){
		return id_Kota;
	}

	public int getId(){
		return id;
	}
}
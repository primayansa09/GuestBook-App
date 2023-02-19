package com.example.guestbook.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProvinsiResponse{

	@SerializedName("provinsi")
	private List<ProvinsiItem> provinsi;

	public List<ProvinsiItem> getProvinsi(){
		return provinsi;
	}
}
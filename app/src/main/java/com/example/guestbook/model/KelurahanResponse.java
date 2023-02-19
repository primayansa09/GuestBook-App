package com.example.guestbook.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class KelurahanResponse{

	@SerializedName("kelurahan")
	private List<KelurahanItem> kelurahan;

	public List<KelurahanItem> getKelurahan(){
		return kelurahan;
	}
}
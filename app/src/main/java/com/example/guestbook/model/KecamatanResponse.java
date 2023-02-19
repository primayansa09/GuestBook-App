package com.example.guestbook.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class KecamatanResponse{

	@SerializedName("kecamatan")
	private List<KecamatanItem> kecamatan;

	public List<KecamatanItem> getKecamatan(){
		return kecamatan;
	}
}
package com.example.guestbook.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class KotaResponse{

	@SerializedName("kota_kabupaten")
	private List<KotaKabupatenItem> kotaKabupaten;

	public List<KotaKabupatenItem> getKotaKabupaten(){
		return kotaKabupaten;
	}
}
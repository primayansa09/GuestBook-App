package com.example.guestbook.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VisitorResponse{

	@SerializedName("data")
	private List<VisitorResultsItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public List<VisitorResultsItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public int getStatus(){
		return status;
	}
}
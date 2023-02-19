package com.example.guestbook.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class VisitorResultsItem implements Parcelable {

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("nama")
	private String nama;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("kehadiran")
	private String kehadiran;

	@SerializedName("kecamatan")
	private String kecamatan;

	@SerializedName("kodePos")
	private int kodePos;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("kota_Kabupaten")
	private String kotaKabupaten;

	@SerializedName("kelurahan")
	private String kelurahan;

	@SerializedName("alamat")
	private String alamat;

	public VisitorResultsItem() {

	}

	public VisitorResultsItem(String nama, String noHp, String email, String alamat, String provinsi, String kotaKabupaten, String kecamatan, String kelurahan, int kodePos, String kehadiran) {
		this.nama = nama;
		this.noHp = noHp;
		this.email = email;
		this.alamat = alamat;
		this.provinsi = provinsi;
		this.kotaKabupaten = kotaKabupaten;
		this.kecamatan = kecamatan;
		this.kelurahan = kelurahan;
		this.kodePos = kodePos;
		this.kehadiran = kehadiran;
	}

	public VisitorResultsItem(Parcel in) {
		provinsi = in.readString();
		nama = in.readString();
		noHp = in.readString();
		kehadiran = in.readString();
		kecamatan = in.readString();
		kodePos = in.readInt();
		id = in.readInt();
		email = in.readString();
		kotaKabupaten = in.readString();
		kelurahan = in.readString();
		alamat = in.readString();
	}

	public static final Creator<VisitorResultsItem> CREATOR = new Creator<VisitorResultsItem>() {
		@Override
		public VisitorResultsItem createFromParcel(Parcel in) {
			return new VisitorResultsItem(in);
		}

		@Override
		public VisitorResultsItem[] newArray(int size) {
			return new VisitorResultsItem[size];
		}
	};

	public String getProvinsi(){
		return provinsi;
	}

	public String getNama(){
		return nama;
	}

	public String getNoHp(){
		return noHp;
	}

	public String getKehadiran(){
		return kehadiran;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public int getKodePos(){
		return kodePos;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getKotaKabupaten(){
		return kotaKabupaten;
	}

	public String getKelurahan(){
		return kelurahan;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(provinsi);
		parcel.writeString(nama);
		parcel.writeString(noHp);
		parcel.writeString(kehadiran);
		parcel.writeString(kecamatan);
		parcel.writeInt(kodePos);
		parcel.writeInt(id);
		parcel.writeString(email);
		parcel.writeString(kotaKabupaten);
		parcel.writeString(kelurahan);
		parcel.writeString(alamat);
	}

	public void setProvinsi(String provinsi) {
		this.provinsi = provinsi;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public void setKehadiran(String kehadiran) {
		this.kehadiran = kehadiran;
	}

	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}

	public void setKodePos(int kodePos) {
		this.kodePos = kodePos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setKotaKabupaten(String kotaKabupaten) {
		this.kotaKabupaten = kotaKabupaten;
	}

	public void setKelurahan(String kelurahan) {
		this.kelurahan = kelurahan;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
}
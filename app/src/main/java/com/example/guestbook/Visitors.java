package com.example.guestbook;

import android.os.Parcel;
import android.os.Parcelable;

public class Visitors implements Parcelable {

    private int Id;
    private String nama;
    private String no_hp;
    private String email;
    private String alamat;
    private String provinsi;
    private String kota_kabupaten;
    private String kecamatan;
    private String kelurahan;
    private int kodePos;
    private String kehadiran;

    public Visitors() {

    }

    protected Visitors(Parcel in) {
        Id = in.readInt();
        nama = in.readString();
        no_hp = in.readString();
        email = in.readString();
        alamat = in.readString();
        provinsi = in.readString();
        kota_kabupaten = in.readString();
        kecamatan = in.readString();
        kelurahan = in.readString();
        kodePos = in.readInt();
        kehadiran = in.readString();
    }

    public static final Creator<Visitors> CREATOR = new Creator<Visitors>() {
        @Override
        public Visitors createFromParcel(Parcel in) {
            return new Visitors(in);
        }

        @Override
        public Visitors[] newArray(int size) {
            return new Visitors[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota_kabupaten() {
        return kota_kabupaten;
    }

    public void setKota_kabupaten(String kota_kabupaten) {
        this.kota_kabupaten = kota_kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public int getKodePos() {
        return kodePos;
    }

    public void setKodePos(int kodePos) {
        this.kodePos = kodePos;
    }

    public String getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(String kehadiran) {
        this.kehadiran = kehadiran;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(nama);
        parcel.writeString(no_hp);
        parcel.writeString(email);
        parcel.writeString(alamat);
        parcel.writeString(provinsi);
        parcel.writeString(kota_kabupaten);
        parcel.writeString(kecamatan);
        parcel.writeString(kelurahan);
        parcel.writeInt(kodePos);
        parcel.writeString(kehadiran);

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

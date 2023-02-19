package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.guestbook.databinding.ActivityDetailBinding;
import com.example.guestbook.model.VisitorResultsItem;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    public static final String EXTRA_VISITOR = "extra_visitor";
    public static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        VisitorResultsItem item = getIntent().getParcelableExtra(EXTRA_VISITOR);
        String mNama = item.getNama();
        String mNoHp = item.getNoHp();
        String mAlamat = item.getAlamat();
        String mEmail = item.getEmail();
        String mProvinsi = item.getProvinsi();
        String mKotaKabupaten = item.getKotaKabupaten();
        String mKecamatan = item.getKecamatan();
        String mKelurahan = item.getKelurahan();
        int mKodePos = item.getKodePos();
        String kodePos = Integer.toString(mKodePos);
        String mKehadiran = item.getKehadiran();

        binding.nameUser.setText(mNama);
        binding.phone.setText(mNoHp);
        binding.email.setText(mEmail);
        binding.alamat.setText(mAlamat);
        binding.provinsi.setText(mProvinsi);
        binding.kabupatenKota.setText(mKotaKabupaten);
        binding.kecamatan.setText(mKecamatan);
        binding.kelurahan.setText(mKelurahan);
        binding.kodePos.setText(kodePos);
        binding.kehadiran.setText(mKehadiran);

        Log.e(TAG,"Data: " + item);

        binding.toolbarDetail.backDetail.setOnClickListener(view -> {
            finish();
        });
    }
}
package com.example.rezafd.my_apps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rezafd.my_apps.API.ApiRequest;
import com.example.rezafd.my_apps.API.Retroserver;
import com.example.rezafd.my_apps.Model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Prestasi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText Nama, Peringkat, Tingkat, Penyelenggara;
    Button btnsave;
    ProgressDialog pg;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestasi);
        Nama = (EditText) findViewById(R.id.input_nama);
        Peringkat = (EditText) findViewById(R.id.input_peringkat);
        Tingkat = (EditText) findViewById(R.id.input_Tingkat);
        Penyelenggara = (EditText) findViewById(R.id.input_penyelenggara);
        btnsave = (Button) findViewById(R.id.btn_save);
        pg = new ProgressDialog(this);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setMessage("Sending Data....");
                pg.setCancelable(false);
                pg.show();

                String sNama = Nama.getText().toString();
                String sPeringkat = Peringkat.getText().toString();
                String sTingkat = Tingkat.getText().toString();
                String sPenyelenggara = Penyelenggara.getText().toString();
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

                Call<ResponsModel> send = api.sendPrestasi(sNama, sPeringkat, sTingkat, sPenyelenggara);
                send.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        if (kode == "1") {
                            Toast.makeText(Prestasi.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Prestasi.this, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");

                    }
                });


            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_narasumber) {
            Intent intent = new Intent(Prestasi.this, Narasumber.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            // Handle the camera action
        } else if (id == R.id.nav_pekerjaan) {
            Intent intent = new Intent(Prestasi.this, Pekerjaan.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_penelitian) {
            Intent intent = new Intent(Prestasi.this, Penelitian.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_pm) {
            Intent intent = new Intent(Prestasi.this, Pm.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_prestasi) {
            Intent intent = new Intent(Prestasi.this, Prestasi.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

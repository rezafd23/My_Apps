package com.example.rezafd.my_apps.API;

import com.example.rezafd.my_apps.Model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by REZAFD on 12/11/2017.
 */

public interface ApiRequest {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendPrestasi(@Field("Nama") String nama,
                                    @Field("Peringkat") String peringkat,
                                    @Field("Tingkat") String tingkat,
                                    @Field("Penyelenggara") String penyelenggara);
}

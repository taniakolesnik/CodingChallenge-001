package com.example.codingchallengevoxmarkets;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangeServiceGenerator {

    public static final String BASE_API_URL="https://api.voxmarkets.co.uk/v2/";

    public static OkHttpClient.Builder client = new OkHttpClient.Builder();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}

package com.example.codingchallengevoxmarkets;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExchangeApiClient {

    @GET("exchanges")
    Call<List<Exchange>> getExchangeList();

}

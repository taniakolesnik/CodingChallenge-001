package com.example.codingchallengevoxmarkets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private ExchangeRecyclerViewAdapter adapter;

    @BindView(R.id.exchange_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ButterKnife.bind(this);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExchangeRecyclerViewAdapter( this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        getExchangeList();

    }

    private void getExchangeList() {


        ExchangeApiClient client = ExchangeServiceGenerator.createService(ExchangeApiClient.class);
        Call<List<Exchange>> call = client.getExchangeList();
        call.enqueue(new Callback<List<Exchange>>() {
            @Override
            public void onResponse(Call<List<Exchange>> call, Response<List<Exchange>> response) {
                ArrayList<Exchange> exchanges;
                if (response.body()!=null){
                    Log.i(TAG, "Callback successful with body NOT empty");
                    exchanges = (ArrayList)response.body();
                    adapter.updateAdapter(exchanges);
                } else {
                    Log.i(TAG, "Callback successful with body empty");
                }
            }

            @Override
            public void onFailure(Call<List<Exchange>> call, Throwable t) {
                Log.i(TAG, "Callback failed " + t.toString());
            }
        });

    }
}

package com.example.codingchallengevoxmarkets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private int sortMode;
    private ExchangeRecyclerViewAdapter adapter;

    @BindView(R.id.exchange_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.sort_name_button)
    Button sortByNameButton;

    @BindView(R.id.sort_code_button)
    Button sortByCodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ButterKnife.bind(this);

         sortMode = 1; // 0 - name, 1 - code
        //TODO save selected value in shared preferences

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExchangeRecyclerViewAdapter( this, new ArrayList<>(), sortMode);
        recyclerView.setAdapter(adapter);
        updateExchangeListView(sortMode);

        sortByCodeButton.setOnClickListener(v -> {
            sortMode=1;
            updateExchangeListView(sortMode);
        });

        sortByNameButton.setOnClickListener(v -> {
            sortMode=0;
            updateExchangeListView(sortMode);
        });
    }

    private void updateExchangeListView(int sortMode) {

        ExchangeApiClient client = ExchangeServiceGenerator.createService(ExchangeApiClient.class);
        Call<List<Exchange>> call = client.getExchangeList();
        call.enqueue(new Callback<List<Exchange>>() {
            @Override
            public void onResponse(Call<List<Exchange>> call, Response<List<Exchange>> response) {
                ArrayList<Exchange> exchanges = new ArrayList<>();
                if (response.body()!=null){
                    for (Exchange exchange : response.body()){
                        if (exchange.getExchangeFactSetCode()!=null){
                            exchanges.add(exchange);
                        }
                    }
                    switch (sortMode){
                        case 0:
                            Collections.sort(exchanges, (exchange1, exchange2) -> exchange1.getExchangeName().compareTo(exchange2.getExchangeName()));
                             break;
                        case 1:
                            Collections.sort(exchanges, (exchange1, exchange2) -> exchange1.getExchangeFactSetCode().compareTo(exchange2.getExchangeFactSetCode()));
                            break;
                    }
                    adapter.updateAdapter(exchanges,sortMode);
                }
            }

            @Override
            public void onFailure(Call<List<Exchange>> call, Throwable t) {
                Log.i(TAG, "Callback failed " + t.toString());
            }
        });

    }
}

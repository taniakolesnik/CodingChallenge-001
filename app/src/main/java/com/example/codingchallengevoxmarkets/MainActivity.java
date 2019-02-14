package com.example.codingchallengevoxmarkets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ButterKnife.bind(this);

         sortMode = 1; // 0 - name, 1 - code
        //TODO save selected value in shared preferences

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExchangeRecyclerViewAdapter( this, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        updateExchangeListView(sortMode);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_name_menu_item:
                sortMode=0;
                break;
            case R.id.sort_code_menu_item:
                sortMode=1;
                break;
        }
        updateExchangeListView(sortMode);
        return super.onOptionsItemSelected(item);
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
                    adapter.updateAdapter(exchanges);
                }
            }

            @Override
            public void onFailure(Call<List<Exchange>> call, Throwable t) {
                Log.i(TAG, "Callback failed " + t.toString());
            }
        });

    }
}

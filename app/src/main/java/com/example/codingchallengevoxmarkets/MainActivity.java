package com.example.codingchallengevoxmarkets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.exchange_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<Exchange> fakedata = createFakedata();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExchangeRecyclerViewAdapter adapter = new ExchangeRecyclerViewAdapter( this, fakedata);
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<Exchange> createFakedata() {
        ArrayList<Exchange> fakedata = new ArrayList<>();
        for (int i = 0; i <=100; i++){
            fakedata.add(new Exchange("name" + i, "code" + i));
        }
        return fakedata;
    }
}

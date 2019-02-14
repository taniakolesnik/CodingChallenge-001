package com.example.codingchallengevoxmarkets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeRecyclerViewAdapter extends RecyclerView.Adapter<ExchangeRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Exchange> mData;
    private LayoutInflater layoutInflater;

    public ExchangeRecyclerViewAdapter(Context context, List<Exchange> mData) {
        this.context = context;
        this.mData = mData;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void updateAdapter(List<Exchange> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ExchangeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_view, viewGroup, false);
        return new ExchangeRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExchangeRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Exchange exchangeItem = mData.get(position);
        viewHolder.nameTextView.setText(exchangeItem.getExchangeName());
        viewHolder.factSetCodetextView.setText(exchangeItem.getExchangeFactSetCode());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.exchange_name_textView)
        TextView nameTextView;
        @BindView(R.id.exchange_factSetCode_textView)
        TextView factSetCodetextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

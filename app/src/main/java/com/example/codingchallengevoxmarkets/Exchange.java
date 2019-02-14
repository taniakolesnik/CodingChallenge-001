package com.example.codingchallengevoxmarkets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exchange {

    @SerializedName("exchangeName")
    @Expose
    private String exchangeName;

    @SerializedName("exchangeFactSetCode")
    @Expose
    private String exchangeFactSetCode;


    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeFactSetCode() {
        return exchangeFactSetCode;
    }

    public void setExchangeFactSetCode(String exchangeFactSetCode) {
        this.exchangeFactSetCode = exchangeFactSetCode;
    }
}

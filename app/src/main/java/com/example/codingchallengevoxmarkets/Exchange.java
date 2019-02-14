package com.example.codingchallengevoxmarkets;

public class Exchange {

    private String exchangeName;
    private String exchangeFactSetCode;

    public Exchange(String exchangeName, String exchangeFactSetCode) {
        this.exchangeName = exchangeName;
        this.exchangeFactSetCode = exchangeFactSetCode;
    }

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

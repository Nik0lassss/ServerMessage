package com.nikola.chk.message_service.entity;

/**
 * Created by Nikola on 9/11/2015.
 */
import java.util.HashSet;
import java.util.Set;

public class Stock implements java.io.Serializable {

    private Integer stockId;
    private String stockCode;
    private String stockName;
    private Set<StockDailyRecord> stockDailyRecords =
            new HashSet<StockDailyRecord>(0);

    public Stock(String stockCode, Integer stockId, Set<StockDailyRecord> stockDailyRecords, String stockName) {
        this.stockCode = stockCode;
        this.stockId = stockId;
        this.stockDailyRecords = stockDailyRecords;
        this.stockName = stockName;
    }

    public Stock() {
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Set<StockDailyRecord> getStockDailyRecords() {
        return stockDailyRecords;
    }

    public void setStockDailyRecords(Set<StockDailyRecord> stockDailyRecords) {
        this.stockDailyRecords = stockDailyRecords;
    }

    //getter, setter and constructor
}
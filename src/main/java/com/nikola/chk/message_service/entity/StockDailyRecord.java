package com.nikola.chk.message_service.entity;

/**
 * Created by Nikola on 9/11/2015.
 */
import java.util.Date;
import java.util.Set;

public class StockDailyRecord implements java.io.Serializable {

    private Integer recordId;
    private Stock stock;
    private Float priceOpen;
    private Float priceClose;
    private Float priceChange;
    private Long volume;
    private Date date;

    public StockDailyRecord(Integer recordId, Stock stock, Float priceOpen, Float priceClose, Float priceChange, Long volume, Date date) {
        this.recordId = recordId;
        this.stock = stock;
        this.priceOpen = priceOpen;
        this.priceClose = priceClose;
        this.priceChange = priceChange;
        this.volume = volume;
        this.date = date;
    }

    public StockDailyRecord() {
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Float getPriceOpen() {
        return priceOpen;
    }

    public void setPriceOpen(Float priceOpen) {
        this.priceOpen = priceOpen;
    }

    public Float getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(Float priceClose) {
        this.priceClose = priceClose;
    }

    public Float getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Float priceChange) {
        this.priceChange = priceChange;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //getter, setter and constructor
}

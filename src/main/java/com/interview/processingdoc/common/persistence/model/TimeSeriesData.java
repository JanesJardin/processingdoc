package com.interview.processingdoc.common.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author jing.c
 * @Date: 18-4-14
 */
@TableName("time_series_data")
public class TimeSeriesData extends Model<TimeSeriesData> {

    /*
     * 主键id
    */
    @TableId(value="item_id",type= IdType.AUTO)
    private Integer itemId;

    /**
     * stock code
     */
    @TableField("stock_code")
    private String stockCode;

    /**
     * trading date
     */
    @TableField("trading_date")
    private Date tradingDate;

    /**
     * item value
     */
    @TableField("item_value")
    private Double itemValue;

    public Integer getItemId() { return itemId; }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) { this.stockCode = stockCode; }

    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public Double getItemValue() {
        return itemValue;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", stockCode=").append(stockCode);
        sb.append(", tradingDate=").append(tradingDate);
        sb.append(", itemValue=").append(itemValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    protected Serializable pkVal() {
        return itemId;
    }
}

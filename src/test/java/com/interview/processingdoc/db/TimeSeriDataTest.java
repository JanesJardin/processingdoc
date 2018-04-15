package com.interview.processingdoc.db;

import com.interview.processingdoc.ProcDocApplicationTests;
import com.interview.processingdoc.common.persistence.dao.TimeSeriesDataMapper;
import com.interview.processingdoc.common.persistence.model.TimeSeriesData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description
 * @Author jing.c
 * @Date: 18-4-15
 */
public class TimeSeriDataTest extends ProcDocApplicationTests {
    @Autowired
    TimeSeriesDataMapper mapper;

    DecimalFormat decimalFormat=new DecimalFormat(".00");
    @Test
    public void insertTest() {
        TimeSeriesData s = new TimeSeriesData();
        s.setTradingDate(new Date());
        s.setItemValue(Double.parseDouble(decimalFormat.format(0.01)));
        s.setStockCode("000010");
        Integer r = mapper.insert(s);
        assertEquals(r, new Integer(1));
        System.out.println(s.toString());
    }

    @Test
    public void selTest() {
        TimeSeriesData s = mapper.selectById(1);
        System.out.println(s.toString());
    }

    @Test
    public void updateTest() throws ParseException {
        TimeSeriesData s = mapper.selectById(1);
        s.setItemValue(Double.parseDouble(decimalFormat.format(0.01)));
        assertEquals(mapper.updateById(s), new Integer(1));

    }

    @Test
    public void delTest() {
        TimeSeriesData s = mapper.selectById(1);
        assertEquals(mapper.deleteById(s), new Integer(1));
    }
}

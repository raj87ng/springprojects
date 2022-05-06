package com.rajat.springbatch.config;

import com.rajat.springbatch.entity.Sales;
import org.springframework.batch.item.ItemProcessor;

public class SalesProcessor implements ItemProcessor<Sales,Sales> {

    /*@Override
    public Sales process(Sales sales) throws Exception {
        if(customer.getCountry().equals("United States")) {
            return customer;
        }else{
            return null;
        }
    }*/

    @Override
    public Sales process(Sales sales) throws Exception {

            return sales;

    }
}

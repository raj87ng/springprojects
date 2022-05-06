package com.rajat.springbatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "SALES_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales implements Serializable {

    private static final long serialVersionUID = -250461513796092774L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SALE_ID",unique = true,nullable = false)
    private Long sale_id;
    @Column(name="REGION")
    private  String region;
    @Column(name="COUNTRY")
    private String country;
    @Column(name="ITEM_TYPE")
    private String item_type;
    @Column(name="SALES_CHANNEL")
    private String sales_channel;
    @Column(name="ORDER_PRIORITY")
    private String order_priority;
    @Column(name="ORDER_DATE")
    private String order_date;
    @Column(name="ORDER_ID")
    private Long order_id;
    @Column(name="SHIP_DATE")
    private String ship_date;
    @Column(name="UNITS_SOLD")
    private Integer units_sold;
    @Column(name="UNIT_PRICE")
    private BigDecimal unit_price;
    @Column(name="UNIT_COST")
    private BigDecimal unit_cost;
    @Column(name="TOTAL_REVENUE")
    private BigDecimal total_revenue;
    @Column(name="TOTAL_COST")
    private BigDecimal total_cost;
    @Column(name="TOTAL_PROFIT")
    private BigDecimal total_profit;


}

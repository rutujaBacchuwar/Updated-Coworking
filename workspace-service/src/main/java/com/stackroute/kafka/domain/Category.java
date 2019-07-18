package com.stackroute.kafka.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Arrays;

@Data
public class Category {

    @Id
    int categoryId;
    String categoryName;
    // BigDecimal price;
    String[] additionalAmenities;
    double hourlyPrice;
    double monthlyPrice;
    double dailyPrice;
    int capacity;
    String status="Available";


    private Dimension dimension;   

}

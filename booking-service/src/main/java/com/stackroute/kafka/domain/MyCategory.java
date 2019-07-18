package com.stackroute.kafka.domain;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class MyCategory {


    int categoryId;
    String categoryName;
    double price;
    private MyLocation myLocation;
    private MySpace mySpace;

    public MyCategory() {
    }

    public MyCategory(int categoryId, String categoryName, double price) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "MyCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", myLocation=" + myLocation +
                ", mySpace=" + mySpace +
                '}';
    }
}

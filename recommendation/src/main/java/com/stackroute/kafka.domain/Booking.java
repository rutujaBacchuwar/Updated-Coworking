package com.stackroute.kafka.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;



@Data
public class Booking {

    @Id
    private int bookingId;
    private boolean status;
    private MyUser myUser;

    public Booking() {
    }

    public Booking(int bookingId, boolean status, MyUser myUser) {
        this.bookingId = bookingId;
        this.status = status;

        this.myUser = myUser;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", status=" + status +
                ", myUser=" + myUser +
                '}';
    }
}


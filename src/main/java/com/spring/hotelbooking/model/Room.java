package com.spring.hotelbooking.model;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    private BigDecimal roomPrice;
    private Boolean isBooked = false;
    @Lob
    private Blob photo;

    @OneToMany(mappedBy="room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookedRoom> bookings;

    public Room(){
        this.bookings = new ArrayList<>();
    }
    public void addBooking(BookedRoom bookedRoom){
        if(this.bookings == null){
            this.bookings = new ArrayList<>();
        }
        bookings.add(bookedRoom);
        bookedRoom.setRoom(this);
        this.isBooked = true;
        String bookingCode = RandomStringUtils.randomNumeric(10);
        bookedRoom.setBookingConfirmationCode(bookingCode);

    }

}

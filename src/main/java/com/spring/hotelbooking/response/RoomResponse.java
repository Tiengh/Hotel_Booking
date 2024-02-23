package com.spring.hotelbooking.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private String roomType;
    private BigDecimal roomPrice;
    private Boolean isBooked;
    private Blob photo;
    private List<BookedRoomResponse> bookings;


    public RoomResponse(Long id, String roomType, BigDecimal roomPrice) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public RoomResponse(Long id, String roomType, BigDecimal roomPrice,
                        boolean isBooked, byte[] photoByte, List<BookedRoomResponse> bookings) throws SQLException {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.photo = photoByte != null ? new SerialBlob(photoByte) : null;
        this.bookings = bookings;
    }

}

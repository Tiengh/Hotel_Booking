package com.spring.hotelbooking.controller;

import com.spring.hotelbooking.model.Room;
import com.spring.hotelbooking.response.RoomResponse;
import com.spring.hotelbooking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @PostMapping("/add/new-room")
    public ResponseEntity<RoomResponse> addRoom(@RequestParam("photo") MultipartFile photo,
                                                @RequestParam("roomType") String roomType,
                                                @RequestParam("roomPrice") BigDecimal roomPrice) throws SQLException, IOException {

        Room saveRoom = roomService.addNewRoom(photo, roomType, roomPrice);
        RoomResponse response = new RoomResponse(saveRoom.getId(), saveRoom.getRoomType(), saveRoom.getRoomPrice());
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        boolean check = roomService.deleteRoomById(id);
        if(check){
            return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Room not exist", HttpStatus.OK);
        }
    }
}

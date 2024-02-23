package com.spring.hotelbooking.service.Impl;

import com.spring.hotelbooking.model.Room;
import com.spring.hotelbooking.repository.RoomRepository;
import com.spring.hotelbooking.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    public Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if(!photo.isEmpty()) {
            byte[] photoByte = photo.getBytes();
            Blob photoBlob = new SerialBlob(photoByte);
            room.setPhoto(photoBlob);
        }
        Room cs =  roomRepository.save(room);
        return cs;
    }

    @Transactional
    public boolean deleteRoomById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if(roomOptional.isPresent()) {
            roomRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

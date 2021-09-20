package task.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import task.entity.Room;
import task.exeption.OperationForbidden;
import task.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RequestService requestService;
    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(Integer id, HttpServletRequest request) throws IOException, GeoIp2Exception, OperationForbidden {
        Room room = roomRepository.findById(id).get();
        if (!matchClientCountry(request, room)) {
            throw new OperationForbidden("Your country doesn't match with room's country");
        }
        return room;
    }

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room changeBulbState(Integer id) {
        Room room = roomRepository.findById(id).get();
        roomRepository.update(room.changeState(), room.getId());
        return room;
    }

    @Override
    public void deleteRoomById(Integer id) {
        roomRepository.deleteById(id);
    }

    @Override
    public boolean matchClientCountry(HttpServletRequest request, Room room) throws IOException, GeoIp2Exception {
        String clientCountry = "Belarus";
              //  requestService.getClientCountry(requestService.getClientIp(request));
        return room.getCountry().equals(clientCountry);
    }
}

package task.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import task.entity.Room;
import task.exeption.OperationForbidden;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface RoomService {
    List<Room> getRooms();
    Room getRoom(Integer id, HttpServletRequest request) throws IOException, GeoIp2Exception, OperationForbidden;
    boolean matchClientCountry(HttpServletRequest request, Room room) throws IOException, GeoIp2Exception;
    Room changeBulbState(Integer id);
    Room addRoom(Room room);
    void deleteRoomById(Integer id);
}

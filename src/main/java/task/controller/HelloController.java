package task.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import task.entity.Room;
import task.exeption.OperationForbidden;
import task.service.RoomService;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/rooms")
public class HelloController {
    @Autowired
    RoomService roomService;

    @GetMapping()
    public String getRooms(Model model) {
        model.addAttribute("rooms", roomService.getRooms());
        return "allRooms";
    }

    @GetMapping("/{id}")
    public String getRoom(@PathVariable("id") Integer id, Model model, HttpServletRequest request) throws IOException, GeoIp2Exception, OperationForbidden {
        model.addAttribute("room", roomService.getRoom(id, request));
        return "room";
    }

    @GetMapping("/create")
    public String createRoom(Model model) {
        model.addAttribute("room", new Room());
        return "createRoom";
    }

    @PostMapping()
    public String getCreatedRoom(Room room) {
        roomService.addRoom(room);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoomById(id);
        return "redirect:/rooms";
    }

    @MessageMapping("/roomBulbSwitch")
    @SendTo("/roomBulbSwitch")
    public Room changeRoomBulbState(@Payload Integer id){
        return roomService.changeBulbState(id);
    }
}
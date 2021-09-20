package task.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import task.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Modifying
    @Query("UPDATE Room r SET r.state = ?1 WHERE r.id = ?2")
    void update(boolean state, Integer id);
}

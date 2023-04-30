package com.inai.arna.repository;

import com.inai.arna.dto.response.RoomView;
import com.inai.arna.model.category.Room;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @EntityGraph(value = "Room.itemCategories")
    List<RoomView> findAllBy();
}

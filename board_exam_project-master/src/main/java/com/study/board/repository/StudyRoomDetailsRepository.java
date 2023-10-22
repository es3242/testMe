package com.study.board.repository;

import com.study.board.entity.StudyRoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRoomDetailsRepository extends JpaRepository<StudyRoomDetails, Integer> {


}

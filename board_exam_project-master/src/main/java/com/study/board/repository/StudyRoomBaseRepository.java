package com.study.board.repository;

import com.study.board.entity.StudyRoomBase;
import com.study.board.entity.StudyRoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRoomBaseRepository extends JpaRepository<StudyRoomBase, String> {

    void deleteByStudyRoomName(String studyRoomName);
}

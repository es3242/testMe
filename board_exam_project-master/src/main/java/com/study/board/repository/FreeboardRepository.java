package com.study.board.repository;

import com.study.board.entity.Freeboard;
import com.study.board.entity.Pdf;
import com.study.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeboardRepository extends JpaRepository<Freeboard, Integer> {

    List<Freeboard> findAllByUserId(Long userId); //마이페이지에서 유저가 작성한 자유게시판 글들을 찾을 때 사용하는 함수

}

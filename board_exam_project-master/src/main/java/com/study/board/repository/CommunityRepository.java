package com.study.board.repository;

import com.study.board.entity.Community;
import com.study.board.entity.Freeboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findAllByUserId(long userId); //마이페이지에서 유저가 작성한 자유게시판 글들을 찾을 때 사용하는 함수

    List<Community> findByTitleContaining(String keyword); //제목으로 검색할 때 사용하는 함수

    List<Community> findByContentContaining(String keyword);

    @Query("SELECT c FROM Community c WHERE c.isDeleted = true")
    List<Community> findDeletedCommunities();
}
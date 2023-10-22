package com.study.board.repository;

import com.study.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommunityId(Long communityId); //게시글 댓글 생성 순으로 오름차순 정렬
    List<Comment> findByCommunityIdAndIsDeleted(Long communityId, boolean isDeleted);

}


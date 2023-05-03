package com.study.board.repository;

import com.study.board.entity.Pdf;
import com.study.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfRepository extends JpaRepository<Pdf, Integer> {
    List<Pdf> findByUserOrderByCreatedAtDesc(User user);

}

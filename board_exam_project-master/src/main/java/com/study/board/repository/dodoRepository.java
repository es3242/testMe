package com.study.board.repository;

import com.study.board.ga.dodo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface dodoRepository extends JpaRepository<dodo, Integer> {

    Page<dodo> findByDonameContaining(String searchKeyword, Pageable pageable);
}

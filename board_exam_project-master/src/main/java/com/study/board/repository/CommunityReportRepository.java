package com.study.board.repository;

import com.study.board.entity.CommunityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityReportRepository extends JpaRepository<CommunityReport, Long> {
}

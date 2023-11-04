package com.study.board.service;

import com.study.board.entity.CommunityReport;
import com.study.board.repository.CommunityReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommunityReportService {

    @Autowired
    private CommunityReportRepository communityReportRepository;

    public CommunityReport createCommunityReport(CommunityReport communityReport) {
        communityReport.setCreateAt(new Date()); // CreateAt 타임스탬프 설정
        return communityReportRepository.save(communityReport); // 커뮤니티 신고 저장 및 반환
    }

    // 다른 메서드들...
}

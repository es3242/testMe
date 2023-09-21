package com.study.board.service;

import com.study.board.entity.CommentReport;
import com.study.board.repository.CommentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentReportService {

    @Autowired
    private CommentReportRepository commentReportRepository;

    public CommentReport createCommentReport(CommentReport commentReport) {
        commentReport.setCreateAt(LocalDateTime.now()); // CreateAt 타임스탬프 설정
        return commentReportRepository.save(commentReport); // 댓글 신고 저장 및 반환
    }

    // 다른 메서드들...
}


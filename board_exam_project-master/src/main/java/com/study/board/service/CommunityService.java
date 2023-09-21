package com.study.board.service;

import com.study.board.entity.Community;
import com.study.board.entity.Freeboard;
import com.study.board.entity.User;
import com.study.board.repository.CommunityRepository;
import com.study.board.repository.FreeboardRepository;
import com.study.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    // create operation
    public Community createCommunity(Community community) {
        if (community.getCreateAt() == null) {
            community.setCreateAt(LocalDateTime.now());
        }
        community.setUpdateAt(LocalDateTime.now());
        community.setIsDeleted(true);
        return communityRepository.save(community);
    }

    // read operation
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public List<Community> getDeletedCommunities() {
        return communityRepository.findDeletedCommunities();
    }

    public Optional<Community> getCommunityById(long id) {
        return communityRepository.findById(id);
    }

    public Community updateCommunity(long id, Community community) {
        // 업데이트 로직을 여기에 구현
        Community existingCommunity = communityRepository.findById(id).orElse(null);
        if (existingCommunity != null) {
            existingCommunity.setTitle(community.getTitle());
            existingCommunity.setContent(community.getContent());
            existingCommunity.setUpdateAt(LocalDateTime.now()); // 현재 시간 설정
            // 필요한 경우 추가 속성 추가
            return communityRepository.save(existingCommunity);
        }
        return null;
    }



    // delete operation
    /*public void deleteCommunity(long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Freeboard not found with id " + id));
        communityRepository.delete(community);
    }*/

    public List<Community> getContentByUserId(Long userId){ //user의 id를 받아서 user Id로 작성된 글들을 찾아 Freeboard 타입 리스트로 반환
        return communityRepository.findAllByUserId(userId);
    }



    // s operation

    public List<Community> searchCommunities(Long query) { //사용자의 id로 작성된 community를 찾는 코드.
        return communityRepository.findAllByUserId(query); // Modify the query method to match your search criteria
    }

    /*public List<Community> searchByTitleContaining(String keyword) { //제목으로 검색하기
        return communityRepository.findByTitleContaining(keyword);
    }*/
    public List<Community> searchByTitleContaining(String keyword) {
        List<Community> allResults = communityRepository.findByTitleContaining(keyword);
        List<Community> filteredResults = new ArrayList<>();

        for (Community community : allResults) {
            if (community.getIsDeleted()) { // is_deleted가 true인 경우만 추가
                filteredResults.add(community);
            }
        }

        return filteredResults;
    }


    /*public List<Community> searchByContentContaining(String keyword) {
        return communityRepository.findByContentContaining(keyword);
    }*/
    public List<Community> searchByContentContaining(String keyword) {
        List<Community> allResults = communityRepository.findByContentContaining(keyword);
        List<Community> filteredResults = new ArrayList<>();

        for (Community community : allResults) {
            if (community.getIsDeleted()) { // is_deleted가 true인 경우만 추가
                filteredResults.add(community);
            }
        }

        return filteredResults;
    }

    public void incrementCommentNumber(Long communityId) { //댓글 추가시 community 테이블에 comment_number 수 증가시키는 코드
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("Community with ID " + communityId + " not found."));

        community.setCommentNumber(community.getCommentNumber() + 1);
        communityRepository.save(community);
    }

    public void decrementCommentNumber(Long communityId) { //댓글 삭제 시 보유한 댓글 개수가 감소하도록 하는 메서드
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("Community with ID " + communityId + " not found."));

        int currentCommentNumber = community.getCommentNumber();
        if (currentCommentNumber > 0) {
            community.setCommentNumber(currentCommentNumber - 1);
            communityRepository.save(community); // 변경사항 저장
        }
    }

}

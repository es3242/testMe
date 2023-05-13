package com.study.board.service;

import com.study.board.entity.Freeboard;
import com.study.board.repository.FreeboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FreeboardService {

    @Autowired
    private FreeboardRepository freeboardRepository;

    // create operation
    public Freeboard createFreeboard(Freeboard freeboard) {
        if (freeboard.getContentAt() == null) {
            freeboard.setContentAt(LocalDateTime.now());

        }
        freeboard.setUpdatedAt(LocalDateTime.now());
        freeboard.setIsDeleted(1);
        return freeboardRepository.save(freeboard);
    }

    // read operation
    public List<Freeboard> getAllFreeboards() {
        return freeboardRepository.findAll();
    }

    public Optional<Freeboard> getFreeboardById(Integer id) {
        return freeboardRepository.findById(id);
    }

    // update operation
    public Freeboard updateFreeboard(Integer id, Freeboard freeboard) {
        Freeboard existingFreeboard = freeboardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Freeboard not found with id " + id));
        existingFreeboard.setUser(freeboard.getUser());
        existingFreeboard.setTitle(freeboard.getTitle());
        existingFreeboard.setContent(freeboard.getContent());
        existingFreeboard.setContentAt(freeboard.getContentAt());
        existingFreeboard.setUpdatedAt(freeboard.getUpdatedAt());
        existingFreeboard.setIsDeleted(freeboard.getIsDeleted());
        return freeboardRepository.save(existingFreeboard);
    }

    // delete operation
    public void deleteFreeboard(Integer id) {
        Freeboard freeboard = freeboardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Freeboard not found with id " + id));
        freeboardRepository.delete(freeboard);
    }

    public List<Freeboard> getContentByUserId(Long userId){ //user의 id를 받아서 user Id로 작성된 글들을 찾아 Freeboard 타입 리스트로 반환
        return freeboardRepository.findAllByUserId(userId);
    }
}

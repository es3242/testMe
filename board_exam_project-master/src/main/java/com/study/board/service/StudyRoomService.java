package com.study.board.service;

import com.study.board.entity.Freeboard;
import com.study.board.entity.StudyRoomBase;
import com.study.board.entity.StudyRoomDetails;
import com.study.board.repository.StudyRoomBaseRepository;
import com.study.board.repository.StudyRoomDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudyRoomService {

    @Autowired
    private StudyRoomDetailsRepository studyRoomDetailsRepository;

    @Autowired
    private StudyRoomBaseRepository studyRoomBaseRepository;
    // create operation 테이블 두가지를 동시에 생성 하야하기 때문에 고민
    public StudyRoomDetails createStudyRoom(StudyRoomDetails studyRoomDetails) {

        StudyRoomBase studyRoomBase = new StudyRoomBase();
        studyRoomBase.setStudyRoomName(studyRoomDetails.getStudyRoomBase().getStudyRoomName());
        studyRoomBase.setUser(studyRoomDetails.getUser()); // Set the User object in StudyRoomBase

       /* System.out.println(studyRoomDetails.getStudyRoomBase().getStudyRoomName());
        System.out.println(studyRoomBase.getStudyRoomName());
        System.out.println(studyRoomBase.getUser());
*/

        System.out.println("Received studyRoomDetails:");
        System.out.println("Study User: " + studyRoomDetails.getUser());
        System.out.println("Study Room Name: " + studyRoomDetails.getStudyRoomBase().getStudyRoomName());
        System.out.println("User ID: " + studyRoomDetails.getUserId());
        System.out.println("User Class: " + studyRoomDetails.getUserClass());



        studyRoomBaseRepository.save(studyRoomBase);
        return  studyRoomDetailsRepository.save(studyRoomDetails);
    }


    public List<StudyRoomDetails> getAllStudyRoom() {
        return studyRoomDetailsRepository.findAll();
    }

    @Transactional
    public void deleteStudyRoomByStudyRoomName(String studyRoomName) {

        System.out.println("delete studyRoomName : " + studyRoomName);
        studyRoomBaseRepository.deleteByStudyRoomName(studyRoomName);


    }



}

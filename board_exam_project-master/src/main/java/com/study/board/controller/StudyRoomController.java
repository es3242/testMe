package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.entity.StudyRoomDetails;
import com.study.board.service.FreeboardService;
import com.study.board.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudyRoomController {


    @Autowired
    private StudyRoomService StudyRoomService;

    @GetMapping("/StudyRoomList")
    public String StudyRoomList(Model model) {
        model.addAttribute("StudyRoomList", StudyRoomService.getAllStudyRoom());
        return "kStudyRommlist";
    }

    @GetMapping("/StudyRoomAdd")
    public String add(Model model) {
        model.addAttribute("studyRoomDetails", new StudyRoomDetails());
        return "kStudyRoomAdd";
    }


    @PostMapping("/StudyRoomsave")
    public String save(@ModelAttribute("studyRoomDetails") StudyRoomDetails studyRoomDetails) {

        StudyRoomService.createStudyRoom(studyRoomDetails);
        return "redirect:/StudyRoomList";
    }

    @GetMapping("/StudyRoomDelete/{studyRoomName}")
    public String deleteStudyRoom(@PathVariable("studyRoomName") String studyRoomName) {
        StudyRoomService.deleteStudyRoomByStudyRoomName(studyRoomName);
        return "redirect:/StudyRoomList"; // Redirect to the page after successful deletion
    }

/*
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("freeboard", StudyRoomService.getFreeboardById(id));
        return "kStudyRoomAdd";
    }*/
}

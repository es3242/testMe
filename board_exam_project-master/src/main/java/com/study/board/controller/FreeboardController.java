package com.study.board.controller;


import com.study.board.entity.Freeboard;
import com.study.board.service.FreeboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
//@RequestMapping("/freeboard")
@RestController
public class FreeboardController {

    @Autowired
    private FreeboardService freeboardService;

  /*  @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("freeboards", freeboardService.getAllFreeboards());
        return "freeboard/list";
    }*/
  @GetMapping(value = "/list")
  public List<Freeboard> list() {

      return freeboardService.getAllFreeboards();
  }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("freeboard", new Freeboard());
        return "freeboard/form";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("freeboard", freeboardService.getFreeboardById(id));
        return "freeboard/form";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute("freeboard") Freeboard freeboard) {
        //freeboardService.updateFreeboard(freeboard.getContentId(), freeboard);
        freeboardService.createFreeboard(freeboard);
        return "redirect:/list";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        freeboardService.deleteFreeboard(id);
        return "redirect:/list";
    }
}

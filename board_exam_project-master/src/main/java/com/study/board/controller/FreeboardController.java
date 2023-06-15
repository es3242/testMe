package com.study.board.controller;


import com.study.board.entity.Freeboard;
import com.study.board.service.FreeboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//@RequestMapping("/freeboard")
/*@RestController*/
@Controller
public class FreeboardController {
    @Autowired
    private FreeboardService freeboardService;
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("freeboards", freeboardService.getAllFreeboards());
        return "klist";
    }


    /*
    @GetMapping(value = "/list")
    public List<Freeboard> list() {
      return freeboardService.getAllFreeboards();
    }
    }*/

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE) // JSON
    public ResponseEntity<List<Freeboard>> list() {
      List<Freeboard> freeboards = freeboardService.getAllFreeboards();
      return ResponseEntity.ok().body(freeboards);
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("freeboard", new Freeboard());
        return "freeboard/form";
    } //restController을 사용함으로써 html 반환이 불가능해짐.


    @GetMapping("/addJSON") // JSON
    public ResponseEntity<String> add() {
        return ResponseEntity.ok("Freeboard form is available.");
    } //따라서 json을 반환하여 정상적으로 자유게시판에 들어갔음을 알 수 있게 함



    @GetMapping("/edit/{id}") // JSON 아님
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("freeboard", freeboardService.getFreeboardById(id));
        return "kform";
    }

    @GetMapping(value = "/edit2/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // JSON
    public ResponseEntity<Optional<Freeboard>> edit(@PathVariable("id") Integer id) {
        Optional<Freeboard> freeboard = freeboardService.getFreeboardById(id);
        return ResponseEntity.ok().body(freeboard);
    } // JSON 형태 전환 예시로 만들어놓긴 했으나 프론트 단에서 따로 구현해놓았기 때문에 사용하지는 않음.


    @PostMapping("/save") // JSON 아님
    public String save(@ModelAttribute("freeboard") Freeboard freeboard) {
        //freeboardService.updateFreeboard(freeboard.getContentId(), freeboard);
        System.out.println("글 써짐");
        freeboardService.createFreeboard(freeboard);

        return "redirect:/list";
    }


    @GetMapping("/delete/{id}") // JSON 아님
    public String delete(@PathVariable("id") Integer id) {
        freeboardService.deleteFreeboard(id);
        return "redirect:/list";
    }

}

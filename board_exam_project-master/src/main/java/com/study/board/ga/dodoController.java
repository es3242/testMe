package com.study.board.ga;
import org.springframework.stereotype.Controller;

@Controller
public class dodoController {

/*

    @Autowired
    private dodoService dodoService;


    @GetMapping("/dodo/write") //localhost:8090/board/write
    public String dodoWriteForm() {
        return "dodowrite";
    }


    @PostMapping("/dodo/writepro")
    public String dodoWritePro(dodo dodo, Model model) throws Exception{
        dodoService.write(dodo);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/dodo/list");
        return "message";
    }
    @GetMapping("/dodo/list")
    public String dodoList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "doid", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<dodo> list = null;

        if(searchKeyword == null) {
            list = dodoService.dodoList(pageable);
        }else {
            list = dodoService.dodoSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "dodolist";
    }

*/


}

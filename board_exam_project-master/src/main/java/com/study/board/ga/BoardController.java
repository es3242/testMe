package com.study.board.ga;

import org.springframework.stereotype.Controller;

@Controller
public class BoardController {


    /*@Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String boardmain() {
        System.out.println("나의 이름은 입니다");
        return "mian";
    }

    @GetMapping("/board/write") //localhost:8090/board/write
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{
        boardService.write(board, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        }else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardmodify";

    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception{
        //System.out.println("This is the start of 123123");
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        //System.out.println("This is a start");

        if (file != null) {
            //System.out.println("1 " + file.getOriginalFilename() + "is");
            boardService.write(boardTemp, file);
        } else {
            boardService.write(boardTemp, null);
        }

        //System.out.println("2 " + board.getFilename() + "");
        //System.out.println("3 " + board.getFilepath() + "");

        return "redirect:/board/list";
    }


    @GetMapping("/board/delete")
    public String boardDelete (Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }
*/

}

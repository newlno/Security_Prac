package com.example.Security_Prac.controller;


import com.example.Security_Prac.dto.request.NoteRequestDto;
import com.example.Security_Prac.model.Notice;
import com.example.Security_Prac.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String getNotice(Model model) {
        List<Notice> notices = noticeService.findAll();
        model.addAttribute("notices", notices);
        return "notice/index";
    }

    @PostMapping("/notice")
    public String postNotice(@RequestBody NoteRequestDto requestDto) {
        noticeService.saveNotice(requestDto.getTitle(), requestDto.getContent());
        return "redirect:notice";
    }

    @DeleteMapping("/notice")
    public String deleteNotice(@RequestParam Long id) {
        noticeService.deleteNotice(id);
        return "redirect:notice";
    }
}

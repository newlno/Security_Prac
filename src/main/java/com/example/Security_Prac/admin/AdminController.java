package com.example.Security_Prac.admin;

import com.example.Security_Prac.dto.response.ResponseDto;
import com.example.Security_Prac.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AdminController {

    private final NoteService noteService;

    /**
     * 어드민인 경우 노트 조회
     *
     * @return admin/index.html
     */
    @GetMapping("/admin")
    public String getNoteForAdmin(Authentication authentication, Model model) {
        ResponseDto<?> notes = noteService.getNote(authentication, model);
        model.addAttribute("notes", notes);
        return "admin/index";
    }
}
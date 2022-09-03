package com.example.Security_Prac.controller;

import com.example.Security_Prac.dto.request.NoteRequestDto;
import com.example.Security_Prac.dto.response.ResponseDto;
import com.example.Security_Prac.model.User;
import com.example.Security_Prac.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/note")
    public String getNote(Authentication authentication, Model model) {
        ResponseDto<?> notes = noteService.getNote(authentication, model);
        // note/index.html 에서 notes 사용가능
        model.addAttribute("notes", notes);
        // note/index.html 제공
        return "note/index";
    }

    @PostMapping("/note")
    public String saveNote(Authentication authentication, @RequestBody NoteRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        noteService.saveNote(user, requestDto.getTitle(), requestDto.getContent());
        return "redirect:note";
    }

    @DeleteMapping("/note")
    public String deleteNote(Authentication authentication, @RequestParam Long id) {
        User user = (User) authentication.getPrincipal();
        noteService.deleteNote(user, id);
        return "redirect:note";
    }
}

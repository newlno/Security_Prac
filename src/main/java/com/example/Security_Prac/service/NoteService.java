package com.example.Security_Prac.service;

import com.example.Security_Prac.dto.response.ResponseDto;
import com.example.Security_Prac.exception.AllException;
import com.example.Security_Prac.exception.UserNotFoundException;
import com.example.Security_Prac.model.Note;
import com.example.Security_Prac.model.User;
import com.example.Security_Prac.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;


@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;


    @Transactional(readOnly = true)
    public ResponseDto<?> getNote(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return ResponseDto.fail(AllException.CANNOT_FIND_USER);
        }
        if (user.isAdmin()) {
            return (ResponseDto<?>) noteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        return (ResponseDto<?>) noteRepository.findByUserOrderByIdDesc(user);
    }

    @Transactional
    public Note saveNote(User user, String title, String content) {
        if (user == null) {
            throw new UserNotFoundException();
        }
        Note note = Note.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        return noteRepository.save(note);
    }

    @Transactional
    public ResponseDto<?> deleteNote(User user, Long noteId) {
        if (user == null) {
            return ResponseDto.fail(AllException.CANNOT_FIND_USER);
        }
        Note note = noteRepository.findByIdAndUser(noteId, user);
        if (note != null) {
            noteRepository.delete(note);
        }
        return ResponseDto.success(note.getId());
    }
}

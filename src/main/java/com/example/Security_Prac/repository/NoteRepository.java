package com.example.Security_Prac.repository;

import com.example.Security_Prac.model.Note;
import com.example.Security_Prac.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserOrderByIdDesc(User user);

    Note findByIdAndUser(Long id, User user);
}

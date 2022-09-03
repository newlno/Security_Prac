package com.example.Security_Prac.repository;

import com.example.Security_Prac.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}

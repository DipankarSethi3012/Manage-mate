package com.dipankar.Project.Management.System.repo;

import com.dipankar.Project.Management.System.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {
}

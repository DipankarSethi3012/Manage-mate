package com.dipankar.Project.Management.System.repo;

import com.dipankar.Project.Management.System.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepo extends JpaRepository<Invitation, Long> {
    Invitation findByToken(String token);
    Invitation findByEmail(String userEmail);

}

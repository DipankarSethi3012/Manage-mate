package com.dipankar.Project.Management.System.repo;

import com.dipankar.Project.Management.System.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByIssueId(long issueId);
}

package com.dipankar.Project.Management.System.repo;

import com.dipankar.Project.Management.System.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {
    public List<Issue> findByProjectId(Long projectId);
}

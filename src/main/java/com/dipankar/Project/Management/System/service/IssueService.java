package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.Issue;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.request.IssueRequest;

import java.util.*;

public interface IssueService {
     Optional<Issue> getIssueById(Long issueId) throws Exception;
     List<Issue> getIssueByProjectId(Long projectId) throws Exception;
     Issue createIssue(IssueRequest issueRequest, User user) throws Exception;
     Optional<Issue> updateIssue(Long issueId, IssueRequest updateIssue, Long userId) throws Exception;
     void deleteIssue(Long issueId, Long userId) throws Exception;
     Issue addUserToIssue(Long issueId, Long userId) throws Exception;
     Issue updateStatus(Long issueId, String status) throws Exception;


}

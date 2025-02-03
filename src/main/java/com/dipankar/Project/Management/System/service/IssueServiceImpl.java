package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.Issue;
import com.dipankar.Project.Management.System.entity.Project;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.repo.IssueRepo;
import com.dipankar.Project.Management.System.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService{

    @Autowired
    private IssueRepo issueRepo;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Issue> getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepo.findById(issueId);
        if(issue.isPresent()){
            return  issue;
        }
        throw new Exception("issue not found");
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {

        return issueRepo.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
        Project project = projectService.getProjectById(issueRequest.getProjectId());
        Issue issue = new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        issue.setPriority(issueRequest.getPriority());
        issue.setDueDate(issueRequest.getDueDate());
        issue.setProjectID(issueRequest.getProjectId());

        issue.setProject(project);
        return issueRepo.save(issue);
    }

    @Override
    public Optional<Issue> updateIssue(Long issueId, IssueRequest updateIssue, Long userId) throws Exception {
        return Optional.empty();
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
       getIssueById(issueId);
        issueRepo.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Optional<Issue> getted = getIssueById(issueId);
        Issue issue = getted.get();
        issue.setAssignee(user);
        return issueRepo.save(issue);

    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Optional<Issue> getted = getIssueById(issueId);
        Issue issue = getted.get();
        issue.setStatus(status);
        return issueRepo.save(issue);
    }
}

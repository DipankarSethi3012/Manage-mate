package com.dipankar.Project.Management.System.controllers;

import com.dipankar.Project.Management.System.DTO.IssueDto;
import com.dipankar.Project.Management.System.entity.Issue;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.repo.IssueRepo;
import com.dipankar.Project.Management.System.request.IssueRequest;
import com.dipankar.Project.Management.System.response.AuthResponse;
import com.dipankar.Project.Management.System.response.MessageResponse;
import com.dipankar.Project.Management.System.service.IssueService;
import com.dipankar.Project.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @Autowired
    private IssueRepo issueRepo;

    @GetMapping("/{issueId}")
    public ResponseEntity<?> getIssueById(@PathVariable Long issueId) throws Exception{
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> createIssue(@PathVariable Long projectId) throws Exception{
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping("")
    public ResponseEntity<?> createIssue(@RequestBody IssueRequest issue, @RequestHeader("Authorization") String jwt) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(jwt);
        User user = userService.findUserById(tokenUser.getId());

        Issue createdIssue = issueService.createIssue(issue, tokenUser);
        IssueDto issueDto = new IssueDto();
        issueDto.setDescription(createdIssue.getDescription());
        issueDto.setDueDate(createdIssue.getDueDate());
        issueDto.setId(createdIssue.getId());
        issueDto.setPriority(createdIssue.getPriority());
        issueDto.setProject(createdIssue.getProject());
        issueDto.setProjectId(createdIssue.getProjectID());
        issueDto.setStatus(createdIssue.getStatus());
        issueDto.setTitle(createdIssue.getTitle());
        issueDto.setTags(createdIssue.getTags());
        issueDto.setAssignee(createdIssue.getAssignee());
        return ResponseEntity.ok(issueDto);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<?> deleteIssue(@PathVariable Long issueId, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        issueService.deleteIssue(issueId, user.getId());
        MessageResponse authResponse = new MessageResponse();
        authResponse.setMessage("Issue Deleted");
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllIssues(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(issueRepo.findAll(), HttpStatus.OK);
    }



    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<?> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);

    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<?> updateIssueStatus(@PathVariable Long issueId, @PathVariable String status) throws Exception{
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }
}

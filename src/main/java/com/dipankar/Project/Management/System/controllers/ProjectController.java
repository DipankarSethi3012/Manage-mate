package com.dipankar.Project.Management.System.controllers;

import com.dipankar.Project.Management.System.entity.Chat;
import com.dipankar.Project.Management.System.entity.Invitation;
import com.dipankar.Project.Management.System.entity.Project;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.request.InviteRequest;
import com.dipankar.Project.Management.System.response.MessageResponse;
import com.dipankar.Project.Management.System.service.InvitationService;
import com.dipankar.Project.Management.System.service.ProjectService;
import com.dipankar.Project.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @GetMapping("")
    public ResponseEntity<List<Project>>getProjects(@RequestParam(required = false) String category,
                                                    @RequestParam(required = false) String tag,
                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user, category, tag);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectsById(@PathVariable Long projectId, @RequestHeader("Authorization") String jwt)throws Exception {
            User user = userService.findUserProfileByJwt(jwt);
            Project project = projectService.getProjectById(projectId);
            return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createProject(@RequestHeader("Authorization") String jwt, @RequestBody Project project) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @RequestHeader("Authorization") String jwt, @RequestBody Project project) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project updatedProject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        MessageResponse messageResponse = new MessageResponse("Project Delete Successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProject(@RequestParam String keyword, @RequestHeader("Authorization") String jwt) throws Exception {
         User user = userService.findUserProfileByJwt(jwt);
         List<Project> projects = projectService.searchProjects(keyword, user);
         return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/{projectId}/chat")
    public ResponseEntity<?> getChatProjectsById(@PathVariable Long projectId, @RequestHeader("Authorization") String jwt)throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<?> inviteProject(@RequestBody InviteRequest inviteRequest, @RequestHeader("Authorization") String jwt, @RequestBody Project project) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
//        Project createdProject = projectService.createProject(project, user);
        invitationService.sendInvitation(inviteRequest.getEmail(), inviteRequest.getProjectId());
        MessageResponse messageResponse = new MessageResponse("User Invitation send");

        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<?> acceptInvitation(@RequestParam String token, @RequestHeader("Authorization") String jwt, @RequestBody Project project) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }

}

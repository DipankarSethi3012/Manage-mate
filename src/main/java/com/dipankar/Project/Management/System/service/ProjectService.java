package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.Chat;
import com.dipankar.Project.Management.System.entity.Project;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProjectService {


    Project createProject(Project project, User user) throws Exception;

    List<Project> getProjectByTeam(User user, String category, String tag) throws Exception;

    Project getProjectById(Long projectId) throws Exception;

    void deleteProject(Long projectId, Long userId) throws Exception;

    Project updateProject(Project updateProject, Long id) throws Exception;

    void addUserToProject(Long projectId, Long userId) throws Exception;
    void removeUserFromProject(Long projectId, Long userId) throws Exception;

    Chat getChatByProjectId(Long projectId) throws Exception;

    List<Project> searchProjects(String keyword, User user) throws Exception;



}

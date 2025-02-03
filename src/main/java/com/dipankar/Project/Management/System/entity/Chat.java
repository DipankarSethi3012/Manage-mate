package com.dipankar.Project.Management.System.entity;

import com.dipankar.Project.Management.System.repo.UserRepo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String name;

        @OneToOne
        private Project project;

        @JsonIgnore
        @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Message> messages = new ArrayList<>();

        @ManyToMany
        private List<User> users = new ArrayList<>();

      public Chat(){}

    public Chat(Long id, String name, Project project, List<Message> messages, List<User> users) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.messages = messages;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", messages=" + messages +
                ", users=" + users +
                '}';
    }
}

package com.dipankar.Project.Management.System.controllers;

import com.dipankar.Project.Management.System.entity.Chat;
import com.dipankar.Project.Management.System.entity.Message;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.request.CreateMessageRequest;
import com.dipankar.Project.Management.System.service.MessageService;
import com.dipankar.Project.Management.System.service.ProjectService;
import com.dipankar.Project.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody CreateMessageRequest createMessageRequest) throws
        Exception{
        User user = userService.findUserById(createMessageRequest.getSenderId());
//        if(user == null) throw new Exception("User Not found with id: " + createMessageRequest.getSenderId());
        Chat chats = projectService.getProjectById(createMessageRequest.getProjectId()).getChat();
        if(chats == null) throw new Exception("Chats not found");
        Message sentMessage = messageService.sendMessage(createMessageRequest.getSenderId(), createMessageRequest.getProjectId(), createMessageRequest.getContent());
        return ResponseEntity.ok(sentMessage);
        }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<?> getMessageByChatId(@PathVariable Long projectId) throws Exception{
        List<Message> messageList = messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(messageList);
    }
}

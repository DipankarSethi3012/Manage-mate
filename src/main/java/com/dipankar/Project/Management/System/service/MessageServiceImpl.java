package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.Chat;
import com.dipankar.Project.Management.System.entity.Message;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.repo.MessageRepo;
import com.dipankar.Project.Management.System.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepo userRepo;


    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        Optional<User> senderOptional = userRepo.findById(senderId);
        if(senderOptional.isEmpty()){
            throw new Exception("USer not present with the sender id: " + senderId);
        }

        User sender = senderOptional.get();

        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setChat(chat);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());
        message.setSender(sender);

        Message savedMessage = messageRepo.save(message);
        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Message> findByChatIdOrderByCreatedAtAsc = messageRepo.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return findByChatIdOrderByCreatedAtAsc;
    }
}

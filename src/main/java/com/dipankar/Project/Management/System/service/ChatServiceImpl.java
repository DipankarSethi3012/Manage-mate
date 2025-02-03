package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.Chat;
import com.dipankar.Project.Management.System.repo.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepo chatRepo;
    @Override
    public Chat createChat(Chat chat) {
        return chatRepo.save(chat);
    }
}

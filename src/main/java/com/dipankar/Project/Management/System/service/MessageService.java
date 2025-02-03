package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Long senderId, Long chatId, String content) throws Exception;

    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}

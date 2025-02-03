package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long issueId, Long userId, String comment) throws Exception;
    void deleteComment(Long commentId, Long userId) throws Exception;
    List<Comment> findCommentByIssue(Long issuedId);

}

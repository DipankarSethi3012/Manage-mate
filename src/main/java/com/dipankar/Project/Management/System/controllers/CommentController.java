package com.dipankar.Project.Management.System.controllers;

import com.dipankar.Project.Management.System.entity.Comment;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.request.CreateCommentRequest;
import com.dipankar.Project.Management.System.response.MessageResponse;
import com.dipankar.Project.Management.System.service.CommentService;
import com.dipankar.Project.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> createComment(
            @RequestBody CreateCommentRequest createCommentRequest,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Comment createComment = commentService.createComment(createCommentRequest.getIssueId(), user.getId(), createCommentRequest.getContent());
        return new ResponseEntity<>(createComment, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("comment deleted successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }

    @GetMapping("/{issueId}")
    public ResponseEntity<?> getCommentsByIssueId(@PathVariable Long issueId) {
        List<Comment> comments = commentService.findCommentByIssue(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}

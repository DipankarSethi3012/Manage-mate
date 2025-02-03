package com.dipankar.Project.Management.System.request;

public class CreateCommentRequest {

    private Long issueId;
    private String content;

    public CreateCommentRequest(){};

    public CreateCommentRequest(Long issueId, String content) {
        this.issueId = issueId;
        this.content = content;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

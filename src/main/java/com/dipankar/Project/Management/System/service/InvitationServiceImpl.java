package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.Invitation;
import com.dipankar.Project.Management.System.repo.InvitationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService{

    @Autowired
    private InvitationRepo invitationRepo;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws Exception{
        String invitationToken = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepo.save(invitation);

        String invitationLink = "http://localhost:5173/accept_invitation?token="+invitation;
        emailService.sendEmailWithToken(email, invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation =invitationRepo.findByToken(token);
        if(invitation == null) {
            throw new Exception("Invalid Invitation token");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        Invitation invitation = invitationRepo.findByEmail(userEmail);
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitationRepo.findByToken(token);
        invitationRepo.delete(invitation);

    }
}

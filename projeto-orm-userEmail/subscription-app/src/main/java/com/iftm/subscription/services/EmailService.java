package com.iftm.subscription.services;

import com.iftm.subscription.controllers.EmailController;
import com.iftm.subscription.data.vo.EmailVO;
import com.iftm.subscription.mapper.DozerMapper;
import com.iftm.subscription.models.Email;

import com.iftm.subscription.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;
    public List<EmailVO> findAll() {
        var emails = DozerMapper.parseListObject(repository.findAll(), EmailVO.class);
        emails.stream().forEach(email -> {
            email.add(linkTo(methodOn(EmailController.class).findById(email.getId()))
                    .withSelfRel());
        });
        return emails;
    }
    public EmailVO findById(Long id) {
        var email = DozerMapper.parseObject(repository.findById(id).get(), EmailVO.class);
        email.add(linkTo(methodOn(EmailController.class).findById(email.getId()))
                .withSelfRel());
        return email;
    }

    public List<EmailVO> findAllByRecipient (String emailTo){
        var emails = DozerMapper.parseListObject(repository.findAllByRecipient(emailTo), EmailVO.class);
        emails.stream().forEach(email -> {
            email.add(linkTo(methodOn(EmailController.class).findById(email.getId()))
                    .withSelfRel());
        });
        return emails;
    }

    public List<EmailVO> findAllBySender (String emailFrom){
        var emails = DozerMapper.parseListObject(repository.findAllBySender(emailFrom), EmailVO.class);
        emails.stream().forEach(email -> {
            email.add(linkTo(methodOn(EmailController.class).findById(email.getId()))
                    .withSelfRel());
        });
        return emails;
    }

    public EmailVO save(EmailVO emailVO) {
        if(verifyEmail(emailVO))
        {
            var email = repository.save(DozerMapper.parseObject(emailVO, Email.class));
            emailVO = DozerMapper.parseObject(email, EmailVO.class);
            emailVO.add(linkTo(methodOn(EmailController.class).findById(emailVO.getId()))
                    .withSelfRel());
        }
        return null;
    }
    public EmailVO update(EmailVO emailVO) {
        var dbEmail = repository.findById(emailVO.getId());
        if(dbEmail.isPresent() && verifyEmail(emailVO)) {
            var email = repository.save(DozerMapper.parseObject(emailVO, Email.class));
            emailVO = DozerMapper.parseObject(email, EmailVO.class);
            emailVO.add(linkTo(methodOn(EmailController.class).findById(emailVO.getId()))
                    .withSelfRel());
            return emailVO;
        }
        return null;
    }
    public String delete(Long id) {
        var dbEmail = repository.findById(id);
        if(dbEmail.isPresent()) {
            repository.deleteById(id);
            return "Email with id " + id + " has been deleted!";
        }
        return "ID " + id + " not found!";
    }
    private boolean verifyEmail(EmailVO emailVO) {
        if( !emailVO.getFrom().isBlank() && !emailVO.getFrom().isEmpty() &&
                !emailVO.getTo().isBlank() && !emailVO.getTo().isEmpty() &&
                !emailVO.getSubject().isBlank() && !emailVO.getSubject().isEmpty() &&
                !emailVO.getBody().isBlank() && !emailVO.getBody().isEmpty() ) {
            return true;
        }
        return false;
    }
}

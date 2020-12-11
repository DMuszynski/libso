package pl.dmuszynski.libso.service;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(String to, String subject, String content, boolean isHtmlContent) throws MessagingException;
}
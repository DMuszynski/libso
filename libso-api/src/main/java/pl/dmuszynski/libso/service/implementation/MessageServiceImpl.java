package pl.dmuszynski.libso.service.implementation;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.service.MessageService;
import lombok.RequiredArgsConstructor;

import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;

@RequiredArgsConstructor
@Service(value = "mailService")
public class MessageServiceImpl implements MessageService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMessage(String to, String subject, String content, boolean isHtmlContent) {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, isHtmlContent);
            this.javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

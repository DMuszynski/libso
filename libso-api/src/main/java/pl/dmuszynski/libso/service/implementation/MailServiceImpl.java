package pl.dmuszynski.libso.service.implementation;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.service.MailService;
import lombok.RequiredArgsConstructor;

import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;

@RequiredArgsConstructor
@Service(value = "mailService")
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String to, String subject, String content, boolean isHtmlContent) throws MessagingException {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, isHtmlContent);

        this.javaMailSender.send(mimeMessage);
    }
}

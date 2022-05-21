package com.zhandabo.overgame.service;

import com.zhandabo.overgame.config.MailConfig;
import com.zhandabo.overgame.exception.OvergameException;
import com.zhandabo.overgame.model.constant.ErrorCodeConstant;
import com.zhandabo.overgame.model.dto.user.UserCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private static final String MAIL_SUBJECT = "Спасибо за регистрацию.";
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private final MailConfig mailConfig;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    public void send(String text, String subject, String email, String userPassword) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.imap.ssl.trust", host);
        props.put("mail.imap.socketFactory.port", "993");

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String msg = "Здравствуйте!" +
                "\n\nВы получили доступ в Личный Кабинет OVERGAME." +
                "\n\nВаш логин: " + email + "\n" + "Ваш пароль: " + userPassword +
                "\n\nНикому не передавайте пароль и логин. " +
                "\n\nЖелаем весело провести время на OVERGAME!";

        try {
            helper.setFrom(username); // it should be consistent with * 1. If it is not configured, an error will be reported
            helper.setSubject(subject);
            helper.setTo(email); // email address received
            helper.setText(msg); // message content
            helper.setSentDate(new Date());
            sender.send(message);
        } catch (MailException | MessagingException e) {
            log.error("can't send email: " + e.getMessage());
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.MAILING_ERROR,
                    "messages.exception.mailing-error");
        }
    }

    public void sendRegistrationMessage(UserCreateDto userCreateDto, String password) {
        String[] args = {userCreateDto.getEmail(), password};
        String message = getMessage("messages.registration", args);
        send(message, MAIL_SUBJECT, userCreateDto.getEmail(), password);
    }

    public String getMessage(String code, Object... objects) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(code, null, "Текст отсутствует. Обратитесь в техподдержку", locale);
        if (objects != null && objects.length > 0) {
            if (StringUtils.isEmpty(message)) {
                throw new IllegalStateException("message must not be empty or null");
            }
            return String.format(message, objects);
        } else {
            return message;
        }
    }
}

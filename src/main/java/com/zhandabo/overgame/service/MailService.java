package com.zhandabo.overgame.service;

import com.zhandabo.overgame.exception.OvergameException;
import com.zhandabo.overgame.model.constant.ErrorCodeConstant;
import com.zhandabo.overgame.model.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private static final String MAIL_SUBJECT = "Спасибо за регистрацию.";
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    @Value("${spring.mail.username}")
    private String sender;

    public void send(String text, String subject, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new OvergameException(HttpStatus.BAD_REQUEST, ErrorCodeConstant.MAILING_ERROR,
                    "messages.exception.mailing-error"
            );
        }
    }

    public void sendRegistrationMessage(UserInfoDto userInfoDto, String password) {
        String[] args = {userInfoDto.getEmail(), password};
        String message = getMessage("messages.registration", args);
        send(message, MAIL_SUBJECT, userInfoDto.getEmail());
    }

    public String getMessage(String code, Object... objects) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(code, null, "Текст отсутсвует. Обратитесь в техподдержку", locale);
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

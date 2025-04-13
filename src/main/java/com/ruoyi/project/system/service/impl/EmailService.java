package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.project.system.domain.ResetTokens;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.IResetTokensService;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final String resetUrlPrefix;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IResetTokensService resetTokensService;

    public EmailService(JavaMailSender javaMailSender, @Value("${app.resetUrlPrefix}") String resetUrlPrefix) {
        this.javaMailSender = javaMailSender;
        this.resetUrlPrefix = resetUrlPrefix;
    }

    @Async
    public void sendResetPasswordEmail(String email) {
        SysUser user = sysUserService.findUserByEmail(email);
        if (user == null) {
            // 处理找不到用户的情况
            throw new RuntimeException("User with email " + email + " not found");
        }

        String token = UUID.randomUUID().toString();
        ResetTokens resetToken = new ResetTokens();
        resetToken.setToken(token);
        resetToken.setUserId(user.getUserId());
        resetToken.setExpiryDate(Date.from(Instant.now().plusMillis(3600000))); // 设置过期时间为1小时后

        resetTokensService.insertResetTokens(resetToken);
        String resetUrl = resetUrlPrefix + "/resetPwd.html?token=" + token;
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("popupword@163.com");
            messageHelper.setTo(email);
            messageHelper.setSubject("重置密码");
            messageHelper.setText("请点击以下链接重置您的密码: " + resetUrl, true);
        };

        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            // 处理邮件发送失败的情况
            throw new RuntimeException("Failed to send reset password email", e);
        }
    }

    public void sendFeedback(String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("popupword@163.com");
        message.setTo("lowbyte1024@gmail.com");
        message.setSubject("Feedback: " + title);
        message.setText(content);

        javaMailSender.send(message);
    }
}


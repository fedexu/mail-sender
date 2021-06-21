package com.fedexu.mailer;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Clock;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SendGridHelper {

    Logger logger = LoggerFactory.getLogger(SendGridHelper.class);

    public long eventTime = System.currentTimeMillis();

    @Autowired
    private SendGrid sendGridClient;

    @Value("${sendgrid.mail.template}")
    String emailTemplate;

    @Value("${sendgrid.mail.toAdd}")
    String addTemplate;

    private final String TO = "<toEmail>";
    private final String SUBJECT = "<subject>";
    private final String BODY = "<body>";
    private final String EMAIL = "<email>";

    public int sendMail(List<String> to, String subject, String body) throws IOException {
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(emailTemplate
                .replace(TO, to.stream().map(s -> addTemplate.replace(EMAIL, s)).collect(Collectors.joining()))
                .replace(SUBJECT, securityCheck(subject))
                .replace(BODY, securityCheck(body)));
        Response response = sendGridClient.api(request);
        return response.getStatusCode();
    }

    private String securityCheck(String toCheck) throws IOException {
        List<String> badWords = Arrays.asList(
                "content-type:",
                "mime-version:",
                "multipart/mixed",
                "Content-Transfer-Encoding:",
                "bcc:",
                "cc:",
                "to:",
                "from:");
        if (badWords.stream().anyMatch(toCheck::contains))
            throw new IOException();
        //StripHTML
        return toCheck.replaceAll("<.*?>", "");

    }

}

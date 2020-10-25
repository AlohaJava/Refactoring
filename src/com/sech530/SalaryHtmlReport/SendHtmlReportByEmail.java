package com.sech530.SalaryHtmlReport;

public class SendHtmlReportByEmail {
    public static void sendEmail(String HOST,String SUBJECT, String emailBodyHtml){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // we're going to use google mail to send this message
        mailSender.setHost(HOST);
        // construct the message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        // setting message text, last parameter 'true' says that it is HTML format
        helper.setText(emailBodyHtml, true);
        helper.setSubject(SUBJECT);
        // send the message
        mailSender.send(message);
    }
}

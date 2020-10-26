package com.sech530.SalaryHtmlReport;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    private final Connection connection;
    private final String HOST;
    private final String SUBJECT;

    public SalaryHtmlReportNotifier(Connection databaseConnection, String SUBJECT, String HOST){
        this.connection = databaseConnection;
        this.HOST=HOST;
        this.SUBJECT=SUBJECT;
    }
    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this(databaseConnection,"Monthly department salary report","mail.google.com");
    }

    private PreparedStatement prepareRequest(SalaryReportRequestData salaryReportRequestData) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
        // inject parameters to sql
        ps.setString(0, salaryReportRequestData.getDepartmentId());
        ps.setDate(1, new java.sql.Date(salaryReportRequestData.getDateFrom().toEpochDay()));
        ps.setDate(2, new java.sql.Date(salaryReportRequestData.getDateTo().toEpochDay()));
        return ps;
    }

    public void generateAndSendHtmlSalaryReport(SalaryReportRequestData salaryReportRequestData) {
        try {
            // prepare statement with sql
            PreparedStatement ps = prepareRequest(salaryReportRequestData);
            ResultSet results = ps.executeQuery();
            // create a resulting html
            String emailBodyHtml = new HtmlMailBuilder(results).build();
            // now when the report is built we need to send it to the recipients list
            SendHtmlReportByEmail.sendEmail(HOST,SUBJECT,emailBodyHtml);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
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

    public SalaryHtmlReportNotifier(Connection databaseConnection, String HOST, String SUBJECT){
        this.connection = databaseConnection;
        this.HOST=HOST;
        this.SUBJECT=SUBJECT;
    }
    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this(databaseConnection,"mail.google.com","Monthly department salary report");
    }

    private PreparedStatement prepareRequest(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
        // inject parameters to sql
        ps.setString(0, departmentId);
        ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
        ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
        return ps;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            // prepare statement with sql
            PreparedStatement ps = prepareRequest(departmentId,dateFrom,dateTo);
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
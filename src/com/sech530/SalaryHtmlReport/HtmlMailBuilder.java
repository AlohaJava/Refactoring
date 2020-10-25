package com.sech530.SalaryHtmlReport;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HtmlMailBuilder {
    private ResultSet resultSet;

    public HtmlMailBuilder(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    private void insertRow(ResultSet row, StringBuilder resultingHtml) throws SQLException {
        resultingHtml.append("<tr>"); // add row start tag
        resultingHtml.append("<td>").append(resultSet.getString("emp_name")).append("</td>"); // appending employee name
        resultingHtml.append("<td>").append(resultSet.getDouble("salary")).append("</td>"); // appending employee salary for period
        resultingHtml.append("</tr>"); // add row end tag
    }

    private void insertTotals(double totals, StringBuilder resultingHtml){
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
    }

    public String build() throws SQLException {
        //init with head of html
        StringBuilder resultingHtml = new StringBuilder("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        while (resultSet.next()) {
            // process each row of query results
            insertRow(resultSet,resultingHtml);
            totals += resultSet.getDouble("salary"); // add salary to totals
        }
        insertTotals(totals,resultingHtml);
        //end of html
        resultingHtml.append("</table></body></html>");
        return resultingHtml.toString();
    }
}

package com.sech530.SalaryHtmlReport;

import java.time.LocalDate;

public class SalaryReportRequestData {
    private String departmentId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public SalaryReportRequestData(String departmentId, LocalDate dateFrom, LocalDate dateTo) {
        this.departmentId = departmentId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}

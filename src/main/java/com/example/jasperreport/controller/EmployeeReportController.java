package com.example.jasperreport.controller;

import com.example.jasperreport.service.EmployeeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeReportController {

    private static final String reportExportName = "employeeReport";

    @Autowired
    EmployeeReportService employeeReportService;

    @GetMapping("/report")
    public HttpEntity report() {
        byte[] data = employeeReportService.generateReport();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + reportExportName + "pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }
}

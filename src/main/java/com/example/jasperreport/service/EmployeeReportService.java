package com.example.jasperreport.service;

import com.example.jasperreport.dto.Employee;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeReportService {

    private static final String reportTemplateFile = "employee-rpt.jrxml";

    private List<Employee> empList = Arrays.asList(
            new Employee(1, "Sandeep", "Data Matrix", "Front-end Developer", 20000),
            new Employee(2, "Prince", "Genpact", "Consultant", 40000),
            new Employee(3, "Gaurav", "Silver Touch ", "Sr. Java Engineer", 47000),
            new Employee(4, "Abhinav", "Akal Info Sys", "CTO", 700000)
    );

    public byte[] generateReport() {
        byte[] data = new byte[0];

        try {

            InputStream employeeReportStream = getClass().getResourceAsStream("/reports/" + reportTemplateFile);

            // Compile the Jasper report from .jrxml to .japser
            //JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\employee-rpt.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);

            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(empList);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Demo");

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    jrBeanCollectionDataSource
            );

            // Export the report to a PDF file
            data = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}

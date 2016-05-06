package com.logistics.service;

import com.logistics.entity.Report;

/**
 * Created by Mklaus on 15/8/21.
 */
public interface ReportService {
    public Report getReportByMonth(int year,int month);

    public Report getReportByQuarter(int year,int quarter);

    public Report getReportByYear(int year);
}

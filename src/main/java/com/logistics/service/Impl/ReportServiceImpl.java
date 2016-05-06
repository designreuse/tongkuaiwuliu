package com.logistics.service.Impl;

import com.logistics.dao.*;
import com.logistics.entity.*;
import com.logistics.service.ReportService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Mklaus on 15/8/21.
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private TransportTaskDao taskDao;
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private StaffDao staffDao;
    @Resource
    private OilBuyDao oilBuyDao;

    @Override
    public Report getReportByMonth(int year,int month) {
        int monthLength = getMonthLength(year,month);

        Long start  = 0L;
        Long end    = 0L;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String m = month > 10 ? month+"": "0"+month;

        try {
            Date startDate = dateFormat.parse(year+"-"+ m + "-01");
            Date endDate   = dateFormat.parse(year+"-"+ m + "-" + monthLength);

            start = startDate.getTime();
            end   = endDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Report report = getReport(start,end);
        report.setOutputOfSalary(getSalaryOutput(start,end,1));

        return report;
    }

    @Override
    public Report getReportByQuarter(int year, int quarter) {
        int monthLength = 31;
        if (quarter == 6 || quarter == 9)
            monthLength = 30;

        Long start  = 0L;
        Long end    = 0L;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int monthStart = quarter * 3 - 2;
        int monthEnd   = quarter * 3;
        String ms = (quarter == 4) ? monthStart+"": "0" + monthStart;
        String me = (quarter == 4) ? monthEnd + "": "0" + monthEnd;

        try {
            Date startDate = dateFormat.parse(year+"-"+ ms + "-01");
            Date endDate   = dateFormat.parse(year+"-"+ me + "-" + monthLength);

            start = startDate.getTime();
            end   = endDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Report report = getReport(start,end);
        report.setOutputOfSalary(getSalaryOutput(start,end,3));

        return report;
    }

    @Override
    public Report getReportByYear(int year) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Long start  = 0L;
        Long end    = 0L;

        try {
            Date startDate = dateFormat.parse(year + "-01-01");
            Date endDate   = dateFormat.parse(year + "-12-31");

            start = startDate.getTime();
            end   = endDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Report report = getReport(start,end);
        report.setOutputOfSalary(getSalaryOutput(start,end,12));

        return report;
    }

    public Report getReport(Long start, Long end){
        Report report = new Report();

        report.setOutputOfGoodsBuy(getGoodsRecordOutput(start,end));
        report.setInputOfTask(getInputOfTask(start,end,0));
        report.setInputOfCarry(getInputOfTask(start,end,1));
        report.setOutputOfOil(getOilOutput(start, end));

        return report;
    }

    private int getOilOutput(Long start,Long end){
        int output = 0;
        DetachedCriteria dc = DetachedCriteria.forClass(OilBuy.class);
        dc.add(Restrictions.ge("created_time",start)).add(Restrictions.lt("created_time", end));
        List<OilBuy> l = this.oilBuyDao.search(dc);
        for (OilBuy o : l){
            output += o.getSumPrice();
        }
        return output;
    }

    private int getGoodsRecordOutput(Long start,Long end){
        int output = 0;
        DetachedCriteria dc = DetachedCriteria.forClass(Goods.class);
        dc.add(Restrictions.ge("created_time",start)).add(Restrictions.lt("created_time",end));
        List<Goods> l = goodsDao.search(dc);
        for (Goods g : l){
            System.out.println("g_price = " + g.getSumPrice());
            System.out.println("g_price 0 = " + g.getPrice() * g.getCount());
            output += g.getSumPrice();
        }
        return output;
    }

    private int getInputOfTask(Long start,Long end,int type){
        int output = 0;
        DetachedCriteria dc = DetachedCriteria.forClass(TransportTask.class);
        dc.add(Restrictions.ge("created_time",start)).add(Restrictions.lt("created_time",end));
        List<TransportTask> l = taskDao.search(dc);
        if (type == 0) {
            for (TransportTask tt : l) {
                output += tt.getSumPrice();
            }
        }else {
            for (TransportTask tt : l){
                output += (tt.getSumPrice() * tt.getRate());
            }
        }
        return output;
    }

    public int getMonthLength(int year,int month){
        int length = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11){
            length = 30;
        }else if (month == 2){
            if (year % 400 == 0 || ( (year % 100 != 0) && (year % 4 == 0) ) ){
                length = 29;
            }else {
                length = 28;
            }
        }
        return length;
    }

    private int getSalaryOutput(Long start,Long end,int times){
        int sum = 0;
        List<Staff> l = staffDao.getAll();
        for (Staff s : l){
            System.out.println("hire = " + s.getDateOfHire() + "   start = " + start + "   end = " + end);
            if (s.getDateOfHire() < end) {
                if (s.getDateOfHire() < start) {
                    sum += (s.getSalary() * times);
                }else {
                    double rate = ((end - s.getDateOfHire()) * 1.0) / (end - start);
                    System.out.println("rate = " + rate);
                    System.out.println("temp = " + (s.getSalary() * (rate) * times));
                    sum += (s.getSalary() * (rate) * times);
                }
            }
        }

        return sum;
    }
}

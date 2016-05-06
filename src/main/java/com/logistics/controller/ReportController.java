package com.logistics.controller;

import com.logistics.entity.Report;
import com.logistics.service.ReportService;
import com.logistics.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mklaus on 15/8/21.
 */
@Controller
@RequestMapping("report")
public class ReportController {
    @Resource
    private ReportService reportService;

    /**
     * 查看指定月报告
     * <p>API</p>
     *  GET     /report/month
     * @param year      必需
     * @param month     必需
     * @param resp
     */
    @RequestMapping(value = "{month}",method = RequestMethod.GET)
    public void getReport_month(int year,int month,HttpServletResponse resp) throws IOException {
        Report report = reportService.getReportByMonth(year,month);
//        System.out.println("吴海彬！！！！"+report.getOutput());
        JsonUtil.write(report,resp);
    }

//    @RequestMapping(method = RequestMethod.GET,params = "json")
//    public void getJsonRecs(Integer start, Integer size, HttpServletResponse response) throws IOException {
//        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleType.class);
//        start = start != null ? start : 0;
//        size  = size  != null ? size  : Configurator.getInstance().getInt("defaultPageSize");
//        List<VehicleType> vehicleTypes = vehicleTypeService.get(detachedCriteria, start, size);
//        JSONObject json = new JSONObject();
//        json.put("vehicleTypesLength", vehicleTypeService.getAll().size());
//        JsonUtil.write(vehicleTypes, "vehicleTypes", json, response);
//    }

    /**
     * 查看指定季度报告
     * <p>API</p>
     *  GET     /report/quarter
     * @param year      必需
     * @param quarter   必需
     * @param resp
     */
    @RequestMapping(value = "quarter",method = RequestMethod.GET)
    public void getReport_quarter(int year,int quarter,HttpServletResponse resp) throws IOException {
        Report report = reportService.getReportByQuarter(year,quarter);
        JsonUtil.write(report,resp);
    }

    /**
     * 查找指定年报告
     * <p>API</p>
     *  GET     /report/year
     * @param year      必需
     * @param resp
     */
    @RequestMapping(value = "year",method = RequestMethod.GET)
    public void getReport_year(int year,HttpServletResponse resp) throws IOException {
        Report report = reportService.getReportByYear(year);
        JsonUtil.write(report,resp);
    }
}

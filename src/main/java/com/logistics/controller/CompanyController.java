package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.Company;
import com.logistics.service.CompanyService;
import com.logistics.util.JsonUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.logistics.util.Params.needLongList;

/**
 * Created by wuhaibin on 15/8/19.
 */

@Controller
@RequestMapping("company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET)
    public String company_GET(Integer start,Integer size,Model model,HttpSession session){
        DetachedCriteria dc = DetachedCriteria.forClass(Company.class);
        List<Company> companies = companyService.get(dc,start,size);
        model.addAttribute("companies",companies);
        return "/company/companyAdd";
    }

    @RequestMapping(method = RequestMethod.GET,params = "json")
    public void company_GET_json(Integer start,Integer size,Model model,HttpSession session,HttpServletResponse resp) throws IOException {
        start = start != null ? start : 0;
        size  = size  != null ? size  : Configurator.getInstance().getInt("defaultPageSize");
        List<Company> companies = companyService.get(start, size);
        JSONObject json = new JSONObject();
        json.put("companyLength",companyService.getAll().size());
        JsonUtil.write(companies, "companies", json, resp);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String company_CREATE(Company company,Model model,HttpSession session){
        companyService.add(company);
        model.addAttribute("company", company);
        return "/company/companyManage";
    }

    @RequestMapping(method = RequestMethod.POST,params = "json")
    public void company_CREATE_JSON(Company company,HttpServletResponse resp) throws IOException {
        Integer companyId = companyService.add(company);
        if(companyId!=null){
            JsonUtil.writeMsg(companyId.toString(),resp);
        } else{
            JsonUtil.writeMsg("-1",resp);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String company_UPDATE(Company company,Model model,HttpSession session){
        companyService.update(company);
        model.addAttribute("company", company);
        return "/company/companyManage";
    }

    @RequestMapping(method = RequestMethod.PUT,params = "json")
    public void goods_UPDATE_json(Company company,HttpServletResponse resp) throws IOException {
        companyService.update(company);
        JsonUtil.writeMsg("1", resp);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String company_DELETE(Integer companyId){
        companyService.delete(companyId);
        return "redirect:/company";
    }

    @RequestMapping(value = "{companyId}",method = RequestMethod.GET)
         public String company_id(@PathVariable("companyId") Integer companyId, Model model){
        Company company = companyService.get(companyId);
        model.addAttribute("company", company);
        return "/company/companyInfo";
    }

    @RequestMapping(value = "{companyId}",method = RequestMethod.GET,params = "edit")
    public String company_edit(@PathVariable("companyId") Integer companyId, Model model){
        Company company = companyService.get(companyId);
        model.addAttribute("company", company);
        return "/company/companyEdit";
    }

    @RequestMapping(value = "{companyId}",method = RequestMethod.GET,params = "json")
    public void company_id_json(@PathVariable("companyId") Integer companyId,HttpServletResponse resp) throws IOException {
        Company company = companyService.get(companyId);
        JsonUtil.write(company, resp);
    }

    @RequestMapping(method = RequestMethod.DELETE,params = "json")
    public void goods_DELETE_json(Integer companyId,HttpServletResponse resp) throws IOException {
        companyService.delete(companyId);
        JsonUtil.writeMsg("1", resp);
    }


    @RequestMapping(method = RequestMethod.DELETE,params = "many")
    public void company_DELETE_MANY(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        List<Long> ids = needLongList(req, "ids");
        System.out.println(ids.size());
        for (long id : ids){
            companyService.delete((int) id);
        }
        JsonUtil.writeMsg("1", resp);
    }
}

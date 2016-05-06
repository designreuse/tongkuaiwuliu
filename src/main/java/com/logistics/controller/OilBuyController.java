package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.OilBuy;
import com.logistics.service.OilBuyService;
import com.logistics.util.JsonUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.logistics.util.Params.needLongList;

/**
 * Created by Mklaus on 15/8/21.
 */
@Controller
@RequestMapping("oilBuy")
public class OilBuyController {
    @Resource
    private OilBuyService oilBuyService;
    
    @RequestMapping(method = RequestMethod.GET,params = "json")
    public void oilBuy_get_json(Integer start,Integer size,HttpServletResponse resp) throws IOException{
        DetachedCriteria dc = DetachedCriteria.forClass(OilBuy.class);
        start = start != null ? start : 0;
        size  = size  != null ? size  : Configurator.getInstance().getInt("defaultPageSize");
        JSONObject json = new JSONObject();
        List<OilBuy> oilBuys = oilBuyService.get(dc,start,size);

        json.put("oilBuysLength",oilBuyService.getAll().size());
        JsonUtil.write(oilBuys, "oilBuys", json, resp);
    }

    @RequestMapping(method = RequestMethod.POST,params = "json")
    public void oilBuy_create_json(OilBuy oilBuy,Integer staffId,Integer vehicleId,HttpServletResponse resp) throws IOException{
        Integer oilBuyId = oilBuyService.add(oilBuy, staffId, vehicleId);
        if(oilBuyId!=null){
            JsonUtil.writeMsg(oilBuyId.toString(),resp);
        } else{
            JsonUtil.writeMsg("-1",resp);
        }
    }

    @RequestMapping(method = RequestMethod.PUT,params = "json")
    public void oilBuy_update_json(OilBuy oilBuy,Integer staffId,Integer vehicleId,HttpServletResponse resp) throws IOException{
        oilBuyService.update(oilBuy,staffId,vehicleId);
        JsonUtil.writeMsg("1", resp);
    }

    @RequestMapping(method = RequestMethod.DELETE,params = "json")
    public void oilBuy_delete_json(Integer oilBuyId,HttpServletResponse resp) throws IOException{
        oilBuyService.delete(oilBuyId);
        JsonUtil.writeMsg("1",resp);
    }

    @RequestMapping(method = RequestMethod.DELETE,params = "many")
    public void oilBuy_DELETE_MANY(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        List<Long> ids = needLongList(req,"ids");
        for (long id : ids){
            oilBuyService.delete((int) id);
        }
        JsonUtil.writeMsg("1",resp);
    }
}

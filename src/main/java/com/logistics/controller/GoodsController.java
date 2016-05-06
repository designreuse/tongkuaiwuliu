package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.Goods;
import com.logistics.service.GoodsService;
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
import java.io.IOException;
import java.util.List;

import static com.logistics.util.Params.needLongList;


/**
 * Created by Mklaus on 15/8/16.
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * <p>获取物资</p>
     * <p>GET       /goods</p>
     *
     * @param start     开始获取位置 （默认为0）
     * @param size      获取个数    (默认10)
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String goods_GET(Integer start,Integer size,Model model){
        DetachedCriteria dc = DetachedCriteria.forClass(Goods.class);
        start = start != null ? start : 0;
        size  = size  != null ? size  : Configurator.getInstance().getInt("defaultPageSize");
        List<Goods> goodses = goodsService.get(dc,start,size);
        model.addAttribute("goodses",goodses);
        return "/goods/goodsShowAll";
    }

    /**
     * <p>获取物资 以Json形式返回</p>
     * <p>GET       /goods</p>
     *
     * @param start     开始获取位置 （默认为0）
     * @param size      获取个数      (默认10)
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,params = "json")
    public void goods_GET_json(Integer start,Integer size,HttpServletResponse resp) throws IOException {
        DetachedCriteria dc = DetachedCriteria.forClass(Goods.class);
        start = start != null ? start : 0;
        size  = size  != null ? size  : Configurator.getInstance().getInt("defaultPageSize");
        List<Goods> goodses = goodsService.get(dc, start, size);

        JSONObject json = new JSONObject();
        json.put("goodsesLength",goodsService.getAll().size());
        JsonUtil.write(goodses, "goodses", json, resp);
    }

    /**
     * 创建物资
     * <p>API</p>
     *  POST      /goods
     * <p>返回</p>
     *  成功返回新物资ID，    错误返回 -1
     * @param goods
     * @param resp
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void goods_CREATE(Goods goods,HttpServletResponse resp) throws IOException {
        Integer goodsId = goodsService.add(goods);
        if(goodsId!=null){
            JsonUtil.writeMsg(goodsId.toString(),resp);
        } else{
            JsonUtil.writeMsg("-1",resp);
        }
    }

    /**
     * 更新物资
     * <p>API</p>
     *  PUT       /goods
     * @param goods
     * @param model
     * @return  返回页面      /goods/goodsShow
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String goods_UPDATE(Goods goods,Model model){
        goodsService.update(goods);
        model.addAttribute("goods",goods);
        return "/goods/goodsShow";
    }

    /**
     * 更新物资 （供前端异步使用）
     * <p>API</p>
     *  PUT       /goods?json
     * @param goods
     * @param resp
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.PUT,params = "json")
    public void goods_UPDATE_json(Goods goods,HttpServletResponse resp) throws IOException {
        goodsService.update(goods);
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * 删除物资
     * <p>API</p>
     *  DELETE        /goods
     * @param goodsId   要删除物资的ID（必需）
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String goods_DELETE(Integer goodsId){
        goodsService.delete(goodsId);
        return "redirect:/goods";
    }

    /**
     * 多项删除
     * <p>API</p>
     *  DELETE        /goods?many
     * <p>参数</p>
     *  ids=(1,2,3)
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.DELETE,params = "many")
    public void goods_DELETE_MANY(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        List<Long> ids = needLongList(req,"ids");
        System.out.println(ids.size());
        for (long id : ids){
            goodsService.delete((int) id);
        }
        JsonUtil.writeMsg("1",resp);
    }

    /**
     * 删除物资 （异步删除）
     * <p>API</p>
     * DELETE       /goods?json
     *
     * <p>返回</p>
     *  成功  1
     * @param goodsId   要删除物资的ID（必需）
     * @param resp
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.DELETE,params = "json")
    public void goods_DELETE_json(Integer goodsId,HttpServletResponse resp) throws IOException {
        goodsService.delete(goodsId);
        JsonUtil.writeMsg("1",resp);
    }

    /**
     * 获取单个物资
     * <p>API</p>
     *  GET         /goods/{goodsId}
     *
     * @param goodsId
     * @param model
     * @return      跳转到 /goods/goodsShow
     */
    @RequestMapping(value = "{goodsId}",method = RequestMethod.GET)
    public String goods_id(@PathVariable("goodsId") Integer goodsId, Model model){
        Goods goods = goodsService.get(goodsId);
        model.addAttribute("goods",goods);
        return "/goods/goodsShow";
    }

    /**
     * 获取单个或者 (json)
     * <p>API</p>
     *  GET         /goods/{goodsId}?json
     * @param goodsId
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "{goodsId}",method = RequestMethod.GET,params = "json")
    public void goods_id_json(@PathVariable("goodsId") Integer goodsId,HttpServletResponse resp) throws IOException {
        Goods goods = goodsService.get(goodsId);
        JsonUtil.write(goods,resp);
    }
}

package com.logistics.controller;

import com.logistics.conf.Configurator;
import com.logistics.dao.GoodsGroupDao;
import com.logistics.entity.GoodsGroup;
import com.logistics.entity.GoodsRecord;
import com.logistics.service.GoodsRecordService;
import com.logistics.util.JsonUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mklaus on 15/8/20.
 */
@Controller
@RequestMapping("goodsRecord")
public class GoodsRecordController {
    @Resource
    private GoodsRecordService goodsRecordService;
    @Resource
    private GoodsGroupDao goodsGroupDao;

    /**
     * 获取物资记录
     * <p>API</p>
     *  GET     /goodsRecord
     * @param start     开始获取位置，默认0
     * @param size      获取数量，默认10
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String goodsRecord_GET(Integer start,Integer size,Model model,HttpSession session){
        DetachedCriteria dc = DetachedCriteria.forClass(GoodsRecord.class);
        start = start != null ? start : 0;
        size  = size  != null ? size  : Configurator.getInstance().getInt("defaultPageSize");
        List<GoodsRecord> goodses = goodsRecordService.get(dc,start,size);
        model.addAttribute("goodses",goodses);
        return "/goodsRecord/goodsRecordShowAll";
    }

    /**
     * 创建物资记录
     * <p>API</p>
     *  POST    /goodsRecord
     * @param goodsRecord
     * @param resp
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void goodsRecord_CREATE(GoodsRecord goodsRecord,HttpServletResponse resp) throws IOException {
        Integer goodsRecordId = goodsRecordService.add(goodsRecord);
        if(goodsRecordId!=null){
            JsonUtil.writeMsg(goodsRecordId.toString(), resp);
        } else{
            JsonUtil.writeMsg("-1",resp);
        }
    }

    /**
     * 更新物资记录
     * <p>API</p>
     *  PUT     /goodsRecord
     * @param goodsRecord
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String goods_UPDATE(GoodsRecord goodsRecord,Model model) throws IOException {
        goodsRecordService.update(goodsRecord);
        model.addAttribute("goodsRecord", goodsRecord);
        return "/goodsRecord/showALL";
    }

    /**
     * 删除物资
     * <p>API</p>
     *  DELETE  /goodsRecord
     * @param goodsRecordId     指定要删除物资的ID,必需
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String goods_DELETE(Integer goodsRecordId){
        goodsRecordService.delete(goodsRecordId);
        return "redirect:/goodsRecord";
    }

    /**
     * 获取单个物资
     * <p>API</p>
     *  GET     /goodsRecord/{goodsRecordId}
     * @param goodsRecordId
     * @param model
     * @return  跳转到 /goodsRecord/show
     */
    @RequestMapping(value = "{goodsRecordId}",method = RequestMethod.GET)
    public String goodsRecord_id(@PathVariable("goodsRecordId") int goodsRecordId,Model model){
        GoodsRecord goodsRecord = goodsRecordService.get(goodsRecordId);
        model.addAttribute("goodsRecord",goodsRecord);
        return "/goodsRecord/show";
    }

    /**
     * 获取单个物资 json形式
     * <p>API</p>
     *  GET     /goodsRecord/{goodsRecordId}?json
     * @param goodsRecordId
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "{goodsRecordId}",params = "json")
    public void goodsRecord_id_json(@PathVariable("goodsRecordId") int goodsRecordId,
                                    HttpServletResponse resp)throws IOException{
        GoodsRecord goodsRecord = goodsRecordService.get(goodsRecordId);
        JsonUtil.write(goodsRecord,resp);
    }

    /**
     * 为指定物资记录添加物资组
     * <p>API</p>
     *  POST    /goodsRecord/{goodsRecordId}/goodsGroup
     *
     * @param goodRecordId      指定物资记录ID
     * @param goodsGroup        物资组
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "{goodsRecordId}/goodsGroup",method = RequestMethod.POST)
    public void goodsRecord_addGoodGroup(@PathVariable("goodsRecordId") int goodRecordId,
                                         GoodsGroup goodsGroup,HttpServletResponse resp) throws IOException{
        GoodsRecord goodsRecord = goodsRecordService.get(goodRecordId);
        goodsGroup.setGoodsRecord(goodsRecord);
        int goodsGroupId = goodsGroupDao.save(goodsGroup);
        JsonUtil.writeMsg(goodsGroupId+"",resp);

    }

    /**
     * 删除指定物资记录的物资组
     * <p>API</p>
     *  DELETE      /goodsRecord/{goodsRecordId}/{goodsGroupId}
     * @param goodGroupId       指定物资组ID,必需
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "{goodsRecordId}/{goodsGroupId}",method = RequestMethod.DELETE)
    public void goodsRecord_removeGoodGroup(@PathVariable("goodsGroupId") int goodGroupId,HttpServletResponse resp) throws IOException{
        GoodsGroup goodsGroup = goodsGroupDao.get(goodGroupId);
        goodsGroup.setGoodsRecord(null);
        goodsGroupDao.delete(goodsGroup);
        JsonUtil.write("1",resp);
    }

}

package com.logistics.controller;

import com.logistics.conf.Configurator;
import com.logistics.service.ImageService;
import com.logistics.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Mklaus on 15/8/27.
 */
@Controller
@RequestMapping("image")
public class ImageController {
    @Resource
    private ImageService imageService;

    /**
     * ----------- 文件上传 ------------------
     * */
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String showUploadPage(){
        return "/admin/uploadFile";
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST,params = "carId")
    public void image_car_doUpload(@RequestParam("file") MultipartFile file,Integer carId,
                                   HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String name = "car"+carId;
        if (file != null){
            imageService.upload(Configurator.getInstance().get("imagePathOnUnix"),name,file);
        }
        JsonUtil.writeMsg("1",resp);
    }

    @RequestMapping(value = "get",method = RequestMethod.GET,params = "carId")
    public void image_car_GET(Integer carId,HttpServletResponse resp) throws IOException{
        String name = "car" + carId;
        InputStream is = imageService.load(name);
        OutputStream os = resp.getOutputStream();
        imageService.startPipe(is,os,4096);
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST,params = "staffId")
    public String image_staff_doUpload(@RequestParam("file") MultipartFile file,Integer staffId,
                                       HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String name = "staff" + staffId;
        if (file != null){
            imageService.upload(Configurator.getInstance().get("imagePathOnUnix"),name,file);
        }
        return "redirect:/staff/" + staffId;
    }

    @RequestMapping(value = "get",method = RequestMethod.GET,params = "staffId")
    public void image_staff_GET(Integer staffId,HttpServletResponse resp) throws IOException{
        String name = "staff" + staffId;
        InputStream is = imageService.load(name);
        OutputStream os = resp.getOutputStream();
        imageService.startPipe(is,os,4096);
    }
}

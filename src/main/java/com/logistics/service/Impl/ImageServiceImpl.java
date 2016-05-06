package com.logistics.service.Impl;

import com.logistics.conf.Configurator;
import com.logistics.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;

/**
 * Created by Mklaus on 15/8/27.
 */
@Service
public class ImageServiceImpl implements ImageService ,ImageObserver{
    public static final String IMAGE_PATH = Configurator.getInstance().get("imagePathOnUnix");

    public static final int DEFAULT_WIDTH  = 847;
    public static final int DEFAULT_HEIGHT = 1270;

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return((infoflags & ImageObserver.ALLBITS) != ImageObserver.ALLBITS);
    }

    @Override
    public void store(String name, InputStream is) throws IOException{
        store(name,is,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    @Override
    public void store(String name, InputStream is, Integer width, Integer height) throws IOException{
        BufferedImage img = ImageIO.read(is);

        // 设定图片大小的比例
        double ratio = (height * 1.0000) / width;

        double h = img.getHeight();
        double w = img.getWidth();
        double hwratio = h/w;
        double clipH = hwratio <= ratio ? h : w*ratio ;
        double clipW = hwratio >= ratio ? w : h*ratio ;

        int sx1 = (int) (w-clipW) /2;
        int sx2 = (int) w-sx1;
        int sy1 = (int) (h-clipH) /2;
        int sy2 = (int) h-sy1;

        BufferedImage scaledImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        ((Graphics2D)scaledImg.getGraphics())
                .drawImage(img, 0, 0, width, height ,sx1, sy1, sx2, sy2, this);

        File file = new File(IMAGE_PATH+ File.separator+name+".jpg");
        if (!file.exists())
            file.createNewFile();

        ImageIO.write(scaledImg, "jpg", file);
    }

    @Override
    public InputStream load(String name) throws IOException{
        File file = new File(IMAGE_PATH+File.separator+name+".jpg");
        if (!file.exists())
            throw(new FileNotFoundException("Cover not found:name="+name));

        FileInputStream is = new FileInputStream(file);

        return(is);
    }

    @Override
    public void upload(String parent,String name, MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File(parent, name + ".jpg"));
        }

        if (new File(parent,name+".jpg").exists()){
            System.out.println("parent = " + parent);
        }else {
            System.out.println("parent error = " + parent);
        }
    }

    @Override
    public void startPipe(InputStream is, OutputStream os, int bufferSize) throws IOException{
        byte[] buffer = new byte[bufferSize];
        boolean read = true;

        int read1;
        while((read1 = is.read(buffer, 0, bufferSize)) != -1) {
            os.write(buffer, 0, read1);
        }

        is.close();
        os.close();
    }
}

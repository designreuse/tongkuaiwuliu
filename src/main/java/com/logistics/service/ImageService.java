package com.logistics.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Mklaus on 15/8/27.
 */
public interface ImageService {
    public void store(String name,InputStream is) throws IOException;

    public void store(String name,InputStream is,Integer width,Integer height) throws IOException;

    public InputStream load(String name) throws IOException;

    public void upload(String parent,String name,MultipartFile file) throws IOException;

    public void startPipe(InputStream is, OutputStream os, int bufferSize) throws IOException;
}

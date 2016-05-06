package com.logistics.service;

import com.logistics.entity.Admin;

import java.io.IOException;

/**
 * Created by Mklaus on 15/7/23.
 */
public interface AdminService extends BaseService<Admin>{
    public Admin login(Admin admin);

    public Admin register(Admin admin);

    public boolean isExists(String adminName);

    public int getIdByAdminName(String adminName);

    public boolean passwd(Admin admin,String oldPass,String newPass) throws IOException;
}

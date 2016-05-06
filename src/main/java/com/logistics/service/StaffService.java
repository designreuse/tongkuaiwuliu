package com.logistics.service;

import com.logistics.entity.Staff;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
public interface StaffService extends BaseService<Staff> {
    List<Staff> getStaffList(String job);

    List<Staff> getStaffList(String job, Integer start, Integer size);

    List<Staff> getAvailableDriver();
}

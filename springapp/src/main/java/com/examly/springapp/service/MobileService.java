package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Mobile;

public interface MobileService {
    Mobile addMobile(Mobile mobile);
    List<Mobile> getAllMobile();
    Mobile editMobile(Long mobileId,Mobile updatedMobile);
    Mobile deleteMobile(Long mobileId);
    List<Mobile> getMobilesByMobileIds(List<Long> mobileIds);
    Mobile getMobileById( long mobileId);
    public Mobile updateQuantity(long mobileId,int quantity);
}

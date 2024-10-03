package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.MobileAlreadyExistException;
import com.examly.springapp.model.Mobile;
import com.examly.springapp.repository.MobileRepo;


@Service
public class MobileServiceImpl implements MobileService {
    
   @Autowired 
   private MobileRepo mrepo;


    @Override
    public Mobile addMobile(Mobile mobile) {
        Mobile m=mrepo.findByModel(mobile.getModel());
        if(m!=null)
            throw new MobileAlreadyExistException("Mobile with "+mobile.getModel()+" Already Exist");
        return mrepo.save(mobile);
    }

    @Override
    public Mobile deleteMobile(Long mobileId) {

       if(mrepo.existsById(mobileId)){
              Mobile mobile =mrepo.findById(mobileId).get();
              mrepo.deleteById(mobileId);
              return mobile;
       }
       else return null;
    }

    @Override
    public Mobile editMobile(Long mobileId, Mobile updatedMobile) {

        if(mrepo.existsById(mobileId)){
            updatedMobile.setMobileId(mobileId);
            return mrepo.save(updatedMobile);
        }
        return null;
    }


    @Override
    public List<Mobile> getAllMobile() {
        return mrepo.findAll();
    }

    @Override
    public List<Mobile> getMobilesByMobileIds(List<Long> mobileIds) {
        return mrepo.findAllById(mobileIds);
    }


    public Mobile getMobileById(long mobileId){
        if(mrepo.existsById(mobileId))
        return mrepo.findById(mobileId).get();
        else return null;
    }

    public Mobile updateQuantity(long mobileId,int quantity){
        Mobile m=mrepo.findById(mobileId).get();
        m.setQuantity(quantity);
        return mrepo.save(m);

    }




    


}

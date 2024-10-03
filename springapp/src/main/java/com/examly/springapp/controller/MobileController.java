package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Mobile;
import com.examly.springapp.service.MobileServiceImpl;

@RestController
public class MobileController {
    
    @Autowired 
     private MobileServiceImpl service;

     @PostMapping("/api/mobile")
     public ResponseEntity<String> addMobile(@RequestBody Mobile mobile){

        Mobile m= service.addMobile(mobile);
        if(m!=null){
            return new ResponseEntity<>("Mobile added Successfully.",HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     }


     @GetMapping("/api/mobile")
     public  ResponseEntity<List<Mobile>> getAllMobile(){

        List<Mobile> mb=service.getAllMobile();
        if(mb.size()>0){
            return new ResponseEntity<>(mb,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     }

     @GetMapping("/api/mobiles")
public ResponseEntity<List<Mobile>> getMobilesByMobileIds(@RequestParam List<Long> mobileIds) {
    List<Mobile> mobiles = service.getMobilesByMobileIds(mobileIds);
    if (mobiles != null && !mobiles.isEmpty()) {
        return new ResponseEntity<>(mobiles, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@GetMapping("api/mobiles/{mobileId}") 
  public ResponseEntity<Mobile> getMobileById(@PathVariable long mobileId){
    Mobile m=service.getMobileById(mobileId);
    if(m!=null){
        return new ResponseEntity<>(m, HttpStatus.OK);
    }
    else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}




     @PutMapping("/api/mobile/{mobileId}")
     public  ResponseEntity<Mobile> editMobile(@PathVariable long mobileId,@RequestBody Mobile mobile){

        Mobile mb=service.editMobile(mobileId, mobile);
        if(mb!=null){
            return new ResponseEntity<>(mb,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }





     @DeleteMapping("/api/mobile/{mobileId}")
     public ResponseEntity<Mobile> deleteMobile(@PathVariable long mobileId){

                Mobile mb=service.deleteMobile(mobileId);
                if (mb!=null) {
                    return new ResponseEntity<>(mb,HttpStatus.OK);
                }
                else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     }


     @PutMapping("/api/mobile/quantity/{mobileId}")
     public  ResponseEntity<Mobile> updateQuantity(@PathVariable long mobileId,@RequestBody Mobile m){
 
        Mobile mb=service.updateQuantity(mobileId,m.getQuantity());
        if(mb!=null){
            return new ResponseEntity<>(mb,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }   

}
}
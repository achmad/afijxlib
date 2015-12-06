/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.util;

import com.afi.jx.lib.common.constant.CommonConstant;
import com.afi.jx.lib.common.rest.dto.DtoRespond;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author achmad.ha
 */
public class RestUtil {
    
    public static DtoRespond success(){
        DtoRespond dtoRespond = new DtoRespond();
        dtoRespond.setStat(CommonConstant._200);
        dtoRespond.setMsg(CommonConstant._200Msg);
        return dtoRespond;
    }
    
    public static DtoRespond badRequest(){
        DtoRespond dtoRespond = new DtoRespond();
        dtoRespond.setStat(CommonConstant._400);
        dtoRespond.setMsg(CommonConstant._400Msg);
        return dtoRespond;
    }
    
    public static DtoRespond authenticationFailed(){
        DtoRespond dtoRespond = new DtoRespond();
        dtoRespond.setStat(CommonConstant._403);
        dtoRespond.setMsg(CommonConstant._403Msg);
        return dtoRespond;
    }
    
    public static DtoRespond resourceNotFound(){
        DtoRespond dtoRespond = new DtoRespond();
        dtoRespond.setStat(CommonConstant._404);
        dtoRespond.setMsg(CommonConstant._404Msg);
        return dtoRespond;
    }
    
    public static DtoRespond authorizationFailed(){
        DtoRespond dtoRespond = new DtoRespond();
        dtoRespond.setStat(CommonConstant._401);
        dtoRespond.setMsg(CommonConstant._401Msg);
        return dtoRespond;
    }
    
    public static DtoRespond internalServerError(Exception e){
        DtoRespond dtoRespond = new DtoRespond();
        dtoRespond.setStat(CommonConstant._500);
        dtoRespond.setMsg(e.getMessage());
        return dtoRespond;
    }
    
    public static DtoRespond integrityConstraint(DataIntegrityViolationException dive){
        DtoRespond dtoRespond = new DtoRespond();
        dtoRespond.setMsg(CommonConstant.failedInsertIntCons);
        dtoRespond.setStat(CommonConstant._500);   
        return dtoRespond;
    }
    
   
    
}

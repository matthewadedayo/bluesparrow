/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ContentPortalMasterService;
import service.CouponMappingTypeService;
import service.CouponMasterService;
import service.SubscriptionMasterService;


/**
 *
 * @author OLAWALE
 */
@Controller
@RequestMapping( value = "/billing")
public class CouponController{
    
    private CouponMasterService couponMasterService;
    private CouponMappingTypeService couponMappingTypeService;
    private ContentPortalMasterService contentPortalMasterService;
    private SubscriptionMasterService subscriptionMasterService;
    
    
    
    
    
    @RequestMapping(value = "/coupon_list", method = RequestMethod.GET)
    public String couponMasterList( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
     //   prepareMenu(userId, model);
          
        List<Object[]> couponMasterList = couponMasterService.readAll();
        model.addAttribute("couponMasterList", couponMasterList);
        
        return "admin/coupon_list";

    }
    
      @RequestMapping(value = "/mc_add_coupon", method = RequestMethod.GET)
    public String mcAddCoupon( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/coupon_add";

    }
    
    @RequestMapping(value = "/add_coupon", method = RequestMethod.POST)
    public String addCoupon(@RequestParam("title") String cTitle, @RequestParam("code") String cCode, @RequestParam("discount") String discount, @RequestParam("desc") String desc, @RequestParam("country") String country, @RequestParam("currency") String currency, @RequestParam("amount") String amount, @RequestParam("minAmt") String minAmt, @RequestParam("maxAmt") String maxAmt, @RequestParam("startDate") String startDate,@RequestParam("expiryDate") String expiryDate, HttpSession session, ModelMap model)
    
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        String response = couponMasterService.create(cTitle, cCode, discount, desc, country, currency, amount, minAmt, maxAmt, startDate, expiryDate);
        model.addAttribute("feedback", response);
        
        List<Object[]> couponMasterList = couponMasterService.readAll();
        model.addAttribute("couponMasterList", couponMasterList);
        
        return "admin/coupon_list";
        
    }

    
    
     @RequestMapping(value = "/coupon_map", method = RequestMethod.GET)
    public String mapCoupon( @RequestParam("id") String id, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        List<Object []> subscriptionObjectList = couponMappingTypeService.readAllSubscription(0);
        
        model.addAttribute("subscriptionMasterObjectList", subscriptionObjectList);
        
        Object[] couponMasterObject = couponMasterService.read(Integer.parseInt(id));
        model.addAttribute("couponMasterObject", couponMasterObject);
         
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/coupon_map";

    }
    
   @RequestMapping(value = "/map_coupon", method = RequestMethod.POST)
    public String mapCoupon(HttpServletRequest request, HttpSession session,ModelMap model)
    {
       // @RequestParam("couponId") String couponId, @RequestParam("portal") String portal, @RequestParam("mapType") String mapType, @RequestParam("mapId") String mapId
        String couponId = request.getParameter("couponId");
        String portal = request.getParameter("portal");
        String mapType = request.getParameter("mapType");
        String mapId [] = request.getParameterValues("mapId");
        
        System.out.println("1: " + couponId);
        System.out.println("2: " + portal);
        System.out.println("3: " + mapType);
        System.out.println("4: " + mapId[0]);
        
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
        
        String subIds[] = request.getParameterValues("mapId");
        // prepareMenu(userId, model);
        //  
        // String subIds [] = request.getParameterValues("sub_id");
        
        
        Object [] subscriptionMasterObject = couponMappingTypeService.readBySubscriptionId(0);
//        String reply = couponMappingTypeService.createSubscriptionMapping(Integer.parseInt(couponId), portal, mapType, Integer.parseInt(subscriptionMasterObject[0].toString()));
        String reply = couponMappingTypeService.createSubscriptionMapping(Integer.parseInt(couponId), portal, mapType, Integer.parseInt(subIds[0]));
        // Object[] couponMasterObject = couponMasterService.read(0);
        
      /** if(subIds==null)
        {
            
        }
        else if(subIds.length==0)
        {
            
        }
        else
        {
            for(int count=0; count<subIds.length; ++count)
            {
                reply = couponMappingTypeService.createSubscriptionMapping(Integer.parseInt(subscriptionMasterObject[0].toString()),portal, mapType, Integer.parseInt(subIds[count]));
            }
        }*/
         
       
        List<Object[]> subscriptionMasterObjectList = couponMappingTypeService.readAllSubscription(0);
        model.addAttribute("subscriptionMasterObjectList", subscriptionMasterObjectList);
        
        List<Object[]> couponMasterList = couponMasterService.readAll();
        model.addAttribute("couponMasterList", couponMasterList);
        
        return "admin/coupon_list";

    }
    
   @RequestMapping(value = "/get_coupon_by_country", method = RequestMethod.POST)
    public String getCouponByCountry(@RequestParam("country") String countryId, ModelMap model, HttpSession session)
    {
        
      if(!sessionValid(session))
        {
           return "login";
       }
      // List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
       // model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        
        List<Object[]> allCountryCouponList = couponMasterService.readAll();
        
         
            
        return "admin/coupon_list";
    }
    
     
    
    private boolean sessionValid(HttpSession session)
    {
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        return userObject!=null;
    }

   public void setCouponMasterService(CouponMasterService couponMasterService) {
        this.couponMasterService = couponMasterService;
    }

    public void setCouponMappingTypeService(CouponMappingTypeService couponMappingTypeService) {
        this.couponMappingTypeService = couponMappingTypeService;
    }
        
    
     public void setContentPortalMasterService(ContentPortalMasterService contentPortalMasterService) {
        this.contentPortalMasterService = contentPortalMasterService;
    }
    
      public void setSubscriptionMasterService(SubscriptionMasterService subscriptionMasterService) {
        this.subscriptionMasterService = subscriptionMasterService;
    }
    
}

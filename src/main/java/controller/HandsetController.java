/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MenuMasterDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ContentCategoryMasterService;
import service.ContentMasterService;
import service.HandsetProfileMasterService;
import service.MenuChildService;
import service.MenuMappingService;
import service.MenuMasterService;
import service.UserMasterService;

/**
 *
 * @author OLAWALE
 */
@Controller
@RequestMapping( value = "/hanset")
public class HandsetController{
    
//    private UserMasterService userMasterService;
    private MenuMasterService menuMasterService;
//    private MenuMappingService menuMappingService;
    private MenuChildService menuChildService;
//    private ContentMasterService contentMasterService;
//    private ContentCategoryMasterService contentCategoryMasterService;
   
    private HandsetProfileMasterService handsetProfileMasterService;
    
    //Checked...
    @RequestMapping(value = "/mc_list_handset", method = RequestMethod.GET)
    public String listHandset( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        List<Object[]> allHandsetProfileMasterObjectList = handsetProfileMasterService.readAll();
        model.addAttribute("allHandsetProfileMasterObjectList", allHandsetProfileMasterObjectList);
        
        return "admin/handset_profile_master";

    }
    
    @RequestMapping(value = "/mc_add_handset", method = RequestMethod.GET)
    public String addHandset( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        List<Object[]> allHandsetProfileMasterObjectList = handsetProfileMasterService.readAll();
        model.addAttribute("allHandsetProfileMasterObjectList", allHandsetProfileMasterObjectList);
        
        return "admin/handset_profile_master_add";

    }
    
    @RequestMapping(value = "/add_handset", method = RequestMethod.POST)
    public String addHandset(@RequestParam("user_agent") String userAgent, @RequestParam("make") String make, @RequestParam("model") String handsetModel, @RequestParam("os") String os, @RequestParam("os_version") String osVersion, @RequestParam("width_height") String widthHeight, HttpSession session, ModelMap model)
    {
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String reply = handsetProfileMasterService.create(userAgent, make, handsetModel, os, osVersion, widthHeight);
        
//        prepareMenu(userId, model);
        
        List<Object[]> allHandsetProfileMasterObjectList = handsetProfileMasterService.readAll();
        model.addAttribute("allHandsetProfileMasterObjectList", allHandsetProfileMasterObjectList);
        
        return "admin/handset_profile_master";

    }
    
    //Checked...
    @RequestMapping(value = "/save_handset", method = RequestMethod.POST)
    public String saveHandset(@RequestParam("id") String handsetProfileMasterId, @RequestParam("user_agent") String userAgent, @RequestParam("make") String make, @RequestParam("model") String handsetModel, @RequestParam("os") String os, @RequestParam("os_version") String osVersion, @RequestParam("width_height") String widthHeight, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        String reply = handsetProfileMasterService.update(Integer.parseInt(handsetProfileMasterId), userAgent, make, handsetModel, os, osVersion, widthHeight);
        
//        prepareMenu(userId, model);
        
        List<Object[]> allHandsetProfileMasterObjectList = handsetProfileMasterService.readAll();
        model.addAttribute("allHandsetProfileMasterObjectList", allHandsetProfileMasterObjectList);
        
        return "admin/handset_profile_master";

    }
    
    //Checked...
    @RequestMapping(value = "/edit_handset_profile_mst_details", method = RequestMethod.GET)
    public String editUserDetails(@RequestParam("id") String handsetProfileManagerId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            Object[] handsetProfileMasterObject = handsetProfileMasterService.readHandsetProfileMaster(Integer.parseInt(handsetProfileManagerId));
            
            model.addAttribute("handsetProfileMasterObject", handsetProfileMasterObject);
           
//            prepareMenu(userId, model);
            

        return "admin/handset_profile_master_edit";
    }
    
    private void prepareMenu(String userId, ModelMap model)
    {
                List<Object[]> menuMasterObjectList = menuMasterService.readUserMenuMaster(Integer.parseInt(userId.toString()));
                Map<String, List<Object[]>> menuChildObjectMap = new HashMap<>();
                for(int counter=0; counter<menuMasterObjectList.size(); ++counter)
                {
                    List<Object[]> menuChildObjectList = menuChildService.readUserMenuChild(Integer.parseInt(userId.toString()), Integer.parseInt(menuMasterObjectList.get(counter)[0].toString()));
                    menuChildObjectMap.put(String.valueOf(menuMasterObjectList.get(counter)[0]), menuChildObjectList);
                }
                
                model.addAttribute("menuMasterObjectList", menuMasterObjectList);
                model.addAttribute("menuChildObjectMap", menuChildObjectMap);
                
                model.addAttribute("user_id", userId);
    }
    
//    @RequestMapping(value = "/user_list", method = RequestMethod.GET)
//    public String userList(ModelMap model)
//    {
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
//        return "admin/user_list";
//    }

//    public void setUserMasterService(UserMasterService userMasterService) {
//        this.userMasterService = userMasterService;
//    }
//
    private boolean sessionValid(HttpSession session)
    {
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        return userObject!=null;
    }
    
    public void setMenuMasterService(MenuMasterService menuMasterService) {
        this.menuMasterService = menuMasterService;
    }
//
//    public void setMenuMappingService(MenuMappingService menuMappingService) {
//        this.menuMappingService = menuMappingService;
//    }
//
    public void setMenuChildService(MenuChildService menuChildService) {
        this.menuChildService = menuChildService;
    }
    
    
//
//    public void setContentMasterService(ContentMasterService contentMasterService) {
//        this.contentMasterService = contentMasterService;
//    }
//
//    public void setContentCategoryMasterService(ContentCategoryMasterService contentCategoryMasterService) {
//        this.contentCategoryMasterService = contentCategoryMasterService;
//    }

    public void setHandsetProfileMasterService(HandsetProfileMasterService handsetProfileMasterService) {
        this.handsetProfileMasterService = handsetProfileMasterService;
    }
    
    
    
    
    
    
    
}

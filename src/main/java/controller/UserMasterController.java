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
import service.MenuChildService;
import service.MenuHistoryService;
import service.MenuMappingService;
import service.MenuMasterService;
import service.UserMasterService;

/**
 *
 * @author OLAWALE
 */
@Controller
@RequestMapping( value = "/usermaster")
public class UserMasterController{
    
    private UserMasterService userMasterService;
    private MenuMasterService menuMasterService;
    private MenuMappingService menuMappingService;
    private MenuChildService menuChildService;
    private ContentMasterService contentMasterService;
    private ContentCategoryMasterService contentCategoryMasterService;
    private MenuHistoryService menuHistoryService;
   
    
    //Checked....
    @RequestMapping(value = "/mc_add_user", method = RequestMethod.GET)
    public String mcAddUser(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//                List<Object[]> menuMasterObjectList = menuMasterService.readUserMenuMaster(Integer.parseInt(userId.toString()));
//                Map<String, List<Object[]>> menuChildObjectMap = new HashMap<>();
//                for(int counter=0; counter<menuMasterObjectList.size(); ++counter)
//                {
//                    List<Object[]> menuChildObjectList = menuChildService.readUserMenuChild(Integer.parseInt(userId.toString()), Integer.parseInt(menuMasterObjectList.get(counter)[0].toString()));
//                    menuChildObjectMap.put(String.valueOf(menuMasterObjectList.get(counter)[0]), menuChildObjectList);
//                }
                
//                Object [] menuChildObject = menuChildService.readMenuChild(Integer.parseInt(mcId));
//                model.addAttribute("menuChildObject", menuChildObject);
                
                
//                model.addAttribute("menuMasterObjectList", menuMasterObjectList);
//                model.addAttribute("menuChildObjectMap", menuChildObjectMap);
//                
//                model.addAttribute("user_id", userId);
                
  

//        return "admin/user_master_add";
        return "admin/user_master_add";
    }
    
    @RequestMapping(value = "/mc_list_user", method = RequestMethod.GET)
    public String mcListUser(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
        List<Object[]> userMasterObjectList = userMasterService.readAll();
        model.addAttribute("userMasterObjectList", userMasterObjectList);

        return "admin/user_master_list";
    }
    
    //Checked.....
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String userList(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName, @RequestParam("comp") String company, @RequestParam("desig") String designation, @RequestParam("email") String email, @RequestParam("phone") String phoneNumber, @RequestParam("type") String type, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        String response = userMasterService.create(username, password, firstName, lastName, type);
        String response = userMasterService.create(username, password, firstName, lastName, type, company, designation, email, phoneNumber);
        model.addAttribute("feedback", response);
//        if(response.equals("success"))
//        {
////            System.out.println("Saved..");
//            model.addAttribute("feedback", "Operation Successful");
//        }
//        else if(response.equals("failure"))
//        {
//            model.addAttribute("feedback", "Operation Not Successful");
//        }
//        else if(response.equals("exist"))
//        {
//            model.addAttribute("feedback", "User Already Exist");
//        }
        
//        prepareMenu(userId, model);
        
        List<Object[]> userMasterObjectList = userMasterService.readAll();
        model.addAttribute("userMasterObjectList", userMasterObjectList);
        
        return "admin/user_master_add";
    }

    
    //Checked...
    @RequestMapping(value = "/edit_user_master_status", method = RequestMethod.GET)
    public String editUserMasterStatus(@RequestParam("id") String userId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = userMasterService.updateStatus(Integer.parseInt(userId), Integer.parseInt(status));
                
            model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", status);
            }
            else
            {
                model.addAttribute("status", "none");
            }

        return "admin/outcome";
    }
    
    //Checked....
    @RequestMapping(value = "/edit_user_details", method = RequestMethod.GET)
    public String editUserDetails(@RequestParam("id") String id, HttpSession session, ModelMap model)
    {
        if(!sessionValid(session))
        {
            return "login";
        }
            Object[] userObject = userMasterService.readUser(Integer.parseInt(id));
            
            model.addAttribute("userObject", userObject);
           
//            prepareMenu(userId, model);
            

        return "admin/user_master_edit";
    }
    
    //Checked....
    @RequestMapping(value = "/save_user_details", method = RequestMethod.POST)
    public String saveUserDetails(@RequestParam("id") String id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName, @RequestParam("type") String type, @RequestParam("comp") String company, @RequestParam("desig") String designation, @RequestParam("email") String email, @RequestParam("phone") String phoneNumber, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String response = userMasterService.update(Integer.parseInt(id), username, password, firstName, lastName, type, company, designation, email, phoneNumber);
        model.addAttribute("feedback", response);
//        if(response.equals("success"))
//        {
////            System.out.println("Saved..");
//        }
        
//        prepareMenu(userId, model);s
        
        List<Object[]> userMasterObjectList = userMasterService.readAll();
        model.addAttribute("userMasterObjectList", userMasterObjectList);
        
        return "admin/user_master_list";
    }
    
    private void updateMenuHistory(String mmid, String mcid, HttpSession session)
    {
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        String response = menuHistoryService.create(Integer.parseInt(userObject[0].toString()), Integer.parseInt(mmid), Integer.parseInt(mcid));
        
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

//                List<Object[]> userMasterObjectList = userMasterService.readAll();
//                model.addAttribute("userMasterObjectList", userMasterObjectList);
                model.addAttribute("menuMasterObjectList", menuMasterObjectList);
                model.addAttribute("menuChildObjectMap", menuChildObjectMap);
                
                model.addAttribute("user_id", userId);
    }
    
    private boolean sessionValid(HttpSession session)
    {
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        return userObject!=null;
    }
    
    public void setUserMasterService(UserMasterService userMasterService) {
        this.userMasterService = userMasterService;
    }

    public void setMenuMasterService(MenuMasterService menuMasterService) {
        this.menuMasterService = menuMasterService;
    }

    public void setMenuMappingService(MenuMappingService menuMappingService) {
        this.menuMappingService = menuMappingService;
    }

    public void setMenuChildService(MenuChildService menuChildService) {
        this.menuChildService = menuChildService;
    }

    public void setContentMasterService(ContentMasterService contentMasterService) {
        this.contentMasterService = contentMasterService;
    }

    public void setContentCategoryMasterService(ContentCategoryMasterService contentCategoryMasterService) {
        this.contentCategoryMasterService = contentCategoryMasterService;
    }

    public void setMenuHistoryService(MenuHistoryService menuHistoryService) {
        this.menuHistoryService = menuHistoryService;
    }
    
    
    
    
    
    
    
}

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
@RequestMapping( value = "/menu")
public class MenuController{
    
//    private UserMasterService userMasterService;
    private MenuMasterService menuMasterService;
    private MenuMappingService menuMappingService;
    private MenuChildService menuChildService;
    
        private UserMasterService userMasterService;
        
    private MenuHistoryService menuHistoryService;

//    private ContentMasterService contentMasterService;
//    private ContentCategoryMasterService contentCategoryMasterService;
   
    
    
    @RequestMapping(value = "/menu_list", method = RequestMethod.GET)
    public String menuList( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        List<Object[]> menuLists = menuMappingService.readAllMenuMapping();
//        model.addAttribute("userLists", menuLists);
        return "admin/user_list";
    }
    
    @RequestMapping(value = "/mc_list_menu", method = RequestMethod.GET)
    public String mcListMenu(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        prepareMenu(userId, model);
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
//        return "admin/menu_master";
        return "admin/menu_master_list";
    }
    
    @RequestMapping(value = "/mc_add_menu", method = RequestMethod.GET)
    public String mcAddMenu(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        prepareMenu(userId, model);
//        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
//        return "admin/menu_master_add";
        return "admin/menu_master_add";
    }

    //Checked...
    @RequestMapping(value = "/mc_add_menu_child", method = RequestMethod.GET)
    public String mcAddMenuChild(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        prepareMenu(userId, model);
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
        return "admin/menu_child_add";
    }
    
    @RequestMapping(value = "/get_menuchild_by_status_type", method = RequestMethod.GET)
    public String getMenuChildByStatus(@RequestParam("menu") String menuId, @RequestParam("status") String menuChildMasterStatus, ModelMap model, HttpSession session)
    {
        
//        prepareMenu(userId, model);
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
       
            
            if(menuId.equalsIgnoreCase("0") && menuChildMasterStatus.equalsIgnoreCase("-1"))
            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
                List<Object[]> allMenuChildObjectList = menuChildService.readAll();
                model.addAttribute("allMenuChildObjectList", allMenuChildObjectList);
                
            }
            else if(!menuId.equalsIgnoreCase("0") && menuChildMasterStatus.equalsIgnoreCase("-1"))
            {
                List<Object[]> allMenuChildObjectList = menuChildService.readByMenu(Integer.parseInt(menuId));
                model.addAttribute("allMenuChildObjectList", allMenuChildObjectList);
                
                model.addAttribute("menuChildMasterStatus", menuChildMasterStatus);
            }
            else if(menuId.equalsIgnoreCase("0") && !menuChildMasterStatus.equalsIgnoreCase("-1"))
            {
                List<Object[]> allMenuChildObjectList = menuChildService.readByStatus(Integer.parseInt(menuChildMasterStatus));
                model.addAttribute("allMenuChildObjectList", allMenuChildObjectList);
                
                model.addAttribute("menuChildMasterStatus", menuChildMasterStatus);
            }
            else if(!menuId.equalsIgnoreCase("0") && !menuChildMasterStatus.equalsIgnoreCase("-1"))
            {
                List<Object[]> allMenuChildObjectList = menuChildService.readByMenuStatus(Integer.parseInt(menuId), Integer.parseInt(menuChildMasterStatus));
                model.addAttribute("allMenuChildObjectList", allMenuChildObjectList);
                
                model.addAttribute("menuChildMasterStatus", menuChildMasterStatus);
            }
            
            model.addAttribute("menuId", menuId);
            model.addAttribute("menuChildMasterStatus", menuChildMasterStatus);
                
//            }
//        }
        return "admin/menu_child_list";
    }
    
    
    @RequestMapping(value = "/get_menu_by_status_type", method = RequestMethod.GET)
    public String getBannerByStatus(@RequestParam("status") String menuMasterStatus, ModelMap model, HttpSession session)
    {
        
//        prepareMenu(userId, model);
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
//        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
//        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
       
            
            if(menuMasterStatus.equalsIgnoreCase("-1"))
            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
                List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
                model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
                
            }
//            else if(!contentTypeId.equalsIgnoreCase("0") && bannerMasterStatus.equalsIgnoreCase("-1"))
//            {
//                List<Object[]> allBannerMasterObjectList = bannerMasterService.readByType(Integer.parseInt(contentTypeId));
//                model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
//                
//                model.addAttribute("bannerMasterStatus", bannerMasterStatus);
//            }
            else if(!menuMasterStatus.equalsIgnoreCase("-1"))
            {
                List<Object[]> allMenuMasterObjectList = menuMasterService.readByStatus(Integer.parseInt(menuMasterStatus));
                model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
                
                model.addAttribute("menuMasterStatus", menuMasterStatus);
            }
//            else if(!contentTypeId.equalsIgnoreCase("0") && !bannerMasterStatus.equalsIgnoreCase("-1"))
//            {
//                List<Object[]> allBannerMasterObjectList = bannerMasterService.readByTypeStatus(Integer.parseInt(contentTypeId), Integer.parseInt(bannerMasterStatus));
//                model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
//                
//                model.addAttribute("bannerMasterStatus", bannerMasterStatus);
//            }
            
//            model.addAttribute("contentTypeId", contentTypeId);
            model.addAttribute("menuMasterStatus", menuMasterStatus);
                
//            }
//        }
        return "admin/menu_master_list";
    }
    
    @RequestMapping(value = "/add_menu", method = RequestMethod.POST)
    public String addMenu(@RequestParam("title") String title, @RequestParam("url") String url, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String response = menuMasterService.create(title, url);
        model.addAttribute("feedback", response);
//        prepareMenu(userId, model);
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
//            model.addAttribute("feedback", "Menu Already Exist");
//        }
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        updateMenuMenuChild(session);
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
//        return "admin/menu_master";
        return "admin/menu_master_list";
    }
    
    //Checked...
    @RequestMapping(value = "/add_menu_child", method = RequestMethod.POST)
    public String addMenuChild(@RequestParam("menu_id") String menuId, @RequestParam("title") String title, @RequestParam("url") String url, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String response = menuChildService.create(Integer.parseInt(menuId), title, url);
        model.addAttribute("feedback", response);
//        prepareMenu(userId, model);
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
//            model.addAttribute("feedback", "Submenu Already Exist");
//        }
        List<Object[]> allMenuChildObjectList = menuChildService.readAll();
        model.addAttribute("allMenuChildObjectList", allMenuChildObjectList);
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        
//        updateMenuMenuChild(session);
        
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
//        return "admin/menu_child";
        return "admin/menu_child_list";
    }
    
    @RequestMapping(value = "/mc_list_menu_child", method = RequestMethod.GET)
    public String mcListMenuChild(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        prepareMenu(userId, model);
        List<Object[]> allMenuChildObjectList = menuChildService.readAll();
        model.addAttribute("allMenuChildObjectList", allMenuChildObjectList);
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
        return "admin/menu_child_list";
    }
    
    //Checked...
    @RequestMapping(value = "/mc_map_screen", method = RequestMethod.GET)
    public String mcMappingScreen( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        List<Object[]> allMenuMappingObjectList = menuMappingService.readAll();
        model.addAttribute("allMenuMappingObjectList", allMenuMappingObjectList);
        
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
//        return "admin/menu_mapping";
        return "admin/user_access";
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
    
    //Checked...
    @RequestMapping(value = "/edit_menu_master_status", method = RequestMethod.GET)
    public String editMenuMasterStatus(@RequestParam("id") String userId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        System.out.println("OKKK");
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = menuMasterService.updateStatus(Integer.parseInt(userId), Integer.parseInt(status));
                
            model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", status);
//                updateMenuMenuChild(session);
            }
            else
            {
                model.addAttribute("status", "none");
            }

        return "admin/outcome";
    }
    
    //Checked...
    @RequestMapping(value = "/edit_menu_master_details", method = RequestMethod.GET)
    public String editMenuMasterDetails(@RequestParam("id") String menuMasterId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            Object[] menuMasterObject = menuMasterService.readMenu(Integer.parseInt(menuMasterId));
            
            model.addAttribute("menuMasterObject", menuMasterObject);
           
//            prepareMenu(userId, model);
            

        return "admin/menu_master_edit";
    }
    
    //Checked...
    @RequestMapping(value = "/save_menu_master_details", method = RequestMethod.POST)
    public String saveMenuMasterDetails(@RequestParam("id") String menuMasterId, @RequestParam("title") String title, @RequestParam("url") String url, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            model.addAttribute("feedback", response);
            
            
            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
            
            updateMenuMenuChild(session);
           
//            prepareMenu(userId, model);
            

        return "admin/menu_master_list";
    }
    
//    private void updateMenu(HttpSession session)
//    {
//        Object[] userObject = (Object[]) session.getAttribute("user_object");
//            List<Object[]> menuMasterObjectList = menuMasterService.readUserMenuMaster(Integer.parseInt(userObject[0].toString()));
//            
//            session.setAttribute("menuMasterObjectList", menuMasterObjectList);
//    }
    //Checked...
    @RequestMapping(value = "/edit_menu_child_details", method = RequestMethod.GET)
    public String editMenuChildDetails(@RequestParam("id") String menuChildId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            
        Object[] menuChildObject = menuChildService.readMenuChild(Integer.parseInt(menuChildId));
        model.addAttribute("menuChildObject", menuChildObject);
        
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
           
//            prepareMenu(userId, model);
            
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);

        return "admin/menu_child_edit";
    }
    
    //Checked...
    @RequestMapping(value = "/save_menu_child_details", method = RequestMethod.POST)
    public String saveMenuChildDetails(@RequestParam("id") String menuChildId, @RequestParam("title") String title, @RequestParam("url") String url, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = menuChildService.update(Integer.parseInt(menuChildId), title, url);
            
        model.addAttribute("feedback", response);
        List<Object[]> allMenuChildObjectList = menuChildService.readAll();
        model.addAttribute("allMenuChildObjectList", allMenuChildObjectList);
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
            
            updateMenuMenuChild(session);
//            prepareMenu(userId, model);
            

        return "admin/menu_child_list";
    }
    //Checked...
    private void updateMenuMenuChild(HttpSession session)
    {
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        
        if(userObject[5].toString().equals("ADMIN"))
            {
                List<Object[]> allManuMappingList = menuMappingService.readAllMenuAccess();
                Map<String, List<Object[]>> menuChildObjectMap = new HashMap<>();
                for(int counter=0; counter<allManuMappingList.size(); ++counter)
                {
//                    List<Object[]> menuChildObjectList = menuChildService.readUserMenuChild(Integer.parseInt(user[0].toString()), Integer.parseInt(menuMasterObjectList.get(counter)[0].toString()));
                    List<Object[]> allManuChildMappingList = menuMappingService.readAllMenuChildAccess(Integer.parseInt(allManuMappingList.get(counter)[0].toString()));
                    menuChildObjectMap.put(String.valueOf(allManuMappingList.get(counter)[0]), allManuChildMappingList);

                }
                session.setAttribute("allManuMappingList", allManuMappingList);
                session.setAttribute("menuChildObjectMap", menuChildObjectMap);
            }  
            else
            {
            List<Object[]> allManuMappingList = menuMappingService.readMenuByRole(userObject[5].toString());
                Map<String, List<Object[]>> menuChildObjectMap = new HashMap<>();
                for(int counter=0; counter<allManuMappingList.size(); ++counter)
                {
//                    List<Object[]> menuChildObjectList = menuChildService.readUserMenuChild(Integer.parseInt(user[0].toString()), Integer.parseInt(menuMasterObjectList.get(counter)[0].toString()));
                    List<Object[]> allManuChildMappingList = menuMappingService.readMenuChildByRole(userObject[5].toString(), Integer.parseInt(allManuMappingList.get(counter)[0].toString()));
                    menuChildObjectMap.put(String.valueOf(allManuMappingList.get(counter)[0]), allManuChildMappingList);

                }
                session.setAttribute("allManuMappingList", allManuMappingList);
                session.setAttribute("menuChildObjectMap", menuChildObjectMap);
            }
        
//        List<Object[]> menuMasterObjectList = menuMasterService.readUserMenuMaster(Integer.parseInt(userObject[0].toString()));
//        Map<String, List<Object[]>> menuChildObjectMap = new HashMap<>();
//        for(int counter=0; counter<menuMasterObjectList.size(); ++counter)
//        {
//            List<Object[]> menuChildObjectList = menuChildService.readUserMenuChild(Integer.parseInt(userObject[0].toString()), Integer.parseInt(menuMasterObjectList.get(counter)[0].toString()));
//            menuChildObjectMap.put(String.valueOf(menuMasterObjectList.get(counter)[0]), menuChildObjectList);
////                    menuChildObjectMap.put(String.valueOf(counter), menuChildObjectList);
//        }
//        session.setAttribute("menuChildObjectMap", menuChildObjectMap);
//        session.setAttribute("menuMasterObjectList", menuMasterObjectList);
    }
    //Checked...
    @RequestMapping(value = "/edit_menu_child_status", method = RequestMethod.GET)
    public String editMenuChildStatus(@RequestParam("id") String userId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = menuChildService.updateStatus(Integer.parseInt(userId), Integer.parseInt(status));
                
            model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", status);
//                updateMenuMenuChild(session);
            }
            else
            {
                model.addAttribute("status", "none");
            }

        return "admin/outcome";
    }
    
    //Checked...
    @RequestMapping(value = "/edit_menu_mapping_status", method = RequestMethod.GET)
    public String editMenuMappingStatus(@RequestParam("id") String userId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = menuMappingService.updateStatus(Integer.parseInt(userId), Integer.parseInt(status));
                
            model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", status);
//                updateMenuMenuChild(session);
            }
            else
            {
                model.addAttribute("status", "none");
            }

        return "admin/outcome";
    }
    
    
    @RequestMapping(value = "/get_role_menu_map", method = RequestMethod.GET)
    public String getRoleMenuMap(@RequestParam("roleType") String roleType, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        
        
    List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
                    
            for(int count=0; count < allMenuMasterObjectList.size(); ++count)
        {
            Object[] menuMappingObject = menuMappingService.readByRoleMenu(Integer.parseInt(allMenuMasterObjectList.get(count)[0].toString()), roleType);
            
            
            
            if(menuMappingObject==null)
            {
                System.out.println("val: " + null);
                allMenuMasterObjectList.get(count)[4] = 0;
                
            }
            else if(menuMappingObject[3].toString().equals("0"))
            {
                allMenuMasterObjectList.get(count)[4] = 0;
                System.out.println("onj: " + menuMappingObject[3]);
            }
            else
            {
                allMenuMasterObjectList.get(count)[4] = 1;
            }
        }
        
        
     
            
      

            model.addAttribute("roleType", roleType);

            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);


        return "admin/user_menu_mapping";


        
    }
    
    @RequestMapping(value = "/get_role_menu_child_map", method = RequestMethod.GET)
//    public String getRoleMenuChildMap(@RequestParam("roleType") String roleType, @RequestParam("menuId") String menuId, HttpSession session,ModelMap model)
    public String getRoleMenuChildMap(@RequestParam("roleType") String roleType, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//        List<Object[]> allMenuChildObjectList = menuChildService.readByMenu(Integer.parseInt(menuId));
        List<Object[]> allMenuChildObjectList = menuChildService.readAll();
                    
        for(int count=0; count < allMenuChildObjectList.size(); ++count)
        {
//            Object[] menuChildMappingObject = menuMappingService.readByRoleMenuMenuChild(Integer.parseInt(allMenuChildObjectList.get(count)[0].toString()), roleType, Integer.parseInt(menuId));
            Object[] menuChildMappingObject = menuMappingService.readByRoleMenuChild(Integer.parseInt(allMenuChildObjectList.get(count)[0].toString()), roleType);
            
            
            
            if(menuChildMappingObject==null)
            {
                System.out.println("val: " + null);
                allMenuChildObjectList.get(count)[5] = 0;
                
            }
            else if(menuChildMappingObject[4].toString().equals("0"))
            {
                allMenuChildObjectList.get(count)[5] = 0;
                System.out.println("onj: " + menuChildMappingObject[4]);
            }
            else
            {
                allMenuChildObjectList.get(count)[5] = 1;
            }
        }

        model.addAttribute("roleType", roleType);
//        model.addAttribute("menuId", menuId);

        model.addAttribute("allMenuChildObjectList", allMenuChildObjectList);
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);


        return "admin/user_menu_mapping_1";
 
    }
    
    
    //Checked...
    @RequestMapping(value = "/mc_add_user_menu_mapping", method = RequestMethod.GET)
    public String mcAddUserMenuMapping( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        
        List<Object[]> allUserMasterObjectList = userMasterService.readAll();
//        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        model.addAttribute("allUserMasterObjectList", allUserMasterObjectList);
//        model.addAttribute("status", "none");
//        return "admin/user_menu_menu_child_mapping";
//        return "admin/user_menu_menu_child_mapping";
        return "admin/user_menu_mapping";
    }
    
    
    @RequestMapping(value = "/mc_add_user_menu_mapping1", method = RequestMethod.GET)
    public String mcAddUserMenuMapping1(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        prepareMenu(userId, model);
        
        List<Object[]> allUserMasterObjectList = userMasterService.readAll();
        List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
        model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        model.addAttribute("allUserMasterObjectList", allUserMasterObjectList);
//        model.addAttribute("status", "none");
//        return "admin/user_menu_menu_child_mapping";
//        return "admin/user_menu_menu_child_mapping";
        return "admin/user_menu_mapping_1";
    }
    
    //Checked...
    @RequestMapping(value = "/load_child_menu", method = RequestMethod.GET)
    public String loadChildMenu(@RequestParam("user_id") String userId, @RequestParam("menu_id") String menuId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        List<Object[]> menuChildMenus = menuChildService.readMenuChildMenus(Integer.parseInt(menuId));
        List<Object[]> allUserMenuMapping = menuMappingService.readUserMenuMapping(Integer.parseInt(userId), Integer.parseInt(menuId));
        
        model.addAttribute("menuChildMenus", menuChildMenus);
        model.addAttribute("allUserMenuMapping", allUserMenuMapping);
        
        
        
        return "admin/menu_child_fragment";
    }
//    menuId: menuId,
//                     roleType: roleType,
//                    menuMappingStatus: menuMappingStatus},
    
    @RequestMapping(value = "/add_priviledge", method = RequestMethod.GET)
    public String addPriviledge(@RequestParam("menuId") String menuId, @RequestParam("roleType") String roleType, @RequestParam("menuMappingStatus") String menuMappingStatus, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }

//        String response = menuMappingService.addPriviledge(Integer.parseInt(userId), Integer.parseInt(menuId), Integer.parseInt(menuItem));
        String response = menuMappingService.addPriviledge(Integer.parseInt(menuId), roleType, Integer.parseInt(menuMappingStatus));
//        contentTypePortalMappingService.createUpdate
        
        model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", menuMappingStatus);
            }
            else
            {
                model.addAttribute("status", "none");
            }
        return "admin/outcome";
    }
    
    @RequestMapping(value = "/add_priviledge_child", method = RequestMethod.GET)
    public String addPriviledgeChild(@RequestParam("menuId") String menuId, @RequestParam("menuChildId") String menuChildId, @RequestParam("roleType") String roleType, @RequestParam("menuChildMappingStatus") String menuChildMappingStatus, HttpSession session, ModelMap model)
    {
        System.out.println("111");
        if(!sessionValid(session))
        {
            return "login";
        }

//        String response = menuMappingService.addPriviledge(Integer.parseInt(userId), Integer.parseInt(menuId), Integer.parseInt(menuItem));
        String response = menuMappingService.addPriviledgeChild(Integer.parseInt(menuId), Integer.parseInt(menuChildId), roleType, Integer.parseInt(menuChildMappingStatus));
//        contentTypePortalMappingService.createUpdate
        System.out.println("222");
        System.out.println(response);
        model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", menuChildMappingStatus);
            }
            else
            {
                model.addAttribute("status", "none");
            }
        return "admin/outcome";
    }
    
    private void updateMenuHistory(String mmid, String mcid, HttpSession session)
    {
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        String response = menuHistoryService.create(Integer.parseInt(userObject[0].toString()), Integer.parseInt(mmid), Integer.parseInt(mcid));
        
    }
    
    //Checked...
//    @RequestMapping(value = "/add_priviledge", method = RequestMethod.GET)
//    public String addPriviledge(@RequestParam("user_id") String userId, @RequestParam("menu_id") String menuId, @RequestParam("menu_item") String menuItem, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//
//        String response = menuMappingService.addPriviledge(Integer.parseInt(userId), Integer.parseInt(menuId), Integer.parseInt(menuItem));
//        
//        
//        model.addAttribute("response", response);
//            if(response.equals("success"))
//            {
//                model.addAttribute("status", 1);
//            }
//            else
//            {
//                model.addAttribute("status", "none");
//            }
//        return "admin/outcome";
//    }

    private boolean sessionValid(HttpSession session)
    {
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        return userObject!=null;
    }
    
    public void setMenuMappingService(MenuMappingService menuMappingService) {
        this.menuMappingService = menuMappingService;
    }

    public void setMenuMasterService(MenuMasterService menuMasterService) {
        this.menuMasterService = menuMasterService;
    }

    public void setMenuChildService(MenuChildService menuChildService) {
        this.menuChildService = menuChildService;
    }

    public void setUserMasterService(UserMasterService userMasterService) {
        this.userMasterService = userMasterService;
    }

    public void setMenuHistoryService(MenuHistoryService menuHistoryService) {
        this.menuHistoryService = menuHistoryService;
    }
    
    
    
    
    
    
    
}

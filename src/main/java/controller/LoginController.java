/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MenuMasterDao;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ContentCategoryMasterService;
import service.ContentMasterService;
import service.HistoryService;
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
@RequestMapping( value = "/login")
public class LoginController{
    
    private UserMasterService userMasterService;
    private MenuMasterService menuMasterService;
    private MenuMappingService menuMappingService;
    private MenuChildService menuChildService;
    private ContentMasterService contentMasterService;
    private ContentCategoryMasterService contentCategoryMasterService;
    
    private HistoryService historyService;
    private MenuHistoryService menuHistoryService;
   
    
    //Checked...
    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestParam("email") String username, @RequestParam("password") String password, @RequestParam("remember") String remember, ModelMap model, HttpServletRequest request, HttpSession session){return null;}
    public String login( ModelMap model, HttpServletRequest request, HttpSession session)
    {
//        @RequestParam("email") String username, @RequestParam("password") String password,
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        String rmb = request.getParameter("remember");
        
        System.out.println("OKKKK");
        Object[] user = userMasterService.login(username, password);
        System.out.println("u: " + user);
        if(user!=null)
        {
            String addr = request.getRemoteAddr();
            System.out.println("Addr: " + addr);
            
            String timeStamp = new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(Calendar.getInstance().getTime());
            List<Object[]> allManuMappingList = null;
            Map<String, List<Object[]>> menuChildObjectMap=null;
            if(user[5].toString().equals("ADMIN"))
            {
                allManuMappingList = menuMappingService.readAllMenuAccess();
                menuChildObjectMap = new HashMap<>();
                for(int counter=0; counter<allManuMappingList.size(); ++counter)
                {
//                    List<Object[]> menuChildObjectList = menuChildService.readUserMenuChild(Integer.parseInt(user[0].toString()), Integer.parseInt(menuMasterObjectList.get(counter)[0].toString()));
                    List<Object[]> allManuChildMappingList = menuMappingService.readAllMenuChildAccess(Integer.parseInt(allManuMappingList.get(counter)[0].toString()));
                    menuChildObjectMap.put(String.valueOf(allManuMappingList.get(counter)[0]), allManuChildMappingList);

                }
//                model.addAttribute("allManuMappingList", allManuMappingList);
//                model.addAttribute("menuChildObjectMap", menuChildObjectMap);
            }
            
//                List<Object[]> menuMasterObjectList = menuMasterService.readUserMenuMaster(Integer.parseInt(user[0].toString()));
//                Map<String, List<Object[]>> menuChildObjectMap = new HashMap<>();
//                for(int counter=0; counter<menuMasterObjectList.size(); ++counter)
//                {
//                    List<Object[]> menuChildObjectList = menuChildService.readUserMenuChild(Integer.parseInt(user[0].toString()), Integer.parseInt(menuMasterObjectList.get(counter)[0].toString()));
//                    menuChildObjectMap.put(String.valueOf(menuMasterObjectList.get(counter)[0]), menuChildObjectList);
//
//                }


            
            else
            {
            allManuMappingList = menuMappingService.readMenuByRole(user[5].toString());
                menuChildObjectMap = new HashMap<>();
                for(int counter=0; counter<allManuMappingList.size(); ++counter)
                {
//                    List<Object[]> menuChildObjectList = menuChildService.readUserMenuChild(Integer.parseInt(user[0].toString()), Integer.parseInt(menuMasterObjectList.get(counter)[0].toString()));
                    List<Object[]> allManuChildMappingList = menuMappingService.readMenuChildByRole(user[5].toString(), Integer.parseInt(allManuMappingList.get(counter)[0].toString()));
                    menuChildObjectMap.put(String.valueOf(allManuMappingList.get(counter)[0]), allManuChildMappingList);

                }
//                model.addAttribute("allManuMappingList", allManuMappingList);
//                model.addAttribute("menuChildObjectMap", menuChildObjectMap);
            }
            
            List<Object[]> allMenuHistoryList = menuHistoryService.readAllByUserMaster(Integer.parseInt(user[0].toString()));
            
//            List<String> allManuMappingList = menuMappingService.readByRole(user[5].toString());
//            List<Object[]> allManuMappingList = menuMappingService.readMenuByRole(user[5].toString());
//            System.out.println("sz: " + allManuMappingList.size());
//            System.out.println("sz: " + allManuMappingList.get(0)[1]);
//            
//            List<Object[]> allManuChildMappingList = menuMappingService.readMenuChildByRole(user[5].toString());
//            System.out.println("szc: " + allManuChildMappingList.size());
//            System.out.println("szc: " + allManuChildMappingList.get(0)[7]);
            
//                model.addAttribute("allManuMappingList", allManuMappingList);
//                model.addAttribute("menuChildObjectMap", menuChildObjectMap);
                
//New comment
//                model.addAttribute("user_object", user);
//                model.addAttribute("allMenuHistoryList", allMenuHistoryList);

                
                String reply = historyService.create(username, request.getRemoteAddr(), timeStamp);
                
//                session.setAttribute("u", user);
                
//                return "admin/user_master";
    
//          Update
            session.setAttribute("user_object", user);

        session.setAttribute("allManuMappingList", allManuMappingList);
        session.setAttribute("menuChildObjectMap", menuChildObjectMap);


        session.setAttribute("allMenuHistoryList", allMenuHistoryList);



                return "in";


//            }
        }

        return "out";
    }
    
    
    
    @RequestMapping(value = "/log_out", method = RequestMethod.GET)
    public String logOut(ModelMap model)
    {
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
        return "out";
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home( HttpSession session,ModelMap model)
    {
        System.out.println("CONFIRMfff");
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
//        
//        List<Object[]> userMasterObjectList = userMasterService.readAll();
//        model.addAttribute("userMasterObjectList", userMasterObjectList);
        
        return "admin/index";
//        return "admin/home";
//        return "admin/index";
    }
    
//    @RequestMapping(value = "/user_list", method = RequestMethod.GET)
//    public String userList(ModelMap model)
//    {
//        List<Object[]> objects = userMasterService.readAll();
//        model.addAttribute("userLists", objects);
//        return "admin/user_list";
//    }

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

    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    public void setMenuHistoryService(MenuHistoryService menuHistoryService) {
        this.menuHistoryService = menuHistoryService;
    }
    
    
    
    
    
    
    
}

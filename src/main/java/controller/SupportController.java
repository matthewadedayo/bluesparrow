/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MenuMasterDao;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import service.ContentPortalMasterService;
import service.FaqPortalMappingService;
import service.HandsetProfileMasterService;
import service.MenuChildService;
import service.MenuMappingService;
import service.MenuMasterService;
import service.UserMasterService;
import service.FaqsMasterService;
import service.FeatureMasterService;
import service.SubscriptionFeatureMappingService;
import service.SubscriptionMasterService;

/**
 *
 * @author OLAWALE
 */
@Controller
@RequestMapping( value = "/support")
public class SupportController{
    
//    private UserMasterService userMasterService;
    private MenuMasterService menuMasterService;
//    private MenuMappingService menuMappingService;
    private MenuChildService menuChildService;
//    private ContentMasterService contentMasterService;
//    private ContentCategoryMasterService contentCategoryMasterService;
   private ContentPortalMasterService contentPortalMasterService;
    private HandsetProfileMasterService handsetProfileMasterService;
    private FaqsMasterService faqsMasterService;
    private FeatureMasterService featureMasterService;
    private FaqPortalMappingService faqPortalMappingService;
    private SubscriptionMasterService subscriptionMasterService;
    private SubscriptionFeatureMappingService subscriptionFeatureMappingService;
    
    //Checked...
    @RequestMapping(value = "/mc_list_fag_mster", method = RequestMethod.GET)
    public String mcListFaqsMaster( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        List<Object[]> allFaqsMasterObjectList = faqsMasterService.readAll();
        model.addAttribute("allFaqsMasterObjectList", allFaqsMasterObjectList);
        
        return "admin/faqs_list";

    }
    
    
    @RequestMapping(value = "/edit_faqs_details", method = RequestMethod.GET)
    public String editFaqsDetails(@RequestParam("id") String id, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        Object[] faqsMasterObject = faqsMasterService.read(Integer.parseInt(id));
        model.addAttribute("faqsMasterObject", faqsMasterObject);
        
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        if(faqsMasterObject[3].toString().equals("PORTAL"))
        {
            List<Object> allFaqsPortalIdMappingObjectList = faqPortalMappingService.readPortalIdByFaqsMasterId(Integer.parseInt(id));
            model.addAttribute("allFaqsPortalIdMappingObjectList", allFaqsPortalIdMappingObjectList);
        }
        
        return "admin/faqs_edit";

    }
    
    @RequestMapping(value = "/save_faqs_master", method = RequestMethod.POST)
    public String saveFaqsMaster(HttpServletRequest request, @RequestParam("files") String files, HttpSession session, ModelMap model)
    {
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        System.out.println(question);
//        System.out.println(answer);
//        System.out.println(status);
//        System.out.println(contVisibility);
//        System.out.println(portalList[0]);
        
        String faqsMasterId = request.getParameter("fm_id");
        System.out.println("id: " + faqsMasterId);
        String fQuestion = request.getParameter("qtitle");
        String fAnswer = request.getParameter("answer");
        System.out.println("fAnswer: " + fAnswer);
        String fStatus = request.getParameter("status");
        String fContVisibility = request.getParameter("cont_visible");
        String fPortalList[] = request.getParameterValues("portal");
        
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = f.format(date);
        
        String reply = faqsMasterService.update(Integer.parseInt(faqsMasterId), fQuestion, fAnswer, fContVisibility, createDate, Integer.parseInt(fStatus));
//        Object[] faqsMasterObject = faqsMasterService.readByContent(fQuestion, fAnswer, createDate);
//        System.out.println("reply: " + reply);
        reply = faqPortalMappingService.remove(Integer.parseInt(faqsMasterId));
        if(fPortalList!=null)
        {
            if(fPortalList.length!=0)
            {
//                reply = faqPortalMappingService.remove(Integer.parseInt(faqsMasterId));
                for(int count=0; count<fPortalList.length; ++count)
                {
                    reply = faqPortalMappingService.create(Integer.parseInt(fPortalList[count]), Integer.parseInt(faqsMasterId));
                }
            }
        }
        List<Object[]> allFaqsMasterObjectList = faqsMasterService.readAll();
        model.addAttribute("allFaqsMasterObjectList", allFaqsMasterObjectList);
//        prepareMenu(userId, model);
        
//        List<Object[]> allHandsetProfileMasterObjectList = handsetProfileMasterService.readAll();
//        model.addAttribute("allHandsetProfileMasterObjectList", allHandsetProfileMasterObjectList);
        
        return "admin/faqs_list";

    }
    
    @RequestMapping(value = "/mc_add_faq_mster", method = RequestMethod.GET)
    public String mcAddFaqsMaster( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/faqs_add";

    }
    
    @RequestMapping(value = "/mc_add_sub_mster", method = RequestMethod.GET)
    public String mcAddSubMaster( HttpSession session,ModelMap model)
    {
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/sub_add";

    }
    
    @RequestMapping(value = "/mc_list_sub_mster", method = RequestMethod.GET)
    public String mcListSubMaster( HttpSession session,ModelMap model)
    {
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        List<Object[]> allSubscriptionMasterObjectList = subscriptionMasterService.readAll();
        model.addAttribute("allSubscriptionMasterObjectList", allSubscriptionMasterObjectList);
        
        return "admin/sub_list";

    }
    
    @RequestMapping(value = "/mc_add_feat_mster", method = RequestMethod.GET)
    public String mcAddFeatureMaster( HttpSession session,ModelMap model)
    {
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/feature_add";

    }
    
    @RequestMapping(value = "/mc_list_feat_mster", method = RequestMethod.GET)
    public String mcListFeatureMaster( HttpSession session,ModelMap model)
    {
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/feature_list";

    }
    
    
    @RequestMapping(value = "/add_feat", method = RequestMethod.POST)
    public String addFeatureMaster(@RequestParam("portal_id") String portalId, @RequestParam("ftext") String featureText, HttpSession session,ModelMap model)
    {
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        
        String reply = featureMasterService.create(Integer.parseInt(portalId), featureText);
        
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/feature_list";

    }
    
    
    @RequestMapping(value = "/add_sub", method = RequestMethod.POST)
    public String addSubscriptionMaster(HttpServletRequest request, @RequestParam("portal_id") String portalId, @RequestParam("sub_name") String subName, @RequestParam("sub_price") String subPrice, @RequestParam("sub_package") String subPackage, @RequestParam("sub_currency") String subCurrency, HttpSession session,ModelMap model)
    {
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        String featureIds [] = request.getParameterValues("feature_id");
        
        String reply = subscriptionMasterService.create(Integer.parseInt(portalId), subName, subPrice, subPackage, subCurrency);
        Object [] subscriptionMasterObject = subscriptionMasterService.readByAll(Integer.parseInt(portalId), subName, subPrice, subPackage);
        
        if(featureIds==null)
        {
            
        }
        else if(featureIds.length==0)
        {
            
        }
        else
        {
            for(int count=0; count<featureIds.length; ++count)
            {
                reply = subscriptionFeatureMappingService.create(Integer.parseInt(subscriptionMasterObject[0].toString()), Integer.parseInt(featureIds[count]));
            }
        }
        List<Object[]> allSubscriptionMasterObjectList = subscriptionMasterService.readAll();
        model.addAttribute("allSubscriptionMasterObjectList", allSubscriptionMasterObjectList);
        
        return "admin/sub_list";

    }
    
    
    @RequestMapping(value = "/save_sub", method = RequestMethod.POST)
    public String saveSubscriptionMaster(HttpServletRequest request, @RequestParam("sub_id") String subscriptionId, @RequestParam("portal_id") String portalId, @RequestParam("sub_name") String subName, @RequestParam("sub_price") String subPrice, @RequestParam("sub_package") String subPackage, @RequestParam("sub_currency") String subCurrency, HttpSession session,ModelMap model)
    {
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        String featureIds [] = request.getParameterValues("feature_id");
        
        String reply = subscriptionMasterService.update(Integer.parseInt(subscriptionId), Integer.parseInt(portalId), subName, subPrice, subPackage, subCurrency);
        String reply2 = subscriptionFeatureMappingService.removeAllBySubscriptionId(Integer.parseInt(subscriptionId));
//        Object [] subscriptionMasterObject = subscriptionMasterService.readByAll(Integer.parseInt(portalId), subName, subPrice, subPackage);
        
        if(featureIds==null)
        {
            
        }
        else if(featureIds.length==0)
        {
            
        }
        else
        {
            for(int count=0; count<featureIds.length; ++count)
            {
                reply = subscriptionFeatureMappingService.create(Integer.parseInt(subscriptionId), Integer.parseInt(featureIds[count]));
            }
        }
        List<Object[]> allSubscriptionMasterObjectList = subscriptionMasterService.readAll();
        model.addAttribute("allSubscriptionMasterObjectList", allSubscriptionMasterObjectList);
        
        return "admin/sub_list";

    }
    
    
    @RequestMapping(value = "/edit_sub", method = RequestMethod.GET)
    public String addSubscriptionMaster(HttpServletRequest request, @RequestParam("id") String id,  HttpSession session,ModelMap model)
    {
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        
        
        Object[] subscriptionMasterObject = subscriptionMasterService.read(Integer.parseInt(id));
        
        List<Object[]> allSubscriptionFeatureMappingObjectList = subscriptionFeatureMappingService.readAllBySubscriptionId(Integer.parseInt(subscriptionMasterObject[0].toString()));
        
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        List<Object[]> allSubscriptionMasterObjectList = subscriptionMasterService.readAll();
        
//        model.addAttribute("allSubscriptionMasterObjectList", allSubscriptionMasterObjectList);
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        model.addAttribute("subscriptionMasterObject", subscriptionMasterObject);
        model.addAttribute("allSubscriptionFeatureMappingObjectList", allSubscriptionFeatureMappingObjectList);
        
        
        
        List<Object[]> allFeatureMasterObject = featureMasterService.readAllByPortalId(Integer.parseInt(subscriptionMasterObject[2].toString()));
        
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        model.addAttribute("allFeatureMasterObject", allFeatureMasterObject);
        
        
        return "admin/sub_edit";

        
    }
    
    
    @RequestMapping(value = "/ajax_get_feature_text", method = RequestMethod.GET)
    public String ajaxGetFeatureText(@RequestParam("portal_id") String portalId, HttpSession session,ModelMap model)
    {
        System.out.println("really");
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        //  
        
        List<Object[]> allFeatureMasterObject = featureMasterService.readAllByPortalId(Integer.parseInt(portalId));
        
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        model.addAttribute("allFeatureMasterObject", allFeatureMasterObject);
        
        return "admin/ajax/ajax_feature_list";

    }
    
    @RequestMapping(value = "/ajax_get_feature_text2", method = RequestMethod.GET)
    public String ajaxGetFeatureText2(@RequestParam("portal_id") String portalId, @RequestParam("sub_id") String subId, HttpSession session,ModelMap model)
    {
//        System.out.println("really");
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        prepareMenu(userId, model);
        //  
        
        List<Object[]> allSubscriptionFeatureMappingObjectList = subscriptionFeatureMappingService.readAllBySubscriptionId(Integer.parseInt(subId));
        
        List<Object[]> allFeatureMasterObject = featureMasterService.readAllByPortalId(Integer.parseInt(portalId));
        
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        model.addAttribute("allFeatureMasterObject", allFeatureMasterObject);
        model.addAttribute("allSubscriptionFeatureMappingObjectList", allSubscriptionFeatureMappingObjectList);
        
        return "admin/ajax/ajax_feature_list2";

    }
    
    @RequestMapping(value = "/add_faqs_master", method = RequestMethod.POST)
//    status=0&cont_visible=0&portal=1&portal=2&qtitle=&files=
    public String addFaqsMaster(HttpServletRequest request, @RequestParam("files") String files, HttpSession session, ModelMap model)
    {
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        System.out.println(question);
//        System.out.println(answer);
//        System.out.println(status);
//        System.out.println(contVisibility);
//        System.out.println(portalList[0]);
        
        String fQuestion = request.getParameter("qtitle");
        String fAnswer = request.getParameter("answer");
        System.out.println("fAnswer: " + fAnswer);
        String fStatus = request.getParameter("status");
        String fContVisibility = request.getParameter("cont_visible");
        String fPortalList[] = request.getParameterValues("portal");
        
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = f.format(date);
        
        String reply = faqsMasterService.create(fQuestion, fAnswer, fContVisibility, createDate, Integer.parseInt(fStatus));
        Object[] faqsMasterObject = faqsMasterService.readByContent(fQuestion, fAnswer, createDate);
        System.out.println("reply: " + reply);
        if(fPortalList!=null)
        {
            if(fPortalList.length!=0)
            {
                for(int count=0; count<fPortalList.length; ++count)
                {
                    reply = faqPortalMappingService.create(Integer.parseInt(fPortalList[count]), Integer.parseInt(faqsMasterObject[0].toString()));
                }
            }
        }
        List<Object[]> allFaqsMasterObjectList = faqsMasterService.readAll();
        model.addAttribute("allFaqsMasterObjectList", allFaqsMasterObjectList);
//        prepareMenu(userId, model);
        
//        List<Object[]> allHandsetProfileMasterObjectList = handsetProfileMasterService.readAll();
//        model.addAttribute("allHandsetProfileMasterObjectList", allHandsetProfileMasterObjectList);
        
        return "admin/faqs_list";

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

    public void setFaqsMasterService(FaqsMasterService faqsMasterService) {
        this.faqsMasterService = faqsMasterService;
    }

    public void setContentPortalMasterService(ContentPortalMasterService contentPortalMasterService) {
        this.contentPortalMasterService = contentPortalMasterService;
    }

    public void setFaqPortalMappingService(FaqPortalMappingService faqPortalMappingService) {
        this.faqPortalMappingService = faqPortalMappingService;
    }

    public void setFeatureMasterService(FeatureMasterService featureMasterService) {
        this.featureMasterService = featureMasterService;
    }

    public void setSubscriptionMasterService(SubscriptionMasterService subscriptionMasterService) {
        this.subscriptionMasterService = subscriptionMasterService;
    }

    public void setSubscriptionFeatureMappingService(SubscriptionFeatureMappingService subscriptionFeatureMappingService) {
        this.subscriptionFeatureMappingService = subscriptionFeatureMappingService;
    }

    
    
    
    
    
    
    
    
}

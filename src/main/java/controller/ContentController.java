/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


import dao.MenuMasterDao;
//import entity.ContentCategoryMaster;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.BannerMasterService;
import service.ContentCategoryMappingService;
import service.ContentCategoryMasterService;
import service.ContentFileService;
import service.ContentMasterService;
import service.ContentMetaDetailsService;
import service.ContentMetaMasterService;
import service.ContentPortalCategoryMappingService;
import service.ContentPortalMasterService;
import service.ContentSubCategoryMasterService;
import service.ContentTypeMasterService;
import service.MenuChildService;
import service.MenuMappingService;
import service.MenuMasterService;
import service.UserMasterService;
import service.ContentTypePortalMappingService;
import service.ContentPortalMappingService;
import service.MenuHistoryService;
//import sun.nio.ch.IOUtil;

/**
 *
 * @author OLAWALE
 */
@Controller
@RequestMapping( value = "/content")
public class ContentController{
    
//    private UserMasterService userMasterService;
    private MenuMasterService menuMasterService;
    private MenuMappingService menuMappingService;
    private MenuChildService menuChildService;
    private ContentMasterService contentMasterService;
    private ContentCategoryMasterService contentCategoryMasterService;
    
    private ContentCategoryMappingService contentCategoryMappingService;
    
    private ContentFileService contentFileService;
    
    private ContentPortalMasterService contentPortalMasterService;
   
    private ContentPortalCategoryMappingService contentPortalCategoryMappingService;
    private ContentTypePortalMappingService contentTypePortalMappingService;
    
    private ContentMetaDetailsService contentMetaDetailsService;
    
    private ContentMetaMasterService contentMetaMasterService;
    
    private BannerMasterService bannerMasterService;
    
    //New
    private ContentTypeMasterService contentTypeMasterService;
    private MenuHistoryService menuHistoryService;
    
    
    private ContentSubCategoryMasterService contentSubCategoryMasterService;
    private UserMasterService userMasterService;
    private ContentPortalMappingService contentPortalMappingService;
    
    //New CMS
    //Update Begins.....
    @RequestMapping(value = "/add_content_file", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public String addContentFile(HttpServletRequest request, @RequestParam("content_master_id") String contentMasterId, @RequestParam("content_master_title") String contentMasterTitle, @RequestParam("content_type_title") String contentTypeTitle, @RequestParam("content_cat_title") String contentCategoryTitle, @RequestParam("content_sub_cat_title") String contentSubCategoryTitle, @RequestParam ("file") MultipartFile file, HttpSession session, ModelMap model)
    {
        System.out.println("NA SOO...");
        // Save file on system
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        String widthHeight = request.getParameter("width_height")!=null && !request.getParameter("width_height").equals("") ? request.getParameter("width_height"):"";
        String osVersion = (request.getParameter("os_version")!=null && !request.getParameter("os_version").equals("")) ? request.getParameter("os_version"):"0.0";
        String osName = request.getParameter("os_name")!=null && !request.getParameter("os_name").equals("") ? request.getParameter("os_name"):"";
        
        Date d = new Date();
        
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String dateString = f.format(d);
        
        
        if (!file.isEmpty()) 
        {
  
            String path = session.getServletContext().getRealPath("/");
            path = path.substring(0, path.lastIndexOf("CMSBM"));
            path = path.concat("ROOT/");
            
            String fileName = file.getOriginalFilename();

            String relativePath = "/content/"+dateString+"/"+fileName;
            String fullPath = path + "content" + File.separator+dateString + File.separator + fileName;

            String reply = contentFileService.create(Integer.parseInt(contentMasterId), relativePath, widthHeight, osName, osVersion);
            if(reply.equals("success"))
            {

                  try
                  {


                      File dir = new File(path + "content" + File.separator + dateString);
                              if (!dir.exists())
                                      dir.mkdirs();


                      byte barr[] = file.getBytes();
                      BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(fullPath));
                      bout.write(barr);
                      bout.flush();
                      bout.close();

                      System.out.println("Saved");
                      
                      Object[] userObject = (Object[]) session.getAttribute("user_object");

                        Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeTitle, contentCategoryTitle, contentSubCategoryTitle};    
                        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
                        model.addAttribute("feedback", "Operation Successful!!!");
                        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
                        {

                            List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
                            model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                        }
                        else
                        {

                            List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
                            model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                        }

                  }
                  catch(Exception ex)
                  {
                      System.err.println("Error Occure");
                  }
            }
            else
            {

                model.addAttribute("feedback", "Operation Not Successful!!!");
            }
        }

        return "admin/content_file_add";

    }
    
    
    @RequestMapping(value = "/bk_add_content_file",  method = RequestMethod.GET)
    public String backAddContentFile(@RequestParam("content_master_id") String contentMasterId, @RequestParam("content_master_title") String contentMasterTitle, @RequestParam("content_type_title") String contentTypeTitle, @RequestParam("content_cat_title") String contentCategoryTitle, @RequestParam("content_sub_cat_title") String contentSubCategoryTitle, HttpSession session, ModelMap model)
    {

        
        if(!sessionValid(session))
        {
            return "login";
        }
       
        Object[] userObject = (Object[]) session.getAttribute("user_object");

          Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeTitle, contentCategoryTitle, contentSubCategoryTitle}; 
            model.addAttribute("contentMasterDisplay", contentMasterDisplay);
          List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
          model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                        

                  
        return "admin/content_file_add";

    }
    
    
    @RequestMapping(value = "/add_content_master", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public String addContentMaster(@RequestParam("user_id") String userId, @RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("type") String contentTypeMasterId, @RequestParam("store_type") String storeType, @RequestParam("small_thumb") MultipartFile smallThumb, @RequestParam("middle_thumb") MultipartFile middleThumb, @RequestParam ("large_thumb") MultipartFile largeThumb, @RequestParam("small_thumb_url") String smallThumbUrl, @RequestParam("middle_thumb_url") String middleThumbUrl, @RequestParam ("large_thumb_url") String largeThumbUrl, @RequestParam("cont_cat_mst_id") String contentCategoryMasterId, @RequestParam("cont_sub_cat_mst_id") String contentSubCategoryMasterId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        Date d = new Date();
        
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String dateString = f.format(d);

        String path = session.getServletContext().getRealPath("/");
        path = path.substring(0, path.lastIndexOf("CMSBM"));
        path = path.concat("ROOT/");

        if(storeType.equals("LOCAL"))
        {
            if (!smallThumb.isEmpty() && !middleThumb.isEmpty() && !largeThumb.isEmpty()) 
            {

                String smallThumbName = smallThumb.getOriginalFilename();

                String middleThumbName = middleThumb.getOriginalFilename();

                String largeThumbName = largeThumb.getOriginalFilename();

                String smallRelativePath = "/content/"+dateString + "/" + smallThumbName;
                String middleRelativePath = "/content/"+dateString+"/"+middleThumbName;
                String largeRelativePath = "/content/"+dateString+"/"+largeThumbName;
                
                String smallRelativePathReal = path + "content" + File.separator+dateString + File.separator + smallThumbName;
                String middleRelativePathReal = path + "content" + File.separator+dateString + File.separator + middleThumbName;
                String largeRelativePathReal = path + "content" + File.separator+dateString + File.separator + largeThumbName;

                String reply = contentMasterService.create(Integer.parseInt(userId), title, desc, Integer.parseInt(contentTypeMasterId),  smallRelativePath, middleRelativePath, largeRelativePath, storeType, Integer.parseInt(contentCategoryMasterId), Integer.parseInt(contentSubCategoryMasterId));

                if(reply.equals("success"))
                {
                    // Save file on system

                    try
                    {

                        File smallDir = new File(path + "content" + File.separator+dateString);
                                if (!smallDir.exists())
                                        smallDir.mkdirs();

                        byte barr[] = smallThumb.getBytes();
                        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(smallRelativePathReal));
                        bout.write(barr);
                        bout.flush();
                        bout.close();

                        barr = middleThumb.getBytes();
                        bout = new BufferedOutputStream(new FileOutputStream(middleRelativePathReal));
                        bout.write(barr);
                        bout.flush();
                        bout.close();
//
                        barr = largeThumb.getBytes();

                        bout = new BufferedOutputStream(new FileOutputStream(largeRelativePathReal));
                        bout.write(barr);
                        bout.flush();
                        bout.close();
                        
                        

                        if(!sessionValid(session))
                        {
                            return "login";
                        }
                        Object[] userObject = (Object[]) session.getAttribute("user_object");

                        Object[] contentCategoryMasterObject = contentCategoryMasterService.readContentCategoryMaster(Integer.parseInt(contentCategoryMasterId));
                        Object[] contentSubCategoryMasterObject = contentSubCategoryMasterService.readByContentSubCategoryMaster(Integer.parseInt(contentSubCategoryMasterId));
                        Object[] contentTypeMasterObject = contentTypeMasterService.readContentTypeMaster(Integer.parseInt(contentTypeMasterId));
                        Object[] contentMasterObject = contentMasterService.readByOthers(Integer.parseInt(userId), title, desc, Integer.parseInt(contentTypeMasterId),  smallThumbUrl, middleThumbUrl, largeThumbUrl, storeType, Integer.parseInt(contentCategoryMasterId));

                        Object[] contentMasterDisplay=new Object[5];
                        contentMasterDisplay[0] = contentMasterObject[0];
                        contentMasterDisplay[1] = contentMasterObject[1];
                        contentMasterDisplay[2] = contentTypeMasterObject[1];
                        contentMasterDisplay[3] = contentCategoryMasterObject[1];

                        if(contentSubCategoryMasterObject!=null)
                        {
                            contentMasterDisplay[4] = contentSubCategoryMasterObject[1];
                        }
                        else
                        {
                            contentMasterDisplay[4] = null;
                        }

                        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
                        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
                        {

                            List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterObject[0].toString()));
                            model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                        }
                        else
                        {

                            List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterObject[0].toString()));
                            model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                        }

                        return "admin/content_file_add";
                        
                    }
                    catch(Exception ex)
                    {
    //                    System.err.println("Error Occure");
                    }
                }
            }
        }
        else if(storeType.equals("REMOTE"))
        {
            if (!smallThumbUrl.isEmpty() && !middleThumbUrl.isEmpty() && !largeThumbUrl.isEmpty()) 
            {
                String reply = contentMasterService.create(Integer.parseInt(userId), title, desc, Integer.parseInt(contentTypeMasterId),  smallThumbUrl, middleThumbUrl, largeThumbUrl, storeType, Integer.parseInt(contentCategoryMasterId), Integer.parseInt(contentSubCategoryMasterId));
                if(reply.equals("success"))
                {

                    if(!sessionValid(session))
                    {
                        return "login";
                    }
                    Object[] userObject = (Object[]) session.getAttribute("user_object");

                    Object[] contentCategoryMasterObject = contentCategoryMasterService.readContentCategoryMaster(Integer.parseInt(contentCategoryMasterId));
                    Object[] contentSubCategoryMasterObject = contentSubCategoryMasterService.readByContentSubCategoryMaster(Integer.parseInt(contentSubCategoryMasterId));
                    Object[] contentTypeMasterObject = contentTypeMasterService.readContentTypeMaster(Integer.parseInt(contentTypeMasterId));
                    Object[] contentMasterObject = contentMasterService.readByOthers(Integer.parseInt(userId), title, desc, Integer.parseInt(contentTypeMasterId),  smallThumbUrl, middleThumbUrl, largeThumbUrl, storeType, Integer.parseInt(contentCategoryMasterId));
                    
                    System.out.println("ERROR LOC: " + contentSubCategoryMasterObject);
                    Object[] contentMasterDisplay=new Object[5];
                    contentMasterDisplay[0] = contentMasterObject[0];
                    contentMasterDisplay[1] = contentMasterObject[1];
                    contentMasterDisplay[2] = contentTypeMasterObject[1];
                    contentMasterDisplay[3] = contentCategoryMasterObject[1];
                    if(contentSubCategoryMasterObject!=null)
                    {
                        contentMasterDisplay[4] = contentSubCategoryMasterObject[1];
                    }
                    else
                    {
                        contentMasterDisplay[4] = null;
                    }
                    model.addAttribute("contentMasterDisplay", contentMasterDisplay);
                    if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
                    {

                        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterObject[0].toString()));
                        model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                    }
                    else
                    {
                        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterObject[0].toString()));
                        model.addAttribute("allContentFileObjectList", allContentFileObjectList);

                    }

                    return "admin/content_file_add";
                }
            }
        }
    return null;
        

    }
    
    @RequestMapping(value = "/add_content_meta_detail", method = RequestMethod.POST)
    public String addContentMetaDetail(@RequestParam("content_master_id") String contentMasterId, @RequestParam("content_master_title") String contentMasterTitle, @RequestParam("content_type_title") String contentTypeTitle, @RequestParam("content_cat_title") String contentCategoryTitle, @RequestParam("content_sub_cat_title") String contentSubCategoryTitle, @RequestParam("content_meta_master_title") String contentMetaMasterTitle, @RequestParam("content_mst_detail_value") String contentMstDetailValue, HttpSession session,  ModelMap model)
    {

        if(!sessionValid(session))
        {
            return "login";
        }

        String response = contentMetaDetailsService.create(Integer.parseInt(contentMasterId), contentMetaMasterTitle, contentMstDetailValue);
        System.out.println("reply: " + response);
        if(response.equals("success"))
        {
            model.addAttribute("feedback", "Operation Successful");
        }
        else if(response.equals("failure"))
        {
            model.addAttribute("feedback", "Operation Not Successful");
        }
        else if(response.equals("exist"))
        {
            model.addAttribute("feedback", "Content Category Already Exist");
        }
        List<Object[]> contentMetaMasterList = contentMetaMasterService.readAll();
        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readByContentMaster(Integer.parseInt(contentMasterId));

        model.addAttribute("contentMetaMasterList", contentMetaMasterList);
        model.addAttribute("contentMetaDetailsList", contentMetaDetailsList);
        
        Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeTitle, contentCategoryTitle, contentSubCategoryTitle}; 
        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");

                    if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
                    {
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAllModified();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    }
                    else
                    {
                        //By User
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readByIdModified(Integer.parseInt(userObject[0].toString()));
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    }
//
//
//        model.addAttribute("contentMasterId", contentMasterId);
        
        
//        return "admin/content_meta_details";
        return "admin/content_metadata_add";
    }
    
    
    @RequestMapping(value = "/save_content_cat_mst_details", method = RequestMethod.POST)
    public String saveContentCatMstDetails(@RequestParam("id") String contentCategoryMasterId, @RequestParam("title") String title, @RequestParam("local_title") String localTitle,  HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            
        String response = contentCategoryMasterService.update(Integer.parseInt(contentCategoryMasterId), title, localTitle);
//        model.addAttribute("contentCategoryMasterObject", contentCategoryMasterObject);
        if(response.equals("success"))
        {
//            System.out.println("Saved..");
            model.addAttribute("feedback", "Operation Successful");
        }
        else if(response.equals("failure"))
        {
            model.addAttribute("feedback", "Operation Not Successful");
        }
        else if(response.equals("exist"))
        {
            model.addAttribute("feedback", "Content Category Already Exist");
        }

        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        return "admin/content_category_master";
    }
    
    //New CMS END
    
    //Update Begins
    //Checked...
    
    
    //Update
    //Checked...
    @RequestMapping(value = "/mc_add_content_cat_mst", method = RequestMethod.GET)
    public String mcAddContentCategoryMaster(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
        updateMenuHistory(mmid, mcid, session);
        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        System.out.println("okk");
        return "admin/content_category_master";
    }
    
    
    
    
    
    @RequestMapping(value = "/add_content_cat_mst", method = RequestMethod.POST)
    public String addContentCategoryMaster(@RequestParam("title") String title, @RequestParam("local_title") String localTitle , @RequestParam("content_type_id") String contentTypeId, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String response = contentCategoryMasterService.create(title, localTitle, Integer.parseInt(contentTypeId));
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
//            model.addAttribute("feedback", "Content Category Already Exist");
//        }
  
        
        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        return "admin/content_category_master";
    }
    
    @RequestMapping(value = "/mc_add_sub_cat_mst", method = RequestMethod.GET)
    public String mcAddSubCatMst(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        prepareMenu(userId, model);
  
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//
        
        List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readAll();
        model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);

        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        return "admin/content_sub_category_master";
    }
    
    @RequestMapping(value = "/add_sub_cat_mst", method = RequestMethod.POST)
    public String addSubCategoryMaster(@RequestParam("title") String title, @RequestParam("local_title") String localTitle , @RequestParam("content_type_id") String contentTypeId, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String response = contentCategoryMasterService.create(title, localTitle, Integer.parseInt(contentTypeId));
        
//        prepareMenu(userId, model);
        if(response.equals("success"))
        {
//            System.out.println("Saved..");
            model.addAttribute("feedback", "Operation Successful");
        }
        else if(response.equals("failure"))
        {
            model.addAttribute("feedback", "Operation Not Successful");
        }
        else if(response.equals("exist"))
        {
            model.addAttribute("feedback", "Content Category Already Exist");
        }
  
        
        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        return "admin/content_category_master";
    }
    
    
    @RequestMapping(value = "/get_portal_cat_map_by_stat", method = RequestMethod.POST)
    public String getPortalCatMapByStat(HttpServletRequest request, @RequestParam("status") String contentCategoryMasterStatus,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        String contentPortalMasterId = request.getParameter("content_portal_master")!=null && !request.getParameter("content_portal_master").equals("") ? request.getParameter("content_portal_master"):"";
        
//         List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        
//        List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//      
//        model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//        
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
            
            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(1);
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
            model.addAttribute("contentCategoryMasterStatus", contentCategoryMasterStatus);
            model.addAttribute("contentCategoryPortalMappingStatus", contentCategoryMasterStatus);
                    
        for(int count=0; count < allContentCategoryMasterObjectList.size(); ++count)
        {
            Object[] allPortalCategoryMappingObjectList = contentPortalCategoryMappingService.readByCategoryPortal(Integer.parseInt(allContentCategoryMasterObjectList.get(count)[0].toString()), Integer.parseInt(contentPortalMasterId));
            
            
            
            if(allPortalCategoryMappingObjectList==null)
            {
                
                allContentCategoryMasterObjectList.get(count)[3] = 0;
                
            }
            else if(allPortalCategoryMappingObjectList[3].toString().equals("0"))
            {
                allContentCategoryMasterObjectList.get(count)[3] = 0;
                System.out.println("onj: " + allPortalCategoryMappingObjectList[3]);
            }
            else
            {
                allContentCategoryMasterObjectList.get(count)[3] = 1;
            }
        }
        
        if(!contentCategoryMasterStatus.equals("-1"))
        {
            List<Object[]> tempList = allContentCategoryMasterObjectList;
            allContentCategoryMasterObjectList = new ArrayList<>();
            for(int i=0; i<tempList.size(); ++i)
            {
                System.out.println("ST: " + tempList.get(i)[3].toString());
                if(tempList.get(i)[3].toString().equals(contentCategoryMasterStatus))
                {
                    allContentCategoryMasterObjectList.add(tempList.get(i));
                }
            }
        }
     
            
      

            model.addAttribute("contentPortalMasterId", contentPortalMasterId);

            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        return "admin/portal_category_mapp";
    }
    
    //Operation not completed
    @RequestMapping(value = "/get_porta_type_map_by_typ_stat", method = RequestMethod.GET)
    public String getPortaTypeMapByTypStat(HttpServletRequest request, @RequestParam("status") String contentTypeMasterStatus, ModelMap model, HttpSession session)
    {
//        System.out.println("SUB");
//        prepareMenu(userId, model);
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String contentPortalMasterId = request.getParameter("content_portal_master")!=null && !request.getParameter("content_portal_master").equals("") ? request.getParameter("content_portal_master"):"";
        
        
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(1);
        List<Object[]> allContentTypeMasterObjectList=null;
        List<Object[]> allContentTypeMasterObjectList2 = contentTypeMasterService.readAll();
        
        allContentTypeMasterObjectList = contentTypeMasterService.readAll();

        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);

            model.addAttribute("contentTypeMasterStatus", contentTypeMasterStatus);
        
        
        
        
        
//        System.out.println("Type Size: " + allContentTypeMasterObjectList.size());
        for(int count=0; count < allContentTypeMasterObjectList.size(); ++count)
        {
            Object[] allTypePortalMappingStatus = contentTypePortalMappingService.readByTypePortal(Integer.parseInt(allContentTypeMasterObjectList.get(count)[0].toString()), Integer.parseInt(contentPortalMasterId));
//            System.out.println("::: " + contentTypeMasterStatus);
//            System.out.println("::: " + contentPortalMasterId);
//            Object[] allTypePortalMappingStatus = contentTypePortalMappingService.readByTypePortalStatus(Integer.parseInt(allContentTypeMasterObjectList.get(count)[0].toString()), Integer.parseInt(contentPortalMasterId), Integer.parseInt(contentTypeMasterStatus));
            
//            System.out.println("onj: " + allTypePortalMappingStatus);
            
            if(allTypePortalMappingStatus==null)
            {
//                System.out.println("enter: null");
                allContentTypeMasterObjectList.get(count)[3] = 0;

            }
            else if(allTypePortalMappingStatus[3].toString().equals("0"))
            {
                allContentTypeMasterObjectList.get(count)[3] = 0;
//                System.out.println("val: " + allTypePortalMappingStatus[0]);
            }
            else
            {
                allContentTypeMasterObjectList.get(count)[3] = 1;
//                System.out.println("val1: " + allTypePortalMappingStatus[0]);
            }
        }
        
        if(!contentTypeMasterStatus.equals("-1"))
        {
            List<Object[]> tempList = allContentTypeMasterObjectList;
            allContentTypeMasterObjectList = new ArrayList<>();
            for(int i=0; i<tempList.size(); ++i)
            {
                if(tempList.get(i)[3].toString().equals(contentTypeMasterStatus))
                {
                    allContentTypeMasterObjectList.add(tempList.get(i));
                }
            }
        }
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
     
//            System.out.println("SZ: " + allContentTypeMasterObjectList.size());
            model.addAttribute("contentTypePortalMappingStatus", contentTypeMasterStatus);
            model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
            model.addAttribute("allContentTypeMasterObjectList2", allContentTypeMasterObjectList2);

            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
            model.addAttribute("contentPortalMasterId", contentPortalMasterId);
//            model.addAttribute("contentTypeId", contentTypeId);
//            model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
 
        return "admin/portal_type_mapp";
  
    }
    
    @RequestMapping(value = "/get_sub_cat_by_cat_stat", method = RequestMethod.GET)
    public String getSubCatByCatStat(@RequestParam("type") String contentTypeId, @RequestParam("cat") String contentCategoryMasterId, ModelMap model, HttpSession session)
    {
        System.out.println("SUB");
//        prepareMenu(userId, model);
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        
        

        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
            if(contentTypeId.equalsIgnoreCase("0") && contentCategoryMasterId.equalsIgnoreCase("0"))
            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
                List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readAll();
                model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);
                System.out.println("size: " + allContentSubCategoryMasterObjectList.size());
            }
            else if(!contentTypeId.equalsIgnoreCase("0") && contentCategoryMasterId.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readByContentType(Integer.parseInt(contentTypeId));
                model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);
                System.out.println("size: " + allContentSubCategoryMasterObjectList.size());
            }
            else if(contentTypeId.equalsIgnoreCase("0") && !contentCategoryMasterId.equalsIgnoreCase("0"))
            {
//                List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readByContentCategoryMaster(Integer.parseInt(contentCategoryMasterId));
                List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readByContentCat2(Integer.parseInt(contentCategoryMasterId));
                model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);
                System.out.println("size: " + allContentSubCategoryMasterObjectList.size());
            }
            else if(!contentTypeId.equalsIgnoreCase("0") && !contentCategoryMasterId.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readByTypeAndContentCategoryMaster(Integer.parseInt(contentTypeId), Integer.parseInt(contentCategoryMasterId));
                model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);
                System.out.println("size: " + allContentSubCategoryMasterObjectList.size());
            }
//        }
//        else
//        {
//            if(contentTypeId.equalsIgnoreCase("0") && contentMasterStatus.equalsIgnoreCase("0"))
//            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//            }
//            else if(!contentTypeId.equalsIgnoreCase("0") && contentMasterStatus.equalsIgnoreCase("0"))
//            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByIdByType(Integer.parseInt(userObject[0].toString()),  Integer.parseInt(contentTypeId));
//                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//            }
//            else if(contentTypeId.equalsIgnoreCase("0") && !contentMasterStatus.equalsIgnoreCase("0"))
//            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByIdByStatus2(Integer.parseInt(userObject[0].toString()), Integer.parseInt(contentMasterStatus));
//                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                System.out.println("size: " + allContentMasterObjectList.size());
//            }
//            else if(!contentTypeId.equalsIgnoreCase("0") && !contentMasterStatus.equalsIgnoreCase("0"))
//            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByIdByTypeStatus(Integer.parseInt(userObject[0].toString()), Integer.parseInt(contentTypeId), Integer.parseInt(contentMasterStatus));
//                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                System.out.println("size: " + allContentMasterObjectList.size());
//            }
//        }
        model.addAttribute("contentTypeId", contentTypeId);
        model.addAttribute("contentCategoryMasterId", contentCategoryMasterId);
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        
//
//            if(type.equals("ALL"))
//            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);    
//            }
//            else
//            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
//                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//            }

        System.out.println("okkkkkk");
        return "admin/content_sub_category_master";
        
    }
    
    @RequestMapping(value = "/add_content_sub_cat_mst", method = RequestMethod.POST)
    public String addContentSubCatMst(@RequestParam("title") String title, @RequestParam("local_title") String localTitle , @RequestParam("cont_cat_id") String contentCategoryId, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String response = contentSubCategoryMasterService.create(title, localTitle, Integer.parseInt(contentCategoryId));
        
//        prepareMenu(userId, model);
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
//            model.addAttribute("feedback", "Content Category Already Exist");
//        }
  
        
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readAll();
        model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
         List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        return "admin/content_sub_category_master";
    }
    
    
    @RequestMapping(value = "/save_content_sub_cat_mst_details", method = RequestMethod.POST)
    public String saveContentSubCatMstDetails(@RequestParam("id") String contentSubCategoryMasterId, @RequestParam("title") String title, @RequestParam("local_title") String localTitle, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String response = contentSubCategoryMasterService.update(Integer.parseInt(contentSubCategoryMasterId), title, localTitle);
        
//        prepareMenu(userId, model);
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
//            model.addAttribute("feedback", "Content Category Already Exist");
//        }
  
        
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readAll();
        model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
         List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        return "admin/content_sub_category_master";
    }
    
    @RequestMapping(value = "/edit_content_cat_mst_details", method = RequestMethod.GET)
    public String editContentCatMstDetails(@RequestParam("id") String contentCategoryMasterId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            
        Object[] contentCategoryMasterObject = contentCategoryMasterService.readContentCategoryMaster(Integer.parseInt(contentCategoryMasterId));
        model.addAttribute("contentCategoryMasterObject", contentCategoryMasterObject);
        
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        
//        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
//        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
           
//            prepareMenu(userId, model);
            
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);

        return "admin/content_category_master_edit";
    }
    
    @RequestMapping(value = "/edit_content_sub_cat_mst_details", method = RequestMethod.GET)
    public String editContentSubCatMstDetails(@RequestParam("id") String contentSubCategoryMasterId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            
        Object[] contentSubCategoryMasterObject = contentSubCategoryMasterService.readContentSubCategoryMaster(Integer.parseInt(contentSubCategoryMasterId));
        model.addAttribute("contentSubCategoryMasterObject", contentSubCategoryMasterObject);
        
//        List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readAll();
//        model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);
//        
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        
//        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
//        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
           
//            prepareMenu(userId, model);
            
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);

        return "admin/content_sub_category_master_edit";
    }
    
    @RequestMapping(value = "/edit_category_master_status", method = RequestMethod.GET)
    public String editCategoryMasterStatus(@RequestParam("id") String contentMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentCategoryMasterService.updateStatus(Integer.parseInt(contentMasterId), Integer.parseInt(status));
                
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
    
    
    @RequestMapping(value = "/edit_metadata_detail_status", method = RequestMethod.GET)
    public String editMetadataDetailStatus(@RequestParam("id") String contentMetaDetailsId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentMetaDetailsService.updateStatus(Integer.parseInt(contentMetaDetailsId), Integer.parseInt(status));
                
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
    
    @RequestMapping(value = "/edit_sub_category_master_status", method = RequestMethod.GET)
    public String editSubCategoryMasterStatus(@RequestParam("id") String subCategoryMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentSubCategoryMasterService.updateStatus(Integer.parseInt(subCategoryMasterId), Integer.parseInt(status));
                
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
    
    @RequestMapping(value = "/mc_add_content_mster", method = RequestMethod.GET)
    public String mcAddContentMaster(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid, HttpSession session, ModelMap model)
    {
        
//        prepareMenu(userId, model);
  
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        updateMenuHistory(mmid, mcid, session);
        
        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        return "admin/content_master_add";
    }
    
    private void updateMenuHistory(String mmid, String mcid, HttpSession session)
    {
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        String response = menuHistoryService.create(Integer.parseInt(userObject[0].toString()), Integer.parseInt(mmid), Integer.parseInt(mcid));
        
    }
    
    @RequestMapping(value = "/mc_add_banner_master", method = RequestMethod.GET)
    public String mcAddBannerMaster(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid, HttpSession session, ModelMap model)
    {
        
//        prepareMenu(userId, model);
  
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        updateMenuHistory(mmid, mcid, session);
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        return "admin/banner_master_add";
    }
    
    
    
    @RequestMapping(value = "/mc_add_md_content", method = RequestMethod.GET)
//    public String mcAddMetadataContent(@RequestParam("id") String contentMasterId, HttpSession session,  ModelMap model)
//    public String mcAddMetadataContent(HttpSession session,  ModelMap model)
    public String mcAddMetadataContent(@RequestParam("content_master_id") String contentMasterId, @RequestParam("content_master_title") String contentMasterTitle, @RequestParam("content_type_title") String contentTypeTitle, @RequestParam("content_cat_title") String contentCategoryTitle, @RequestParam("content_sub_cat_title") String contentSubCategoryTitle, HttpSession session,  ModelMap model)
    {
        
//        prepareMenu(userId, model);
  
        
        if(!sessionValid(session))
        {
            return "login";
        }

        
        Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeTitle, contentCategoryTitle, contentSubCategoryTitle}; 
        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
//        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readAll();
        
        List<Object[]> contentMetaMasterList = contentMetaMasterService.readAll();
//        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readByContentMaster(Integer.parseInt(contentMasterId));
//        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readAll();
        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readByContentMaster(Integer.parseInt(contentMasterId));
        
        
//        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
//
        model.addAttribute("contentMetaMasterList", contentMetaMasterList);
        model.addAttribute("contentMetaDetailsList", contentMetaDetailsList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");

//                        model.addAttribute("contentMasterId", contentMasterId);
                    if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
                    {
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAllModified();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    }
                    else
                    {
                        //By User
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readByIdModified(Integer.parseInt(userObject[0].toString()));
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    }
        
//
//
//        model.addAttribute("contentMasterId", contentMasterId);
        
        return "admin/content_metadata_add";
    }
    
    @RequestMapping(value = "/mc_list_content_mster", method = RequestMethod.GET)
    public String mcListContentMaster(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid, ModelMap model, HttpSession session)
    {
        
//        prepareMenu(userId, model);
        
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        
        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
        {
            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
        }
        else
        {
            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
        }

        return "admin/content_master_list";
        
    }
    
    @RequestMapping(value = "/edit_content_master_details", method = RequestMethod.GET)
    public String editMenuChildDetails(@RequestParam("id") String contentMasterId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            
        Object[] contentMasterObject = contentMasterService.readContentMaster(Integer.parseInt(contentMasterId));
        model.addAttribute("contentMasterObject", contentMasterObject);
        
        System.out.println("szzzz: " + contentMasterObject);
        
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
           
//            prepareMenu(userId, model);
            
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);

//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        return "admin/content_master_edit";
    }
    
    @RequestMapping(value = "/get_portal_by_cat_status", method = RequestMethod.GET)
    public String getPortalByCat(@RequestParam("type") String contentTypeId, @RequestParam("status") String contentMasterStatus, ModelMap model, HttpSession session)
    {
        
//        prepareMenu(userId, model);
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        
        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
        {
            if(contentTypeId.equalsIgnoreCase("0") && contentMasterStatus.equalsIgnoreCase("0"))
            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
                List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                System.out.println("size: " + allContentMasterObjectList.size());
            }
            else if(!contentTypeId.equalsIgnoreCase("0") && contentMasterStatus.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(Integer.parseInt(contentTypeId));
                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                System.out.println("size: " + allContentMasterObjectList.size());
            }
            else if(contentTypeId.equalsIgnoreCase("0") && !contentMasterStatus.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentMasterObjectList = contentMasterService.readByStatus2(Integer.parseInt(contentMasterStatus));
                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                System.out.println("size: " + allContentMasterObjectList.size());
            }
            else if(!contentTypeId.equalsIgnoreCase("0") && !contentMasterStatus.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentMasterObjectList = contentMasterService.readByTypeStatus(Integer.parseInt(contentTypeId), Integer.parseInt(contentMasterStatus));
                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                System.out.println("size: " + allContentMasterObjectList.size());
            }
        }
        else
        {
            if(contentTypeId.equalsIgnoreCase("0") && contentMasterStatus.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
            }
            else if(!contentTypeId.equalsIgnoreCase("0") && contentMasterStatus.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentMasterObjectList = contentMasterService.readByIdByType(Integer.parseInt(userObject[0].toString()),  Integer.parseInt(contentTypeId));
                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
            }
            else if(contentTypeId.equalsIgnoreCase("0") && !contentMasterStatus.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentMasterObjectList = contentMasterService.readByIdByStatus2(Integer.parseInt(userObject[0].toString()), Integer.parseInt(contentMasterStatus));
                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                System.out.println("size: " + allContentMasterObjectList.size());
            }
            else if(!contentTypeId.equalsIgnoreCase("0") && !contentMasterStatus.equalsIgnoreCase("0"))
            {
                List<Object[]> allContentMasterObjectList = contentMasterService.readByIdByTypeStatus(Integer.parseInt(userObject[0].toString()), Integer.parseInt(contentTypeId), Integer.parseInt(contentMasterStatus));
                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                System.out.println("size: " + allContentMasterObjectList.size());
            }
        }
        model.addAttribute("contentTypeId", contentTypeId);
        model.addAttribute("contentMasterStatus", contentMasterStatus);
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        
//
//            if(type.equals("ALL"))
//            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);    
//            }
//            else
//            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
//                model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//            }

        System.out.println("okkkkkk");
        return "admin/content_master_list";
        
    }
    
    
    
    @RequestMapping(value = "/save_content_master_details", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    public String saveContentMasterDetails(@RequestParam("id") String contentMasterId, @RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("type") String type, @RequestParam("store_type") String storeType, @RequestParam("small_thumb") MultipartFile smallThumb, @RequestParam("middle_thumb") MultipartFile middleThumb, @RequestParam ("large_thumb") MultipartFile largeThumb, @RequestParam("small_thumb_url") String smallThumbUrl, @RequestParam("middle_thumb_url") String middleThumbUrl, @RequestParam ("large_thumb_url") String largeThumbUrl, @RequestParam("cont_cat_mst_id") String contentCategoryMasterId, @RequestParam("small") String small, @RequestParam("mid") String mid, @RequestParam("large") String large, HttpSession session, ModelMap model)
    public String saveContentMasterDetails(@RequestParam("id") String contentMasterId, @RequestParam("title") String contentMasterTitle, @RequestParam("desc") String desc, @RequestParam("store_type") String storeType, @RequestParam("small_thumb") MultipartFile smallThumb, @RequestParam("middle_thumb") MultipartFile middleThumb, @RequestParam ("large_thumb") MultipartFile largeThumb, @RequestParam("small_thumb_url") String smallThumbUrl, @RequestParam("middle_thumb_url") String middleThumbUrl, @RequestParam ("large_thumb_url") String largeThumbUrl, @RequestParam("small") String small, @RequestParam("mid") String mid, @RequestParam("large") String large, @RequestParam("cont_mst_type") String contentTypeMasterTitle, @RequestParam("cont_cat_mst_title") String contentCategoryMasterTitle, @RequestParam("cont_sub_cat_mst_title") String contentSubCategoryMasterTitle, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        Date d = new Date();
        
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String dateString = f.format(d);
        
//        String path = session.getServletContext().getRealPath("/");
//        path = path.substring(0, path.lastIndexOf("CMSBM"));
//        path = path.concat("ROOT\\");

        String path = session.getServletContext().getRealPath("/");
        path = path.substring(0, path.lastIndexOf("CMSBM"));
        path = path.concat("ROOT/");

        Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeMasterTitle, contentCategoryMasterTitle, contentSubCategoryMasterTitle}; 
        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
        
        if(storeType.equals("LOCAL"))
        {
//            if (!smallThumb.isEmpty() && !middleThumb.isEmpty() && !largeThumb.isEmpty()) 
            {

                dateString = small.substring(small.indexOf("/", 3)+1, small.lastIndexOf("/"));
                
                System.out.println(dateString);
                
                String smallThumbName = smallThumb.getOriginalFilename();

                String middleThumbName = middleThumb.getOriginalFilename();

                String largeThumbName = largeThumb.getOriginalFilename();

                System.out.println("smallT: " + smallThumbName);
                System.out.println("smallT: " + middleThumbName);
                System.out.println("smallT: " + largeThumbName);
                
                System.out.println("smallT2: " + small);
                System.out.println("smallT2: " + mid);
                System.out.println("smallT2: " + large);
                

    
//                String smallRelativePath = "/content/"+dateString + "/" + smallThumbName;
//                String middleRelativePath = "/content/"+dateString+"/"+middleThumbName;
//                String largeRelativePath = "/content/"+dateString+"/"+largeThumbName;

                String smallRelativePath = "";
                String middleRelativePath = "";
                String largeRelativePath = "";

                smallRelativePath = smallThumbName.isEmpty() ? small : "/content/"+dateString + "/" + smallThumbName;
                middleRelativePath = middleThumbName.isEmpty() ? mid : "/content/"+dateString + "/" + middleThumbName;
                largeRelativePath = largeThumbName.isEmpty() ? large : "/content/"+dateString + "/" + largeThumbName;
                
                
//                String smallRelativePathReal = path + "content" + File.separator+dateString + File.separator + smallThumbName;
//                String middleRelativePathReal = path + "content" + File.separator+dateString + File.separator + middleThumbName;
//                String largeRelativePathReal = path + "content" + File.separator+dateString + File.separator + largeThumbName;

                String smallRelativePathReal = "";
                String middleRelativePathReal = "";
                String largeRelativePathReal = "";
                
                smallRelativePathReal = smallThumbName.isEmpty() ? "" :  path + "content" + File.separator+dateString + File.separator + smallThumbName;
                middleRelativePathReal = middleThumbName.isEmpty() ? "" : path + "content" + File.separator+dateString + File.separator + middleThumbName;
                largeRelativePathReal = largeThumbName.isEmpty() ? "" : path + "content" + File.separator+dateString + File.separator + largeThumbName;

                String reply = contentMasterService.update(Integer.parseInt(contentMasterId), contentMasterTitle, desc,  smallRelativePath, middleRelativePath, largeRelativePath, storeType);

                if(reply.equalsIgnoreCase("success"))
                {
                    // Save file on system

                    try
                    {

//                        File smallDir = new File(path + File.separator + "content" + File.separator+dateString);
                        File smallDir = new File(path + "content" + File.separator+dateString);
                                if (!smallDir.exists())
                                        smallDir.mkdirs();

                        if(!smallRelativePathReal.isEmpty())
                        {
                            byte barr[] = smallThumb.getBytes();
                            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(smallRelativePathReal));
                            bout.write(barr);
                            bout.flush();
                            bout.close();
                        }
                        if(!middleRelativePathReal.isEmpty())
                        {
                            byte barr [] = middleThumb.getBytes();
                            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(middleRelativePathReal));
                            bout.write(barr);
                            bout.flush();
                            bout.close();
                        }
//
                        if(!largeRelativePathReal.isEmpty())
                        {
                            byte barr [] = largeThumb.getBytes();
                            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(largeRelativePathReal));
                            bout.write(barr);
                            bout.flush();
                            bout.close();
                        }
//                        prepareMenu(userId, model);
            //  
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                        
                        Object[] userObject = (Object[]) session.getAttribute("user_object");

//                        Object[] contentMasterObject = contentMasterService.readContentMaster(Integer.parseInt(contentMasterId));
//                        model.addAttribute("contentMasterObject", contentMasterObject);
                        
                        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
                        model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                        
//                        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//                        {
//                            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                        }
//                        else
//                        {
//                            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                        }
            //            
            //            System.out.println("Saved");

                        return "admin/content_file_edit";
                    }
                    catch(Exception ex)
                    {
    //                    System.err.println("Error Occure");
                    }
                }
            }
        }
        else if(storeType.equals("REMOTE"))
        {
            if (!smallThumbUrl.isEmpty() && !middleThumbUrl.isEmpty() && !largeThumbUrl.isEmpty()) 
            {
//                String reply = contentMasterService.update(Integer.parseInt(contentMasterId), title, desc, type,  smallThumbUrl, middleThumbUrl, largeThumbUrl, storeType, Integer.parseInt(contentCategoryMasterId));
                String reply = contentMasterService.update(Integer.parseInt(contentMasterId), contentMasterTitle, desc, smallThumbUrl, middleThumbUrl, largeThumbUrl, storeType);
                if(reply.equals("success"))
                {
//                    prepareMenu(userId, model);
            
//                    List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                    model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    if(!sessionValid(session))
                    {
                        return "login";
                    }
                    Object[] userObject = (Object[]) session.getAttribute("user_object");

//                    Object[] contentMasterObject = contentMasterService.readContentMaster(Integer.parseInt(contentMasterId));
//                        model.addAttribute("contentMasterObject", contentMasterObject);
                        
                        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
                        model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                    
//                    if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//                    {
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                    }
//                    else
//                    {
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                    }

                    return "admin/content_file_edit";
                }
            }
        }
//        return "content_master_add";
    return null;
        

    }
    
    @RequestMapping(value = "/edit_content_file_status", method = RequestMethod.GET)
    public String editContentFileStatus(@RequestParam("id") String contentFileId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentFileService.updateStatus(Integer.parseInt(contentFileId), Integer.parseInt(status));
                
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
    
    @RequestMapping(value = "/save_content_files_details", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public String saveContentFilesDetails(HttpServletRequest request, @RequestParam("id") String contentMasterId, @RequestParam("content_master_title") String contentMasterTitle, @RequestParam("content_type_title") String contentTypeMasterTitle, @RequestParam("content_cat_title") String contentCategoryMasterTitle, @RequestParam("content_sub_cat_title") String contentSubCategoryMasterTitle, @RequestParam ("file") MultipartFile file, HttpSession session, ModelMap model)
    {
        
        System.out.println("NA SOO...");
        // Save file on system
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        String widthHeight = request.getParameter("width_height")!=null && !request.getParameter("width_height").equals("") ? request.getParameter("width_height"):"";
        String osVersion = (request.getParameter("os_version")!=null && !request.getParameter("os_version").equals("")) ? request.getParameter("os_version"):"0.0";
        String osName = request.getParameter("os_name")!=null && !request.getParameter("os_name").equals("") ? request.getParameter("os_name"):"";
        Date d = new Date();
        
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String dateString = f.format(d);
        
//        model.addAttribute("contentMasterId", contentMasterId);
//        model.addAttribute("contentMasterTitle", contentMasterTitle);
//        model.addAttribute("contentTypeMasterTitle", contentTypeMasterTitle);
//        model.addAttribute("contentCategoryMasterTitle", contentCategoryMasterTitle);
//        model.addAttribute("contentSubCategoryMasterTitle", contentSubCategoryMasterTitle);
        
        Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeMasterTitle, contentCategoryMasterTitle, contentSubCategoryMasterTitle}; 
        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
                
        if (!file.isEmpty()) 
        {
//            String path = session.getServletContext().getRealPath("/");
//            path = path.substring(0, path.lastIndexOf("CMSBM"));
//            path = path.concat("ROOT\\");
            
            String path = session.getServletContext().getRealPath("/");
            path = path.substring(0, path.lastIndexOf("CMSBM"));
            path = path.concat("ROOT/");
            
            String fileName = file.getOriginalFilename();

            String relativePath = "/content/"+dateString+"/"+fileName;
            String fullPath = path + "content" + File.separator+dateString + File.separator + fileName;

            String reply = contentFileService.create(Integer.parseInt(contentMasterId), relativePath, widthHeight, osName, osVersion);
            if(reply.equals("success"))
            {
                
//                String fileName = file.getOriginalFilename();

                 

                  try
                  {

//                      File dir = new File(path + File.separator + "content" + File.separator + dateString);
                      File dir = new File(path + "content" + File.separator + dateString);
                              if (!dir.exists())
                                      dir.mkdirs();


                      byte barr[] = file.getBytes();
                      BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(fullPath));
//                      BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path+"/media/content_files/"+osName+"/"+osVersion+"/"+fileName));
                      bout.write(barr);
                      bout.flush();
                      bout.close();

                      System.out.println("Saved");
                      
                      Object[] userObject = (Object[]) session.getAttribute("user_object");

//                        Object[] contentMasterObject = contentMasterService.readByOthers(Integer.parseInt(userId), title, desc, Integer.parseInt(contentTypeMasterId),  smallRelativePath, middleRelativePath, largeRelativePath, storeType, Integer.parseInt(contentCategoryMasterId));
                        
//                        model.addAttribute("contentMasterId", contentMasterObject[0].toString());
                        
//                        Object[] contentMasterIdName = { contentMasterId, contentMasterTitle, contentTypeTitle, contentCategoryTitle, contentSubCategoryTitle};    
//                        model.addAttribute("contentMasterIdName", contentMasterIdName);
                        model.addAttribute("feedback", "Operation Successful!!!");
                        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
                        {
                            System.out.println("1111");
//                            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                            List<Object[]> allContentMasterObjectList = contentMasterService.readAllModified();
//                            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                            List<Object[]> allContentFileObjectList = contentFileService.readAll();
                            List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
                            model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                        }
                        else
                        {
                            System.out.println("2222");
//                            List<Object[]> allContentMasterObjectList = contentMasterService.readByIdModified(Integer.parseInt(userObject[0].toString()));
//                            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                            List<Object[]> allContentFileObjectList = contentFileService.readAll();
                            List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
                            model.addAttribute("allContentFileObjectList", allContentFileObjectList);
                        }

                  }
                  catch(Exception ex)
                  {
                      System.err.println("Error Occure");
                  }
            }
            else
            {
//                return "admin/content_files";
                model.addAttribute("feedback", "Operation Fail!!!");
            }
        }

        return "admin/content_file_edit";

    }
    
    
    
    @RequestMapping(value = "/ajax_get_cont_cat", method = RequestMethod.GET)
//    @ResponseBody
    public String ajaxGetContCat( @RequestParam("content_type_id") String contentTypeId, HttpSession session,ModelMap model)
    {
        
//        try {
            if(!sessionValid(session))
            {
                return "login";
            }
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByContentType(Integer.parseInt(contentTypeId));
            
//        prepareMenu(userId, model);
//        if(response.equals("success"))
//        {
//            model.addAttribute("feedback", "Operation Successful");
//        }
//        else if(response.equals("failure"))
//        {
//            model.addAttribute("feedback", "Operation Not Successful");
//        }
//        else if(response.equals("exist"))
//        {
//            model.addAttribute("feedback", "Content Category Already Exist");
//        }


//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

//        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
//        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);

//        Gson gson = new Gson();
////        String userJson = gson.toJson(allContentCategoryMasterObjectList, ContentCategoryMaster.class);
//        String userJson = gson.toJson(allContentCategoryMasterObjectList);
//        System.out.println("ddd: " + userJson);
//        
//        System.out.println("PASSS");
        
//        String jsonElement = gson.toJson(allContentCategoryMasterObjectList.toString());
//        
//        
//        JsonElement jsonElement1 = gson.toJsonTree(allContentCategoryMasterObjectList.toString());
//        System.out.println("jj: " + jsonElement1);
////                JsonArray jsonArray = jsonElement.getAsJsonArray();
//        System.out.println("j: " + jsonElement);
//        //        JSONArray jsonA = JSONArray.fromObject(allContentCategoryMasterObjectList);
//
//        JSONArray jsonArray = new JSONArray(allContentCategoryMasterObjectList);
//            System.out.println("ccc: "+jsonArray);
        return "admin/ajax/ajax_content_category_master";
//return userJson;
//        } catch (JSONException ex) {
//            Logger.getLogger(ContentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
    }
    
    
    
    @RequestMapping(value = "/ajax_get_cont_sub_cat", method = RequestMethod.GET)
//    @ResponseBody
    public String ajaxGetContSubCat( @RequestParam("content_cat_id") String contentCatId, HttpSession session,ModelMap model)
    {
        
//        try {
            if(!sessionValid(session))
            {
                return "login";
            }
            List<Object[]> allContentSubCategoryMasterObjectList = contentSubCategoryMasterService.readByContentCatAndStatus(Integer.parseInt(contentCatId));
            
//        prepareMenu(userId, model);
//        if(response.equals("success"))
//        {
//            model.addAttribute("feedback", "Operation Successful");
//        }
//        else if(response.equals("failure"))
//        {
//            model.addAttribute("feedback", "Operation Not Successful");
//        }
//        else if(response.equals("exist"))
//        {
//            model.addAttribute("feedback", "Content Category Already Exist");
//        }


//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
model.addAttribute("allContentSubCategoryMasterObjectList", allContentSubCategoryMasterObjectList);


        return "admin/ajax/ajax_content_sub_category_master";

    }
    
    
    @RequestMapping(value = "/mc_list_banner_master", method = RequestMethod.GET)
    public String mcListBannerMaster(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid, ModelMap model, HttpSession session)
    {
        
//        prepareMenu(userId, model);
        
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        List<Object[]> allBannerMasterObjectList = bannerMasterService.readAll();
        model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
        
        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
        {
//            List<Object[]> allBannerMasterObjectList = bannerMasterService.readAll();
//            model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
        }
        else
        {
//            List<Object[]> allContentMasterObjectList = bannerMasterService.readById(Integer.parseInt(userObject[0].toString()));
//            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
            
//            List<Object[]> allBannerMasterObjectList = bannerMasterService.readAll();
//            model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
        }

        return "admin/banner_master_list";
        
    }
    
    @RequestMapping(value = "/edit_content_meta_details", method = RequestMethod.GET)
//    public String editContentMetaDetails(@RequestParam("id") String contentMetaDetailId, @RequestParam("content_master_id") String contentMasterId, HttpSession session,  ModelMap model)
    public String editContentMetaDetails(@RequestParam("content_master_id") String contentMasterId, @RequestParam("content_master_title") String contentMasterTitle, @RequestParam("content_type_title") String contentTypeTitle, @RequestParam("content_cat_title") String contentCategoryTitle, @RequestParam("content_sub_cat_title") String contentSubCategoryTitle, HttpSession session, ModelMap model)
    {
        
//        prepareMenu(userId, model);
  
        if(!sessionValid(session))
        {
            return "login";
        }

        
        Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeTitle, contentCategoryTitle, contentSubCategoryTitle}; 
        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
//        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readAll();
        
        List<Object[]> contentMetaMasterList = contentMetaMasterService.readAll();
//        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readByContentMaster(Integer.parseInt(contentMasterId));
//        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readAll();
        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readByContentMaster(Integer.parseInt(contentMasterId));
        
        
//        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
//
        model.addAttribute("contentMetaMasterList", contentMetaMasterList);
        model.addAttribute("contentMetaDetailsList", contentMetaDetailsList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");

//                        model.addAttribute("contentMasterId", contentMasterId);
                    if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
                    {
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAllModified();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    }
                    else
                    {
                        //By User
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readByIdModified(Integer.parseInt(userObject[0].toString()));
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    }
        
//
//
//        model.addAttribute("contentMasterId", contentMasterId);
        
        return "admin/content_metadata_edit";
        
        
        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//
////        String response = contentMetaDetailsService.update(Integer.parseInt(contentMetaDetailId, contentMetaMasterTitle, contentMstDetailValue));
//        Object[] contentMetaDetailObject = contentMetaDetailsService.readByContentMetaDetail(Integer.parseInt(contentMetaDetailId), Integer.parseInt(contentMasterId));
//        
//        
////        List<Object[]> contentMetaMasterList = contentMetaMasterService.readAll();
////        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readByContentMaster(Integer.parseInt(contentMasterId));
//        
//
////        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
////
//        model.addAttribute("contentMetaDetailObject", contentMetaDetailObject);
////        model.addAttribute("contentMetaMasterList", contentMetaMasterList);
////        model.addAttribute("contentMetaDetailsList", contentMetaDetailsList);
////
////
//        model.addAttribute("contentMasterId", contentMasterId);
//        
//        
//        return "admin/content_meta_details_edit";
    }
    
    
    @RequestMapping(value = "/save_content_meta_details", method = RequestMethod.POST)
    public String saveContentMetaDetails(@RequestParam("content_master_id") String contentMasterId, @RequestParam("content_master_title") String contentMasterTitle, @RequestParam("content_type_title") String contentTypeTitle, @RequestParam("content_cat_title") String contentCategoryTitle, @RequestParam("content_sub_cat_title") String contentSubCategoryTitle, @RequestParam("content_meta_master_title") String contentMetaMasterTitle, @RequestParam("content_mst_detail_value") String contentMstDetailValue, HttpSession session,  ModelMap model)
    {
        
//        prepareMenu(userId, model);
  
        
        if(!sessionValid(session))
        {
            return "login";
        }

        String response = contentMetaDetailsService.create(Integer.parseInt(contentMasterId), contentMetaMasterTitle, contentMstDetailValue);
        System.out.println("reply: " + response);
        if(response.equals("success"))
        {
//            System.out.println("Saved..");
            model.addAttribute("feedback", "Operation Successful");
        }
        else if(response.equals("failure"))
        {
            model.addAttribute("feedback", "Operation Not Successful");
        }
        else if(response.equals("exist"))
        {
            model.addAttribute("feedback", "Content Category Already Exist");
        }
        List<Object[]> contentMetaMasterList = contentMetaMasterService.readAll();
        List<Object[]> contentMetaDetailsList = contentMetaDetailsService.readByContentMaster(Integer.parseInt(contentMasterId));
        

//        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
//
        model.addAttribute("contentMetaMasterList", contentMetaMasterList);
        model.addAttribute("contentMetaDetailsList", contentMetaDetailsList);
        
        Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeTitle, contentCategoryTitle, contentSubCategoryTitle}; 
        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");

//                        model.addAttribute("contentMasterId", contentMasterId);
                    if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
                    {
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAllModified();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    }
                    else
                    {
                        //By User
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readByIdModified(Integer.parseInt(userObject[0].toString()));
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
                    }

        return "admin/content_metadata_edit";
    }
    
    
    @RequestMapping(value = "/bk_save_content_files_details", method = RequestMethod.GET)
    public String bkSaveContentFilesDetails(@RequestParam("id") String contentMasterId, @RequestParam("content_master_title") String contentMasterTitle, @RequestParam("content_type_title") String contentTypeMasterTitle, @RequestParam("content_cat_title") String contentCategoryMasterTitle, @RequestParam("content_sub_cat_title") String contentSubCategoryMasterTitle, HttpSession session, ModelMap model)
    {
        System.out.println("NA SOO...");
        // Save file on system
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        Object[] contentMasterDisplay = { contentMasterId, contentMasterTitle, contentTypeMasterTitle, contentCategoryMasterTitle, contentSubCategoryMasterTitle}; 
        model.addAttribute("contentMasterDisplay", contentMasterDisplay);
        
        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
        model.addAttribute("allContentFileObjectList", allContentFileObjectList);

        return "admin/content_file_edit";

    }
    

    @RequestMapping(value = "/add_banner_master", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    public String addBannerMaster(@RequestParam("content_type_master_id") String contentTypeMasterId, @RequestParam("title") String bannerTitle, @RequestParam("size") String bannerSize, @RequestParam("url") String bannerUrl, @RequestParam("banner_image") MultipartFile bannerImage, @RequestParam("dis_title") String displayTitle, @RequestParam("dis_desc") String displayDescription, @RequestParam("dis_amount") String displayAmount, @RequestParam("dis_disc_percentage") String displayDiscountPercentage, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, HttpSession session, ModelMap model)
    public String addBannerMaster(HttpServletRequest request, @RequestParam("content_type_master_id") String contentTypeMasterId, @RequestParam("title") String bannerTitle, @RequestParam("size") String bannerSize, @RequestParam("url") String bannerUrl, @RequestParam("banner_image") MultipartFile bannerImage, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, HttpSession session, ModelMap model)
    {
        System.out.println("st: " + startDate);
        if(!sessionValid(session))
        {
            return "login";
        }
        String displayTitle = request.getParameter("dis_title");
        String displayDescription = request.getParameter("dis_desc");
        String displayAmount = request.getParameter("dis_amount").equals("")?"0.0" : request.getParameter("dis_amount");
        String displayDiscountPercentage = request.getParameter("dis_disc_percentage").equals("")?"0.0" : request.getParameter("dis_amount");
        Date d = new Date();
        System.out.println("haaa");
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String dateString = f.format(d);
        
//        String path = session.getServletContext().getRealPath("/");
//        path = path.substring(0, path.lastIndexOf("CMSBM"));
//        path = path.concat("ROOT\\");

    

        String path = session.getServletContext().getRealPath("/");
        path = path.substring(0, path.lastIndexOf("CMSBM"));
        path = path.concat("ROOT/");
        
//        System.out.println("1: " + smallThumbUrl);
//        System.out.println("1: " + smallThumb.getOriginalFilename());
//        if(storeType.equals("LOCAL"))
//        {
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
            if (!bannerImage.isEmpty()) 
            {

                String bannerImageName = bannerImage.getOriginalFilename();

//                String middleThumbName = middleThumb.getOriginalFilename();
//
//                String largeThumbName = largeThumb.getOriginalFilename();

                 String bannerImageRelativePath = "/content/banner/"+dateString + "/" + bannerImageName;
//                String smallRelativePath = "/content/banner/"+dateString + "/" + bannerImageName;
//                String middleRelativePath = "/content/"+dateString+"/"+middleThumbName;
//                String largeRelativePath = "/content/"+dateString+"/"+largeThumbName;
                
                String bannerImageRelativePathReal = path + "content" + File.separator + "banner" + File.separator+dateString + File.separator + bannerImageName;
//                String smallRelativePathReal = path + "content" + File.separator + "banner" + File.separator+dateString + File.separator + bannerImageName;
//                String middleRelativePathReal = path + "content" + File.separator+dateString + File.separator + middleThumbName;
//                String largeRelativePathReal = path + "content" + File.separator+dateString + File.separator + largeThumbName;

                String reply = bannerMasterService.create(Integer.parseInt(contentTypeMasterId), bannerTitle,  bannerImageRelativePath, bannerSize, bannerUrl, displayTitle, displayDescription, Double.parseDouble(displayAmount), Double.parseDouble(displayDiscountPercentage), startDate, endDate);

                
//                String reply = "failure";
                
                if(reply.equals("success"))
                {
                    // Save file on system

                    try
                    {

//                        File smallDir = new File(path + File.separator + "content" + File.separator+dateString);
                        File smallDir = new File(path + "content" + File.separator + "banner" + File.separator+dateString);
                                if (!smallDir.exists())
                                        smallDir.mkdirs();

                        byte barr[] = bannerImage.getBytes();
//                        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + smallRelativePath));
                        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(bannerImageRelativePathReal));
                        bout.write(barr);
                        bout.flush();
                        bout.close();

                        
                        
                        System.out.println("RealPath: "+bannerImageRelativePathReal);
//                        prepareMenu(userId, model);
            //  
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);

//                        if(!sessionValid(session))
//                        {
//                            return "login";
//                        }
//                        Object[] userObject = (Object[]) session.getAttribute("user_object");

//                        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//                        {
                            List<Object[]> allBannerMasterObjectList = bannerMasterService.readAll();
                            model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
//                        }
//                        else
//                        {
//                            List<Object[]> allBannerMasterObjectList = bannerMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                            model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
//                        }

            //            
            //            System.out.println("Saved");
                        model.addAttribute("feedback", "Operation Successful");
                        return "admin/banner_master_add";
                    }
                    catch(Exception ex)
                    {
    //                    System.err.println("Error Occure");
                    }
                }
            }
        
        
//        return "content_master_add";
    return null;
        

    }
    
    @RequestMapping(value = "/edit_banner_details", method = RequestMethod.GET)
    public String editBannerDetails(@RequestParam("id") String id, HttpSession session, ModelMap model)
    {
        if(!sessionValid(session))
        {
            return "login";
        }
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        Object[] bannerMasterObject = bannerMasterService.readBanner(Integer.parseInt(id));

        model.addAttribute("bannerMasterObject", bannerMasterObject);
           
//            prepareMenu(userId, model);
            

        return "admin/banner_master_edit";
    }
    
    
    @RequestMapping(value = "/save_banner_details", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public String saveBannerDetails(HttpServletRequest request, @RequestParam("id") String bannerId, @RequestParam("content_type_master_id") String contentTypeMasterId,  @RequestParam("title") String bannerTitle, @RequestParam("size") String bannerSize, @RequestParam("url") String bannerUrl, @RequestParam("banner_image") MultipartFile bannerImage, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String displayTitle = request.getParameter("dis_title");
        String displayDescription = request.getParameter("dis_desc");
        String displayAmount = request.getParameter("dis_amount").equals("")?"0.0" : request.getParameter("dis_amount");
        String displayDiscountPercentage = request.getParameter("dis_disc_percentage").equals("")?"0.0" : request.getParameter("dis_amount");
        Date d = new Date();
        System.out.println("haaa");
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String dateString = f.format(d);
        
//        String path = session.getServletContext().getRealPath("/");
//        path = path.substring(0, path.lastIndexOf("CMSBM"));
//        path = path.concat("ROOT\\");

    

        String path = session.getServletContext().getRealPath("/");
        path = path.substring(0, path.lastIndexOf("CMSBM"));
        path = path.concat("ROOT/");
        
//        System.out.println("1: " + smallThumbUrl);
//        System.out.println("1: " + smallThumb.getOriginalFilename());
//        if(storeType.equals("LOCAL"))
//        {
            if (!bannerImage.isEmpty()) 
            {

                String bannerImageName = bannerImage.getOriginalFilename();

//                String middleThumbName = middleThumb.getOriginalFilename();
//
//                String largeThumbName = largeThumb.getOriginalFilename();

                 String bannerImageRelativePath = "/content/banner/"+dateString + "/" + bannerImageName;
//                String smallRelativePath = "/content/banner/"+dateString + "/" + bannerImageName;
//                String middleRelativePath = "/content/"+dateString+"/"+middleThumbName;
//                String largeRelativePath = "/content/"+dateString+"/"+largeThumbName;
                
                String bannerImageRelativePathReal = path + "content" + File.separator + "banner" + File.separator+dateString + File.separator + bannerImageName;
//                String smallRelativePathReal = path + "content" + File.separator + "banner" + File.separator+dateString + File.separator + bannerImageName;
//                String middleRelativePathReal = path + "content" + File.separator+dateString + File.separator + middleThumbName;
//                String largeRelativePathReal = path + "content" + File.separator+dateString + File.separator + largeThumbName;

                String reply = bannerMasterService.update(Integer.parseInt(bannerId), Integer.parseInt(contentTypeMasterId), bannerTitle,  bannerImageRelativePath, bannerSize, bannerUrl, displayTitle, displayDescription, Double.parseDouble(displayAmount), Double.parseDouble(displayDiscountPercentage), startDate, endDate);

                
//                String reply = "failure";
                
                if(reply.equals("success"))
                {
                    // Save file on system

                    try
                    {

//                        File smallDir = new File(path + File.separator + "content" + File.separator+dateString);
                        File smallDir = new File(path + "content" + File.separator + "banner" + File.separator+dateString);
                                if (!smallDir.exists())
                                        smallDir.mkdirs();

                        byte barr[] = bannerImage.getBytes();
//                        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + smallRelativePath));
                        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(bannerImageRelativePathReal));
                        bout.write(barr);
                        bout.flush();
                        bout.close();

                        
                        
                        System.out.println("RealPath: "+bannerImageRelativePathReal);
//                        prepareMenu(userId, model);
            //  
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);

//                        if(!sessionValid(session))
//                        {
//                            return "login";
//                        }
//                        Object[] userObject = (Object[]) session.getAttribute("user_object");

//                        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//                        {
                            List<Object[]> allBannerMasterObjectList = bannerMasterService.readAll();
                            model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
                            List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
                            model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
//                        }
//                        else
//                        {
//                            List<Object[]> allBannerMasterObjectList = bannerMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                            model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
//                        }

            //            
            //            System.out.println("Saved");
                        model.addAttribute("feedback", "Operation Successful");
                        return "admin/banner_master_list";
                    }
                    catch(Exception ex)
                    {
    //                    System.err.println("Error Occure");
                        ex.printStackTrace();
                    }
                }
            }
        
        
//        return "content_master_add";
    return null;
        

    }
    
    @RequestMapping(value = "/edit_banner_master_status", method = RequestMethod.GET)
    public String editBannerMasterStatus(@RequestParam("banner_id") String bannerId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        System.out.println("Bann");
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = bannerMasterService.updateStatus(Integer.parseInt(bannerId), Integer.parseInt(status));
                System.out.println(response);
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
    
    
    @RequestMapping(value = "/get_content_cat", method = RequestMethod.GET)
    public String getContentCategory(@RequestParam("id") String contentTypeId, HttpSession session,ModelMap model)
    {
        System.out.println(contentTypeId);
        if(!sessionValid(session))
        {
            return "login";
        }
//        prepareMenu(userId, model);
  
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
        List<Object[]> allContentCategoryMasterObjectList=null;
        if(!contentTypeId.equals("0"))
        {
            allContentCategoryMasterObjectList = contentCategoryMasterService.readByContentType(Integer.parseInt(contentTypeId));
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        }
        else
        {
            allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        }
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        System.out.println("okk");
        model.addAttribute("contentTypeId", contentTypeId);
        return "admin/content_category_master";
    }
    
    @RequestMapping(value = "/mc_add_list_content_portal_mst", method = RequestMethod.GET)
    public String mcAddListContentPortalMaster(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        System.out.println("bbb");
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        
//        prepareMenu(userId, model);
        
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/content_portal_master_add";
    }
    
    @RequestMapping(value = "/add_list_content_portal_mst", method = RequestMethod.POST)
    public String addContentPortalMaster(@RequestParam("title") String title, @RequestParam("desc") String desc, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        String response = contentCategoryMasterService.create(title, localTitle);
        String response = contentPortalMasterService.create(title, desc);
        if(response.equals("success"))
        {
//            System.out.println("Saved..");
            model.addAttribute("feedback", "Operation Successful");
        }
        else if(response.equals("failure"))
        {
            model.addAttribute("feedback", "Operation Not Successful");
        }
        else if(response.equals("exist"))
        {
            model.addAttribute("feedback", "Content Mapping Already Exist");
        }
//        prepareMenu(userId, model);
        
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/content_portal_master_add";
    }
    
    
    @RequestMapping(value = "/mc_list_content_portal", method = RequestMethod.GET)
    public String mcListContentPortal(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        System.out.println("bbb");
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        
//        prepareMenu(userId, model);
        
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
        
        return "admin/content_portal_master_list";
    }
    
    
    @RequestMapping(value = "/get_banner_by_status_type", method = RequestMethod.GET)
    public String getBannerByStatus(@RequestParam("type") String contentTypeId, @RequestParam("status") String bannerMasterStatus, ModelMap model, HttpSession session)
    {
        
//        prepareMenu(userId, model);
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
       
            
            if(contentTypeId.equalsIgnoreCase("0") && bannerMasterStatus.equalsIgnoreCase("-1"))
            {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
                List<Object[]> allBannerMasterObjectList = bannerMasterService.readAll();
                model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
                
            }
            else if(!contentTypeId.equalsIgnoreCase("0") && bannerMasterStatus.equalsIgnoreCase("-1"))
            {
                List<Object[]> allBannerMasterObjectList = bannerMasterService.readByType(Integer.parseInt(contentTypeId));
                model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
                
                model.addAttribute("bannerMasterStatus", bannerMasterStatus);
            }
            else if(contentTypeId.equalsIgnoreCase("0") && !bannerMasterStatus.equalsIgnoreCase("-1"))
            {
                List<Object[]> allBannerMasterObjectList = bannerMasterService.readByStatus(Integer.parseInt(bannerMasterStatus));
                model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
                
                model.addAttribute("bannerMasterStatus", bannerMasterStatus);
            }
            else if(!contentTypeId.equalsIgnoreCase("0") && !bannerMasterStatus.equalsIgnoreCase("-1"))
            {
                List<Object[]> allBannerMasterObjectList = bannerMasterService.readByTypeStatus(Integer.parseInt(contentTypeId), Integer.parseInt(bannerMasterStatus));
                model.addAttribute("allBannerMasterObjectList", allBannerMasterObjectList);
                
                model.addAttribute("bannerMasterStatus", bannerMasterStatus);
            }
            
            model.addAttribute("contentTypeId", contentTypeId);
            model.addAttribute("bannerMasterStatus", bannerMasterStatus);
                
//            }
//        }
        return "admin/banner_master_list";
    }
    
    
    @RequestMapping(value = "/get_portal_by_status", method = RequestMethod.GET)
    public String getPortalByStatus(@RequestParam("status") String contentPortalStatus, ModelMap model, HttpSession session)
    {
        if(contentPortalStatus.equalsIgnoreCase("-1"))
        {
//                List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);

        }

        else
        {
            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(Integer.parseInt(contentPortalStatus));
            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);

            
        }

        
        model.addAttribute("contentPortalStatus", contentPortalStatus);
        
        return "admin/content_portal_master_list";
    }

    @RequestMapping(value = "/mc_port_cont_mp", method = RequestMethod.GET)
    public String mcPortalContentMapping(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
        Object[] userObject = (Object[]) session.getAttribute("user_object");

//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(1);
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAllFromContentMaster();
//            List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
            List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAllFromContentMaster();
//            List<Object[]> allUserMasterObjectList = userMasterService.readByType("CONTENT-PROVIDER");
            List<Object[]> allUserMasterObjectList = userMasterService.readAllFromContentMaster();
//            List<Object[]> allPortalContentMappingObjectList = portalContentMappingService.readAll();
//            List<Object[]> allContentMasterObjectList = contentMasterService.readAllForPortalMapping();
            
            

            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
            model.addAttribute("allUserMasterObjectList", allUserMasterObjectList);
            model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
            
//            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
   
        return "admin/portal_content_mapp";
    }
    
    //new cms
    @RequestMapping(value = "/mc_cont_port_type_mp", method = RequestMethod.GET)
    public String mcContentPortalTypeMapping(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid,  HttpSession session,ModelMap model)
    {
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        prepareMenu(userId, model);
        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//    //        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
            
            
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(1);

            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
//        }
//        else
//        {
  
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//            
//            model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//
//            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
        
        return "admin/portal_type_mapp";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Update Close
    
    //Checked...
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Checked...
    @RequestMapping(value = "/mc_add_content_file", method = RequestMethod.GET)
    public String mcAddContentFile(@RequestParam("id") String contentMasterId, @RequestParam("type") String contentMasterType, HttpSession session,  ModelMap model)
    {
        
//        prepareMenu(userId, model);
  
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        List<Object[]> allContentFileObjectList = contentFileService.readAll();
        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
//        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
        model.addAttribute("allContentFileObjectList", allContentFileObjectList);
//        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);

        model.addAttribute("contentMasterId", contentMasterId);
        model.addAttribute("contentMasterType", contentMasterType);
//        if(contentMasterType.equals("AUDIO-EBOOK"))
//        {
//            return "admin/content_files_ebook";
//        }
        return "admin/content_files";
    }
    
    //Checked...
    
    
    
    
    
    
    
    
//    @RequestMapping(value = "/uploadquetfile1", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    public ModelAndView uploadQuetionFile1(@RequestParam("file") MultipartFile file,  HttpSession session)
    
//@RequestParam("user_id") String userId, @RequestParam("width_height") String widthHeight, @RequestParam("os_name") String osName, @RequestParam("os_version") String osVersion,     
    //Checked...
    
    
    //To confirm if video can be uploaded
    //Should be deleted
//    @RequestMapping(value = "/add_content_file", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    public String addContentFile(@RequestParam("content_master_id") String contentMasterId, @RequestParam("width_height") String widthHeight, @RequestParam("os_name") String osName, @RequestParam("os_version") String osVersion, @RequestParam ("file") MultipartFile file, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        Date d = new Date();
//        
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        String dateString = f.format(d);
//        
//        if (!file.isEmpty()) 
//        {
//            String path = session.getServletContext().getRealPath("/");
////            path = path.substring(0, path.lastIndexOf("CMSBM"));
////            path = path.concat("ROOT/");
//            
//            String fileName = file.getOriginalFilename();
//
////            String relativePath = "/content/"+dateString+"/"+fileName;
//            String fullPath = path + "content" + File.separator+dateString + File.separator + fileName;
//            if(true)
//            {
//                  try
//                  {
//
////                      File dir = new File(path + File.separator + "content" + File.separator + dateString);
//                      File dir = new File(path + "content" + File.separator + dateString);
//                              if (!dir.exists())
//                                      dir.mkdirs();
//
//
//                      byte barr[] = file.getBytes();
//                      BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(fullPath));
////                      BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path+"/media/content_files/"+osName+"/"+osVersion+"/"+fileName));
//                      bout.write(barr);
//                      bout.flush();
//                      bout.close();
//
//                      System.out.println("Saved");
//
//                  }
//                  catch(Exception ex)
//                  {
//                      System.err.println("Error Occure");
//                  }
//            }
//
//        }
//
//        return null;
//
//    }
    
    @RequestMapping(value = "/demo", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public String demo(@RequestParam("m") String m, @RequestParam("n") MultipartFile n, HttpSession session, ModelMap model)
    {
        System.out.println("WORKING...");
        return null;
    }
    
//    CommonsMultipartFile
//    @ResponseBody
//    @RequestPart
    
//    @PostMapping("/demoo")
//    @RequestMapping(value = {"/demoo"}, method = RequestMethod.POST)
//    public @ResponseBody String demoo( @RequestParam("file") MultipartFile file, HttpSession session, ModelMap model)
//    {
//        System.out.println("WORKINGcc...");
//        return null;
//    }
    //Checked....
    
    
    //Checked...
//    @RequestMapping(value = "/save_content_master_details", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    public String saveContentMasterDetails(@RequestParam("id") String contentMasterId, @RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("type") String type, @RequestParam("store_type") String storeType, @RequestParam("small_thumb") MultipartFile smallThumb, @RequestParam("middle_thumb") MultipartFile middleThumb, @RequestParam ("large_thumb") MultipartFile largeThumb, @RequestParam("small_thumb_url") String smallThumbUrl, @RequestParam("middle_thumb_url") String middleThumbUrl, @RequestParam ("large_thumb_url") String largeThumbUrl, @RequestParam("cont_cat_mst_id") String contentCategoryMasterId, @RequestParam("small") String small, @RequestParam("mid") String mid, @RequestParam("large") String large, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        Date d = new Date();
//        
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        String dateString = f.format(d);
//        
////        String path = session.getServletContext().getRealPath("/");
////        path = path.substring(0, path.lastIndexOf("CMSBM"));
////        path = path.concat("ROOT\\");
//
//        String path = session.getServletContext().getRealPath("/");
//        path = path.substring(0, path.lastIndexOf("CMSBM"));
//        path = path.concat("ROOT/");
//
//        if(storeType.equals("LOCAL"))
//        {
//            if (!smallThumb.isEmpty() && !middleThumb.isEmpty() && !largeThumb.isEmpty()) 
//            {
//
//                dateString = small.substring(small.indexOf("/", 3)+1, small.lastIndexOf("/"));
//                
//                System.out.println(dateString);
//                
//                String smallThumbName = smallThumb.getOriginalFilename();
//
//                String middleThumbName = middleThumb.getOriginalFilename();
//
//                String largeThumbName = largeThumb.getOriginalFilename();
//
////                String smallRelativePath = "/content/"+dateString + "/" + smallThumbName;
////                String middleRelativePath = "/content/"+dateString+"/"+middleThumbName;
////                String largeRelativePath = "/content/"+dateString+"/"+largeThumbName;
//
//                String smallRelativePath = "/content/"+dateString + "/" + smallThumbName;
//                String middleRelativePath = "/content/"+dateString+"/"+middleThumbName;
//                String largeRelativePath = "/content/"+dateString+"/"+largeThumbName;
//                
//                String smallRelativePathReal = path + "content" + File.separator+dateString + File.separator + smallThumbName;
//                String middleRelativePathReal = path + "content" + File.separator+dateString + File.separator + middleThumbName;
//                String largeRelativePathReal = path + "content" + File.separator+dateString + File.separator + largeThumbName;
//
//                String reply = contentMasterService.update(Integer.parseInt(contentMasterId), title, desc, type,  smallRelativePath, middleRelativePath, largeRelativePath, storeType, Integer.parseInt(contentCategoryMasterId));
//
//                if(reply.equalsIgnoreCase("success"))
//                {
//                    // Save file on system
//
//                    try
//                    {
//
////                        File smallDir = new File(path + File.separator + "content" + File.separator+dateString);
//                        File smallDir = new File(path + "content" + File.separator+dateString);
//                                if (!smallDir.exists())
//                                        smallDir.mkdirs();
//
//                        byte barr[] = smallThumb.getBytes();
//                        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(smallRelativePathReal));
//                        bout.write(barr);
//                        bout.flush();
//                        bout.close();
//
//                        barr = middleThumb.getBytes();
//                        bout = new BufferedOutputStream(new FileOutputStream(middleRelativePathReal));
//                        bout.write(barr);
//                        bout.flush();
//                        bout.close();
////
//                        barr = largeThumb.getBytes();
//                        bout = new BufferedOutputStream(new FileOutputStream(largeRelativePathReal));
//                        bout.write(barr);
//                        bout.flush();
//                        bout.close();
//            
////                        prepareMenu(userId, model);
//            //  
////                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
////                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                        
//                        Object[] userObject = (Object[]) session.getAttribute("user_object");
//
//                        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//                        {
//                            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                        }
//                        else
//                        {
//                            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                        }
//            //            
//            //            System.out.println("Saved");
//
//                        return "admin/content_master";
//                    }
//                    catch(Exception ex)
//                    {
//    //                    System.err.println("Error Occure");
//                    }
//                }
//            }
//        }
//        else if(storeType.equals("REMOTE"))
//        {
//            if (!smallThumbUrl.isEmpty() && !middleThumbUrl.isEmpty() && !largeThumbUrl.isEmpty()) 
//            {
//                String reply = contentMasterService.update(Integer.parseInt(contentMasterId), title, desc, type,  smallThumbUrl, middleThumbUrl, largeThumbUrl, storeType, Integer.parseInt(contentCategoryMasterId));
//                if(reply.equals("success"))
//                {
////                    prepareMenu(userId, model);
//            
////                    List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
////                    model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                    if(!sessionValid(session))
//                    {
//                        return "login";
//                    }
//                    Object[] userObject = (Object[]) session.getAttribute("user_object");
//
//                    if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//                    {
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                    }
//                    else
//                    {
//                        List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//                    }
//
//                    return "admin/content_master";
//                }
//            }
//        }
////        return "content_master_add";
//    return null;
//        
//
//    }
//    
    
    //Should be deleted
    
    
//    @RequestMapping(value = "/add_content_master_url", method = RequestMethod.POST)
//    public String addContentMasterUrl(@RequestParam("user_id") String userId, @RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("type") String type, @RequestParam("store_type") String storeType, @RequestParam("small_thumb") String smallThumb, @RequestParam("middle_thumb") String middleThumb, @RequestParam ("large_thumb") String largeThumb, @RequestParam("cont_cat_mst_id") String contentCategoryMasterId, ModelMap model)
//    {
////        String path = session.getServletContext().getRealPath("/");
//        System.out.println("1: " + smallThumb);
//        
//        
////        if (!smallThumb.isEmpty() && !middleThumb.isEmpty() && !largeThumb.isEmpty()) 
////        {
////        
////            String s = smallThumb.getOriginalFilename();
////
////          String m = middleThumb.getOriginalFilename();
////
////          String l = largeThumb.getOriginalFilename();
////            
////            String smallDirectory = path+"/media/content_master/small_thumnail/"+s;
////            String middleDirectory = path+"/media/content_master/medium_thumnail/"+m;
////            String largeDirectory = path+"/media/content_master/large_thumnail/"+l;
////            String reply = contentMasterService.create(title, desc, type, Integer.parseInt(userRating), Integer.parseInt(userRated),  smallDirectory, middleDirectory, largeDirectory, storeType, Integer.parseInt(contentCategoryMasterId));
////            if(reply.equals("success"))
////            {
////                // Save file on system
////
////                try
////                {
////                    File smallDir = new File(path + File.separator + "media" + File.separator+"content_master" + File.separator+"small_thumnail");
////                            if (!smallDir.exists())
////                                    smallDir.mkdirs();
////
////                    File mediumDir = new File(path + File.separator + "media" + File.separator+"content_master" + File.separator+"medium_thumnail");
////                            if (!mediumDir.exists())
////                                    mediumDir.mkdirs();
////
////                    File largeDir = new File(path + File.separator + "media" + File.separator+"content_master" + File.separator+"large_thumnail");
////                            if (!largeDir.exists())
////                                    largeDir.mkdirs();
////        //            
////        //            
////                    byte barr[] = smallThumb.getBytes();
////                    BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(smallDirectory));
////                    bout.write(barr);
////                    bout.flush();
////                    bout.close();
////
////                    barr = middleThumb.getBytes();
////                    bout = new BufferedOutputStream(new FileOutputStream(middleDirectory));
////                    bout.write(barr);
////                    bout.flush();
////                    bout.close();
////
////                    barr = largeThumb.getBytes();
////                    bout = new BufferedOutputStream(new FileOutputStream(largeDirectory));
////                    bout.write(barr);
////                    bout.flush();
////                    bout.close();
////        //            
//////                    String reply = contentMasterService.create(title, desc, type, Integer.parseInt(userRating), Integer.parseInt(userRated),  smallDirectory, middleDirectory, largeDirectory, storeType, Integer.parseInt(contentCategoryMasterId));
////        //            
////                    prepareMenu(userId, model);
////        //  
////                    List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
////                    model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
////        //            
////        //            System.out.println("Saved");
////
////                    return "admin/content_master";
////                }
////                catch(Exception ex)
////                {
//////                    System.err.println("Error Occure");
////                }
////            }
////        }
////        return "content_master_add";
//return null;
//
//    }
//    
    
    
    
    
    
    
    //cHECKED...
    @RequestMapping(value = "/add_content_cat_mpp", method = RequestMethod.POST)
    public String addContentCategory_mapping(@RequestParam("content_master") String contentMaster, @RequestParam("content_category_master") String contentCategoryMaster, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        String response = contentCategoryMappingService.create(Integer.parseInt(contentMaster), Integer.parseInt(contentCategoryMaster));
        if(response.equals("success"))
        {
//            System.out.println("Saved..");
            model.addAttribute("feedback", "Operation Successful");
        }
        else if(response.equals("failure"))
        {
            model.addAttribute("feedback", "Operation Not Successful");
        }
        else if(response.equals("exist"))
        {
            model.addAttribute("feedback", "Content Mapping Already Exist");
        }
//        prepareMenu(userId, model);
  
//        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readAll();/
//        
//        
//        model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
//        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
        {
//            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readAll();

            model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        }
        else
        {
            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readByUserId(Integer.parseInt(userObject[0].toString()));

            model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        }

        return "admin/content_category_mapping";
    }
    
    //Checked...
    //New
    @RequestMapping(value = "/get_content_portal_cat_mpp", method = RequestMethod.POST)
    public String addContentPortalCategoryMapping(@RequestParam("content_portal_master") String contentPortalMasterId,  HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
//         List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        
//        List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//      
//        model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//        
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
            
            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(1);
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
                    
            for(int count=0; count < allContentCategoryMasterObjectList.size(); ++count)
        {
            Object[] allPortalCategoryMappingObjectList = contentPortalCategoryMappingService.readByCategoryPortal(Integer.parseInt(allContentCategoryMasterObjectList.get(count)[0].toString()), Integer.parseInt(contentPortalMasterId));
            
            
            
            if(allPortalCategoryMappingObjectList==null)
            {
                
                allContentCategoryMasterObjectList.get(count)[3] = 0;
                
            }
            else if(allPortalCategoryMappingObjectList[3].toString().equals("0"))
            {
                allContentCategoryMasterObjectList.get(count)[3] = 0;
                System.out.println("onj: " + allPortalCategoryMappingObjectList[3]);
            }
            else
            {
                allContentCategoryMasterObjectList.get(count)[3] = 1;
            }
        }
        
        
     
            
      

            model.addAttribute("contentPortalMasterId", contentPortalMasterId);

            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        return "admin/portal_category_mapp";
    }
    
    //new cms
    @RequestMapping(value = "/get_content_type_portal_mpp", method = RequestMethod.POST)
    public String addContentPortalTypeMapping(@RequestParam("content_portal_master") String contentPortalMasterId, HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        String response = contentTypePortalMappingService.create(Integer.parseInt(contentPortalMaster), Integer.parseInt(contentTypeMaster));
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(1);
//        List<Object[]> allTypePortalMappingObjectList = contentTypePortalMappingService.readAllWithTypeByContentPortal(Integer.parseInt(contentPortalMasterId));
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        System.out.println("Type Size: " + allContentTypeMasterObjectList.size());
        for(int count=0; count < allContentTypeMasterObjectList.size(); ++count)
        {
            Object[] allTypePortalMappingStatus = contentTypePortalMappingService.readByTypePortal(Integer.parseInt(allContentTypeMasterObjectList.get(count)[0].toString()), Integer.parseInt(contentPortalMasterId));
            
            System.out.println("onj: " + allTypePortalMappingStatus);
            
            if(allTypePortalMappingStatus==null)
            {
                System.out.println("enter: null");
                allContentTypeMasterObjectList.get(count)[3] = 0;
                
            }
            else if(allTypePortalMappingStatus[3].toString().equals("0"))
            {
                allContentTypeMasterObjectList.get(count)[3] = 0;
                System.out.println("onj: " + allTypePortalMappingStatus[3]);
            }
            else
            {
                allContentTypeMasterObjectList.get(count)[3] = 1;
            }
        }
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
     
            
      
            model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);

            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
            model.addAttribute("contentPortalMasterId", contentPortalMasterId);
//            model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
 
        return "admin/portal_type_mapp";
    }
    
    
    @RequestMapping(value = "/get_portal_content_mpp", method = RequestMethod.POST)
    public String addPortalContentMapping(HttpServletRequest request, @RequestParam("content_portal_master") String contentPortalMasterId, HttpSession session,ModelMap model)
    {
       
        if(!sessionValid(session))
        {
            return "login";
        }
        
        String userMaster = request.getParameter("user_master")!=null && !request.getParameter("user_master").equals("") ? request.getParameter("user_master"):"";
        String contentTypeMaster = request.getParameter("content_type_master")!=null && !request.getParameter("content_type_master").equals("") ? request.getParameter("content_type_master"):"";
        String contentCategoryMaster = request.getParameter("content_category_master")!=null && !request.getParameter("content_category_master").equals("") ? request.getParameter("content_category_master"):"";
        
        List<Object[]> allContentMasterObjectList = null;
        if(!userMaster.isEmpty() && !contentTypeMaster.isEmpty() && !contentCategoryMaster.isEmpty())
        {
            allContentMasterObjectList = contentMasterService.readAllForPortalMappingByUserTypeCategory(Integer.parseInt(userMaster), Integer.parseInt(contentTypeMaster), Integer.parseInt(contentCategoryMaster));
            model.addAttribute("userMaster", userMaster);
            model.addAttribute("contentTypeMaster", contentTypeMaster);
            model.addAttribute("contentCategoryMaster", contentCategoryMaster);
        }
        else if(!userMaster.isEmpty() && !contentTypeMaster.isEmpty() && contentCategoryMaster.isEmpty())
        {
            allContentMasterObjectList = contentMasterService.readAllForPortalMappingByUserType(Integer.parseInt(userMaster), Integer.parseInt(contentTypeMaster));
            model.addAttribute("userMaster", userMaster);
            model.addAttribute("contentTypeMaster", contentTypeMaster);
        }
        else if(!userMaster.isEmpty() && contentTypeMaster.isEmpty() && !contentCategoryMaster.isEmpty())
        {
            allContentMasterObjectList = contentMasterService.readAllForPortalMappingByUserCategory(Integer.parseInt(userMaster), Integer.parseInt(contentCategoryMaster));
            model.addAttribute("userMaster", userMaster);
            model.addAttribute("contentCategoryMaster", contentCategoryMaster);
        }
        else if(!userMaster.isEmpty() && contentTypeMaster.isEmpty() && contentCategoryMaster.isEmpty())
        {
            System.out.println("USER ONLY");
            allContentMasterObjectList = contentMasterService.readAllForPortalMappingByUser(Integer.parseInt(userMaster));
            model.addAttribute("userMaster", userMaster);
        }
        
        else if(userMaster.isEmpty() && !contentTypeMaster.isEmpty() && !contentCategoryMaster.isEmpty())
        {
            allContentMasterObjectList = contentMasterService.readAllForPortalMappingByTypeCategory(Integer.parseInt(contentTypeMaster), Integer.parseInt(contentCategoryMaster));
            model.addAttribute("contentTypeMaster", contentTypeMaster);
            model.addAttribute("contentCategoryMaster", contentCategoryMaster);
        }
        else if(userMaster.isEmpty() && !contentTypeMaster.isEmpty() && contentCategoryMaster.isEmpty())
        {
            allContentMasterObjectList = contentMasterService.readAllForPortalMappingByType(Integer.parseInt(contentTypeMaster));
            model.addAttribute("contentTypeMaster", contentTypeMaster);
        }
        else if(userMaster.isEmpty() && contentTypeMaster.isEmpty() && !contentCategoryMaster.isEmpty())
        {
            allContentMasterObjectList = contentMasterService.readAllForPortalMappingByCategory(Integer.parseInt(contentCategoryMaster));
            model.addAttribute("contentCategoryMaster", contentCategoryMaster);
        }
        else
        {
            allContentMasterObjectList = contentMasterService.readAllForPortalMapping();
        }
        
        for(int count=0; count < allContentMasterObjectList.size(); ++count)
        {
            Object[] allContentPortalMappingStatus = contentPortalMappingService.readByContentPortal(Integer.parseInt(allContentMasterObjectList.get(count)[0].toString()), Integer.parseInt(contentPortalMasterId));
            
//            System.out.println("onj: " + allContentPortalMappingStatus);
            
            if(allContentPortalMappingStatus==null)
            {
//                System.out.println("enter: null");
                allContentMasterObjectList.get(count)[5] = 0;
                
            }
            else if(allContentPortalMappingStatus[3].toString().equals("0"))
            {
                allContentMasterObjectList.get(count)[5] = 0;
//                System.out.println("onj: " + allContentPortalMappingStatus[3]);
            }
            else
            {
                allContentMasterObjectList.get(count)[5] = 1;
            }
        }

        

         
//        

//      
//        model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//        
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);

        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
            
            

//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(1);
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAllFromContentMaster();
//            List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
            List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAllFromContentMaster();
//            List<Object[]> allUserMasterObjectList = userMasterService.readByType("CONTENT-PROVIDER");
            List<Object[]> allUserMasterObjectList = userMasterService.readAllFromContentMaster();
            
            
            model.addAttribute("contentPortalMasterId", contentPortalMasterId);

            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
            model.addAttribute("allUserMasterObjectList", allUserMasterObjectList);
            model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
            
            
            
//        }
//        else
//        {
  
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//            
//            model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//
//            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
        
        return "admin/portal_content_mapp";
    }
    
    //cHECKED...
    @RequestMapping(value = "/mc_content_cat_map_screen", method = RequestMethod.GET)
    public String mcContentCategoryMapping( HttpSession session,ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        String response = contentCategoryMasterService.create(title, localTitle);
        
//        prepareMenu(userId, model);
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
        {
////            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
////            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
////            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readAll();
            
            
            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readAll();

            model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        }
        else
        {
            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readByUserId(Integer.parseInt(userObject[0].toString()));

            model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
        }
        return "admin/content_category_mapping";
    }
    
    //Checked...
    
    //Checked...
    
    
    //Checked...
    @RequestMapping(value = "/mc_cont_port_cat_mp", method = RequestMethod.GET)
    public String mcContentPortalCategoryMapping(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid, HttpSession session,ModelMap model)
    {
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
        updateMenuHistory(mmid, mcid, session);
//        prepareMenu(userId, model);
        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//    //        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
            
            
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus(1);
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
            
//            model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);

            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//        else
//        {
  
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//            
//            model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//
//            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
        
        return "admin/portal_category_mapp";
    }
    
    
    
    //Checked...
    @RequestMapping(value = "/edit_content_master_status", method = RequestMethod.GET)
    public String editContentMasterStatus(@RequestParam("id") String contentMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentMasterService.updateStatus(Integer.parseInt(contentMasterId), Integer.parseInt(status));
                
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
    
    
    
    
    
    @RequestMapping(value = "/edit_content_category_mapping_status", method = RequestMethod.GET)
    public String editContentCategoryMappingStatus(@RequestParam("id") String contentMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentCategoryMappingService.updateStatus(Integer.parseInt(contentMasterId), Integer.parseInt(status));
                
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
    
    //Checked...
    @RequestMapping(value = "/edit_content_portal_master_status", method = RequestMethod.GET)
    public String editContentPortalMasterStatus(@RequestParam("id") String contentPortalMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentPortalMasterService.updateStatus(Integer.parseInt(contentPortalMasterId), Integer.parseInt(status));
                
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
    
    //Checked...
   
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpSession session, HttpServletResponse respose)
    {

        InputStream in = null;
        try {
            
            List<Object[]> l = contentFileService.readAll();
            
            String path = l.get(4)[2].toString();
            String fileName = path.substring(path.lastIndexOf("/"));
            File file = new File(path);
            in = new FileInputStream(file);
            
            respose.setContentType("application/force-download");
//            respose.setHeader("Content-Disposition", "attachment; filename=" + "students-learning-laptop-21304419"+".jpg");
            respose.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            
            IOUtils.copy(in, respose.getOutputStream());
            respose.flushBuffer();
            in.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ContentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
    //Checked...
    
    
    //Checked...
    @RequestMapping(value = "/edit_content_files_details", method = RequestMethod.GET)
    public String editContentFilesDetails(@RequestParam("id") String contentFileId, @RequestParam("content_master_id") String contentMasterId, @RequestParam("content_master_type") String contentMasterType, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            
        Object[] contentFilesObject = contentFileService.readContentFiles(Integer.parseInt(contentFileId));
        model.addAttribute("contentFilesObject", contentFilesObject);
        
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
           
//            prepareMenu(userId, model);
            
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
        System.out.println(":::::" + contentMasterId);
            model.addAttribute("content_master_id", contentMasterId);
            model.addAttribute("contentMasterType", contentMasterType);
            
            System.out.println("VALUE: " + contentMasterId);

//            if(contentMasterType.equalsIgnoreCase("AUDIO-EBOOK"))
//            {
//                return "admin/content_files_ebook_edit";
//            }
            
        return "admin/content_files_edit";
    }
    
    //Checked...
    
    
    //Checked...
    @RequestMapping(value = "/edit_content_portal_master_details", method = RequestMethod.GET)
    public String editContentPortalMasterDetails(@RequestParam("id") String contentPortalMasterId, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            
        Object[] contentPortalMasterObject = contentPortalMasterService.readContentPortalMaster(Integer.parseInt(contentPortalMasterId));
        model.addAttribute("contentPortalMasterObject", contentPortalMasterObject);
        
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
           
//            prepareMenu(userId, model);
            
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);

        return "admin/content_portal_master_edit";
    }
    
    
    
    
    
    
    //Checked...
    @RequestMapping(value = "/save_content_portal_master_details", method = RequestMethod.POST)
    public String saveContentPortalMasterDetails(@RequestParam("id") String contentPortalMasterId, @RequestParam("title") String title, @RequestParam("desc") String desc, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
            
        String response = contentPortalMasterService.update(Integer.parseInt(contentPortalMasterId), title, desc);
//        model.addAttribute("contentCategoryMasterObject", contentCategoryMasterObject);
        
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
           
//            prepareMenu(userId, model);
            
//            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
//            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);

        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);

        return "admin/content_portal_master_list";
    }
    //Checked...

    
    
    
    //Checked...
    @RequestMapping(value = "/edit_portal_cat_map_status", method = RequestMethod.GET)
    public String editPortalCatMapStatus(@RequestParam("categoryId") String categoryId, @RequestParam("contentPortalId") String contentPortalId, @RequestParam("catPortalMapStatus") String catPortalMapStatus, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
//            String response = contentPortalCategoryMappingService.updateStatus(Integer.parseInt(contentPortalCategoryMappingId), Integer.parseInt(status));
            String response = contentPortalCategoryMappingService.createUpdate(Integer.parseInt(categoryId), Integer.parseInt(contentPortalId), Integer.parseInt(catPortalMapStatus));
                
            model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", catPortalMapStatus);
            }
            else
            {
                model.addAttribute("status", "none");
            }

        return "admin/outcome";
    }
    
//    @RequestMapping(value = "/edit_portal_type_map_status", method = RequestMethod.GET)
//    public String editPortalTypeMapStatus(@RequestParam("id") String contentPortalTypeMappingId, @RequestParam("status") String status, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//            String response = contentTypePortalMappingService.updateStatus(Integer.parseInt(contentPortalTypeMappingId), Integer.parseInt(status));
//                
//            model.addAttribute("response", response);
//            if(response.equals("success"))
//            {
//                model.addAttribute("status", status);
//            }
//            else
//            {
//                model.addAttribute("status", "none");
//            }
//
//        return "admin/outcome";
//    }
    
    @RequestMapping(value = "/edit_portal_type_map_status", method = RequestMethod.GET)
    public String editPortalTypeMapStatus(@RequestParam("typeId") String typeId, @RequestParam("contPortalId") String contentPortalId, @RequestParam("typePortalMapStatus") String typePortalMapStatus, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentTypePortalMappingService.createUpdate(Integer.parseInt(typeId), Integer.parseInt(contentPortalId), Integer.parseInt(typePortalMapStatus));
                
            model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", typePortalMapStatus);
            }
            else
            {
                model.addAttribute("status", "none");
            }

        return "admin/outcome";
    }
    
    @RequestMapping(value = "/edit_portal_cont_map_status", method = RequestMethod.GET)
    public String editPortalContentMapStatus(@RequestParam("contentMasterId") String categoryMasterId, @RequestParam("contentPortalId") String contentPortalId, @RequestParam("contentPortalMapStatus") String contentPortalMapStatus, HttpSession session, ModelMap model)
    {
        
        if(!sessionValid(session))
        {
            return "login";
        }
            String response = contentPortalMappingService.createUpdate(Integer.parseInt(categoryMasterId), Integer.parseInt(contentPortalId), Integer.parseInt(contentPortalMapStatus));
                
            model.addAttribute("response", response);
            if(response.equals("success"))
            {
                model.addAttribute("status", contentPortalMapStatus);
            }
            else
            {
                model.addAttribute("status", "none");
            }

        return "admin/outcome";
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
    
    private boolean sessionValid(HttpSession session)
    {
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        return userObject!=null;
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

    public void setContentFileService(ContentFileService contentFileService) {
        this.contentFileService = contentFileService;
    }

    public void setContentCategoryMappingService(ContentCategoryMappingService contentCategoryMappingService) {
        this.contentCategoryMappingService = contentCategoryMappingService;
    }

    public void setContentPortalMasterService(ContentPortalMasterService contentPortalMasterService) {
        this.contentPortalMasterService = contentPortalMasterService;
    }

    public void setContentPortalCategoryMappingService(ContentPortalCategoryMappingService contentPortalCategoryMappingService) {
        this.contentPortalCategoryMappingService = contentPortalCategoryMappingService;
    }

    public void setContentMetaDetailsService(ContentMetaDetailsService contentMetaDetailsService) {
        this.contentMetaDetailsService = contentMetaDetailsService;
    }

    public void setContentMetaMasterService(ContentMetaMasterService contentMetaMasterService) {
        this.contentMetaMasterService = contentMetaMasterService;
    }

    public void setBannerMasterService(BannerMasterService bannerMasterService) {
        this.bannerMasterService = bannerMasterService;
    }

    public void setContentTypeMasterService(ContentTypeMasterService contentTypeMasterService) {
        this.contentTypeMasterService = contentTypeMasterService;
    }

    public void setContentSubCategoryMasterService(ContentSubCategoryMasterService contentSubCategoryMasterService) {
        this.contentSubCategoryMasterService = contentSubCategoryMasterService;
    }

    public void setContentTypePortalMappingService(ContentTypePortalMappingService contentTypePortalMappingService) {
        this.contentTypePortalMappingService = contentTypePortalMappingService;
    }

    

    public void setUserMasterService(UserMasterService userMasterService) {
        this.userMasterService = userMasterService;
    }

    public void setContentPortalMappingService(ContentPortalMappingService contentPortalMappingService) {
        this.contentPortalMappingService = contentPortalMappingService;
    }

    public void setMenuHistoryService(MenuHistoryService menuHistoryService) {
        this.menuHistoryService = menuHistoryService;
    }

    
    
    
    
    
    
    
    
}

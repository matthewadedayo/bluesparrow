/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


import dao.MenuMasterDao;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ContentCategoryMappingService;
import service.ContentCategoryMasterService;
import service.ContentFileService;
import service.ContentMasterService;
import service.ContentPortalCategoryMappingService;
import service.ContentPortalMasterService;
import service.ContentTypeMasterService;
import service.MenuChildService;
import service.MenuHistoryService;
import service.MenuMappingService;
import service.MenuMasterService;
import service.UserMasterService;
//import sun.nio.ch.IOUtil;

/**
 *
 * @author OLAWALE
 */
@Controller
@RequestMapping( value = "/review")
public class ReviewContentController{
    
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
    
    private MenuHistoryService menuHistoryService;
    
    //New
    private ContentTypeMasterService contentTypeMasterService;
    //New Update Begins
    
    @RequestMapping(value = "/get_thumbnail", method = RequestMethod.GET)
    public String getThumbnail(@RequestParam("thumbnail_small_url") String thumbnailSmallUrl, HttpSession session, HttpServletRequest request,ModelMap model)
    {
        
//        try 
//        {
            if(!sessionValid(session))
            {
                return "login";
            }
//        System.out.println("Host: "+request.getRemoteHost());
//        System.out.println("Addr: "+request.getRemoteAddr());
//        System.out.println("Local: "+request.getLocalAddr());
//        System.out.println("Local Name: "+request.getLocalName());
            System.out.println("server 444Name: "+request.getServerName());
//        System.out.println("Addr: "+request.get());
            String path = session.getServletContext().getRealPath("/");
            System.out.println("path: " + path);

            path = path.substring(0, path.lastIndexOf("CMSBM"));

            path = path.concat("ROOT");

                    String fullPath = "http://54.90.99.101" + thumbnailSmallUrl;
//            String fullPath = "http://127.0.0.1/ROOT" + thumbnailSmallUrl;
//            String fullPath = "http://" + InetAddress.getLocalHost().getHostAddress() + thumbnailSmallUrl;

            System.out.println("full_path: " + fullPath);

            model.addAttribute("img", fullPath);

            return "reviewer/thumbnail";
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(ReviewContentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return "reviewer/thumbnail";
    }
    
    @RequestMapping(value = "/get_portal_by_cat_status_rev", method = RequestMethod.GET)
    public String getPortalByCatStatusRev(@RequestParam("type") String contentTypeId, @RequestParam("status") String contentMasterStatus, ModelMap model, HttpSession session)
    {
        
//        prepareMenu(userId, model);
        
        if(!sessionValid(session))
        {
            return "login";
        }
        System.out.println("st: " + contentMasterStatus);
        System.out.println("typ: " + contentTypeId);
        List<Object[]> allContentTypeMasterObjectList = contentTypeMasterService.readAll();
        model.addAttribute("allContentTypeMasterObjectList", allContentTypeMasterObjectList);
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
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
        return "reviewer/review_content_list";
        
    }
    
    @RequestMapping(value = "/mc_review_content", method = RequestMethod.GET)
    public String mcReviewContent(@RequestParam("mmid") String mmid, @RequestParam("mcid") String mcid, ModelMap model, HttpSession session)
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
        
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//        }
//        else
//        {
//            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//        }

        
        

        return "reviewer/review_content_list";
        
    }
    
    private void updateMenuHistory(String mmid, String mcid, HttpSession session)
    {
        
        Object[] userObject = (Object[]) session.getAttribute("user_object");
        String response = menuHistoryService.create(Integer.parseInt(userObject[0].toString()), Integer.parseInt(mmid), Integer.parseInt(mcid));
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //New Update End
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @RequestMapping(value = "/search_content", method = RequestMethod.POST)
    public String searchContent(@RequestParam("option") String option, @RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("type") String type, @RequestParam("status") String status, HttpSession session, ModelMap model)
    {
        
//        prepareMenu(userId, model);
        
        
        if(!sessionValid(session))
        {
            return "login";
        }
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();

        if(option.equals("title"))
        {
            List<Object[]> allContentMasterObjectList = contentMasterService.readByTitle(title);
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
        }
        else if(option.equals("desc"))
        {
            List<Object[]> allContentMasterObjectList = contentMasterService.readByDescription(desc);
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
        }
        else if(option.equals("type"))
        {
//            List<Object[]> allContentMasterObjectList = contentMasterService.readByType(type);
//            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
        }
        else if(option.equals("status"))
        {
            List<Object[]> allContentMasterObjectList = contentMasterService.readByStatus(status);
            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
        }

        
        
        return "reviewer/review_content_master";
    }
    
    
//    

    @RequestMapping(value = "/review_option", method = RequestMethod.GET)
    public String reviewOption(@RequestParam("id") String contentMasterId, @RequestParam("status") String status, @RequestParam("remarks") String remarks, HttpSession session,  ModelMap model)
    {
        
//        prepareMenu(userId, model);
  
        
        if(!sessionValid(session))
        {
            return "login";
        }
        
        String response = contentMasterService.updateStatusByReviewer(Integer.parseInt(contentMasterId), Integer.parseInt(status), remarks);
        
        
////        List<Object[]> allContentFileObjectList = contentFileService.readAll();
//        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
////        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//        model.addAttribute("allContentFileObjectList", allContentFileObjectList);
////        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//
//        model.addAttribute("contentMasterId", contentMasterId);

        model.addAttribute("response", response);
        if(response.equals("success"))
        {
            model.addAttribute("status", status);
        }
        else
        {
            model.addAttribute("status", "none");
        }
        
        return "reviewer/outcome";
    }
    
    
    
    
    
    
//    
////    @RequestMapping(value = "/uploadquetfile1", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
////    public ModelAndView uploadQuetionFile1(@RequestParam("file") MultipartFile file,  HttpSession session)
//    
////@RequestParam("user_id") String userId, @RequestParam("width_height") String widthHeight, @RequestParam("os_name") String osName, @RequestParam("os_version") String osVersion,     
//    //Checked...
//    @RequestMapping(value = "/add_content_file", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    public String addContentFile(@RequestParam("content_master_id") String contentMasterId, @RequestParam("width_height") String widthHeight, @RequestParam("os_name") String osName, @RequestParam("os_version") String osVersion, @RequestParam ("file") MultipartFile file, HttpSession session, ModelMap model)
//    {
//        
//        // Save file on system
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
////            String path = session.getServletContext().getRealPath("/");
////            path = path.substring(0, path.lastIndexOf("CMSBM"));
////            path = path.concat("ROOT\\");
//            
//            String path = session.getServletContext().getRealPath("/");
//            path = path.substring(0, path.lastIndexOf("CMSBM"));
//            path = path.concat("ROOT/");
//            
//            String fileName = file.getOriginalFilename();
//
//            String relativePath = "/content/"+dateString+"/"+fileName;
//            String fullPath = path + "content" + File.separator+dateString + File.separator + fileName;
//
//            String reply = contentFileService.create(Integer.parseInt(contentMasterId), relativePath, widthHeight, osName, osVersion);
//            if(reply.equals("success"))
//            {
//                
////                String fileName = file.getOriginalFilename();
//
//                 
//
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
//            else
//            {
////                return "admin/content_files";
//            }
//        }
////        prepareMenu(userId, model);
//
////        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
////        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//        
////          List<Object[]> allContentFileObjectList = contentFileService.readAll();
//        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
//          model.addAttribute("allContentFileObjectList", allContentFileObjectList);
//          model.addAttribute("contentMasterId", contentMasterId);
//        return "admin/content_files";
//
//    }
//    
//    //Checked....
//    @RequestMapping(value = "/add_content_master", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    public String addContentMaster(@RequestParam("user_id") String userId, @RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("type") String type, @RequestParam("store_type") String storeType, @RequestParam("small_thumb") MultipartFile smallThumb, @RequestParam("middle_thumb") MultipartFile middleThumb, @RequestParam ("large_thumb") MultipartFile largeThumb, @RequestParam("small_thumb_url") String smallThumbUrl, @RequestParam("middle_thumb_url") String middleThumbUrl, @RequestParam ("large_thumb_url") String largeThumbUrl, @RequestParam("cont_cat_mst_id") String contentCategoryMasterId, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        Date d = new Date();
//        System.out.println("haaa");
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        String dateString = f.format(d);
//        
////        String path = session.getServletContext().getRealPath("/");
////        path = path.substring(0, path.lastIndexOf("CMSBM"));
////        path = path.concat("ROOT\\");
//
//    
//
//        String path = session.getServletContext().getRealPath("/");
//        path = path.substring(0, path.lastIndexOf("CMSBM"));
//        path = path.concat("ROOT/");
//        
////        System.out.println("1: " + smallThumbUrl);
////        System.out.println("1: " + smallThumb.getOriginalFilename());
//        if(storeType.equals("LOCAL"))
//        {
//            if (!smallThumb.isEmpty() && !middleThumb.isEmpty() && !largeThumb.isEmpty()) 
//            {
//
//                String smallThumbName = smallThumb.getOriginalFilename();
//
//                String middleThumbName = middleThumb.getOriginalFilename();
//
//                String largeThumbName = largeThumb.getOriginalFilename();
//
//                String smallRelativePath = "/content/"+dateString + "/" + smallThumbName;
//                String middleRelativePath = "/content/"+dateString+"/"+middleThumbName;
//                String largeRelativePath = "/content/"+dateString+"/"+largeThumbName;
//                
//                String smallRelativePathReal = path + "content" + File.separator+dateString + File.separator + smallThumbName;
//                String middleRelativePathReal = path + "content" + File.separator+dateString + File.separator + middleThumbName;
//                String largeRelativePathReal = path + "content" + File.separator+dateString + File.separator + largeThumbName;
//
//                String reply = contentMasterService.create(Integer.parseInt(userId), title, desc, type,  smallRelativePath, middleRelativePath, largeRelativePath, storeType, Integer.parseInt(contentCategoryMasterId));
//
//                
////                String reply = "failure";
//                
//                if(reply.equals("success"))
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
////                        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + smallRelativePath));
//                        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(smallRelativePathReal));
//                        bout.write(barr);
//                        bout.flush();
//                        bout.close();
//
//                        barr = middleThumb.getBytes();
////                        bout = new BufferedOutputStream(new FileOutputStream(path + middleRelativePath));
//                        bout = new BufferedOutputStream(new FileOutputStream(middleRelativePathReal));
//                        bout.write(barr);
//                        bout.flush();
//                        bout.close();
////
//                        barr = largeThumb.getBytes();
////                        bout = new BufferedOutputStream(new FileOutputStream(path + largeRelativePath));
////                        bout = new BufferedOutputStream(new FileOutputStream(path + "/" + largeRelativePath));
//                        bout = new BufferedOutputStream(new FileOutputStream(largeRelativePathReal));
//                        bout.write(barr);
//                        bout.flush();
//                        bout.close();
//                        
//                        System.out.println("RealPath: "+smallRelativePathReal);
////                        prepareMenu(userId, model);
//            //  
////                        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
////                        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//
//                        if(!sessionValid(session))
//                        {
//                            return "login";
//                        }
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
//
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
//                String reply = contentMasterService.create(Integer.parseInt(userId), title, desc, type,  smallThumbUrl, middleThumbUrl, largeThumbUrl, storeType, Integer.parseInt(contentCategoryMasterId));
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
//    //Checked...
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
////    @RequestMapping(value = "/add_content_master_url", method = RequestMethod.POST)
////    public String addContentMasterUrl(@RequestParam("user_id") String userId, @RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("type") String type, @RequestParam("store_type") String storeType, @RequestParam("small_thumb") String smallThumb, @RequestParam("middle_thumb") String middleThumb, @RequestParam ("large_thumb") String largeThumb, @RequestParam("cont_cat_mst_id") String contentCategoryMasterId, ModelMap model)
////    {
//////        String path = session.getServletContext().getRealPath("/");
////        System.out.println("1: " + smallThumb);
////        
////        
//////        if (!smallThumb.isEmpty() && !middleThumb.isEmpty() && !largeThumb.isEmpty()) 
//////        {
//////        
//////            String s = smallThumb.getOriginalFilename();
//////
//////          String m = middleThumb.getOriginalFilename();
//////
//////          String l = largeThumb.getOriginalFilename();
//////            
//////            String smallDirectory = path+"/media/content_master/small_thumnail/"+s;
//////            String middleDirectory = path+"/media/content_master/medium_thumnail/"+m;
//////            String largeDirectory = path+"/media/content_master/large_thumnail/"+l;
//////            String reply = contentMasterService.create(title, desc, type, Integer.parseInt(userRating), Integer.parseInt(userRated),  smallDirectory, middleDirectory, largeDirectory, storeType, Integer.parseInt(contentCategoryMasterId));
//////            if(reply.equals("success"))
//////            {
//////                // Save file on system
//////
//////                try
//////                {
//////                    File smallDir = new File(path + File.separator + "media" + File.separator+"content_master" + File.separator+"small_thumnail");
//////                            if (!smallDir.exists())
//////                                    smallDir.mkdirs();
//////
//////                    File mediumDir = new File(path + File.separator + "media" + File.separator+"content_master" + File.separator+"medium_thumnail");
//////                            if (!mediumDir.exists())
//////                                    mediumDir.mkdirs();
//////
//////                    File largeDir = new File(path + File.separator + "media" + File.separator+"content_master" + File.separator+"large_thumnail");
//////                            if (!largeDir.exists())
//////                                    largeDir.mkdirs();
//////        //            
//////        //            
//////                    byte barr[] = smallThumb.getBytes();
//////                    BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(smallDirectory));
//////                    bout.write(barr);
//////                    bout.flush();
//////                    bout.close();
//////
//////                    barr = middleThumb.getBytes();
//////                    bout = new BufferedOutputStream(new FileOutputStream(middleDirectory));
//////                    bout.write(barr);
//////                    bout.flush();
//////                    bout.close();
//////
//////                    barr = largeThumb.getBytes();
//////                    bout = new BufferedOutputStream(new FileOutputStream(largeDirectory));
//////                    bout.write(barr);
//////                    bout.flush();
//////                    bout.close();
//////        //            
////////                    String reply = contentMasterService.create(title, desc, type, Integer.parseInt(userRating), Integer.parseInt(userRated),  smallDirectory, middleDirectory, largeDirectory, storeType, Integer.parseInt(contentCategoryMasterId));
//////        //            
//////                    prepareMenu(userId, model);
//////        //  
//////                    List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//////                    model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//////        //            
//////        //            System.out.println("Saved");
//////
//////                    return "admin/content_master";
//////                }
//////                catch(Exception ex)
//////                {
////////                    System.err.println("Error Occure");
//////                }
//////            }
//////        }
//////        return "content_master_add";
////return null;
////
////    }
////    
//    //Checked...
//    @RequestMapping(value = "/mc_add_content_cat_mst", method = RequestMethod.GET)
//    public String mcAddContentCategoryMaster( HttpSession session,ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////        prepareMenu(userId, model);
//  
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        
//        return "admin/content_category_master";
//    }
//    
//    
//    @RequestMapping(value = "/add_content_cat_mst", method = RequestMethod.POST)
//    public String addContentCategoryMaster(@RequestParam("title") String title, @RequestParam("local_title") String localTitle, HttpSession session,ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        String response = contentCategoryMasterService.create(title, localTitle);
//        
////        prepareMenu(userId, model);
//  
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        
//        return "admin/content_category_master";
//    }
//    
//    //cHECKED...
//    @RequestMapping(value = "/add_content_cat_mpp", method = RequestMethod.POST)
//    public String addContentCategory_mapping(@RequestParam("content_master") String contentMaster, @RequestParam("content_category_master") String contentCategoryMaster, HttpSession session,ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        String response = contentCategoryMappingService.create(Integer.parseInt(contentMaster), Integer.parseInt(contentCategoryMaster));
//        
////        prepareMenu(userId, model);
//  
////        List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
////        Object[] userObject = (Object[]) session.getAttribute("user_object");
////        List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
////        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
////        List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readAll();/
////        
////        
////        model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
////        model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
////        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        
//        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
////            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readAll();
//
//            model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
//            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//        else
//        {
//            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readByUserId(Integer.parseInt(userObject[0].toString()));
//
//            model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
//            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//
//        return "admin/content_category_mapping";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/add_content_portal_cat_mpp", method = RequestMethod.POST)
//    public String addContentPortalCategoryMapping(@RequestParam("content_portal_master") String contentPortalMaster, @RequestParam("content_category_master") String contentCategoryMaster, HttpSession session,ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        String response = contentPortalCategoryMappingService.create(Integer.parseInt(contentPortalMaster), Integer.parseInt(contentCategoryMaster));
//        
////         List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
////        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
////        
////        List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
////      
////        model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
////        
////        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
////        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//
//        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//    //        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//            model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//
//            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//        else
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//    //        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//            model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//
//            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//        
//        return "admin/content_portal_category_mapping";
//    }
//    //cHECKED...
//    @RequestMapping(value = "/mc_content_cat_map_screen", method = RequestMethod.GET)
//    public String mcContentCategoryMapping( HttpSession session,ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////        String response = contentCategoryMasterService.create(title, localTitle);
//        
////        prepareMenu(userId, model);
//        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
////            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
//            List<Object[]> allContentMasterObjectList = contentMasterService.readAll();
////            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
////            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readAll();
//
//            model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
//            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//        else
//        {
//            List<Object[]> allContentMasterObjectList = contentMasterService.readById(Integer.parseInt(userObject[0].toString()));
////            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//            List<Object[]> allContentCategoryMappingObjectList = contentCategoryMappingService.readByUserId(Integer.parseInt(userObject[0].toString()));
//
//            model.addAttribute("allContentCategoryMappingObjectList", allContentCategoryMappingObjectList);
//            model.addAttribute("allContentMasterObjectList", allContentMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//        return "admin/content_category_mapping";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/mc_add_list_content_portal_mst", method = RequestMethod.GET)
//    public String mcAddListContentPortalMaster( HttpSession session,ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        
////        prepareMenu(userId, model);
//        
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//        
//        return "admin/content_portal_master";
//    }
//    //Checked...
//    @RequestMapping(value = "/add_list_content_portal_mst", method = RequestMethod.POST)
//    public String addContentPortalMaster(@RequestParam("title") String title, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////        String response = contentCategoryMasterService.create(title, localTitle);
//        String response = contentPortalMasterService.create(title);
//        
////        prepareMenu(userId, model);
//        
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//        
//        return "admin/content_portal_master";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/mc_cont_port_cat_mp", method = RequestMethod.GET)
//    public String mcContentPortalCategoryMapping( HttpSession session,ModelMap model)
//    {
//        
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////        prepareMenu(userId, model);
//        Object[] userObject = (Object[]) session.getAttribute("user_object");
//        if(userObject[5].toString().equalsIgnoreCase("ADMIN"))
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//    //        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//            model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//
//            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//        else
//        {
//            List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readByStatus();
//    //        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//            List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//
//            List<Object[]> allPortalContentMappingObjectList = contentPortalCategoryMappingService.readAll();
//            model.addAttribute("allPortalContentMappingObjectList", allPortalContentMappingObjectList);
//
//            model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//            model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//        }
//        
//        return "admin/content_portal_category_mapping";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/edit_content_master_status", method = RequestMethod.GET)
//    public String editContentMasterStatus(@RequestParam("id") String contentMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//            String response = contentMasterService.updateStatus(Integer.parseInt(contentMasterId), Integer.parseInt(status));
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
//    
//    
//    @RequestMapping(value = "/edit_category_master_status", method = RequestMethod.GET)
//    public String editCategoryMasterStatus(@RequestParam("id") String contentMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//            String response = contentCategoryMasterService.updateStatus(Integer.parseInt(contentMasterId), Integer.parseInt(status));
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
//    
//    
//    @RequestMapping(value = "/edit_content_category_mapping_status", method = RequestMethod.GET)
//    public String editContentCategoryMappingStatus(@RequestParam("id") String contentMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//            String response = contentCategoryMappingService.updateStatus(Integer.parseInt(contentMasterId), Integer.parseInt(status));
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
//    
//    //Checked...
//    @RequestMapping(value = "/edit_content_portal_master_status", method = RequestMethod.GET)
//    public String editContentPortalMasterStatus(@RequestParam("id") String contentPortalMasterId, @RequestParam("status") String status, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//            String response = contentPortalMasterService.updateStatus(Integer.parseInt(contentPortalMasterId), Integer.parseInt(status));
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
//    
//    //Checked...
//    @RequestMapping(value = "/edit_content_file_status", method = RequestMethod.GET)
//    public String editContentFileStatus(@RequestParam("id") String contentFileId, @RequestParam("status") String status, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//            String response = contentFileService.updateStatus(Integer.parseInt(contentFileId), Integer.parseInt(status));
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
//    
//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    public void download(HttpSession session, HttpServletResponse respose)
//    {
//
//        InputStream in = null;
//        try {
//            
//            List<Object[]> l = contentFileService.readAll();
//            
//            String path = l.get(4)[2].toString();
//            String fileName = path.substring(path.lastIndexOf("/"));
//            File file = new File(path);
//            in = new FileInputStream(file);
//            
//            respose.setContentType("application/force-download");
////            respose.setHeader("Content-Disposition", "attachment; filename=" + "students-learning-laptop-21304419"+".jpg");
//            respose.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//            
//            IOUtils.copy(in, respose.getOutputStream());
//            respose.flushBuffer();
//            in.close();
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ReviewContentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ReviewContentController.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                in.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ReviewContentController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//            
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/edit_content_master_details", method = RequestMethod.GET)
//    public String editMenuChildDetails(@RequestParam("id") String contentMasterId, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
//            
//        Object[] contentMasterObject = contentMasterService.readContentMaster(Integer.parseInt(contentMasterId));
//        model.addAttribute("contentMasterObject", contentMasterObject);
//        
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//           
////            prepareMenu(userId, model);
//            
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//
////        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readByStatus();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//
//        return "admin/content_master_edit";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/edit_content_files_details", method = RequestMethod.GET)
//    public String editContentFilesDetails(@RequestParam("id") String contentFileId, @RequestParam("content_master_id") String contentMasterId, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
//            
//        Object[] contentFilesObject = contentFileService.readContentFiles(Integer.parseInt(contentFileId));
//        model.addAttribute("contentFilesObject", contentFilesObject);
//        
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//           
////            prepareMenu(userId, model);
//            
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//        System.out.println(":::::" + contentMasterId);
//            model.addAttribute("content_master_id", contentMasterId);
//            
//            System.out.println("VALUE: " + contentMasterId);
//
//        return "admin/content_files_edit";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/edit_content_cat_mst_details", method = RequestMethod.GET)
//    public String editContentCatMstDetails(@RequestParam("id") String contentCategoryMasterId, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
//            
//        Object[] contentCategoryMasterObject = contentCategoryMasterService.readContentCategoryMaster(Integer.parseInt(contentCategoryMasterId));
//        model.addAttribute("contentCategoryMasterObject", contentCategoryMasterObject);
//        
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//           
////            prepareMenu(userId, model);
//            
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//
//        return "admin/content_category_master_edit";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/edit_content_portal_master_details", method = RequestMethod.GET)
//    public String editContentPortalMasterDetails(@RequestParam("id") String contentPortalMasterId, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
//            
//        Object[] contentPortalMasterObject = contentPortalMasterService.readContentPortalMaster(Integer.parseInt(contentPortalMasterId));
//        model.addAttribute("contentPortalMasterObject", contentPortalMasterObject);
//        
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//           
////            prepareMenu(userId, model);
//            
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//
//        return "admin/content_portal_master_edit";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/save_content_cat_mst_details", method = RequestMethod.POST)
//    public String saveContentCatMstDetails(@RequestParam("id") String contentCategoryMasterId, @RequestParam("title") String title, @RequestParam("local_title") String localTitle, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
//            
//        String response = contentCategoryMasterService.update(Integer.parseInt(contentCategoryMasterId), title, localTitle);
////        model.addAttribute("contentCategoryMasterObject", contentCategoryMasterObject);
//        
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//           
////            prepareMenu(userId, model);
//            
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//
//        List<Object[]> allContentCategoryMasterObjectList = contentCategoryMasterService.readAll();
//        model.addAttribute("allContentCategoryMasterObjectList", allContentCategoryMasterObjectList);
//
//        return "admin/content_category_master";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/save_content_portal_master_details", method = RequestMethod.POST)
//    public String saveContentPortalMasterDetails(@RequestParam("id") String contentPortalMasterId, @RequestParam("title") String title, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
////            String response = menuMasterService.update(Integer.parseInt(menuMasterId), title, url);
//            
//        String response = contentPortalMasterService.update(Integer.parseInt(contentPortalMasterId), title);
////        model.addAttribute("contentCategoryMasterObject", contentCategoryMasterObject);
//        
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//           
////            prepareMenu(userId, model);
//            
////            List<Object[]> allMenuMasterObjectList = menuMasterService.readAll();
////            model.addAttribute("allMenuMasterObjectList", allMenuMasterObjectList);
//
//        List<Object[]> allContentPortalMasterObjectList = contentPortalMasterService.readAll();
//        model.addAttribute("allContentPortalMasterObjectList", allContentPortalMasterObjectList);
//
//        return "admin/content_portal_master";
//    }
//    //Checked...
////    @RequestMapping(value = "/add_content_file", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    @RequestMapping(value = "/save_content_files_details", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
//    public String saveContentFilesDetails(@RequestParam("id") String contentFileId, @RequestParam("content_master_id") String contentMasterId, @RequestParam("width_height") String widthHeight, @RequestParam("os_name") String osName, @RequestParam("os_version") String osVersion, @RequestParam ("file") MultipartFile file, @RequestParam("date_name") String dateName, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//        
//        Date d = new Date();
//        
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        String dateString = f.format(d);
//        dateString = dateName.substring(dateName.indexOf("/", 3)+1, dateName.lastIndexOf("/"));
//        System.out.println(dateName);
//        
//        if (!file.isEmpty()) 
//        {
////            String path = session.getServletContext().getRealPath("/");
////            path = path.substring(0, path.lastIndexOf("CMSBM"));
////            path = path.concat("ROOT\\");
//            
//            String path = session.getServletContext().getRealPath("/");
//            path = path.substring(0, path.lastIndexOf("CMSBM"));
//            path = path.concat("ROOT/");
//            
//            String fileName = file.getOriginalFilename();
////            String fullPath = path+"/media/content_files/"+osName+"/"+osVersion+"/"+fileName;
////            String fullPath = path+"/content/"+dateString+"/"+fileName;
//            String relativePath = "/content/"+dateString+"/"+fileName;
//            
//            String fullPath = path + "content" + File.separator+dateString + File.separator + fileName;
//            
//            System.out.println("ContentFieId: " + contentFileId);
//            String reply = contentFileService.update(Integer.parseInt(contentFileId), Integer.parseInt(contentMasterId), relativePath, widthHeight, osName, osVersion);
//            System.out.println("reply: " + reply);
//            if(reply.equalsIgnoreCase("success"))
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
//            else
//            {
////                return "admin/content_files";
//            }
//        }
////        prepareMenu(userId, model);
//
////          List<Object[]> allContentFileObjectList = contentFileService.readAll();
//        List<Object[]> allContentFileObjectList = contentFileService.readByContentMasterId(Integer.parseInt(contentMasterId));
//          model.addAttribute("allContentFileObjectList", allContentFileObjectList);
//          model.addAttribute("contentMasterId", contentMasterId);
//        return "admin/content_files";
//    }
//    
//    //Checked...
//    @RequestMapping(value = "/edit_portal_cat_map_status", method = RequestMethod.GET)
//    public String editPortalCatMapStatus(@RequestParam("id") String contentPortalCategoryMappingId, @RequestParam("status") String status, HttpSession session, ModelMap model)
//    {
//        
//        if(!sessionValid(session))
//        {
//            return "login";
//        }
//            String response = contentPortalCategoryMappingService.updateStatus(Integer.parseInt(contentPortalCategoryMappingId), Integer.parseInt(status));
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

    public void setContentTypeMasterService(ContentTypeMasterService contentTypeMasterService) {
        this.contentTypeMasterService = contentTypeMasterService;
    }

    public void setMenuHistoryService(MenuHistoryService menuHistoryService) {
        this.menuHistoryService = menuHistoryService;
    }
    
    
    
    
    
    
    
}

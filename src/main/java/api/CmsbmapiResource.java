/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import  service.CouponMasterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * REST Web Service
 *
 * @author OLAWALE
 */
@Path("cmsbmapi")
public class CmsbmapiResource {

    @Context
    private UriInfo context;
    
    @Autowired
    CouponMasterService couponMasterService;

    /**
     * Creates a new instance of CmsbmapiResource
     */
    public CmsbmapiResource() {
    }

    /**
     * Retrieves representation of an instance of api.CmsbmapiResource
     * @param startDate
     * @param expiryDate
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/date/{st}/{ed}")
    public List<Object[]> getJson(@PathParam("st") String startDate, @PathParam("ed") String expiryDate) {
        //TODO return proper representation object
        System.out.println("Its responding...");
        
        return couponMasterService.readAll();
    }

    /**
     * PUT method for updating or creating an instance of CmsbmapiResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}


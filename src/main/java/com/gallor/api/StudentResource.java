package com.gallor.api;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;


/**
 * Created by gallor on 11/30/15.
 */
public class StudentResource {

    @Context
    private UriInfo uriInfo;

    @Context
    private Request request;

    private String id;

    StudentService studentService;

    public StudentResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
        studentService = new StudentService();
    }


}

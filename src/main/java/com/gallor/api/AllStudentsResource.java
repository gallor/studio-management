package com.gallor.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * Created by gallor on 11/30/15.
 */

@Path("/students/{studentName}")
public class AllStudentsResource {
//    Get All, Get Single, Create one, Delete one, Modify One
//    Get all

    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    StudentService studentService;

    public AllStudentsResource() {
        studentService = new StudentService();
    }

    @GET

    @Produces("application/json")
}

package com.thoughtworks.gaia.student.endpoint;

import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by lavender on 17-4-24.
 */
@Component
@Path("student")
@Api(value = "student", description = "")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentEndPoint {
    @Autowired
    private StudentService studentService;

    @Path("/{id}")
    @ApiOperation(value = "Get student by id", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get student successfully"),
            @ApiResponse(code = 404, message = "No student matches given id")
    })
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Student student = studentService.getStudent(id);
        return Response.ok().entity(student).build();
    }

    @ApiOperation(value = "add student", response = boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add student successfully"),
            @ApiResponse(code = 400, message = "add failed")
    })
    @POST
    public Response addStudent(Student student){
        studentService.addStudent(student);
        return Response.ok().entity(true).build();
    }

    @Path("/{id}")
    @ApiOperation(value = "delete student", response = boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "delete student successfully"),
            @ApiResponse(code = 400, message = "delete failed")
    })
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
        studentService.deleteStudent(id);
        return Response.ok().entity(true).build();
    }

    @ApiOperation(value = "add student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add student successfully"),
            @ApiResponse(code = 400, message = "add failed")
    })
    @PUT
    public Response updateStudent(Student student){
        Student updateStudent = studentService.updateStudent(student);
        return Response.ok().entity(updateStudent).build();
    }
}

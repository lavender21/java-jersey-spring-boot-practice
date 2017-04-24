package com.thoughtworks.gaia.student.endpoint;

import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

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
            @ApiResponse(code = 404, message = "No student matches given id"),
            @ApiResponse(code = 400, message = "bad request")
    })
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Student student = studentService.getStudent(id);
        return Response.ok().entity(student).build();
    }

    @ApiOperation(value = "add student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Add student successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @POST
    public Response addStudent(Student student) throws URISyntaxException {
        Student newstudent = studentService.addStudent(student);
        return Response.created(new URI("/gaia/student/" + newstudent.getId())).entity(newstudent).build();
    }

    @Path("/{id}")
    @ApiOperation(value = "delete student")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "delete student successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.noContent().build();
    }

    @Path("/{id}")
    @ApiOperation(value = "update student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update student successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        student.setId(id);
        Student updateStudent = studentService.updateStudent(student);
        return Response.ok().entity(updateStudent).build();
    }
}

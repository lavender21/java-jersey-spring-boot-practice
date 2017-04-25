package com.thoughtworks.gaia.common.exception.handler;

import com.thoughtworks.gaia.common.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by lavender on 17-4-25.
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception){
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

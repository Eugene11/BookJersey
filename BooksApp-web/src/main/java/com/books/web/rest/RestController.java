package com.books.web.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.books.ejb.BookHandler;
import com.books.web.api.BookDto;
import com.books.ejb.model.Book;
import org.modelmapper.ModelMapper;

import java.util.Collection;



@Path("/api/book")
@Stateless
public class RestController {

    ModelMapper modelMapper = new ModelMapper();


    @EJB
    BookHandler beanInst;



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") Long id) {

        Book book = beanInst.getBook(id);
        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();

        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return Response.status(Response.Status.OK).entity(bookDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postBook(BookDto bookDto) {

        Book book = modelMapper.map(bookDto, Book.class);
        Book created = beanInst.addBook(book);
        BookDto createdBookDto = modelMapper.map(created, BookDto.class);

        return Response.status(Response.Status.CREATED).entity(createdBookDto).build();

    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putBook(@PathParam("id") Long id, BookDto bookDto) {

        Book book = modelMapper.map(bookDto, Book.class);
        book.setId(id);
        Book created = beanInst.update(book);
        BookDto createdBookDto = modelMapper.map(created, BookDto.class);

        return Response.status(Response.Status.OK).entity(createdBookDto).build();

    }


    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        beanInst.deleteBook(id);
        return Response.status(Response.Status.OK).entity("").build();
    }
}
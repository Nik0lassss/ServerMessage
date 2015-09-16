package com.nikola.chk.message_service;

/**
 * Created by Nikola on 9/8/2015.
 */

import com.nikola.chk.message_service.entity.*;
import com.nikola.chk.message_service.error_messages.ErroreObject;
import com.nikola.chk.message_service.hibernate_logic.HibernateEntityLogic;
import com.nikola.chk.message_service.persistance.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Path("/rest")
public class Connector {

    @GET
    @Path("/user/all")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Object> getAllUsers()
    {
        return   HibernateEntityLogic.getAllEntites(User.class);
    };

    @PUT
    @Path("/PutValue/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Object> saveTry( @PathParam("id") String id ,Map<String,Object> params)
    {
        return params;
    }

    @PUT
    @Path("/user/")
    public Response createUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName ){
        User user = new User();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        ErroreObject erroreObject = HibernateEntityLogic.SaveObject(user);
        return Response.ok().build();
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Object getUser(@PathParam("userId") int userId)
    {
        return   HibernateEntityLogic.getEntite(User.class, userId);
    };

    @GET
    @Path("/user/message/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Object> getMessage(@PathParam("userId") int userId)
    {
        User user_to = (User) HibernateEntityLogic.getEntite(User.class,userId);
        List<Object> messages = HibernateEntityLogic.getEntiteCriteriaEquels(Message.class,"user_to",user_to);
        return messages;
    }



    @PUT
    @Path("/user/message")
    public Response putMessage(@FormParam("from_id") int from_id, @FormParam("to_id") int to_id,@FormParam("message_text") String messageText)
    {
        User user_from =(User) HibernateEntityLogic.getEntite(User.class,from_id);
        User user_to = (User) HibernateEntityLogic.getEntite(User.class,to_id);
        Message message = new Message();
        message.setUser_from(user_from);
        message.setUser_to(user_to);
        message.setMessage(messageText);

//       Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        Message object =(Message) session.get(Message.class, 4);
//       session.getTransaction().commit();
//        Message message_test =(Message) HibernateEntityLogic.getEntite(Message.class,3);
//        System.out.println("Message--------------------------------- "+message_test.getMessage());
//        System.out.println("User from last id--------------------------------- " + message_test.getUser_from().getId());
//        System.out.println("User from last name--------------------------------- " + message_test.getUser_from().getLast_name());
//        System.out.println("User from first name ------------------------------" + message_test.getUser_from().getFirst_name());
//        System.out.println("User to last name--------------------------------- " + message_test.getUser_to().getLast_name());
//        System.out.println("User to first name ------------------------------" + message_test.getUser_to().getFirst_name());
//        System.out.println("User to first id ------------------------------" + message_test.getUser_to().getId());
//               User test_user = message_test.getUser_from();
//        System.out.println("Test user id ------------------------------" + test_user.getId());

        //  List<Object> messageS = (List<Object>) HibernateEntityLogic.getEntiteCriteriaEquels(Message.class,"to_id","2");
//
        HibernateEntityLogic.SaveObject(message);

        return Response.ok().build();
    }
}

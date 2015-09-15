package com.nikola.chk.message_service;

/**
 * Created by Nikola on 9/8/2015.
 */

import com.nikola.chk.message_service.entity.Message;
import com.nikola.chk.message_service.entity.Stock;
import com.nikola.chk.message_service.entity.StockDailyRecord;
import com.nikola.chk.message_service.entity.User;
import com.nikola.chk.message_service.error_messages.ErroreObject;
import com.nikola.chk.message_service.hibernate_logic.HibernateEntityLogic;
import com.nikola.chk.message_service.persistance.HibernateUtil;
import org.hibernate.Session;
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
    @Path("/user/{userId}")
    public Response updatingResource(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName , @PathParam("userId") String userId){
        ErroreObject erroreObject = HibernateEntityLogic.SaveObject(new User(firstName,lastName));
        return Response.ok().build();
    }
    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Object getUser(@PathParam("userId") int userId)
    {
        return   HibernateEntityLogic.getEntite(User.class,userId);
    };

    @PUT
    @Path("/user/message")
    public Response putMessage(@FormParam("from_id") int from_id, @FormParam("to_id") int to_id,@FormParam("message_text") String messageText)
    {
//        User user_from =(User) HibernateEntityLogic.getEntite(User.class,from_id);
//
////        user_from.setFirst_name("asd");
////        user_from.setLast_name("sdaasdasd");
//
//
//
 //User user_to = (User) HibernateEntityLogic.getEntite(User.class,to_id);
//
//
 //       Message message = new Message();
 //       message.setUser_from(user_from);
 //       message.setUser_to(user_to);
 //       message.setMessage(messageText);
       Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Message object =(Message) session.get(Message.class, 4);
       session.getTransaction().commit();
        //Message message_test =(Message) HibernateEntityLogic.getEntite(Message.class,3);
 //       User test_user = message_test.getUser_from();

      //  List<Object> messageS = (List<Object>) HibernateEntityLogic.getEntiteCriteriaEquels(Message.class,"to_id","2");
//        Stock stock = new Stock();
//        stock.setStockCode("7052");
//        stock.setStockName("PADINI");
////        //session.save(stock);
//        HibernateEntityLogic.SaveObject(stock);
//        StockDailyRecord stockDailyRecords = new StockDailyRecord();
//        stockDailyRecords.setPriceOpen(new Float("1.2"));
//        stockDailyRecords.setPriceClose(new Float("1.1"));
//        stockDailyRecords.setPriceChange(new Float("10.0"));
//        stockDailyRecords.setVolume(3000000L);
//        stockDailyRecords.setDate(new Date());
//
////        stockDailyRecords.setStock(stock);
//        stock.getStockDailyRecords().add(stockDailyRecords);
//        HibernateEntityLogic.SaveObject(stockDailyRecords);
////        //session.save(stockDailyRecords);
//
      StockDailyRecord stock2 =(StockDailyRecord) HibernateEntityLogic.getEntite(StockDailyRecord.class,36);
//        HibernateEntityLogic.SaveObject(user_from);
//        HibernateEntityLogic.SaveObject(user_to);
//        HibernateEntityLogic.SaveObject(message);

        return Response.ok().build();
    }
}

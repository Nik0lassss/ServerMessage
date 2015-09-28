package com.nikola.chk.message_service;

/**
 * Created by Nikola on 9/8/2015.
 */

import com.nikola.chk.message_service.entity.*;
import com.nikola.chk.message_service.error_messages.ErroreObject;
import com.nikola.chk.message_service.hibernate_logic.HibernateEntityLogic;
import com.nikola.chk.message_service.persistance.HibernateUtil;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
        HibernateEntityLogic.SaveObject(user);
        return Response.ok().build();
    }

    @PUT
    @Path("/security_user/")
    public Response createSecurityUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName ){
        User user = new User();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
         HibernateEntityLogic.SaveObject(user);
        return Response.ok().build();
    }

    @PUT
    @Path("/account/create")
    public Response createAccountUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,@FormParam("login") String login,@FormParam("password") String password){
        User user = new User();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUserLogin(login);
        securityUser.setUserPassword(password);

        HibernateEntityLogic.SaveObject(securityUser);
        user.setSecurityUser(securityUser);
            HibernateEntityLogic.SaveObject(user);


        return Response.ok().build();
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Object getUser(@PathParam("userId") int userId)
    {
        return   HibernateEntityLogic.getEntite(User.class, userId);
    };

    @POST
    @Path("/user/login")
    @Produces(MediaType.APPLICATION_JSON)
    public  Object loginUser(@FormParam("login") String userLogin,@FormParam("password") String userPassword)
    {
        List<Object> securityUserList =(List<Object>) HibernateEntityLogic.getEntiteCriteriaEquelsList(SecurityUser.class, "userLogin", "userPassword",userLogin,userPassword);
        if(0!=securityUserList.size())
        {
            SecurityUser securityUser =(SecurityUser) securityUserList.get(0);
            return  HibernateEntityLogic.getEntiteCriteriaEquelsList(User.class,"securityUser",securityUser);
        }
        return null;
    };


    @GET
    @Path("/user/message/received/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Object> getMessageReceived(@PathParam("userId") int userId)
    {
        return HibernateEntityLogic.getEntiteCriteriaEquelsList(Message.class, "user_from", (User) HibernateEntityLogic.getEntite(User.class, userId));
    }
    @GET
    @Path("/user/message/sent/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Object> getMessageSent(@PathParam("userId") int userId)
    {
        return HibernateEntityLogic.getEntiteCriteriaEquelsList(Message.class, "user_to", (User) HibernateEntityLogic.getEntite(User.class, userId));
    }

    @GET
    @Path("/image/{picture_name}")
    @Produces("image/png")
    public Response getFullImage(@PathParam("picture_name") String file_name) {

        BufferedImage image = null;
        try {
            File imageFile=new File("D:\\MyFolder\\"+file_name+".png");
            if(imageFile!=null) image = ImageIO.read(imageFile);
            else return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            if(image!=null) ImageIO.write(image, "png", baos);
           else return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = baos.toByteArray();

        // uncomment line below to send non-streamed
         return Response.ok(imageData).build();

        // uncomment line below to send streamed
         //return Response.ok(new ByteArrayInputStream(imageData)).build();

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
    private static final String FOLDER_PATH = "D:\\MyFolder\\";

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String uploadFile(@FormDataParam("file") InputStream fis,
                             @FormDataParam("file") FormDataContentDisposition fdcd) {

        OutputStream outpuStream = null;
        String fileName = fdcd.getFileName();
        System.out.println("File Name: " + fdcd.getFileName());
        String filePath = FOLDER_PATH + fileName;

        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            outpuStream = new FileOutputStream(new File(filePath));
            while ((read = fis.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch(IOException iox){
            iox.printStackTrace();
        } finally {
            if(outpuStream != null){
                try{outpuStream.close();} catch(Exception ex){}
            }
        }
        return "File Upload Successfully !!";
    }

}

package com.nikola.chk.message_service;

/**
 * Created by Nikola on 9/8/2015.
 */

import com.nikola.chk.message_service.config.Config;
import com.nikola.chk.message_service.entity.Message;
import com.nikola.chk.message_service.entity.SecurityUser;
import com.nikola.chk.message_service.entity.User;
import com.nikola.chk.message_service.error_messages.ErroreObject;
import com.nikola.chk.message_service.file_manager.FileManager;
import com.nikola.chk.message_service.hibernate_logic.HibernateEntityLogic;
import com.nikola.chk.message_service.response.ResponseList;
import com.nikola.chk.message_service.response.ResponseMessage;
import com.nikola.chk.message_service.response.ResponseObject;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Path("/rest")
public class Connector {


    @GET
    @Path("/user/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseObject getAllUsers() {
        try {
            return new ResponseObject(Config.CODE_OK, HibernateEntityLogic.getEntite(SecurityUser.class, 14));
        } catch (Exception e) {
            return new ResponseObject(Config.CODE_ERRORE, null);
        }

    }


    @PUT
    @Path("/user/")
    public ResponseMessage createUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        User user = new User();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        HibernateEntityLogic.saveObject(user);
        return new ResponseMessage(Config.CODE_OK, "Succesful create user");
    }

    @PUT
    @Path("/security_user/")
    public ResponseMessage createSecurityUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        User user = new User();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        HibernateEntityLogic.saveObject(user);
        return new ResponseMessage(Config.CODE_OK, "Succesful create security user");
    }

    @PUT
    @Path("/account/create")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseObject createAccountUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("login") String login, @FormParam("password") String password) {
        User user = new User();
        try {
            if (HibernateEntityLogic.getEntiteCriteriaEquelsList(User.class, "user_login", login).size() != 0)
                return new ResponseObject(Config.CODE_ERRORE, "A user with this login already exists");
            else {
                user.setFirst_name(firstName);
                user.setLast_name(lastName);
                user.setPhoto_avatar("http://lorempixel.com/400/200/");
                user.setUser_login(login);
                SecurityUser securityUser = new SecurityUser();
                securityUser.setUserPassword(password);
                HibernateEntityLogic.saveObject(user);
                securityUser.setUser_id(user.getUser_id());
                HibernateEntityLogic.saveObject(securityUser);
                //FileManager.createDir(login);
                return new ResponseObject(Config.CODE_OK, user.getUser_id());
            }
        } catch (Exception e) {
            new ResponseObject(Config.CODE_ERRORE, new ErroreObject(e.getMessage(), 1));
        }
        return new ResponseObject(Config.CODE_ERRORE, null);
    }


    @PUT
    @Path("/profile_image/")
    public ResponseMessage setProfileImage(@FormParam("imageName") String imageName, @FormParam("userId") String userId) {
        User user = new User();
        user.setPhoto_avatar(imageName);
        HibernateEntityLogic.updateObject(user);
        return new ResponseMessage(Config.CODE_OK, "Succesful set profile image");
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseObject getUser(@PathParam("userId") int userId) {
        try {
            ResponseObject responseObject = new ResponseObject(Config.CODE_OK, HibernateEntityLogic.getEntiteCriteriaEquelsList(User.class, "securityUser", (SecurityUser) HibernateEntityLogic.getEntite(SecurityUser.class, userId)));
            if (null == responseObject.getResponseObject()) return responseObject;
            else {
                responseObject.setCode(Config.CODE_ERRORE);
                return responseObject;
            }
        } catch (Exception e) {
            return new ResponseObject(Config.CODE_ERRORE, null);
        }

    }

    ;

    @POST
    @Path("/user/login")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseObject loginUser(@FormParam("login") String userLogin, @FormParam("password") String userPassword) {

        List<Object> securityUsersList = (List<Object>) HibernateEntityLogic.getEntiteCriteriaEquelsList(SecurityUser.class, "userPassword", userPassword);
        if (securityUsersList.size() != 0) {
            SecurityUser securityUser = (SecurityUser) securityUsersList.get(0);
            User user = (User) HibernateEntityLogic.getEntite(User.class, securityUser.getUser_id());
            return new ResponseObject(Config.CODE_OK, user);
        }
        return new ResponseObject(Config.CODE_ERRORE, null);
    }


    @GET
    @Path("/user/message/received/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseList getMessageReceived(@PathParam("userId") int userId) {
        User user =  (User) HibernateEntityLogic.getEntite(User.class, userId);
        List<Object> messagesList = HibernateEntityLogic.getEntiteCriteriaEquelsList(Message.class, "user_from",user);
        return new ResponseList(Config.CODE_OK,messagesList);
    }

    @GET
    @Path("/user/message/sent/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseList getMessageSent(@PathParam("userId") int userId) {
        return new ResponseList(Config.CODE_OK, HibernateEntityLogic.getEntiteCriteriaEquelsList(Message.class, "user_to", (User) HibernateEntityLogic.getEntite(User.class, userId)));
    }

    @GET
    @Path("/image/{userLogin}/{pictureName}")
    @Produces("image/jpeg")
    public Response getFullImage(@PathParam("userLogin") String userLogin, @PathParam("pictureName") String pictureName) {
        StringBuilder st = new StringBuilder();
        BufferedImage image = null;
        try {

            File imageFile = new File(Config.imageDirecortyPath + userLogin + "/" + pictureName + ".jpg");
            if (imageFile != null) image = ImageIO.read(imageFile);
            else return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            if (image != null) ImageIO.write(image, "jpg", baos);
            else return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = baos.toByteArray();


        return Response.ok(imageData).build();


    }


    @PUT
    @Path("/user/message")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage putMessage(@FormParam("from_id") int from_id, @FormParam("to_id") int to_id, @FormParam("message_text") String messageText) {
        try{
            User user_from = (User) HibernateEntityLogic.getEntite(User.class, from_id);
            User user_to = (User) HibernateEntityLogic.getEntite(User.class, to_id);
            Message message = new Message();
            message.setUser_from(user_from);
            message.setUser_to(user_to);
            message.setMessage(messageText);
            HibernateEntityLogic.saveObject(message);
            return new ResponseMessage(Config.CODE_OK, "Succesful put message");
        }
       catch (Exception e)
       {
           return new ResponseMessage(Config.CODE_ERRORE, e.getMessage());
       }

    }


    @POST
    @Path("/uploadPhoto/{loginUser}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage uploadPhoto(@PathParam("loginUser") String loginUser, @FormDataParam("file") InputStream fis,
                                       @FormDataParam("file") FormDataContentDisposition fdcd) {
        OutputStream outpuStream = null;
        String fileName = fdcd.getFileName();
        String fileNameWithOutExt = FilenameUtils.removeExtension(fileName);
        System.out.println("File Name: " + fdcd.getFileName());
        String filePath = Config.imageDirecortyPath + loginUser + "/" + fileName;

        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            outpuStream = new FileOutputStream(new File(filePath));
            while ((read = fis.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

            outpuStream.flush();
            outpuStream.close();

            List<Object> securityUserList = (List<Object>) HibernateEntityLogic.getEntiteCriteriaEquelsList(SecurityUser.class, "userLogin", loginUser);
            if (0 != securityUserList.size()) {
                try {
                    SecurityUser securityUser = (SecurityUser) securityUserList.get(0);
                    User user = (User) HibernateEntityLogic.getEntiteCriteriaEquelsList(User.class, "securityUser", securityUser).get(0);
                    //user.setPhoto_avatar(Config.URLForLoadImage + securityUser.getUserLogin() + "/" + fileNameWithOutExt);
                    HibernateEntityLogic.updateObject(user);
                } catch (Exception e) {

                }

            }

        } catch (IOException iox) {
            iox.printStackTrace();
        } finally {
            if (outpuStream != null) {
                try {
                    outpuStream.close();
                } catch (Exception ex) {
                }
            }
        }
        return new ResponseMessage(Config.CODE_OK, "Succesful upload image");
    }

    //    private static final String FOLDER_PATH = "MyFolder/";
//
//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.TEXT_PLAIN)
//    public String uploadFile(@FormDataParam("file") InputStream fis,
//                             @FormDataParam("file") FormDataContentDisposition fdcd) {
//
//        OutputStream outpuStream = null;
//        String fileName = fdcd.getFileName();
//        System.out.println("File Name: " + fdcd.getFileName());
//        String filePath = FOLDER_PATH + fileName;
//
//        try {
//            int read = 0;
//            byte[] bytes = new byte[1024];
//            outpuStream = new FileOutputStream(new File(filePath));
//            while ((read = fis.read(bytes)) != -1) {
//                outpuStream.write(bytes, 0, read);
//            }
//            outpuStream.flush();
//            outpuStream.close();
//
//
//        } catch (IOException iox) {
//            iox.printStackTrace();
//        } finally {
//            if (outpuStream != null) {
//                try {
//                    outpuStream.close();
//                } catch (Exception ex) {
//                }
//            }
//        }
//        return "File Upload Successfully !!";
//    }


//    @GET
//    @Path("/test")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response testDir() {
//        List<Object> securityUserList = (List<Object>) HibernateEntityLogic.getEntiteCriteriaEquelsList(SecurityUser.class, "userLogin", "ivan");
//        StringBuilder stringBuilder = new StringBuilder();
//        if (0 != securityUserList.size()) {
//            try {
//                SecurityUser securityUser = (SecurityUser) securityUserList.get(0);
//                stringBuilder.append(securityUser.getId() + "/" + securityUser.getUserLogin());
//                User user = (User) HibernateEntityLogic.getEntiteCriteriaEquelsList(User.class, "securityUser", securityUser).get(0);
//                stringBuilder.append(user.getFirst_name() + "/" + user.getLast_name());
//                user.setPhoto_avatar("test");
//                HibernateEntityLogic.updateObject(user);
//            } catch (Exception e) {
//                stringBuilder.append("/" + e.getMessage());
//            }
//
//        }
//
//        return Response.ok("Ok" + stringBuilder).build();

//    @PUT
//    @Path("/PutValue/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Map<String, Object> saveTry(@PathParam("id") String id, Map<String, Object> params) {
//        return params;
//    }
//    }
}

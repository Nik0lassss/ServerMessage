package com.nikola.chk.message_service.persistance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * Created by Nikolas on 23.09.2015.
 */
@Path("/image")
public class ImageService {

    private static final String FILE_PATH = "D:\\MyFolder\\IMG_20150918_230250.jpg";

    @GET
    @Path("/get")
    @Produces("image/png")
    public Response getFile() {

        File file = new File(FILE_PATH);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=image_from_server.png");
        return response.build();

    }

}

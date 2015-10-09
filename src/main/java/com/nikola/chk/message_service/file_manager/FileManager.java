package com.nikola.chk.message_service.file_manager;

import com.nikola.chk.message_service.config.Config;

import java.io.File;

/**
 * Created by User on 07.10.2015.
 */
public class FileManager {

    public static int createDir(String directoryName)
    {
        new File(Config.imageDirecortyPath+directoryName).mkdir();
        return 0;
    };
}

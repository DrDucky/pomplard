package com.loic.pomplard.utils

import android.os.Environment
import com.loic.pomplard.constants.FilesConstants
import java.io.File

object DataUtils {

    /**
     * Retrieve the file from External Storage / {@see FilesConstants.APP_LOCAL_DIRECTORY}
     * @param filename the name of the file
     * @param extension extension of the file (ex: pdf)
     */
    fun getLocalFile(filename:String, extension:String): File {
        return File(Environment.getExternalStorageDirectory().path + File.separator + FilesConstants.APP_LOCAL_DIRECTORY + File.separator, filename + extension)
    }
}
package com.ystartor.crm.util;

import java.util.UUID;

public class UploadUtils {

    public static String getUuidFileName(String fileName) {
        int inx = fileName.lastIndexOf(".");
        String exions = fileName.substring(inx);
        return UUID.randomUUID().toString().replace("-", "")+ exions;
    }

    public static String getPath(String uploadFileName) {
        int code1 = uploadFileName.hashCode();
        int d1 = code1 & 0xf;//一级目录
        int code2 = code1 >>> 4;
        int d2 = code2 & 0xf;//二级目录
        return "/" + d1 + "/" + d2;
    }


}

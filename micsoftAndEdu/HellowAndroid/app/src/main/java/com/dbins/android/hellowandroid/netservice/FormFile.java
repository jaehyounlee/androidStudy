package com.dbins.android.hellowandroid.netservice;

import okhttp3.MediaType;

/**
 * Created by dave on 2016. 7. 30..
 */
public class FormFile {

    private String name ;

    private String file ;

    private MediaType type ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public FormFile(String name, String file, String mimeType) {
        this.name = name;
        this.file = file;
        this.type = getMediaType(mimeType);
    }

    private MediaType getMediaType(String type) {
        return MediaType.parse(type) ;
    }

}

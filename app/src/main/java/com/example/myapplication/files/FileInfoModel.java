package com.example.myapplication.files;

public class FileInfoModel {

    String id,filename,fileurl;


    public FileInfoModel() {
    }

    public FileInfoModel(String id,String filename, String fileurl) {
        this.id = id;
        this.filename = filename;
        this.fileurl = fileurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return filename;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public String getFileUrl() {
        return fileurl;
    }

    public void setFileUrl(String fileurl) {
        this.fileurl = fileurl;
    }
}

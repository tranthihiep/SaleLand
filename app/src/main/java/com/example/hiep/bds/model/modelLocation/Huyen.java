package com.example.hiep.bds.model.modelLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Huyen {
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("SolrID")
    @Expose
    private String solrID;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("STT")
    @Expose
    private Integer sTT;
    @SerializedName("TinhThanhID")
    @Expose
    private Integer tinhThanhID;
    @SerializedName("TinhThanhTitle")
    @Expose
    private String tinhThanhTitle;
    @SerializedName("TinhThanhTitleAscii")
    @Expose
    private String tinhThanhTitleAscii;
    @SerializedName("Created")
    @Expose
    private Object created;
    @SerializedName("Updated")
    @Expose
    private Object updated;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSolrID() {
        return solrID;
    }

    public void setSolrID(String solrID) {
        this.solrID = solrID;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSTT() {
        return sTT;
    }

    public void setSTT(Integer sTT) {
        this.sTT = sTT;
    }

    public Integer getTinhThanhID() {
        return tinhThanhID;
    }

    public void setTinhThanhID(Integer tinhThanhID) {
        this.tinhThanhID = tinhThanhID;
    }

    public String getTinhThanhTitle() {
        return tinhThanhTitle;
    }

    public void setTinhThanhTitle(String tinhThanhTitle) {
        this.tinhThanhTitle = tinhThanhTitle;
    }

    public String getTinhThanhTitleAscii() {
        return tinhThanhTitleAscii;
    }

    public void setTinhThanhTitleAscii(String tinhThanhTitleAscii) {
        this.tinhThanhTitleAscii = tinhThanhTitleAscii;
    }

    public Object getCreated() {
        return created;
    }

    public void setCreated(Object created) {
        this.created = created;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }

}

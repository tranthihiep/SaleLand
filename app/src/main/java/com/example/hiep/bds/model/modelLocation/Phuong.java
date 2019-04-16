package com.example.hiep.bds.model.modelLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phuong {
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
    @SerializedName("QuanHuyenID")
    @Expose
    private Integer quanHuyenID;
    @SerializedName("QuanHuyenTitle")
    @Expose
    private String quanHuyenTitle;
    @SerializedName("QuanHuyenTitleAscii")
    @Expose
    private String quanHuyenTitleAscii;
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

    public Integer getQuanHuyenID() {
        return quanHuyenID;
    }

    public void setQuanHuyenID(Integer quanHuyenID) {
        this.quanHuyenID = quanHuyenID;
    }

    public String getQuanHuyenTitle() {
        return quanHuyenTitle;
    }

    public void setQuanHuyenTitle(String quanHuyenTitle) {
        this.quanHuyenTitle = quanHuyenTitle;
    }

    public String getQuanHuyenTitleAscii() {
        return quanHuyenTitleAscii;
    }

    public void setQuanHuyenTitleAscii(String quanHuyenTitleAscii) {
        this.quanHuyenTitleAscii = quanHuyenTitleAscii;
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

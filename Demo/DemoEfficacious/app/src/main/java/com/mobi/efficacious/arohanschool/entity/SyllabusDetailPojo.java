package com.mobi.efficacious.arohanschool.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SyllabusDetailPojo {

    @SerializedName("SyllabusDetail")
    @Expose
    private List<com.mobi.efficacious.arohanschool.entity.SyllabusDetail> syllabusDetail = null;

    public List<com.mobi.efficacious.arohanschool.entity.SyllabusDetail> getSyllabusDetail() {
        return syllabusDetail;
    }

    public void setSyllabusDetail(List<com.mobi.efficacious.arohanschool.entity.SyllabusDetail> syllabusDetail) {
        this.syllabusDetail = syllabusDetail;
    }

}
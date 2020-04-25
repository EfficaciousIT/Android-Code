package com.mobi.efficacious.arohanschool.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetail {
   /* {
        "LoginDetails": [
        {
            "intUserType_id": 5,
                "intUser_id": 1,
                "intStudent_id": 0,
                "intStandard_id": 0,
                "intDivision_id": "",
                "intSchool_id": 1,
                "intDepartment_id": null,
                "intAcademic_id": 2
        }
    ]
    }*/
    @SerializedName("vchFCMToken")
    @Expose
    private String vchFCMTOken;
    @SerializedName("vchEmail")
    @Expose
    private String vchEmaIl;

    public String getIntUser_Id() {
        return intUser_Id;
    }

    public void setIntUser_Id(String intUser_Id) {
        this.intUser_Id = intUser_Id;
    }

    @SerializedName("intUser_id")
    @Expose
    private String intUser_Id;
    @SerializedName("intSchool_id")
    @Expose
    private String intSchoolIid;
    @SerializedName("intUserType_id")
    @Expose
    private String intUserTypeId;
    @SerializedName("intStudent_id")
    @Expose
    private String intStudentId;
    @SerializedName("vchStudentFirst_name")
    @Expose
    private String vchStudentFirstName;
    @SerializedName("vchStudentLast_name")
    @Expose
    private String vchStudentLastName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("std")
    @Expose
    private String std;
    @SerializedName("intstandard_id")
    @Expose
    private String intstandardId;
    @SerializedName("intDivision_id")
    @Expose
    private String intDivisionId;
    @SerializedName("vchStandard_name")
    @Expose
    private String vchStandardName;
    @SerializedName("vchDivisionName")
    @Expose
    private String vchDivisionName;
    @SerializedName("vchUser_name")
    @Expose
    private String vchUserName;
    @SerializedName("vchPassword")
    @Expose
    private String vchPassword;
    @SerializedName("intAcademic_id")
    @Expose
    private String intAcademicId;
    @SerializedName("intTeacher_id")
    @Expose
    private String intTeacherId;
    @SerializedName("vchFirst_name")
    @Expose
    private String vchFirstName;
    @SerializedName("vchLast_name")
    @Expose
    private String vchLastName;
    @SerializedName("intStaff_id")
    @Expose
    private String intStaffId;
    @SerializedName("intAdmin_id")
    @Expose
    private String intAdminId;
    @SerializedName("intPrincipal_id")
    @Expose
    private String intPrincipalId;
    @SerializedName("intManager_id")
    @Expose
    private Integer intManagerId;
    public String getVchFCMTOken() {
        return vchFCMTOken;
    }

    public void setVchFCMTOken(String vchFCMTOken) {
        this.vchFCMTOken = vchFCMTOken;
    }

    public String getVchEmaIl() {
        return vchEmaIl;
    }

    public void setVchEmaIl(String vchEmaIl) {
        this.vchEmaIl = vchEmaIl;
    }



    public String getIntSchoolIid() {
        return intSchoolIid;
    }

    public void setIntSchoolIid(String intSchoolIid) {
        this.intSchoolIid = intSchoolIid;
    }

/*
    @SerializedName("vchFCMToken")
    @Expose
    private String vchFCMTOken;
    @SerializedName("vchEmail")
    @Expose
    private String vchEmaIl;
    @SerializedName("intUser_id")
    @Expose
    private Integer intUserId;
    @SerializedName("intSchool_id")
    @Expose
    private Integer intSchoolIid;
    @SerializedName("intUserType_id")
    @Expose
    private Integer intUserTypeId;
    @SerializedName("intStudent_id")
    @Expose
    private Integer intStudentId;
    @SerializedName("vchStudentFirst_name")
    @Expose
    private String vchStudentFirstName;
    @SerializedName("vchStudentLast_name")
    @Expose
    private String vchStudentLastName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("std")
    @Expose
    private String std;
    @SerializedName("intstandard_id")
    @Expose
    private Integer intstandardId;
    @SerializedName("intDivision_id")
    @Expose
    private Integer intDivisionId;
    @SerializedName("vchStandard_name")
    @Expose
    private String vchStandardName;
    @SerializedName("vchDivisionName")
    @Expose
    private String vchDivisionName;
    @SerializedName("vchUser_name")
    @Expose
    private String vchUserName;
    @SerializedName("vchPassword")
    @Expose
    private String vchPassword;
  *//*  @SerializedName("intAcademic_id")
    @Expose
    private Integer intAcademicId;*//*
    @SerializedName("intTeacher_id")
    @Expose
    private Integer intTeacherId;
    @SerializedName("vchFirst_name")
    @Expose
    private String vchFirstName;
    @SerializedName("vchLast_name")
    @Expose
    private String vchLastName;
    @SerializedName("intStaff_id")
    @Expose
    private Integer intStaffId;
    @SerializedName("intAdmin_id")
    @Expose
    private Integer intAdminId;
    @SerializedName("intPrincipal_id")
    @Expose
    private Integer intPrincipalId;
    @SerializedName("intManager_id")
    @Expose
    private Integer intManagerId;*/

    public String getIntUserTypeId() {
        return intUserTypeId;
    }

    public void setIntUserTypeId(String intUserTypeId) {
        this.intUserTypeId = intUserTypeId;
    }

    public String getIntStudentId() {
        return intStudentId;
    }

    public void setIntStudentId(String intStudentId) {
        this.intStudentId = intStudentId;
    }

    public String getVchStudentFirstName() {
        return vchStudentFirstName;
    }

    public void setVchStudentFirstName(String vchStudentFirstName) {
        this.vchStudentFirstName = vchStudentFirstName;
    }

    public String getVchStudentLastName() {
        return vchStudentLastName;
    }

    public void setVchStudentLastName(String vchStudentLastName) {
        this.vchStudentLastName = vchStudentLastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getIntstandardId() {
        return intstandardId;
    }

    public void setIntstandardId(String intstandardId) {
        this.intstandardId = intstandardId;
    }

    public String getIntDivisionId() {
        return intDivisionId;
    }

    public void setIntDivisionId(String intDivisionId) {
        this.intDivisionId = intDivisionId;
    }

    public String getVchStandardName() {
        return vchStandardName;
    }

    public void setVchStandardName(String vchStandardName) {
        this.vchStandardName = vchStandardName;
    }

    public String getVchDivisionName() {
        return vchDivisionName;
    }

    public void setVchDivisionName(String vchDivisionName) {
        this.vchDivisionName = vchDivisionName;
    }

    public String getVchUserName() {
        return vchUserName;
    }

    public void setVchUserName(String vchUserName) {
        this.vchUserName = vchUserName;
    }

    public String getVchPassword() {
        return vchPassword;
    }

    public void setVchPassword(String vchPassword) {
        this.vchPassword = vchPassword;
    }

  /*  public Integer getIntAcademicId() {
        return intAcademicId;
    }

    public void setIntAcademicId(Integer intAcademicId) {
        this.intAcademicId = intAcademicId;
    }
*/
    public String getIntTeacherId() {
        return intTeacherId;
    }

    public void setIntTeacherId(String intTeacherId) {
        this.intTeacherId = intTeacherId;
    }

    public String getVchFirstName() {
        return vchFirstName;
    }

    public void setVchFirstName(String vchFirstName) {
        this.vchFirstName = vchFirstName;
    }

    public String getVchLastName() {
        return vchLastName;
    }

    public void setVchLastName(String vchLastName) {
        this.vchLastName = vchLastName;
    }

    public String getIntStaffId() {
        return intStaffId;
    }

    public void setIntStaffId(String intStaffId) {
        this.intStaffId = intStaffId;
    }

    public String getIntAdminId() {
        return intAdminId;
    }

    public void setIntAdminId(String intAdminId) {
        this.intAdminId = intAdminId;
    }

    public String getIntPrincipalId() {
        return intPrincipalId;
    }

    public void setIntPrincipalId(String intPrincipalId) {
        this.intPrincipalId = intPrincipalId;
    }

    public Integer getIntManagerId() {
        return intManagerId;
    }

    public void setIntManagerId(Integer intManagerId) {
        this.intManagerId = intManagerId;
    }

    public LoginDetail(String vchFCMTOken, String vchEmaIl, String intUser_Id, String intSchoolIid, String intAcademicId) {
        this.vchFCMTOken = vchFCMTOken;
        this.vchEmaIl = vchEmaIl;
        this.intUser_Id = intUser_Id;
        this.intSchoolIid = intSchoolIid;
//        this.intAcademicId = intAcademicId;
    }

    public LoginDetail(String intUser_Id, String intSchoolIid, String intUserTypeId, String vchUserName, String vchPassword, Integer intAcademicId) {
        this.intUser_Id = intUser_Id;
        this.intSchoolIid = intSchoolIid;
        this.intUserTypeId = intUserTypeId;
        this.vchUserName = vchUserName;
        this.vchPassword = vchPassword;
//        this.intAcademicId = intAcademicId;
    }
}
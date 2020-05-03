package com.mobi.efficacious.TraffordSchool.Interface;


import com.mobi.efficacious.TraffordSchool.entity.AttendanceDetail;
import com.mobi.efficacious.TraffordSchool.entity.AttendanceDetailPojo;
import com.mobi.efficacious.TraffordSchool.entity.ChatDetail;
import com.mobi.efficacious.TraffordSchool.entity.ChatDetailsPojo;
import com.mobi.efficacious.TraffordSchool.entity.DashboardDetailsPojo;
import com.mobi.efficacious.TraffordSchool.entity.EventDetail;
import com.mobi.efficacious.TraffordSchool.entity.EventDetailPojo;
import com.mobi.efficacious.TraffordSchool.entity.LeaveDetail;
import com.mobi.efficacious.TraffordSchool.entity.LeaveDetailPojo;
import com.mobi.efficacious.TraffordSchool.entity.LibraryDetailPojo;
import com.mobi.efficacious.TraffordSchool.entity.LoginDetail;
import com.mobi.efficacious.TraffordSchool.entity.LoginDetailsPojo;
import com.mobi.efficacious.TraffordSchool.entity.NoticeboardDetail;
import com.mobi.efficacious.TraffordSchool.entity.ProfileDetail;
import com.mobi.efficacious.TraffordSchool.entity.ProfileDetailsPojo;
import com.mobi.efficacious.TraffordSchool.entity.SchoolDetailsPojo;
import com.mobi.efficacious.TraffordSchool.entity.StandardDetail;
import com.mobi.efficacious.TraffordSchool.entity.StandardDetailsPojo;
import com.mobi.efficacious.TraffordSchool.entity.StudentStandardwiseDetailPojo;
import com.mobi.efficacious.TraffordSchool.entity.MarkAttendence;
import com.mobi.efficacious.TraffordSchool.entity.SyllabusDetailPDFPojo;
import com.mobi.efficacious.TraffordSchool.entity.SyllabusDetailPojo;
import com.mobi.efficacious.TraffordSchool.entity.TeacherDetail;
import com.mobi.efficacious.TraffordSchool.entity.TeacherDetailPojo;
import com.mobi.efficacious.TraffordSchool.entity.TimeTableDetailPojo;
import io.reactivex.Observable;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DataService {
    //Login
    @GET("Login")
    Observable<SchoolDetailsPojo> getSchoolDetails(@Query("command") String command,
                                                    @Query("GalleryId") String GalleryId);
    @GET("Login")
    Observable<LoginDetailsPojo> getLoginDetails(@Query("command") String command,
                                                 @Query("intUserType_id") String intUserType_id,
                                                 @Query("vchUser_name") String vchUser_name,
                                                 @Query("vchPassword") String vchPassword,
                                                 @Query("intAcademic_id") String intAcademic_id,
                                                 @Query("intSchool_id") String intSchool_id);
//Dashbaord
    @GET("Dashboard")
    Observable<DashboardDetailsPojo> getDashboardDetails(@Query("command") String command,
                                                         @Query("intAcademic_id") String intAcademic_id,
                                                         @Query("intSchool_id") String intSchool_id);
    @GET("Dashboard")
    Observable<DashboardDetailsPojo> getDashboardDetails(@Query("command") String command,
                                                         @Query("intAcademic_id") String intAcademic_id,
                                                         @Query("intDivision_id") String intDivision_id,
                                                         @Query("intstanderd_id") String intstanderd_id);


    @GET("Dashboard")
    Observable<DashboardDetailsPojo> getDashboardDetails(@Query("command") String command,
                                                         @Query("intAcademic_id") String intAcademic_id);

    @GET("OnlineClassSchedule")
    Observable<DashboardDetailsPojo> getOnlineClassDetails(@Query("command") String command,
                                                           @Query("intTeacher_id") String intTeacher_id,
                                                           @Query("intAcademic_id") String intAcademic_id,
                                                           @Query("intSchool_id") String intSchool_id,
                                                           @Query("intStandard_id") String intStandard_id);

    @GET("OnlineClassTimetable")
    Observable<DashboardDetailsPojo> getOnlineClassTimetable(@Query("command") String command,
                                                           @Query("intTeacher_id") String intTeacher_id,
                                                           @Query("intAcademic_id") String intAcademic_id,
                                                           @Query("intSchool_id") String intSchool_id,
                                                           @Query("intStandard_id") String intStandard_id);

//Standard
    @GET("Standard")
    Observable<StandardDetailsPojo> getStandardDetails(@Query("command") String command,
                                                       @Query("intSchool_id") String intSchool_id,
                                                       @Query("intAcademic_id") String intAcademic_id,
                                                       @Query("intStandard_id") String intStandard_id,
                                                       @Query("intDivision_id") String intDivision_id,
                                                       @Query("intTeacher_id") String intTeacher_id,
                                                       @Query("vchType") String vchType);
    //Student StandardWise details
    @GET("StudentStandardWise")
    Observable<StudentStandardwiseDetailPojo> getStudentStandardWiseDetails(@Query("command") String command,
                                                                            @Query("intSchool_id") String intSchool_id,
                                                                            @Query("intAcademic_id") String intAcademic_id,
                                                                            @Query("intStandard_id") String intStandard_id,
                                                                            @Query("intDivision_id") String intDivision_id
    );
    @GET("Attendance")
    Observable<AttendanceDetailPojo> getAttendanceDetails(@Query("command") String command,
                                                          @Query("intschool_id") String intschool_id,
                                                          @Query("intUserType_id") String intUserType_id,
                                                          @Query("intstanderd_id") String intstanderd_id,
                                                          @Query("intdivision_id") String intdivision_id,
                                                          @Query("intAcademic_id") String intAcademic_id,
                                                          @Query("dtDate") String dtDate,
                                                          @Query("status") String status,
                                                          @Query("intUser_id") String intUser_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("Attendance")
    Observable<ResponseBody>  MarkAttendence(@Query("command") String command,
                                             @Body MarkAttendence attendanceDetail);

    @GET("Attendance")
    Observable<AttendanceDetailPojo> getAttendancedetails(@Query("command") String command,
                                                          @Query("intschool_id") String intschool_id,
                                                          @Query("intAcademic_id") String intAcademic_id,
                                                          @Query("intUser_id") String intUser_id);
    @GET("Profile")
    Observable<ProfileDetailsPojo> getProfiledetails(@Query("command") String command,
                                                     @Query("intschool_id") String intschool_id,
                                                     @Query("intAcademic_id") String intAcademic_id,
                                                     @Query("intUser_id") String intUser_id);

    @GET("TeacherDetail")
    Observable<TeacherDetailPojo> getTeacherDetails(@Query("command") String command,
                                                    @Query("intSchool_id") String intSchool_id);
    @GET("Staff")
    Observable<TeacherDetailPojo> getStaffDetails(@Query("command") String command,
                                                    @Query("intSchool_id") String intSchool_id);

    @GET("SyllabusDetail")
    Observable<SyllabusDetailPojo> getSyllabusDetails(@Query("command") String command,
                                                      @Query("intSchool_id") String intSchool_id,
                                                      @Query("intSubject_id") String intSubject_id,
                                                      @Query("intSTD_id") String intSTD_id);
    @GET("TimeTable")
    Observable<TimeTableDetailPojo> getTimeTableDetails(@Query("command") String command,
                                                        @Query("intSchool_id") String intSchool_id,
                                                        @Query("Day") String Day,
                                                        @Query("intAcademic_id") String intAcademic_id,
                                                        @Query("intStandard_id") String intStandard_id,
                                                        @Query("intTeacher_id") String intTeacher_id,
                                                        @Query("intDivision_id") String intDivision_id);

    @GET("Library")
    Observable<LibraryDetailPojo> getLibraryDetails(@Query("command") String command,
                                                    @Query("intSchool_id") String intSchool_id,
                                                    @Query("intStandard_id") String intStandard_id,
                                                    @Query("intStudent_id") String intStudent_id);
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("SyllabusDetailPDF")
    Observable<SyllabusDetailPDFPojo> getSyllabusDetailPDF(@Query("command") String command,
                                                           @Query("intSchool_id") String intSchool_id,
                                                           @Query("intSubject_id") String intSubject_id,
                                                           @Query("intSTD_id") String intSTD_id);

    @POST("Standard")
    Observable<ResponseBody> UpdateDairyHomework(@Query("command") String command,
                                                 @Body StandardDetail standardDetail);
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("Standard")
    Observable<ResponseBody> InsertDairyHomework(@Query("command") String command,
                                                 @Body StandardDetail standardDetail);

    @GET("Leave")
    Observable<LeaveDetailPojo> getLeaveDetailDetails(@Query("command") String command,
                                                      @Query("intAcademic_id") String intAcademic_id,
                                                      @Query("intUserType_id") String intUserType_id,
                                                      @Query("intUser_id") String intUser_id,
                                                      @Query("intSchool_id") String intSchool_id,
                                                      @Query("intTeacher_id") String intTeacher_id);
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("Leave")
    Observable<ResponseBody> updateLeaveDetail(@Query("command") String command,
                                               @Body LeaveDetail leaveDetail);

    @GET("Event")
    Observable<EventDetailPojo> getEventDetails(@Query("command") String command,
                                                @Query("vchStandard_id") String vchStandard_id,
                                                @Query("intAcademic_id") String intAcademic_id,
                                                @Query("intSchool_id") String intSchool_id,
                                                @Query("intUser_id") String intUser_id
    );
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("Event")
    Observable<ResponseBody> InsertEvent(@Query("command") String command,
                                         @Body EventDetail eventDetail);
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("Noticeboard")
    Observable<ResponseBody> InsertNotice(@Query("command") String command,
                                          @Body NoticeboardDetail noticeboardDetail);

    @Multipart
    @POST("FileUpload")
    Observable<ResponseBody> upload(
            @Part MultipartBody.Part file,
            @Query("extension") String extension,
            @Query("EventDescription") String EventDescription,
            @Query("Folder_id") String Folder_id,
            @Query("intSchool_id") String intSchool_id
    );
    @Multipart
    @POST("FileUpload")
    Observable<ResponseBody> upload(
            @Part MultipartBody.Part file,
            @Query("extension") String extension
    );
    @Multipart
    @POST("Profile")
    Observable<ResponseBody> uploadProfile(
            @Part MultipartBody.Part file,
            @Query("vchProfile") String vchProfile,
            @Query("command") String command,
            @Query("intschool_id") String intschool_id,
            @Query("intAcademic_id") String intAcademic_id,
            @Query("intUser_id") String intUser_id
    );

    @GET("Chat")
    Observable<ChatDetailsPojo> getChatUserDetails(@Query("command") String command,
                                                   @Query("intSchool_id") String intSchool_id,
                                                   @Query("intUserid") String intUserid,
                                                   @Query("intStandard_id") String intStandard_id,
                                                   @Query("intDivision_id") String intDivision_id,
                                                   @Query("intAcademic_id") String intAcademic_id);
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("Chat")
    Observable<ResponseBody> SendChatMessage(@Query("command") String command,
                                             @Body ChatDetail chatDetail);
    @POST("Login")
    Observable<ResponseBody> FCMTokenUpdate(@Query("command") String command,
                                            @Body LoginDetail loginDetail);
    @POST("FileUpload")
    Observable<ResponseBody> delete(
            @Query("id") String id,
            @Query("command") String command,
            @Query("intSchool_id") String intSchool_id
    );


    @GET("ProfileDetails")
    Observable<SyllabusDetailPDFPojo> getResultUrl(@Query("command") String command,
                                                           @Query("intSchool_id") String intSchool_id,
                                                           @Query("intSubject_id") String intSubject_id,
                                                           @Query("intSTD_id") String intSTD_id);
}

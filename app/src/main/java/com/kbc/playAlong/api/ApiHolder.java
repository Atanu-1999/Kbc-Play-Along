package com.kbc.playAlong.api;

import com.kbc.playAlong.response.Banner_Response;
import com.kbc.playAlong.response.BlogDetails_Response;
import com.kbc.playAlong.response.Blog_Response;
import com.kbc.playAlong.response.ContactResponse;
import com.kbc.playAlong.response.ContestRules_Response;
import com.kbc.playAlong.response.Contest_response;
import com.kbc.playAlong.response.JoinContest_Response;
import com.kbc.playAlong.response.Login_Response;
import com.kbc.playAlong.response.Notification_Response;
import com.kbc.playAlong.response.Pages_Response;
import com.kbc.playAlong.response.Points_Response;
import com.kbc.playAlong.response.Question_Response;
import com.kbc.playAlong.response.RecentWinner_Response;
import com.kbc.playAlong.response.Register_Response;
import com.kbc.playAlong.response.Setting_Response;
import com.kbc.playAlong.response.Update_Profile_Response;
import com.kbc.playAlong.response.UserProfile_Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ApiHolder {
    @POST("banners")
    @FormUrlEncoded
    Call<Banner_Response> User_benner(@Field("token") String token);
    /*Register*/
    @POST("register")
    @FormUrlEncoded
    Call<Register_Response> UserRegister(@Field("name") String name,
                                         @Field("emailId") String emailId,
                                         @Field("phoneNumber") String phoneNumber,
                                         @Field("dob") String dob,
                                         @Field("city") String city,
                                         @Field("pincode") String pincode,
                                         @Field("address") String address,
                                         @Field("deviceId") String deviceId,
                                         @Field("password") String password);
    /*Login*/
    @POST("login")
    @FormUrlEncoded
    Call<Login_Response> UserLogin(@Field("phoneNumber") String phoneNumber,
                                   @Field("deviceId") String deviceId,
                                   @Field("password") String password);
    @POST("myprofile")
    @FormUrlEncoded
    Call<UserProfile_Response> get_user(@Field("token") String token);

    @POST("blogs")
    @FormUrlEncoded
    Call<Blog_Response> get_blog(@Field("token") String token);

    @POST("contests")
    @FormUrlEncoded
    Call<Contest_response> get_contest(@Field("token") String token);

    @POST("contest_winners")
    @FormUrlEncoded
    Call<RecentWinner_Response> get_winner(@Field("token") String token);

    @POST("contest_rules")
    @FormUrlEncoded
    Call<ContestRules_Response> get_rules(@Field("contestId") String contestId,
                                          @Field("token") String token);

    @POST("blogDetails")
    @FormUrlEncoded
    Call<BlogDetails_Response> get_blog_details(@Field("blogId") String blogId,
                                                @Field("token") String token);

    @POST("pages")
    @FormUrlEncoded
    Call<Pages_Response> get_pages(@Field("pageId") String pageId,
                                   @Field("token") String token);

//    @POST("getpaymenthistory")
//    @FormUrlEncoded
//    Call<Transaction_Response> get_history(@Field("accountId") String accountId,
//                                           @Field("token") String token);

    @PATCH("updateProfile")
    @FormUrlEncoded
    Call<Update_Profile_Response> profile(@Field("accountId") String accountId,
                                          @Field("name") String name,
                                          @Field("emailId") String emailId,
                                          @Field("dob") String dob,
                                          @Field("city") String city,
                                          @Field("pincode") String pincode,
                                          @Field("address") String address,
                                          @Field("token") String token);
    @POST("contest_questions")
    @FormUrlEncoded
    Call<Question_Response> get_question(@Field("contestId") String contestId,
                                         @Field("token") String token);

    @POST("getnotifications")
    @FormUrlEncoded
    Call<Notification_Response> get_notification(@Field("accountId") String accountId,
                                                 @Field("token") String token);

    @PATCH("updatePoint")
    @FormUrlEncoded
    Call<Points_Response> points(@Field("accountId") String accountId,
                                 @Field("contestId") String contestId,
                                 @Field("questionId") String questionId,
                                 @Field("user_answer") String user_answer,
                                 @Field("points") String points,
                                 @Field("playing_date") String playing_date,
                                 @Field("token") String token);

    @POST("settings")
    @FormUrlEncoded
    Call<Setting_Response> get_setting(@Field("token") String token);

    @POST("enquiry")
    @FormUrlEncoded
    Call<ContactResponse> get_contact(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("phone") String phone,
                                      @Field("message") String message,
                                      @Field("token") String token);
    @POST("join_contest")
    @FormUrlEncoded
    Call<JoinContest_Response> join_contest(@Field("accountId") String accountId,
                                            @Field("contestId") String contestId,
                                            @Field("token") String token);

}

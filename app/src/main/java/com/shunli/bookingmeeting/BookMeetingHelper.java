package com.shunli.bookingmeeting;

import com.shunli.bookingmeeting.models.MeetingInfoModel;
import com.shunli.bookingmeeting.utils.JsonUtil;
import com.shunli.bookingmeeting.utils.ToastUtil;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.ArrayList;

public class BookMeetingHelper {


    public static void bookingMeeting(String cookies, String date) {
        System.out.println("------bookingMeeting--------");
        // 登陆 Url
        String loginUrl = "http://meeting.ke.com/meeting/yudingmeeting";

        HttpClient httpClient = new HttpClient();

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(loginUrl);

        postMethod.setRequestHeader("cookie", cookies);
        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = {
                new NameValuePair("Theme", "realsee.app Daily Scrum"),
                new NameValuePair("Moderator", "907390"),
                new NameValuePair("showdata", date),
                new NameValuePair("starttime", "11:00:00"),
                new NameValuePair("stoptime", "12:00:00"),
                new NameValuePair("notice", "0"),
                new NameValuePair("roomid", "313"),
        };
        postMethod.setRequestBody(data);
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            System.out.println("statusCode" + statusCode);
//                // 打印出返回数据，检验一下是否成功
            String text = postMethod.getResponseBodyAsString();
            System.out.println(text);
            ToastUtil.showShortToast(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getMeetingList(String cookies, String date, String roomId) {
        System.out.println("------getMeetingList--------");
        // 登陆 Url
        String loginUrl = "https://meeting.ke.com/meeting/today/" + date;

        HttpClient httpClient = new HttpClient();

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        GetMethod postMethod = new GetMethod(loginUrl);

        postMethod.setRequestHeader("cookie", cookies);
        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = {
                new NameValuePair("roomIds", roomId),
        };
        postMethod.setQueryString(data);
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            System.out.println("statusCode" + statusCode);
//                // 打印出返回数据，检验一下是否成功
            String text = postMethod.getResponseBodyAsString();

            ArrayList<MeetingInfoModel> meetingInfoModels = JsonUtil.jsonToList(text, MeetingInfoModel.class);

            System.out.printf("" + meetingInfoModels.size());

            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

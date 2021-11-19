package com.shunli.bookingmeeting;

import com.shunli.bookingmeeting.utils.StringUtil;
import com.shunli.bookingmeeting.utils.ToastUtil;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;


public class HttpLoginHelper {
    // 登陆 Url
    private static final String loginUrl = "http://meeting.ke.com/login/sso1";

    private String cookies = "";

    public String getLoginCookies(){
        if(StringUtil.isBlanks(cookies)){
            cookies = login();
        }

        return cookies;
    }

    public String login()  {

        // 需登陆后访问的 Url
//        String dataUrl = "http://meeting.ke.com/home/ljquickbooking/";
        HttpClient httpClient = new HttpClient();

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(loginUrl);

        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = {
                new NameValuePair("Account", "hanshunli001"),
                new NameValuePair("Timestamp", "1637117553"),
                new NameValuePair("Signature", "34cbeb181292a38036e325dab4852e56")
        };
        postMethod.setRequestBody(data);
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode = httpClient.executeMethod(postMethod);

            // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
                System.out.println("cookies = " + c.toString());
            }

            String text = postMethod.getResponseBodyAsString();
            System.out.println(text);


            if (statusCode == 302) {//重定向到新的URL
                System.out.println("模拟登录成功");
                // 进行登陆后的操作
//                GetMethod getMethod = new GetMethod(dataUrl);
                // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
//                getMethod.setRequestHeader("cookie", tmpcookies.toString());
                // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
                // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
//                postMethod.setRequestHeader("Referer", "http://passport.mop.com/");
//                postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
//                httpClient.executeMethod(getMethod);
//                // 打印出返回数据，检验一下是否成功
//                String text = getMethod.getResponseBodyAsString();
//                System.out.println(text);
                return tmpcookies.toString();

            } else {
                ToastUtil.showShortToast("登录失败");
                System.out.println("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }
}

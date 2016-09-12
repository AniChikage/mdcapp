package cn.edu.zafu.easemob.netapp;

import android.provider.Settings;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Moe Winder on 2016/5/12.
 */
public class ConnNet {


    private static final  String urlLogin="http://www.clr-vision.com:18080/Therapista/user/login";
    private static final  String urlgetConsellors="http://www.clr-vision.com:18080/Therapista/consellor/getConsellors";
    private static final  String urladdConsellor="http://www.clr-vision.com:18080/Therapista/consellor/addConsellor";
    private static final  String urlgetConsellorAds="http://www.clr-vision.com:18080/Therapista/consellor/getConsellorAds";
    private static final  String urlgetConsellor="http://www.clr-vision.com:18080/Therapista/consellor/getConsellor";
    private static final  String urlgetArticals="http://www.clr-vision.com:18080/Therapista/article/getArticles";
    private static final  String urlgetArticalAds="http://www.clr-vision.com:18080/Therapista/article/getArticleAds";
    private static final  String urlgetArtical="http://www.clr-vision.com:18080/Therapista/article/getArticle";
    private static final  String urlGetSchedule="http://www.clr-vision.com:18080/Therapista/schedule/getSchedulesByDate";
    private static final  String urlGetScheduleBySid="http://www.clr-vision.com:18080/Therapista/schedule/getSchedule";
    private static final  String urlAddNeed="http://www.clr-vision.com:18080/Therapista/need/addNeed";
    private static final  String urlGetNeeds="http://www.clr-vision.com:18080/Therapista/need/getNeeds";
    private static final  String urlGetUser="http://www.clr-vision.com:18080/Therapista/user/getUser";
    private static final  String urlAddOrder="http://www.clr-vision.com:18080/Therapista/order/addOrder";
    private static final  String urlGetOrders="http://www.clr-vision.com:18080/Therapista/order/getOrders";
    private static final  String urlGetOrder="http://www.clr-vision.com:18080/Therapista/order/getOrder";
    private static final  String urlDelOrder="http://www.clr-vision.com:18080/Therapista/order/delOrder";
    private static final  String urlGetOrderByUid="http://www.clr-vision.com:18080/Therapista/order/getOrdersByUid";
    private static final  String urladdHarvest="http://www.clr-vision.com:18080/Therapista/harvest/addHarvest";
    private static final  String urladdHarvestz="http://www.clr-vision.com:18080/Therapista/harvest/addHarvest";
    private static final  String urlgetQuestions="http://www.clr-vision.com:18080/Therapista/measurement/getMeasurements";
    /*
    private static final  String urlLogin="http://10.15.3.101:8080/Therapista/user/login";
    private static final  String urlgetConsellors="http://10.15.3.101:8080/Therapista/consellor/getConsellors";
    private static final  String urlgetConsellorAds="http://10.15.3.101:8080/Therapista/consellor/getConsellorAds";
    private static final  String urladdConsellor="http://10.15.3.101:8080/Therapista/consellor/addConsellor";
    private static final  String urlgetConsellor="http://10.15.3.101:8080/Therapista/consellor/getConsellor";
    private static final  String urlgetArticals="http://10.15.3.101:8080/Therapista/article/getArticles";
    private static final  String urlgetArticalAds="http://10.15.3.101:8080/Therapista/article/getArticleAds";
    private static final  String urlgetArtical="http://10.15.3.101:8080/Therapista/article/getArticle";
    */  //private static final String URLVAR="http://182.48.119.26:18080/Test/LoginController/login.json";
    //暂时做参考，未调用
    public String getConn(String urlpath,String email, String password, String tele) {
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email",email));
            params.add(new BasicNameValuePair("password",password));
            params.add(new BasicNameValuePair("tel",tele));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlpath);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="登录失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        return result;

    }

    /*
    * 用户注册
    * */
    public String doRegister(String urlpath, String email, String password, String telephone)
    {
        String result = "";
        /*if(!isEmail(email)){
            result = "邮箱格式不正确，请重新输入";
        }
        else if(!isTelephone(telephone)){
            result = "手机号码格式不正确，请重新输入";
        }
        else if(!checkPassword(password)){
            result = "密码应不少于6位";
        }
        else{*/
            try {
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email",email));
                params.add(new BasicNameValuePair("password",password));
                params.add(new BasicNameValuePair("tel",telephone));

                HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
                HttpPost httpPost = new HttpPost(urlpath);
                httpPost.setEntity(entity);
                HttpClient client = new DefaultHttpClient();
                HttpResponse httpResponse = client.execute(httpPost);

                if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
                {
                    result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                    Log.v("asd",result);
                }
                else
                {
                    result="注册失败！";
                }

            } catch (Exception e) {
                e.printStackTrace();
                result = e.toString();
            }
        //}
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    /*
    * 用户登录
    * */
    public String doLogin(String email, String password) {
        String result = "";
        byte[] wer;
        try {
            //List<>
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username",email));
            params.add(new BasicNameValuePair("password",password));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlLogin);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="登录失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        return result;
    }

    public String getQuestions(){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlgetQuestions);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        // result = parseConnselor(result);
        return result;
    }

    public String getOrders(){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlGetOrders);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        // result = parseConnselor(result);
        return result;
    }

    //获取用户
    public String getUser(String user_email){
        String result="";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email",user_email));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlGetUser);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                //Log.v("asd",singleConsellor);
            }
            else {
                result="注册失败！";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    public String addHarvest(String uid, String mid, String result){
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("uid",uid));
            params.add(new BasicNameValuePair("mid",mid));
            params.add(new BasicNameValuePair("result",result));
            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urladdHarvest);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        Log.v("fine",result);
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    //预约
    public String addOrder(String uid, String nid, String sid, String starttime, String period){
        String result="";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("uid",uid));
            params.add(new BasicNameValuePair("nid",nid));
            params.add(new BasicNameValuePair("sid",sid));
            params.add(new BasicNameValuePair("starttime",starttime));
            params.add(new BasicNameValuePair("period",period));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlAddOrder);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                //Log.v("asd",singleConsellor);
            }
            else {
                result="预约失败！";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    public String getOrder(String oid){
        String result="";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("oid",oid));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlGetOrder);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                //Log.v("asd",singleConsellor);
            }
            else {
                result="获取oid预约失败！";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    public String delOrder(String oid){
        String result="";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("oid",oid));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlDelOrder);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                //Log.v("asd",singleConsellor);
            }
            else {
                result="获取oid预约失败！";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    public String getOrderByUid(String user_id){
        String result="";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("uid",user_id));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlGetOrderByUid);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                //Log.v("asd",singleConsellor);
            }
            else {
                result="获取oid预约失败！";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }



    /*
    * 获取咨询师
    * */
    public String getConsellors(){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlgetConsellors);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
       // result = parseConnselor(result);
        return result;
    }

    //获取所有需求
    public String getNeeds(){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlGetNeeds);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    //添加需求
    public String addNeed(String uid, String requirement){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("uid",uid));
            params.add(new BasicNameValuePair("requirement",requirement));
            params.add(new BasicNameValuePair("closure","0"));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlAddNeed);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        // result = parseConnselor(result);
        return result;
    }

    //获取某个咨询师的日程
    public String getPerSchedule(String cid, String sdate){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("cid",cid));
            params.add(new BasicNameValuePair("sdate",sdate));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlGetSchedule);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        // result = parseConnselor(result);
        return result;
    }

    //get schedule by sid
    public String getSchedule(String sid){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("sid",sid));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlGetScheduleBySid);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        // result = parseConnselor(result);
        return result;
    }

    //获取咨询师推荐广告
    public String getConsellorAds(){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlgetConsellorAds);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    //获取今日要闻
    public String getArticals(){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlgetArticals);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    public String addHarvestz(String result){
        try {
//            JSONArray jsonArray = new JSONArray();
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("harvestz",result));
            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urladdHarvestz);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        Log.e("fine",result);
        return result;
    }

    //获取hear广告
    public String getArticalAds(){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlgetArticalAds);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="获取失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        result = result.replace("\\\"","\"");
        result = result.replace("\"{","{");
        result = result.replace("}\"","}");
        return result;
    }

    //获取单个咨询师
    public String getUrlgetConsellor(String csid){
        String singleConsellor="";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id",csid));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlgetConsellor);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
                singleConsellor= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                //Log.v("asd",singleConsellor);
            }
            else {
                singleConsellor="注册失败！";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            singleConsellor = e.toString();
        }
        singleConsellor = singleConsellor.replace("\\\"","\"");
        singleConsellor = singleConsellor.replace("\"{","{");
        singleConsellor = singleConsellor.replace("}\"","}");
        return singleConsellor;
    }

    //获取具体文章
    public String getArticle(String aid){
        String articleStr="";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("aid",aid));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urlgetArtical);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
                articleStr= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else {
                articleStr="注册失败！";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            articleStr = e.toString();
        }
        articleStr = articleStr.replace("\\\"","\"");
        articleStr = articleStr.replace("\"{","{");
        articleStr = articleStr.replace("}\"","}");
        return articleStr;
    }

    /*
    * 咨询师注册
    * */
    public String CSdoRegister(){
        String result = "";
        try {
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username","test1"));
            params.add(new BasicNameValuePair("password","111111"));
            params.add(new BasicNameValuePair("realname","realtest1"));
            params.add(new BasicNameValuePair("gender","男"));
            params.add(new BasicNameValuePair("birth","1995-02-06"));
            params.add(new BasicNameValuePair("tel","18714711471"));
            params.add(new BasicNameValuePair("description","fghfgh"));
            params.add(new BasicNameValuePair("career","111111"));
            params.add(new BasicNameValuePair("selfxp","111111"));
            params.add(new BasicNameValuePair("recruit","111111"));
            params.add(new BasicNameValuePair("rid","11"));
            params.add(new BasicNameValuePair("pid1","11"));
            params.add(new BasicNameValuePair("pid2","11"));
            params.add(new BasicNameValuePair("pid3","11"));
            params.add(new BasicNameValuePair("did1","11"));
            params.add(new BasicNameValuePair("did2","11"));
            params.add(new BasicNameValuePair("did3","11"));
            params.add(new BasicNameValuePair("tid1","11"));
            params.add(new BasicNameValuePair("tid2","11"));
            params.add(new BasicNameValuePair("tid3","11"));

            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(urladdConsellor);
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="登录失败！";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
        }
        return result;
    }

    /*
    * 解析咨询师json
    * */
    private String parseConnselor(String jsonString){
        String result="";
        try {
            JSONArray jsonObjs = new JSONObject(jsonString).getJSONArray("consellors");
            String s = "";
            for(int i = 0; i < jsonObjs.length() ; i++){
                JSONObject jsonObj = (JSONObject)jsonObjs.get(i);
                int id = jsonObj.getInt("id");
                String name = jsonObj.getString("username");
                String gender = jsonObj.getString("gender");
                result +=  "ID号"+id + ", 姓名：" + name + ",性别：" + gender+ "\n" ;
            }
        } catch (JSONException e) {
            System.out.println("Jsons parse error !");
            e.printStackTrace();
        }
        return result;
    }

    //解析JSON
    public String parseJson(String jsonstr){
        String status="12";
        try{
            JSONObject jsonobj = new JSONObject(jsonstr);
            status = jsonobj.getString("addUser");
        }
        catch (JSONException ex){
            ex.printStackTrace();;
        }
        return status;
    }

    //判断邮箱地址合法性
    private boolean isEmail(String email){
        String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //判断手机号码合法性
    private boolean isTelephone(String tel){
        return tel.matches("[1][358]\\d{9}");
    }

    //密码长度大于6
    private boolean checkPassword(String password){
        return password.length()>=6 ? true:false;
    }

}

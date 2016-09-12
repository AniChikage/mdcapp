package cn.edu.zafu.easemob.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import cn.edu.zafu.easemob.R;
import cn.edu.zafu.easemob.Main.Mainpage;
import cn.edu.zafu.easemob.netapp.ConnNet;

/**
 * Created by AniChikage on 2016/6/17.
 */
public class Login extends Activity{

    private TextView login_text_login;
    private TextView login_text_scan;
    private TextView login_text_register;
    private TextView login_text_forget;
    private EditText login_et_email;
    private EditText login_et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题栏
        setContentView(R.layout.login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏

        init();

        login_text_login.setOnClickListener(new login_login());
        login_text_scan.setOnClickListener(new scan());
        login_text_register.setOnClickListener(new login_register());
        login_text_forget.setOnClickListener(new login_forget());
    }

    //初始化程序
    private void init(){
        login_text_login=(TextView) findViewById(R.id.login_text_login);
        login_text_scan=(TextView)findViewById(R.id.login_text_scan);
        login_text_register=(TextView)findViewById(R.id.login_text_register);
        login_text_forget = (TextView)findViewById(R.id.login_text_forget);
        login_et_email=(EditText)findViewById(R.id.login_et_email);
        login_et_password=(EditText)findViewById(R.id.login_et_password);

    }

    /*
    *
    * */
    class login_forget implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Toast.makeText(Login.this,"开发中……",Toast.LENGTH_LONG).show();
        }
    }

    //登录事件
    class login_login implements View.OnClickListener{
        @Override
        public void onClick(View v){

            if(login_et_email.getText().toString().equals("")||login_et_password.getText().toString().equals("")){
                Toast.makeText(Login.this,"用户名或者密码不能为空",Toast.LENGTH_LONG).show();
            }
            else{
                new Thread(new Runnable() {
                    public void run() {
                        try{
                            login();
                            ConnNet operaton=new ConnNet();
                            String result=operaton.doLogin(login_et_email.getText().toString(),login_et_password.getText().toString());
                            String resul=operaton.getConn("http://www.clr-vision.com:18080/Therapista/user/login",login_et_email.getText().toString(),login_et_password.getText().toString(),"111");
                            Message msg=new Message();
                            msg.obj=resul;
                            handler.sendMessage(msg);
                        }
                        catch (Exception ex){
                            Toast.makeText(Login.this,"用户名或者密码不能为空",Toast.LENGTH_LONG).show();
                        }

                    }
                }).start();
            }

        }
    }

    //以游客身份浏览
    class scan implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //Toast.makeText(Login.this,"正在开发中……",Toast.LENGTH_LONG).show();
            try{
                Intent intent = new Intent(Login.this, Mainpage.class);
                Bundle bundle = new Bundle();
                bundle.putString("app_username","scan_visitor");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
            catch (Exception ex)
            {
                Toast.makeText(Login.this,ex.toString(),Toast.LENGTH_LONG).show();
                Log.e("i",ex.toString());
            }

        }
    }

    //以游客身份浏览
    class login_register implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
            //finish();
        }
    }

    //环信登录

    private void login() {
        String u=login_et_email.getText().toString();
        String p=login_et_password.getText().toString();
        if (TextUtils.isEmpty(u)||TextUtils.isEmpty(p)){
            Toast.makeText(getApplicationContext(),"账号或密码不能为空！",Toast.LENGTH_LONG).show();
            return ;
        }
        //这里先进行自己服务器的登录操作
        //自己服务器登录成功后再执行环信服务器的登录操作
        EMChatManager.getInstance().login(u, p, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        EMGroupManager.getInstance().loadAllGroups();
                        EMChatManager.getInstance().loadAllConversations();
                        //Toast.makeText(Login.this, "登陆聊天服务器成功", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "登陆聊天服务器成功！");
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e("TAG", "登陆聊天服务器中 " + "progress:" + progress + " status:" + status);
            }

            @Override
            public void onError(int code, String message) {
                Log.e("TAG", "登陆聊天服务器失败！");
                //Toast.makeText(MainActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            Log.e("doregister" ,string);
            //Toast.makeText(Login.this, String.valueOf(string.indexOf("login")), Toast.LENGTH_SHORT).show();
            if(string.indexOf("token")>0) {
                Intent intent =new Intent(Login.this, Mainpage.class);
                Bundle bundle = new Bundle();
                bundle.putString("app_username",login_et_email.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                //Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
            }
            /*
            else if(string.indexOf("unregistered")>0) {
                Toast.makeText(Login.this,"该账号尚未注册！",Toast.LENGTH_LONG);
            }*/
            else{
                Toast.makeText(Login.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
           // Toast.makeText(Login.this, string, Toast.LENGTH_SHORT).show();
            Log.e(string,string);
            super.handleMessage(msg);
        }
    };

    ////////////////////======================================================
    /**
     * 加载本地文件,并转换为byte数组
     * @return
     */
    public static byte[] loadFile() {
        File file = new File("/mnt/sdcard/DCIM/bb.jpg");

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        byte[] data = null ;

        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream((int) file.length());

            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray() ;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    fis = null;
                }

                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data ;
    }

    /**
     * 对byte[]进行压缩
     *
     * @param
     * @return 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        System.out.println("before:" + data.length);

        GZIPOutputStream gzip = null ;
        ByteArrayOutputStream baos = null ;
        byte[] newData = null ;

        try {
            baos = new ByteArrayOutputStream() ;
            gzip = new GZIPOutputStream(baos);

            gzip.write(data);
            gzip.finish();
            gzip.flush();

            newData = baos.toByteArray() ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                gzip.close();
                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("after:" + newData.length);
        return newData ;
    }
}

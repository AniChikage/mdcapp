package cn.edu.zafu.easemob.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import cn.edu.zafu.easemob.R;
import cn.edu.zafu.easemob.netapp.ConnNet;

/**
 * Created by Moe Winder on 2016/5/12. comicyueyu@gmail.com
 */

        public class Register extends Activity {

            private TextView tv_register;
            private TextView tv_return;
            private TextView tv_yhxy;
            private EditText et_email;
            private EditText et_password;
            private EditText et_telephone;
            private static final Gson gson=new Gson();
            private static final OkHttpClient mOkHttpClient=new OkHttpClient();
            private static String urlPath="http://www.clr-vision.com:18080/Therapista/user/addUser";

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题栏
                setContentView(R.layout.register);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
                init();
    }

    //初始化各参数
    private void init()
    {
        tv_register=(TextView) findViewById(R.id.register_tv_register);
        tv_return=(TextView)findViewById(R.id.register_tv_return);
        tv_yhxy=(TextView)findViewById(R.id.register_tv_yhxy);
        et_email=(EditText)findViewById(R.id.register_et_email);
        et_password=(EditText)findViewById(R.id.register_et_password);
        et_telephone=(EditText)findViewById(R.id.register_et_telephone);
        tv_register.setOnClickListener(new conn());
        tv_return.setOnClickListener(new returnMain());
        tv_yhxy.setOnClickListener(new yhxy());
    }

    //注册函数
    class conn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                public void run() {
                    ConnNet operaton=new ConnNet();
                    String result=operaton.doRegister(urlPath,et_email.getText().toString(),et_password.getText().toString(),et_telephone.getText().toString());
                    Message msg=new Message();
                    msg.obj=result;
                    handler.sendMessage(msg);
                }
            }).start();
            new Thread(new Runnable() {
                public void run() {

                    String u=et_email.getText().toString();
                    String p=et_password.getText().toString();
                    try{
                        RequestBody requestBody= new FormEncodingBuilder()
                                .add("username",u)
                                .add("password",p)
                                .build();
                        String url="http://163.44.165.63/huanxin/index.php";
                        Request request=new Request.Builder().url(url).post(requestBody).build();
                        /*Response response = mOkHttpClient.newCall(request).execute();
                        Log.e("TAG",response.toString());
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_LONG).show();
                        } else {
                            throw new IOException("Unexpected code " + response);
                        }*/
                        mOkHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {
                                Log.e("TAG","Error,register failure.");
                            }
                            @Override
                            public void onResponse(Response response) throws IOException {
                                String result=response.body().string();
                                Log.e("YES",result);
                                //Toast.makeText(Hxregtest.this,result,Toast.LENGTH_LONG).show();
                                //RegisterModel bean=gson.fromJson(result,RegisterModel.class);
                                Message message=Message.obtain();
                                message.obj=result;
                                //message.what=REGISTER;
                                mHandler.sendMessage(message);
                            }
                        });
                    }
                    catch(Exception ex){
                        Toast.makeText(Register.this,ex.toString(),Toast.LENGTH_LONG).show();
                        Log.e("EX",ex.toString());
                    }
                }
            }).start();
        }
    }

    //用户协议
    class yhxy implements View.OnClickListener{
        public void onClick(View v){
            new AlertDialog.Builder(Register.this)
                    .setTitle("用户协议")
                    .setMessage("开发中……")
                    .setPositiveButton("确定", null)
                    .show();
        }
    }

    //返回主界面
    class returnMain implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent = new Intent(Register.this, Login.class);
            Register.this.finish();
            startActivity(intent);
        }
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            if(string.equals("registered email")){
                Toast.makeText(Register.this, "该邮箱已经注册！", Toast.LENGTH_SHORT).show();
            }
            else if(string.equals("1")){
                Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Register.this, string, Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String bean= (String) msg.obj;
            //Toast.makeText(Register.this,"注册成功！",Toast.LENGTH_LONG).show();
            super.handleMessage(msg);
        }
    };
}

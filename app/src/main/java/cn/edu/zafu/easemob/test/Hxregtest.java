package cn.edu.zafu.easemob.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import cn.edu.zafu.easemob.model.RegisterModel;

/**
 * Created by AniChikage on 2016/7/7.
 */
public class Hxregtest extends Activity{
    private EditText username;
    private EditText pwd;
    private Button btnreg;
    private static final Gson gson=new Gson();
    private static final OkHttpClient mOkHttpClient=new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regtest);

        username=(EditText)findViewById(R.id.hxregtestusername);
        pwd=(EditText)findViewById(R.id.hxregtestpwd);
        btnreg=(Button)findViewById(R.id.btnregtest);

        btnreg.setOnClickListener(new regtest());

    }

    class regtest implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                public void run() {

                    String u=username.getText().toString();
                    String p=pwd.getText().toString();
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
                        Toast.makeText(Hxregtest.this,ex.toString(),Toast.LENGTH_LONG).show();
                        Log.e("EX",ex.toString());
                    }
                }
            }).start();

        }
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String bean= (String) msg.obj;
            Toast.makeText(Hxregtest.this,"注册成功！",Toast.LENGTH_LONG).show();
            super.handleMessage(msg);
        }
    };
}

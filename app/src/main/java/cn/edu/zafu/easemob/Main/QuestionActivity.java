package cn.edu.zafu.easemob.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zafu.easemob.Adapter.ArticleTextAdapter;
import cn.edu.zafu.easemob.Adapter.QuestionAdapter;
import cn.edu.zafu.easemob.R;
import cn.edu.zafu.easemob.netapp.ConnNet;

public class QuestionActivity extends ActionBarActivity {

    private Context hcontext;
    private ListView viewFlow;
    private QuestionAdapter questionListViewAdapter;
    private List<Map<String, Object>> listItems, questionList;
    private Button postHarvest;
    private String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        hcontext=this.getApplicationContext();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            user_id = bundle.getString("user_id");
            Log.e("userid",user_id);
            try{
                init();
                SysnConsellors();
                postHarvest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        int counter_err=0;
                        //pending illegal
                        int i;
                        boolean flag = false;
                        for (i = 0; i<questionListViewAdapter.length; i++ ) {
                            if ((Integer)questionListViewAdapter.tagList[i] == 0){
                                flag = true;
                            }
                        }
                        if (flag){
                            new AlertDialog.Builder(QuestionActivity.this)
                                    .setTitle("提交")
                                    .setMessage("提交成功")
                                    .setPositiveButton("确定",
                                            new DialogInterface.OnClickListener(){
                                                public void onClick(DialogInterface dialoginterface, int i){
                                                    //按钮事件
                                                    Toast.makeText(QuestionActivity.this, "确定",Toast.LENGTH_LONG).show();
                                                }
                                            }).show();
                            return;
                        }
                        //
                        */
                        Log.e("log","handle begin");
                        new Thread(new Runnable() {
                            public void run() {
                                try{
                                    int i;

                                    ConnNet operaton=new ConnNet();
                                    JSONArray jsonArray = new JSONArray();
                                    for (i=0;i<questionListViewAdapter.length;i++){
                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject.put("uid", "12");
                                        jsonObject.put("mid", (String)questionList.get(i).get("mid"));
                                        jsonObject.put("result", ((Integer)questionListViewAdapter.tagList[i]).toString());
                                        jsonArray.put(jsonObject);
                                    }
                                    Log.e("log","handle in progress");
                                    Log.v("bitch", jsonArray.toString());
                                    String result = operaton.addHarvestz(jsonArray.toString());
                                    Message msg=new Message();
                                    msg.obj=result;
                                    addHandler.sendMessage(msg);

                                } catch (Exception ex){
                                    Log.v("fuck",ex.toString());
//                                Toast.makeText(QuestionActivity.this,"失败",Toast.LENGTH_LONG).show();
                                }

                            }
                        }).start();
                        Log.e("log","handle done");
                        /*
                        if (counter_err>0){
                            Toast.makeText(QuestionActivity.this,"失败",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(QuestionActivity.this, "成功", Toast.LENGTH_LONG).show();
                        }
                        //Intent intent = new Intent(QuestionActivity.this, Mainpage.class);
                        //startActivity(intent);
                        //finish();
                        */
                    }
                });
            }
            catch (Exception ex){
                Toast.makeText(QuestionActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
            }
        }


    }

    Handler addHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            Log.e("addHarvestz",string);
            try{
            }
            catch (Exception ex){
                Log.e("jsonErr",ex.toString());
            }
            super.handleMessage(msg);
        }
    };

    private void init() {
        postHarvest = (Button) findViewById(R.id.post_harvest);
        viewFlow = (ListView)findViewById(R.id.question_listView);
    }

    private void SysnConsellors(){
        new Thread(new Runnable() {
            public void run() {
                try{
                    ConnNet operaton=new ConnNet();
                    String result=operaton.getQuestions();
                    Message msg=new Message();
                    msg.obj=result;
                    handler.sendMessage(msg);
                } catch (Exception ex){
                    Toast.makeText(QuestionActivity.this,"失败",Toast.LENGTH_LONG).show();
                }

            }
        }).start();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;

            List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
            try {
                JSONArray jsonObjs = new JSONObject(string).getJSONArray("measurements");
                for(int i = 0; i < jsonObjs.length() ; i++){
                    JSONObject jsonObj = (JSONObject)jsonObjs.get(i);
                    int mid = jsonObj.getInt("mid");
                    int gid = jsonObj.getInt("gid");
                    String content = jsonObj.getString("content");
                    String c1 = jsonObj.getString("c1");
                    int r1 = jsonObj.getInt("r1");
                    String c2 = jsonObj.getString("c2");
                    int r2 = jsonObj.getInt("r2");
                    String c3 = jsonObj.getString("c3");
                    int r3 = jsonObj.getInt("r3");
                    String c4 = jsonObj.getString("c4");
                    int r4 = jsonObj.getInt("r4");
                    String c5 = jsonObj.getString("c5");
                    int r5 = jsonObj.getInt("r5");
                    Map<String, Object> map = new HashMap<String, Object>();
                    //Log.e("get: img length",String.valueOf(jsonObj.getString("portrait").length()));
                    // Log.e("get: img string",jsonObj.getString("portrait"));
                    //===============================================================
//                    String asdf=jsonObj.getString("portrait");
//                    asdf=asdf.replace("/n","/n");

                    //===============================================================
//                    Drawable drawable =new BitmapDrawable(getBitmapFromByte(Base64.decode(asdf,Base64.DEFAULT)));
                    //Drawable drawable =new BitmapDrawable(getBitmapFromByte(jsonObj.getString("portrait").getBytes()));
                    map.put("mid", String.valueOf(mid));               //咨询师头像
                    map.put("gid",String.valueOf(gid));
                    map.put("content", content);              //姓名
                    map.put("c1", c1);     //性别
                    map.put("c2", c2); //职业等级
                    map.put("c3", c3); //案例时长
                    map.put("c4", c4); //擅长领域
                    map.put("c5", c5); //价格
                    map.put("r1", r1);
                    map.put("r2", r2);
                    map.put("r3", r3);
                    map.put("r4", r4);
                    map.put("r5", r5);
                    listItems.add(map);
                }
            } catch (JSONException e) {
                System.out.println("Jsons parse error !");
                e.printStackTrace();
            }

            questionList = listItems;
            questionListViewAdapter = new QuestionAdapter(hcontext, questionList);
            viewFlow.setAdapter(questionListViewAdapter);
            viewFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try{
                        ListView lv = (ListView)parent;
                        HashMap<String,Object> person = (HashMap<String,Object>)lv.getItemAtPosition(position);//SimpleAdapter返回Map
                        if(person!=null){
                            //Toast.makeText(getApplicationContext(), person.get("csabid").toString(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(QuestionActivity.this, Csdetail.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("csnoid",person.get("csabid").toString());
//                            intent.putExtras(bundle);
//                            startActivity(intent);
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"kong", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex){
                        Toast.makeText(QuestionActivity.this,ex.toString() ,Toast.LENGTH_SHORT).show();
                        Log.e("get error",ex.toString());
                    }
                }
            });
            //Log.e("TAG" ,string);
            //Log.e("TAG" ,String.valueOf(string.length()));
            super.handleMessage(msg);
        }
    };


}

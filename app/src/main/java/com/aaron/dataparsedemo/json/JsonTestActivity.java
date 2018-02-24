package com.aaron.dataparsedemo.json;

import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.aaron.dataparsedemo.BuildConfig;
import com.aaron.dataparsedemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json 使用
 */
public class JsonTestActivity extends AppCompatActivity {
    private TextView tv_create_json, tv_parse_json;
    private String jsonData = "[{\"sex\":\"girl\",\"addr\":\"ddd\",\"name\":\"john\"},{\"sex\":\"boy\",\"addr\":\"ccc\",\"name\":\"tom\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_test);
        tv_create_json = (TextView) findViewById(R.id.tv_create_json);
        tv_parse_json = (TextView) findViewById(R.id.tv_parse_json);

        KLog.init(BuildConfig.LOG_DEBUG,"JsonTestActivity");
        buildJsonData();
        parseJsonData();
    }

    /**
     * 创建Json数据
     */
    private void buildJsonData() {
        Map<String, String> map1 = new ArrayMap<>();
        Map<String, String> map2 = new ArrayMap<>();
        List<Map> list = new ArrayList<>();

        map1.put("name", "john");
        map1.put("sex", "girl");
        map1.put("addr", "ddd");

        map2.put("name", "tom");
        map2.put("sex", "boy");
        map2.put("addr", "ccc");

        list.add(map1);
        list.add(map2);

        List<PersonBean> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PersonBean p = new PersonBean();
            p.setName("john");
            p.setSex("girl");
            p.setAddr("ccc");
            persons.add(p);
        }

        Gson gson = new Gson();

        String str = gson.toJson(persons);
        KLog.json("PersionBean转JSON：\n",str);
        tv_create_json.setText("PersionBean转JSON：\n" + str);
        /*
        [{"addr":"ccc","name":"john","sex":"girl"},{"addr":"ccc","name":"john","sex":"girl"},...]
         */

        String str2 = gson.toJson(map1, new TypeToken<Map<String, String>>() {
        }.getType());
        KLog.json("buildJsonData2----", str2);
        tv_create_json.setText(tv_create_json.getText() + "\nMap转JSON：\n" + str2);
        /*
        {"sex":"girl","addr":"ddd","name":"john"}
         */
        PersonBean p = gson.fromJson(str2, PersonBean.class);
        KLog.i("parseJsonData2----", p.toString());
        tv_create_json.setText(tv_create_json.getText() + "\n上面JSON转PersionBean：\n" + p.toString());
        /*
        name:john,sexgirl,addrddd
         */

        String str3 = gson.toJson(list, new TypeToken<List<Map>>() {
        }.getType());
        KLog.json("buildJsonData3----", str3);
        tv_create_json.setText(tv_create_json.getText() + "\nList转JSON：\n" + str3);
        /*
        [{"sex":"girl","addr":"ddd","name":"john"},{"sex":"boy","addr":"ccc","name":"tom"}]
         */
    }

    private void parseJsonData() {
//---------------不用Gson
//        try {
//            JSONObject jsonObject = new JSONObject(jsonData);
//            JSONArray jsonArray = jsonObject.getJSONArray("persion");
//            for(int i = 0;i<jsonArray.length();i++){
//                PersonBean personBean = new PersonBean();
//                personBean.setName(jsonArray.getJSONObject(i).getString("name"));
//                personBean.setSex(jsonArray.getJSONObject(i).getString("sex"));
//                personBean.setAddr(jsonArray.getJSONObject(i).getString("addr"));
//                Log.i("parseJsonData","第"+i+"个："+personBean.toString()+"\n");
//                tv_parse_json.setText("\n"+tv_parse_json.getText() + "\n转JSON：\n" + personBean.toString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//---------------用Gson
        Gson gson = new Gson();
        List<PersonBean> pp = gson.fromJson(jsonData, new TypeToken<List<PersonBean>>() {
        }.getType());
        tv_parse_json.setText(jsonData);
        for (PersonBean p : pp) {
            KLog.i("parseJsonData", p.toString() + "\n");
            tv_parse_json.setText(tv_parse_json.getText() + "\n转JSON：\n" + p.toString());
        }
    }
}

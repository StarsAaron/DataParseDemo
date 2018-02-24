package com.aaron.dataparsedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aaron.dataparsedemo.json.JsonTestActivity;
import com.aaron.dataparsedemo.xml.Book;
import com.aaron.dataparsedemo.xml.BookParser;
import com.aaron.dataparsedemo.xml.DomBookParser;
import com.aaron.dataparsedemo.xml.PullBookParser;
import com.aaron.dataparsedemo.xml.SaxBookParser;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class DataParseTestActivity extends AppCompatActivity {
    private Button btn_xml, btn_json_test;
    private TextView tv_data;
    private BookParser bookParser;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_parse_test);
        btn_xml = (Button) findViewById(R.id.btn_xml);
        btn_json_test = (Button) findViewById(R.id.btn_json_test);
        tv_data = (TextView) findViewById(R.id.tv_data);

        // 构造数据
        books = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setId(i);
            book.setName("Tom");
            book.setPrice(i * 20);
            books.add(book);
        }

        btn_xml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Dom序列化数据
                    bookParser = new DomBookParser();
                    String str1 = bookParser.serialize(books);
                    tv_data.setText(tv_data.getText() + "\nBook列表Dom序列化：\n" + str1);
                    KLog.xml("\nBook列表Dom序列化：\n" + str1);
                    List<Book> booksList1 = bookParser.parse(getAssets().open("Test.xml"));
                    tv_data.setText(tv_data.getText() + "\n从xml解析出Book列表：\n" + booksList1.toString());
                    KLog.i("\n从xml解析出Book列表：\n" + booksList1.toString());
                    // Sax序列化数据
                    bookParser = new SaxBookParser();
                    String str2 = bookParser.serialize(books);
                    tv_data.setText(tv_data.getText() + "\nBook列表Sax序列化：\n" + str2);
                    KLog.xml("\nBook列表Sax序列化：\n" + str2);
                    List<Book> booksList2 = bookParser.parse(getAssets().open("Test.xml"));
                    tv_data.setText(tv_data.getText() + "\n从xml解析出Book列表：\n" + booksList2.toString());
                    KLog.i("\n从xml解析出Book列表：\n" + booksList2.toString());

                    // Pull序列化数据
                    bookParser = new PullBookParser();
                    String str3 = bookParser.serialize(books);
                    tv_data.setText(tv_data.getText() + "\nBook列表Pull序列化：\n" + str3);
                    KLog.xml("\nBook列表Pull序列化：\n" + str3);
                    List<Book> booksList3 = bookParser.parse(getAssets().open("Test.xml"));
                    tv_data.setText(tv_data.getText() + "\n从xml解析出Book列表：\n" + booksList3.toString());
                    KLog.i("\n从xml解析出Book列表：\n" + booksList3.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btn_json_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(JsonTestActivity.class);
            }
        });
    }

    private void goActivity(Class name) {
        Intent intent = new Intent(DataParseTestActivity.this, name);
        startActivity(intent);
    }
}

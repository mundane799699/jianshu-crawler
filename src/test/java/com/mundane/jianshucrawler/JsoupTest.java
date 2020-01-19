package com.mundane.jianshucrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

public class JsoupTest {

    @Test
    public void testFile() throws Exception {
        //解析文件
        File file = new File("/Users/mundane/VSCodeProjects/testJsoup/test.html");
        Document doc = Jsoup.parse(file, "utf8");

        String tagText = doc.getElementsByTag("h1").first().text();

        System.out.println(tagText);

    }
}

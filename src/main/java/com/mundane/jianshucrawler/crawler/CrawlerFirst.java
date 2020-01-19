package com.mundane.jianshucrawler.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerFirst {

    public static void main(String[] args) throws Exception {
        //1. 打开浏览器,创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "https://www.jianshu.com/bookmarks";
        //2. 输入网址,发起get请求创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);

        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36";
        httpGet.addHeader("user-agent", userAgent);

        String host = "www.jianshu.com";
        httpGet.addHeader("host", host);

        String cookie = "__yadk_uid=QBk3H5SwLYbu1oIffL4K7RLSXptTm4OH; locale=zh-CN; read_mode=day; default_font=font2; Hm_lvt_0c0e9d9b1e7d617b3e6842e85b9fb068=1578993801,1579073786,1579074492,1579231891; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221626396%22%2C%22%24device_id%22%3A%2216d01bbcc3e4b9-035ee58390077e-12356d56-1296000-16d01bbcc3f854%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_utm_source%22%3A%22recommendation%22%2C%22%24latest_utm_medium%22%3A%22seo_notes%22%2C%22%24latest_utm_campaign%22%3A%22maleskine%22%2C%22%24latest_utm_content%22%3A%22note%22%2C%22%24latest_referrer_host%22%3A%22%22%7D%2C%22first_id%22%3A%2216d01bbcc3e4b9-035ee58390077e-12356d56-1296000-16d01bbcc3f854%22%7D; remember_user_token=W1sxNjI2Mzk2XSwiJDJhJDEwJFFKSkJPaS5zVjhEVU5IV2lsc05UcHUiLCIxNTc5Mzk3MTE0Ljc5NjI3MDYiXQ%3D%3D--e86f6970cc490929385302add032bfc45dbfd2c1; _m7e_session_core=3bff30791d2a1852b566327c98d5d937; Hm_lpvt_0c0e9d9b1e7d617b3e6842e85b9fb068=1579397124";
        httpGet.addHeader("Cookie", cookie);

        String referer = "https://www.jianshu.com/bookmarks";
        httpGet.addHeader("referer", referer);

        String xPjax = "true";
        httpGet.addHeader("x-pjax", xPjax);

        //3.按回车，发起请求，返回响应，使用HttpClient对象发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //4. 解析响应，获取数据
        //判断状态码是否是200
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf8");

            parseContent(content);
        }
    }

    private static void parseContent(String content) {
        Document doc = Jsoup.parse(content);

        Elements elements = doc.select("a.title");
        for (Element element : elements) {
            System.out.println(element.text());
        }
    }
}

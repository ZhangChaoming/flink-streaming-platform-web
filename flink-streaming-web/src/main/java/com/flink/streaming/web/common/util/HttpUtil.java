package com.flink.streaming.web.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhuhuipei
 * @Description:
 * @date 2020-08-06
 * @time 23:05
 */
@Slf4j
public class HttpUtil {


    public final static int TIME_OUT_30_S = 1000 * 30;

    public final static int TIME_OUT_15_S = 1000 * 15;

    public final static int TIME_OUT_20_S = 1000 * 20;

    public final static int TIME_OUT_3_S = 1000 * 3;

    public final static int TIME_OUT_5_M = 1000 * 60 * 5;

    public final static int TIME_OUT_1_M = 1000 * 60 * 1;


    public static HttpHeaders buildHttpHeaders(String contentType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (StringUtils.isEmpty(contentType)) {
            httpHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        } else {
            httpHeaders.add("Content-Type", contentType);
        }
        httpHeaders.add("X-Requested-With", "XMLHttpRequest");
        httpHeaders.add("Accept", "text/plain;charset=utf-8");
        return httpHeaders;
    }

    public static RestTemplate buildRestTemplate(Integer connectTimeout) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(connectTimeout);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }


    public static boolean isHttpsOrHttp(String url) {

        String regUrl = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
        Pattern p = Pattern.compile(regUrl);
        Matcher m = p.matcher(url.trim());
        if (!m.matches()) {
            return false;
        }
        return true;
    }


}

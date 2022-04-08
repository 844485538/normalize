package com.sxd.demo.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author 李健新
 * @Date 2022/4/8
 * @Description
 */
@Component
public class RestTemplateUtils {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发起 Get 请求
     *
     * @param url      地址
     * @param params   参数
     * @return
     */
    public ResponseEntity sendGet(String url, Map<String, String> params) {
        ResponseEntity responseEntity = restTemplate.getForEntity(generateRequestParameters(url, params), String.class);
        return responseEntity;
    }


    /**
     * 发起 Post 请求
     * @param url
     * @param params
     * @return
     */
    public ResponseEntity sendPost(String url, Map<String, String> params) {
        return sendPost(url, null, params);
    }

    /**
     * 发起 Post 请求
     * @param url
     * @param params
     * @return
     */
    public ResponseEntity sendPost(String url, Map<String, String> headers, Map<String, String> params) {
        return restTemplate.postForEntity(url, generatePostJson(headers, params), String.class);
    }

    /**
     * 构建Get请求地址
     *
     * @param url      地址
     * @param params   参数
     * @return
     */
    private static String generateRequestParameters(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.size() > 0) {
            sb.append("?");
            for (Map.Entry map : params.entrySet()) {
                sb.append(map.getKey()).append("=").append(map.getValue()).append("&");
            }
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 构建 Post 请求体
     * @param headers
     * @param params
     * @return
     */
    private static HttpEntity<Map<String, String>> generatePostJson(Map<String, String> headers, Map<String, String> params) {
        // 设置Header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (headers != null && headers.size() > 0){
            headers.forEach(
                    (headerName, headerValue) -> {
                        httpHeaders.set(headerName, headerValue);
                    }
            );
        }
        // 设置请求体
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
        return httpEntity;
    }

}

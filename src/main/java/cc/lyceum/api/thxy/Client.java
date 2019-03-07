package cc.lyceum.api.thxy;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LyceumHewun
 * @date 2018-9-5 MODIFY
 */
public interface Client {

    /**
     * GET方法
     *
     * @param url     url
     * @param headers headers
     * @return Response
     * @throws IOException okhttp Exception
     */
    Response get(String url, Map<String, String> headers) throws IOException;

    /**
     * POST方法
     *
     * @param url     url
     * @param headers headers
     * @param forms   forms
     * @return Response
     * @throws IOException okhttp Exception
     */
    Response post(String url, Map<String, String> headers, Map<String, String> forms) throws IOException;

    String getBody(String url, Map<String, String> headers, String charsetName);

    String getBody(String url, Map<String, String> headers);

    String getBody(String url, String charsetName);

    String getBody(String url);

    default String getBody(String url, Map<String, String> parameter, Map<String, String> headers) {
        if (parameter.size() != 0) {
            url += "?";
        }
        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }
        url = url.substring(0, url.length() - 1);
        return getBody(url, headers);
    }

    String postBody(String url, Map<String, String> headers, Map<String, String> forms, String charsetName);

    String postBody(String url, Map<String, String> headers, Map<String, String> forms);

    String postBody(String url, Map<String, String> forms, String charsetName);

    String postBody(String url, Map<String, String> forms);

    ConcurrentHashMap<String, List<Cookie>> getCookieStore();

    /**
     * okhttp的cookieJar, 用于管理请求中的cookie
     * <p>
     * 这里是每次请求有新的返回cookie就添加到cookieStore
     *
     * @param cookieStore cookieStore
     * @return CookieJar
     */
    default CookieJar newCookieJar(ConcurrentHashMap<String, List<Cookie>> cookieStore) {
        return new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                list = new ArrayList<>(list);
                if (!cookieStore.isEmpty()) {
                    for (Cookie cookie1 : cookieStore.get(httpUrl.host())) {
                        boolean repeat = false;
                        for (Cookie cookie2 : list) {
                            if (Objects.equals(cookie1.name(), cookie2.name())) {
                                repeat = true;
                                break;
                            }
                        }
                        if (!repeat) {
                            list.add(cookie1);
                        }
                    }
                }
                cookieStore.put(httpUrl.host(), list);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
////                手动设置cookie测试
//                List<Cookie> cookies = new ArrayList<>();
//                Cookie cookie = new Cookie.Builder().name("JSESSIONID")
//                        .value("170D7DAE308A049B50E92862D71829AC")
//                        .hostOnlyDomain("jwgl.thxy.cn")
//                        .build();
//                cookies.add(cookie);
                return cookies != null ? cookies : new ArrayList<>();
            }
        };
    }
}

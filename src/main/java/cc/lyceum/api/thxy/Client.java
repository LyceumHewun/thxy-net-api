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

public interface Client {

    public ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    public Response get(String url, Map<String, String> headers) throws IOException;

    public Response post(String url, Map<String, String> headers, Map<String, String> forms) throws IOException;

    default String getBody(String url, Map<String, String> headers) {
        try {
            return get(url, headers).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    default String postBody(String url, Map<String, String> headers, Map<String, String> forms) {
        try {
            return post(url, headers, forms).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    default String getBody(String url) {
        return getBody(url, null);
    }

    default String postBody(String url, Map<String, String> forms) {
        return postBody(url, null, forms);
    }

    default CookieJar newCookieJar() {
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

                        // 如果不重复
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
                return cookies != null ? cookies : new ArrayList<>();
            }
        };
    }
}

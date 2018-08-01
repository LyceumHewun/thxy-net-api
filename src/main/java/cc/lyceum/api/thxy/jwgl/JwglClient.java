package cc.lyceum.api.thxy.jwgl;

import cc.lyceum.api.thxy.Client;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public abstract class JwglClient implements Client {

    public ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    @Override
    public Response get(String url, Map<String, String> headers) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // cookie
                .cookieJar(this.newCookieJar())
                .build();

        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        // add header
        if (headers != null) {
            headers.forEach(requestBuilder::addHeader);
        }

        Request request = requestBuilder.build();

        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            return response;
        } else {
            return null;
        }
    }

    @Override
    public Response post(String url, Map<String, String> headers, Map<String, String> forms) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // cookie
                .cookieJar(this.newCookieJar())
                .build();

        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        // add header
        if (headers != null) {
            headers.forEach(requestBuilder::addHeader);
        }

        // form
        if (forms != null) {
            FormBody.Builder formBody = new FormBody.Builder();
            forms.forEach(formBody::add);
            requestBuilder.post(formBody.build());
        }

        Request request = requestBuilder.build();

        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            return response;
        } else {
            return null;
        }
    }

    CookieJar newCookieJar() {
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

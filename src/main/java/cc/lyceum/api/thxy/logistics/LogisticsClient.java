package cc.lyceum.api.thxy.logistics;

import cc.lyceum.api.thxy.Client;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public abstract class LogisticsClient implements Client {

    public ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    @Override
    public Response get(String url, Map<String, String> headers) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                // cookie
                .cookieJar(this.newCookieJar(cookieStore))
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
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                // cookie
                .cookieJar(this.newCookieJar(cookieStore))
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

    @Override
    public String getBody(String url, Map<String, String> headers) {
        try {
            return new String(get(url, headers).body().bytes(), "GB2312");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String postBody(String url, Map<String, String> headers, Map<String, String> forms) {
        try {
            return new String(post(url, headers, forms).body().bytes(), "GB2312");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getBody(String url) {
        return this.getBody(url, null);
    }

    @Override
    public String postBody(String url, Map<String, String> forms) {
        return this.postBody(url, null, forms);
    }
}

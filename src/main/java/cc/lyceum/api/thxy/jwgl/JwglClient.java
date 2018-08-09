package cc.lyceum.api.thxy.jwgl;

import cc.lyceum.api.thxy.Client;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class JwglClient implements Client {

    public ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    @Override
    public Response get(String url, Map<String, String> headers) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
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
}

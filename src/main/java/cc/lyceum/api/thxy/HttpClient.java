package cc.lyceum.api.thxy;

import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author LyceumHewun
 * @date 2018-9-5
 */
public class HttpClient extends AbstractClient {

    private ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    public HttpClient() {
    }

    public HttpClient(String charsetName) {
        super(charsetName);
    }

    @Override
    public Response get(String url, Map<String, String> headers) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .cookieJar(this.newCookieJar(cookieStore))
                .build();
        Request.Builder requestBuilder = new Request.Builder()
                .url(url);
        if (headers != null) {
            headers.forEach(requestBuilder::addHeader);
        }
        Request request = requestBuilder.build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response;
        } else if (response.code() == 404) {
            return get(url, headers);
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
                .cookieJar(this.newCookieJar(cookieStore))
                .build();
        Request.Builder requestBuilder = new Request.Builder()
                .url(url);
        if (headers != null) {
            headers.forEach(requestBuilder::addHeader);
        }
        if (forms != null) {
            FormBody.Builder formBody = new FormBody.Builder();
            forms.forEach(formBody::add);
            requestBuilder.post(formBody.build());
        }
        Request request = requestBuilder.build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response;
        } else if (response.code() == 404) {
            return get(url, headers);
        } else {
            return null;
        }
    }

    @Override
    public ConcurrentHashMap<String, List<Cookie>> getCookieStore() {
        return cookieStore;
    }
}

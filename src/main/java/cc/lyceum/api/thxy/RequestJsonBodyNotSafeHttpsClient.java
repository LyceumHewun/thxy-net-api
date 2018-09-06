package cc.lyceum.api.thxy;

import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author LyceumHewun
 * @date 2018-9-6
 */
public class RequestJsonBodyNotSafeHttpsClient extends NotSafeHttpsClient {

    private ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
    private TrustAllManager trustAllManager = new TrustAllManager();

    public RequestJsonBodyNotSafeHttpsClient() {
    }

    public RequestJsonBodyNotSafeHttpsClient(String charsetName) {
        super(charsetName);
    }

    /**
     * forms 里必须有一个key为json的字符串
     *
     * @param url     url
     * @param headers headers
     * @param forms   forms
     * @return Response
     * @throws IOException okhttp Exception
     */
    @Override
    public Response post(String url, Map<String, String> headers, Map<String, String> forms) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , forms.get("json"));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .cookieJar(this.newCookieJar(cookieStore))
                .sslSocketFactory(createTrustAllSSLFactory(trustAllManager), trustAllManager)
                .hostnameVerifier(createTrustAllHostnameVerifier())
                .build();
        Request.Builder requestBuilder = new Request.Builder()
                .post(body)
                .url(url);
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
}

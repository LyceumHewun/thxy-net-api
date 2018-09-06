package cc.lyceum.api.thxy;

import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author LyceumHewun
 * @date 2018-9-5
 */
public class NotSafeHttpsClient extends AbstractClient {

    private ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
    private TrustAllManager trustAllManager = new TrustAllManager();

    public NotSafeHttpsClient() {
    }

    public NotSafeHttpsClient(String charsetName) {
        super(charsetName);
    }

    @Override
    public Response get(String url, Map<String, String> headers) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .cookieJar(this.newCookieJar(cookieStore))
                .sslSocketFactory(createTrustAllSSLFactory(trustAllManager), trustAllManager)
                .hostnameVerifier(createTrustAllHostnameVerifier())
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
                .sslSocketFactory(createTrustAllSSLFactory(trustAllManager), trustAllManager)
                .hostnameVerifier(createTrustAllHostnameVerifier())
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
        } else {
            return null;
        }
    }

    @Override
    public ConcurrentHashMap<String, List<Cookie>> getCookieStore() {
        return cookieStore;
    }

    SSLSocketFactory createTrustAllSSLFactory(TrustAllManager trustAllManager) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{trustAllManager}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return ssfFactory;
    }

    HostnameVerifier createTrustAllHostnameVerifier() {
        return (hostname, session) -> true;
    }

    /**
     * 忽略SSL证书
     */
    class TrustAllManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}

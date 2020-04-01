package com.koshm.news.api;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
  public static final String BASE_URL = "https://newsapi.org/v2/";
  
  public static Retrofit retrofit;
  
  public static Retrofit getApiClient() {
    if (retrofit == null)
      retrofit = (new Retrofit.Builder()).baseUrl("https://newsapi.org/v2/").client(getUnsafeOkHttpClient().build()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).build(); 
    return retrofit;
  }
  
  public static OkHttpClient.Builder getUnsafeOkHttpClient() {
    try {
      TrustManager[] arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = new X509TrustManager() {
          public void checkClientTrusted(X509Certificate[] param1ArrayOfX509Certificate, String param1String) throws CertificateException {}
          
          public void checkServerTrusted(X509Certificate[] param1ArrayOfX509Certificate, String param1String) throws CertificateException {}
          
          public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
          }
        };
      SSLContext sSLContext = SSLContext.getInstance("SSL");
      sSLContext.init(null, arrayOfTrustManager, new SecureRandom());
      SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.sslSocketFactory(sSLSocketFactory, (X509TrustManager)arrayOfTrustManager[0]);
      builder.hostnameVerifier(new HostnameVerifier() {
            public boolean verify(String param1String, SSLSession param1SSLSession) {
              return true;
            }
          });
      return builder;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
}


/* Location:              D:\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\koshm\news\api\ApiClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
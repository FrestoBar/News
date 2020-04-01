package com.koshm.news.api;

import com.koshm.news.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<News> getNews(@Query("country") String paramString1, @Query("apiKey") String paramString2);

    @GET("everything")
    Call<News> getNewsSearch(@Query("q") String paramString1, @Query("language") String paramString2, @Query("sortBy") String paramString3, @Query("apiKey") String paramString4);
}


/* Location:              D:\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\koshm\news\api\ApiInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
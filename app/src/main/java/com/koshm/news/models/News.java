package com.koshm.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
  @Expose
  @SerializedName("articles")
  private List<Article> article;
  
  @Expose
  @SerializedName("status")
  private String status;
  
  @Expose
  @SerializedName("totalResult")
  private int totalResult;
  
  public List<Article> getArticle() {
    return this.article;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public int getTotalResult() {
    return this.totalResult;
  }
  
  public void setArticle(List<Article> paramList) {
    this.article = paramList;
  }
  
  public void setStatus(String paramString) {
    this.status = paramString;
  }
  
  public void setTotalResult(int paramInt) {
    this.totalResult = paramInt;
  }
}


/* Location:              D:\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\koshm\news\models\News.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
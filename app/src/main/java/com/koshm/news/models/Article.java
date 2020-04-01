package com.koshm.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {
  @Expose
  @SerializedName("author")
  private String author;
  
  @Expose
  @SerializedName("description")
  private String description;
  
  @Expose
  @SerializedName("publishedAt")
  private String publishedAt;
  
  @Expose
  @SerializedName("source")
  private Source source;
  
  @Expose
  @SerializedName("title")
  private String title;
  
  @Expose
  @SerializedName("url")
  private String url;
  
  @Expose
  @SerializedName("urlToImage")
  private String urlToImage;
  
  public String getAuthor() {
    return this.author;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getPublishedAt() {
    return this.publishedAt;
  }
  
  public Source getSource() {
    return this.source;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public String getUrlToImage() {
    return this.urlToImage;
  }
  
  public void setAuthor(String paramString) {
    this.author = paramString;
  }
  
  public void setDescription(String paramString) {
    this.description = paramString;
  }
  
  public void setPublishedAt(String paramString) {
    this.publishedAt = paramString;
  }
  
  public void setSource(Source paramSource) {
    this.source = paramSource;
  }
  
  public void setTitle(String paramString) {
    this.title = paramString;
  }
  
  public void setUrl(String paramString) {
    this.url = paramString;
  }
  
  public void setUrlToImage(String paramString) {
    this.urlToImage = paramString;
  }
}


/* Location:              D:\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\koshm\news\models\Article.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
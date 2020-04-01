package com.koshm.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {
  @Expose
  @SerializedName("id")
  private String id;
  
  @Expose
  @SerializedName("name")
  private String name;
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setId(String paramString) {
    this.id = paramString;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
}


/* Location:              D:\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\koshm\news\models\Source.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.koshm.news;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class Utils {
  public static ColorDrawable[] vibrantLightColorList = new ColorDrawable[] { new ColorDrawable(Color.parseColor("#ffeead")), new ColorDrawable(Color.parseColor("#93cfb3")), new ColorDrawable(Color.parseColor("#fd7a7a")), new ColorDrawable(Color.parseColor("#faca5f")), new ColorDrawable(Color.parseColor("#1ba798")), new ColorDrawable(Color.parseColor("#6aa9ae")), new ColorDrawable(Color.parseColor("#ffbf27")), new ColorDrawable(Color.parseColor("#d93947")) };
  
  public static String DateFormat(String paramString) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
    try {
      return simpleDateFormat.format((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")).parse(paramString));
    } catch (ParseException parseException) {
      parseException.printStackTrace();
      return paramString;
    } 
  }
  
  public static String DateToTimeFormat(String paramString) {
    PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
    try {
      return prettyTime.format((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)).parse(paramString));
    } catch (ParseException parseException) {
      parseException.printStackTrace();
      return null;
    } 
  }
  
  public static String getCountry() {
    return Locale.getDefault().getCountry().toLowerCase();
  }
  
  public static ColorDrawable getRandomDrawbleColor() {
    int i = (new Random()).nextInt(vibrantLightColorList.length);
    return vibrantLightColorList[i];
  }

  public static String getLanguage(){
    Locale locale = Locale.getDefault();
    String country = locale.getLanguage();
    return country.toLowerCase();
  }
}


/* Location:              D:\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\koshm\news\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package com.example.vera.coolweathers.util;

import android.text.TextUtils;

import com.example.vera.coolweathers.db.City;
import com.example.vera.coolweathers.db.County;
import com.example.vera.coolweathers.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Vera on 2018/1/9/0009.
 */


public class Utility {
    /**
     * 解析和处理服务器返回的省级数据.
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response.trim())){
            try{
                JSONArray allProvince=new JSONArray(response);
                for(int i=0;i<allProvince.length();i++){
                    JSONObject provinceObject=allProvince.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response,int provinceId){
        /**
         * 解析和处理服务器返回的市级数据
         */
        if (!TextUtils.isEmpty(response.trim())){
            try{
                JSONArray allCity=new JSONArray(response);
                for(int i=0;i<allCity.length();i++){
                    JSONObject cityObject=allCity.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return  true;
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return  false;
    }
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response.trim())){
            try{
                JSONArray allCounty=new JSONArray(response);
                for(int i=0;i<allCounty.length();i++){
                    JSONObject countyObject=allCounty.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return  true;
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return  false;
    }

}

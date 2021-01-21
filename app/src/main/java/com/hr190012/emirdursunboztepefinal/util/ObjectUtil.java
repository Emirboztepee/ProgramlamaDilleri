package com.hr190012.emirdursunboztepefinal.util;

import com.google.gson.Gson;
import com.hr190012.emirdursunboztepefinal.model.ProgramlamaModel;


public class ObjectUtil {

    //jsondaki verileri model sınıfına gönderir.
    public  static String dilToJsonString(ProgramlamaModel programlamaModel)
    {
        Gson gson = new Gson();
        return   gson.toJson(programlamaModel);
    }

    public  static ProgramlamaModel jsonStringToDil(String jsonString)
    {
        Gson gson = new Gson();
        return  gson.fromJson(jsonString,ProgramlamaModel.class);
    }
}

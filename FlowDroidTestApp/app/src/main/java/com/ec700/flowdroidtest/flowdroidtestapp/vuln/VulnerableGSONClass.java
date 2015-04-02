package com.ec700.flowdroidtest.flowdroidtestapp.vuln;

import android.telephony.gsm.GsmCellLocation;

import com.google.gson.Gson;

/**
 * Created by low-poly-Life on 3/26/2015.
 */
public class VulnerableGSONClass {
    public String s;

    public VulnerableGSONClass(){
        Gson gson = new Gson();
        int x = gson.fromJson("1", int.class); //cannot use fromJson, uses reflection
        String sink = gson.toJson(x);
    }
}

package com.hashmapinc.tempus.witsml.DrillTest.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class Util {
    public static JSONObject merge(JSONObject oldWell, JSONObject newWell) {
        Iterator<String> keys = newWell.keys();
        Object obj1, obj2;
        while (keys.hasNext()) {
            String next = keys.next();
            if (newWell.isNull(next)) {
                continue;
            }

            obj1 = oldWell.get(next);

            if (!oldWell.has(next)) {
                oldWell.putOpt(next, obj1);
            }

            obj2 = newWell.get(next);

            if (obj1 instanceof JSONObject && obj2 instanceof JSONObject) {
                merge((JSONObject) obj1, (JSONObject) obj2);
            } else if (obj1 instanceof JSONArray && obj2 instanceof JSONArray) {
                JSONArray arr1 = (JSONArray)obj1;
                JSONArray arr2 = (JSONArray) obj2;
                if (arr2.length() == 0) continue;
                for (int i = 0; i < arr2.length(); i++) {
                    arr1.put(arr2.getJSONObject(i));
                }
            } else if (obj1 instanceof String && obj2 instanceof String){
                if (obj1.equals("") && !obj2.equals("")){
                    oldWell.put(next,obj2);
                }
            } else if (obj1 instanceof Float && obj2 instanceof Float) {
                if (obj1.equals(0.0) && !obj2.equals(0.0)) {
                    oldWell.put(next, obj2);
                }
            }
        }
        return oldWell;
    }
}

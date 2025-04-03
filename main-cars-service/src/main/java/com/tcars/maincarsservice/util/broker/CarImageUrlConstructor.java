package com.tcars.maincarsservice.util.broker;

import java.util.List;

public class CarImageUrlConstructor {

    private static final String COMMA = ",";

    //todo check about static
    public static String constructUrl(List<String> urls) {
        StringBuilder macroUrl = new StringBuilder();
        urls.forEach(url -> macroUrl.append(url).append(COMMA));
        return macroUrl.substring(0, macroUrl.length());
    }

}

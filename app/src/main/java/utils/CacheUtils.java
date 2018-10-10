package utils;

import android.content.Context;

public class CacheUtils {
    /**
     * @param url
     * @param json
     * url AS key
     *  json AS value
     */
    public static void setCache(String url, String json, Context ctx) {
        PrefUtils.setString(ctx, url, json);
    }

    public static String getCache(String url, Context ctx) {
        String value = PrefUtils.getString(ctx, url, null);

        return value;
    }
}

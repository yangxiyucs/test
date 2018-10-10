package utils;

        import android.content.Context;
        import android.content.SharedPreferences;

public class PrefUtils {
    public static boolean getBoolean(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return  sp.getBoolean(key,value);
    }
    public static void setBoolean(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();

    }

    public static String getString(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return  sp.getString(key,value);

    }

    public static void setString(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();

    }
}

package com.example.zhbj.domain;

import java.util.ArrayList;

/**
 * using Gson format, skill
 * 1, {}  class, [] ArrayList
 * <p>
 * 2.keep the name the same
 */
public class NewsMenu {
    public int returnTo;
    public ArrayList<Integer> extend;
    public ArrayList<NewsMenuData> data;

    //side menu obj
    public class NewsMenuData {
        public int id;
        public String title;
        public int type;
        public ArrayList<NewsTabData> Children;

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "title='" + title + '\'' +
                    ", Children=" + Children +
                    '}';
        }
    }

    //tab obj
    public class NewsTabData {
        public int id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "NewsTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsMenu{" +
                "data=" + data +
                '}';
    }
}

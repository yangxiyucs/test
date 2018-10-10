package com.example.zhbj.domain;

import java.util.ArrayList;

import zhbj.base.imple.menu.TopicMenuDetailPager;

/**
 * ҳǩ�������ݶ���
 * 
 * @author Administrator
 * 
 * 
 */

public class NewsTabBean {

	public NewsTab data;

	public class NewsTab {
		public String more;
		public ArrayList<NewsData> news;
		public ArrayList<TopNews> topnews;
	}

	// �����б����
	public class NewsData {
		public int id;
		public String listimage;
		public String pubdata;
		public String title;
		public String type;
		public String url;

	}

	// ͷ������
	public class TopNews {
		public int id;
		public String topimage;
		public String pubdata;
		public String title;
		public String type;
		public String url;

	}
}

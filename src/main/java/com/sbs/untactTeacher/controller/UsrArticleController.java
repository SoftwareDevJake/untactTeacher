package com.sbs.untactTeacher.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untactTeacher.Util;
import com.sbs.untactTeacher.dto.Article;

@Controller
public class UsrArticleController {
	
	private int articlesLastId;
	private List<Article> articles;
	private Util ct = new Util();
	private String currentDate = ct.today();
	private String updateDate = null;
	
	public UsrArticleController() {
		articles = new ArrayList<>();
		// 멤버변수 초기화
		articlesLastId =  0;
		
		// 게시물 2개 생성
		articles.add(new Article(++articlesLastId, currentDate, "제목1", "내용1", updateDate));
		articles.add(new Article(++articlesLastId, currentDate, "제목2", "내용2", updateDate));
	}
	
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		return articles.get(id - 1);
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;			
	}
	
	@RequestMapping("/usr/article/list2")
	@ResponseBody
	public List<Article> showList(String searchKeyword) {
		
		ArrayList<Article> searchedArticles = searchArticles(searchKeyword);
		return searchedArticles;
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String title, String body)
	{
		
		articles.add(new Article(++articlesLastId, currentDate, title, body, updateDate));
		
		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공하였습니다.");
		rs.put("articleId", articlesLastId);
		
		return rs;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(Integer aid)
	{
		boolean deleteArticleRs = deleteArticle(aid);
		
		Map<String, Object> rs = new HashMap<>();
		if(deleteArticleRs)
		{
			rs.put("msg", "삭제되었습니다.");
			rs.put("aid", aid);
		}
		else
		{
			rs.put("msg", "헤당 게시물이 없습니다.");
		}
		
		return rs;
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Map<String, Object> doModify(Integer id, String title, String body)
	{
		Article selectedArticle = null;
		
		for( Article article : articles)
		{
			if(article.getId() == id)
			{
				selectedArticle = article;
				break;
			}
		}
		
		Map<String, Object> rs = new HashMap<>();
		
		if(selectedArticle == null)
		{
			rs.put("resultCode", "F-1");
			rs.put("msg", String.format("%d번 게시물은 존재하지 않습니다.", id));
			return rs;
		}
		
		selectedArticle.setUpdateDate(Util.today());
		selectedArticle.setTitle(title);
		selectedArticle.setBody(body);
		
		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d번 게시물이 수정 되었습니다..", id));
		
		return rs;
	}
	
	
	private boolean deleteArticle(Integer aid) {
		
		for(Article article : articles)
		{
			if(article.getId() == aid)
			{
				articles.remove(article);
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<Article> searchArticles(String searchKeyword)
	{
		ArrayList<Article> searchedArticles = new ArrayList<>();
		
		for(Article article : articles)
		{
			if(article.getTitle().contains(searchKeyword))
			{
				searchedArticles.add(article);
			}
		}
		
		return searchedArticles;
		
	}
	
}

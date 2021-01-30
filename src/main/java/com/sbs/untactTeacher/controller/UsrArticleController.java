package com.sbs.untactTeacher.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untactTeacher.dto.Article;

@Controller
public class UsrArticleController {
	private int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		articles = new ArrayList<>();
		// 멤버변수 초기화
		articlesLastId =  0;
		
		// 게시물 2개 생성
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "제목2", "내용2"));
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
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String regDate, String title, String body)
	{
		articles.add(new Article(++articlesLastId, regDate, title, body));
		
		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공하였습니다.");
		rs.put("articleId", articlesLastId);
		
		return rs;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(int aid)
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
	public Map<String, Object> doModify(int id, String title, String body)
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
		
		selectedArticle.setTitle(title);
		selectedArticle.setBody(body);
		
		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d번 게시물이 수정 되었습니다..", id));
		
		return rs;
		
	}
	
	
	private boolean deleteArticle(int aid) {
		
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
	
}

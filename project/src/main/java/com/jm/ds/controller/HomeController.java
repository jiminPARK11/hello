package com.jm.ds.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jm.ds.model.DeptModel;
import com.jm.ds.model.ProjectModel;
import com.jm.ds.model.UserModel;
import com.jm.ds.model.UserVo;
import com.jm.ds.service.ProjectService;

@Controller
public class HomeController {
	
	@Autowired
	ProjectService projectService;
	
	String[] nameList = {""};
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView goHome(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		nameList[0] = "";
		List<ProjectModel> projectList = projectService.getProject();
//		List<DeptModel> deptList = projectService.getDept();
		mav.addObject("projectList", projectList);
//		mav.addObject("deptList", deptList);
		mav.setViewName("content/home");
		
		return mav;
	}
	
	@RequestMapping(value = "/home/search", method = RequestMethod.GET)
	public ModelAndView goSearch(HttpServletRequest request, ModelAndView mav) {
		
		String user_nm = request.getParameter("user_nm");
		nameList[0] = user_nm;
		List<ProjectModel> projectList = projectService.search(user_nm);
		
		mav.addObject("projectList", projectList);
		mav.setViewName("content/home");
		
		return mav;
	}
	
	@RequestMapping(value = "/home/view{id}", method = RequestMethod.GET)
	public ModelAndView goView(HttpServletRequest request, ModelAndView mav) {
		//System.out.println("컨트롤러 : " + request.getParameter("id"));
		System.out.println("이름값을 가지고있는가?? " + nameList[0]);
		List<ProjectModel> projectList = projectService.search(nameList[0]);
		mav.addObject("projectList", projectList);
			
		
		String user_id = request.getParameter("id");
		List<ProjectModel> projectEmpList = projectService.view(user_id);
		String hobby = projectService.viewHobby(user_id);
		String[] hobbyList = null;
		if (hobby == null) {
			System.out.println("취미없음");
		} else {
			hobbyList = hobby.split(",");
		}
		mav.addObject("hobby", hobbyList);
		mav.addObject("projectEmpList", projectEmpList);
		mav.setViewName("content/view");
		return mav;
	}
	
	// 데이터 수신
	// 데이터는 METHOD는 GET/POST 가 있음 
	// GET : url query string 데이터 전송 (데이터 url 노출)
	// 예) http://www.url.com/home/insert?id=id&passs=pass
	// POST : 바디 데이터 전송방식
	// 예) http://www.url.com/home/insert (데이터 url 비노출)
	// 이와 같은 내용 때문에 조회 성 접근을 제외한 CUD는 주로 POST 사용합니다.
	// 데이터 수신은 갞체로 받을 수 있습니다.
	@RequestMapping(value = "/home/insert", method = RequestMethod.POST)
	public ModelAndView goInsert(HttpServletRequest request, UserVo user) {
		//System.out.println("이름:" + user.getUser_nm());
		Integer insert = projectService.insert(user);
		//redirect:[경로(html 파일 경로 X)]
		return new ModelAndView("redirect:/home");
	}
	
	// update
	@RequestMapping(value = "/home/update", method = RequestMethod.POST)
	public ModelAndView goUpdate(HttpServletRequest request, UserVo user) {
		//System.out.println("확인 : " + user.getHobby_cd());
		
		Integer update = projectService.update(user);
		return new ModelAndView("redirect:/home");
	}
	
	// delete
	@RequestMapping(value = "/home/delete", method = RequestMethod.POST)
	public ModelAndView goDelete(HttpServletRequest request, String user_id) {
		Integer delete = projectService.delete(user_id);
		return new ModelAndView("redirect:/home");
	}
	
	
}

package com.jm.ds.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jm.ds.mapper.ProjectMapper;
import com.jm.ds.model.DeptModel;
import com.jm.ds.model.ProjectModel;
import com.jm.ds.model.UserModel;
import com.jm.ds.model.UserVo;

@Service
public class ProjectService {
	@Autowired
	public ProjectMapper mapper;

	// 첫화면, id 이름 부서 조회 
	public List<ProjectModel> getProject(){
		return mapper.getProject();
	}
	// 이름 검색
	public List<ProjectModel> search(String user_nm){
		return mapper.search(user_nm);
	}
	// 상세조회
	public List<ProjectModel> view(String user_id){
		return mapper.view(user_id);
	}
	// 상세조회 - 취미
	public String viewHobby(String user_id) {
		return mapper.getHobby(user_id);
	}
	
	//insert
	public Integer insert(UserVo user){
		return mapper.insertUser(user);
	}
	//update
	public Integer update(UserVo user){
		mapper.deleteHobby(user.getUser_id());
		if (user.getHobby_cd() == null) {
			System.out.println("wow");
		} else {
			String[] hobbyList = user.getHobby_cd().split(",");
			for (int i = 0; i < hobbyList.length; i++) {
				UserVo vo = new UserVo();
				vo.setUser_id(user.getUser_id());
				vo.setHobby_cd(hobbyList[i]);
				mapper.insertHobby(vo);
			}
		}
		
		return mapper.updateUser(user);
	}
	//delete
	public Integer delete(String user_id) {
		mapper.deleteHobby(user_id);
		return mapper.deleteUser(user_id);
	}
//	// delete취미
//	public Integer deleteHobby(String user_id) {
//		return mapper.deleteHobby(user_id);
//	}
	
	
}
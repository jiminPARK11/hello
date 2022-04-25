package com.jm.ds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jm.ds.model.DeptModel;
import com.jm.ds.model.ProjectModel;
import com.jm.ds.model.UserModel;
import com.jm.ds.model.UserVo;

@Repository
@Mapper
public interface ProjectMapper {
	List<ProjectModel> getProject();
	List<ProjectModel> search(String user_nm);
	List<ProjectModel> view(String user_id);
//	List<DeptModel> getDept();
	//List<UserModel> insert(String id, String name, String tel, String addr);
	Integer insertUser(UserVo user);
	Integer updateUser(UserVo user);
	Integer deleteUser(String user_id);
	Integer deleteHobby(String user_id);
	Integer insertHobby(UserVo user);
	String getHobby(String user_id);
}

package com.jm.ds.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
	private String user_id;
	private String user_nm;
	private String dept_cd;
	private String telno;
	private String user_addr;
	private String hobby_cd;
}

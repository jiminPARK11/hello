package com.jm.ds.model;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class UserModel {
	private String user_id;
	private String user_nm;
	private String dept_nm;
	private String telno;
	private String user_addr;

}

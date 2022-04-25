package com.jm.ds.model;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class DeptModel {
	private String dept_cd;
	private String up_dept_cd;
	private String dept_nm;
}

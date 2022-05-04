package com.asia.leadsgen.test.model;

import java.util.Set;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserInfo {
	private String userId;
	private String aspRefId;
	private boolean isOwner;
	private Long exp;
	private String email;
	private Set<String> domains;
	private Set<String> globalPermissions;
	private Set<String> modulePermissions;
}

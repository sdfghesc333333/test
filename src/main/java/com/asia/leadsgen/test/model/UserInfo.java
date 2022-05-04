package com.asia.leadsgen.test.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class UserInfo {
	private String userId;
	private String aspRefId1;
	private boolean isOwner1;
	private Long exp1;
	private String email1;
	private Set<String> domains;
	private Set<String> globalPermissions;
	private Set<String> modulePermissions;
}

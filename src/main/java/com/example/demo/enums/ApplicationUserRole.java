package com.example.demo.enums;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.example.demo.enums.ApplicationUserPermission.*;

import com.google.common.collect.Sets;
public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet(EMPLOYEE_READ)),
	ADMIN(Sets.newHashSet(EMPLOYEE_READ,EMPLOYEE_WRITE)),
	ADMININTERN(Sets.newHashSet(EMPLOYEE_READ,EMPLOYEE_WRITE));
	
	
private final Set<ApplicationUserPermission> permission;

private ApplicationUserRole(Set<ApplicationUserPermission> permission) {
	this.permission = permission;
}

public Set<ApplicationUserPermission> getPermission() {
	return permission;
}

public Set<SimpleGrantedAuthority>  grantedAuthorities(){
	
	                Set<SimpleGrantedAuthority> permissions = getPermission().stream()
	                       .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
	                       .collect(Collectors.toSet());
	                
	                permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	                
	                return permissions;
	
}


	
	
}

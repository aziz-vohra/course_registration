package com.system.springboot.dao;

import org.springframework.stereotype.Component;

import com.system.springboot.model.Admin;

@Component
public class AdminDao extends DAO{
	 public void saveAdmin(Admin admin){ 
	        beginTransaction();
	        getSession().save(admin);
	        commitTransaction();
	    }
}

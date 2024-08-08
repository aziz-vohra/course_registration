package com.system.springboot.dao;

import org.springframework.stereotype.Component;

import com.system.springboot.model.User;

import jakarta.persistence.Query;

@Component
public class UserDao extends DAO{
	 public void saveUser(User user){ 
	        beginTransaction();
	        getSession().save(user);
	        commitTransaction();
	    }
	 public User getUserByEmailAndPasswordAndRole(String email, String password, String role) {
	        try {
	            beginTransaction();
	            Query query = getSession().createQuery(
	                    "FROM User WHERE email = :email AND password = :password AND role = :role",
	                    User.class
	            );
	            query.setParameter("email", email);
	            query.setParameter("password", password);
	            query.setParameter("role", role);
	            User user = (User) query.getSingleResult();
	            commitTransaction();
	            return user;
	        } catch (Exception e) {
	            rollbackTransaction();
	            throw e;
	        } finally {
	            closeSession();
	        }
	    }
}

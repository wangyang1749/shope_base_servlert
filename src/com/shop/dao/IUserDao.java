package com.shop.dao;


import com.shop.model.Page;
import com.shop.model.User;

public interface IUserDao {
	User add(User user);
	void delete(int id);
	User update(int id,User user);
	User findById(int id) ;
	Page<User> page(int pageSize, int page,String keyWords);


	User login(String username,String password);
	User findByUsername(String username) ;
}

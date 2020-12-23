package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.Page;
import com.shop.model.User;
import com.shop.utils.JDBCUtil;
import com.shop.utils.UserException;

public class UserDaoImpl implements IUserDao {

	@Override
	public User login(String username,String password) {
		User findUser = findByUsername(username);
		if(findUser==null) {
			throw new UserException("用户不存在！");
		}
		if(!findUser.getPassword().equals(password)) {
			throw new UserException("用户名或者密码错误！");
		}
		return findUser;
	}

	@Override
	public User add(User user) {
		Connection con = null;
		PreparedStatement ps=null;
		user.setType(0);
		String sql="INSERT INTO `user` "
				+ "( `username`, `password`, `phone`, `address`,`type`) "
				+ "VALUES ( ?, ?, ?, ?,?); ";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getAddress());
			ps.setInt(5, user.getType());
			ps.executeUpdate();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closePreparedStatement(ps);
			JDBCUtil.closeConnection(con);
		}
		return null;
	}
	

	@Override
	public User findByUsername(String username) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT * FROM user where username = ?";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs =ps.executeQuery();
			User user = new User();
			rs.next();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
			user.setType(rs.getInt("type"));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.closePreparedStatement(ps);
			JDBCUtil.closeConnection(con);
		}
		return null;
	}

	@Override
	public User update(int id,User user) {
		Connection con = null;
		PreparedStatement ps=null;
		String sql="UPDATE `user` "
				+ "SET `username`=?, `password`=?, `phone`=?, `address`=?,`type`=?"
				+ " WHERE `id`=?";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getAddress());
			ps.setInt(5, user.getType());
			ps.setInt(6, user.getId());

			ps.executeUpdate();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closePreparedStatement(ps);
			JDBCUtil.closeConnection(con);
		}
		return null;
	}

	@Override
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps=null;
		String sql="DELETE FROM `user` WHERE id=?";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closePreparedStatement(ps);
			JDBCUtil.closeConnection(con);
		}

	}



	@Override
	public User findById(int id) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT * FROM user where id = ?";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs =ps.executeQuery();
			User user = new User();
			rs.next();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
			user.setType(rs.getInt("type"));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.closePreparedStatement(ps);
			JDBCUtil.closeConnection(con);
		}
		return null;
	}

	@Override
	public Page<User> page(int pageSize,int page,String keyWords){
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Page<User> pages = new Page<User>();
		List<User> list = new ArrayList<User>();
		String sql1="SELECT * from user ";
		if(keyWords!=null){
			sql1+=" username like '%"+keyWords+"%'";
		}
		sql1 +="ORDER BY id DESC LIMIT ?,?";
		String sql2 = "SELECT  COUNT(*) as count from user";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql1);
			ps.setInt(1, (page-1)*pageSize);
			ps.setInt(2, pageSize);
			rs =ps.executeQuery();

			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				list.add(user);
			}
			ps = con.prepareStatement(sql2);
			rs= ps.executeQuery();
			rs.next();
			int count = rs.getInt("count");
			int p;
			if(count%pageSize==0) {
				p=count/pageSize;
			}else {
				p=count/pageSize+1;
			}

			pages.setTotalSize(count);
			pages.setCurrentPage(page);
			pages.setList(list);
			pages.setPageSize(p);
			return pages;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.closePreparedStatement(ps);
			JDBCUtil.closeConnection(con);
		}
		return null;
	}
	
	
	
	
}

package com.shop.dao;

import com.shop.model.Goods;
import com.shop.model.Page;
import com.shop.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements IGoodsDao {

// `name` varchar(45) NOT NULL,
//  `description` varchar(45) NOT NULL,
//  `price` double NOT NULL,
//	`count` int(11) NOT NULL,
//  `img` varchar(45) NOT NULL,
//  `createTime` varchar(45) NOT NULL,

	@Override
	public Goods add(Goods goods) {
		Connection con = null;
		PreparedStatement ps=null;
		goods.setCreateTime(new java.util.Date());
		String sql="INSERT INTO `goods` "
				+ "( `name`, `description`, `price`, `count`,`img`,`createTime`) "
				+ "VALUES ( ?, ?, ?, ?, ?,?); ";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, goods.getName());
			ps.setString(2, goods.getDescription());
			ps.setDouble(3, goods.getPrice());
			ps.setInt(4, goods.getCount());
			ps.setString(5, goods.getImg());
			ps.setDate(6,new Date(goods.getCreateTime().getTime()));
			ps.executeUpdate();
			return goods;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closePreparedStatement(ps);
			JDBCUtil.closeConnection(con);
		}
		return null;
	}
	

	@Override
	public Goods update(int id,Goods goods) {
		Connection con = null;
		PreparedStatement ps=null;
		String sql="UPDATE `goods` "
				+ "SET `name`=?, `description`=?, `price`=?, `count`=? ,`img`=?"
				+ " WHERE `id`=?";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, goods.getName());
			ps.setString(2, goods.getDescription());
			ps.setDouble(3, goods.getPrice());
			ps.setInt(4, goods.getCount());
			ps.setString(5, goods.getImg());
			ps.setInt(6, goods.getId());
			ps.executeUpdate();
			return goods;
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
		String sql="DELETE FROM `goods` WHERE id=?";
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
	public Goods findById(int id) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT * FROM goods where id = ?";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs =ps.executeQuery();
			Goods goods = new Goods();
			rs.next();
			goods.setId(rs.getInt("id"));
			goods.setName(rs.getString("name"));
			goods.setDescription(rs.getString("description"));
			goods.setPrice(rs.getDouble("price"));
			goods.setCount(rs.getInt("count"));
			goods.setImg(rs.getString("img"));
			goods.setCreateTime(rs.getDate("createTime"));
			return goods;
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
	public Page<Goods> page(int pageSize,int page,String keyWords){
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Page<Goods> pages = new Page<Goods>();
		List<Goods> list = new ArrayList<Goods>();

//		SELECT * from goods where name like "%ddd%" ORDER BY id DESC LIMIT 0,2;
		String sql1="SELECT * from goods ";
		if(keyWords!=null){
			sql1+="where  name like '%"+keyWords+"%' ";
		}
		sql1 +="ORDER BY id DESC LIMIT ?,?";
		String sql2 = "SELECT  COUNT(*) as count from goods";
		try {
			con = JDBCUtil.getInstance().getConnection();
			ps = con.prepareStatement(sql1);
			if (page<=0)
				page=1;
			ps.setInt(1, (page-1)*pageSize);
			ps.setInt(2, pageSize);
			rs =ps.executeQuery();

			while(rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setDescription(rs.getString("description"));
				goods.setPrice(rs.getDouble("price"));
				goods.setCount(rs.getInt("count"));
				goods.setImg(rs.getString("img"));
				goods.setCreateTime(rs.getDate("createTime"));
				list.add(goods);
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

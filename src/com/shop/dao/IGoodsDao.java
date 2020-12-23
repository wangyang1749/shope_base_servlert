package com.shop.dao;


import com.shop.model.Page;
import com.shop.model.Goods;

public interface IGoodsDao {
	Goods add(Goods goods);
	void delete(int id);
	Goods update(int id, Goods goods);
	Goods findById(int id) ;
	Page<Goods> page(int pageSize, int page,String keyWords);
}

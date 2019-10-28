package com.alex_mono.service;

import java.util.List;

import com.alex_mono.domain.Order;
import com.alex_mono.domain.PageBean;

public interface OrderService {

	void save(Order order)throws Exception;

	PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid)throws Exception;

	Order getById(String oid)throws Exception;

	void update(Order order)throws Exception;

	List<Order> findAllByState(String state)throws Exception;

}

package com.alex_mono.dao;

import java.util.List;

import com.alex_mono.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void save(Category c) throws Exception;

}

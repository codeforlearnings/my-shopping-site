package net.pankaj.shoppingbackend.dao;

import java.util.List;

import net.pankaj.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
List<Category> list();
Category get(int id);
boolean add(Category category);


}

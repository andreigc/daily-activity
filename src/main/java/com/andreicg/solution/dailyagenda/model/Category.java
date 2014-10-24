package com.andreicg.solution.dailyagenda.model;

import java.util.ArrayList;
import java.util.List;

import com.andreicg.solution.dailyagenda.json.CategoryJson;

public class Category {

    private int id;
    private String categoryName;
    private String description;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getCategoryName() {
	return categoryName;
    }

    public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public static CategoryJson categoryToCategoryJson(Category cat) {
	CategoryJson catJson = new CategoryJson();
	catJson.setDescription(cat.getDescription());
	catJson.setName(cat.getCategoryName());
	catJson.setId(cat.getId());
	return catJson;
    }

    public static List<CategoryJson> categoryListToCategoryJsonList(
	    List<Category> categories) {
	List<CategoryJson> list = new ArrayList<CategoryJson>();
	for (Category cat : categories) {
	    list.add(categoryToCategoryJson(cat));
	}
	return list;
    }

}

package com.andreicg.solution.dailyagenda.restws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.andreicg.solution.dailyagenda.json.CategoryJson;
import com.andreicg.solution.dailyagenda.model.Category;
import com.andreicg.solution.dailyagenda.model.CategoryDAO;

@Path("/categories")
public class CategoryResource {

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryJson> getCategories(){
	List<Category> categories = CategoryDAO.getAllCategories();
	return Category.categoryListToCategoryJsonList(categories);
    }
 }

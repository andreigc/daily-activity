package com.andreicg.solution.dailyagenda.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private static Connection conn;

    private static Connection getConnection() throws SQLException {
	if (conn == null || conn.isClosed()) {
	    conn = ConnectionFactory.getInstance().getConnection();
	}
	return conn;
    }

    private static List<Category> executeCategorySelectQuery(
	    PreparedStatement prepStatement) throws SQLException {
	ResultSet resultSet = prepStatement.executeQuery();
	List<Category> categories = new ArrayList<Category>();
	// construct category list from query result
	while (resultSet.next()) {
	    int id = resultSet.getInt("ID");
	    String categoryName = resultSet.getString("CategoryName");
	    String description = resultSet.getString("Description");

	    Category category = new Category();
	    category.setId(id);
	    category.setDescription(description);
	    category.setCategoryName(categoryName);

	    categories.add(category);
	}
	return categories;
    }
    
    public static Category getCategory(int categoryId){
	String queryString = "SELECT * from public.\"Category\" WHERE \"ID\" = ?";
	try{
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection.prepareStatement(queryString);
	    prepStatement.setInt(1, categoryId);
	    List<Category> categories = executeCategorySelectQuery(prepStatement);
	    if(categories.size()==1){
		return categories.get(0);
	    }
	}catch(SQLException e){
	    e.printStackTrace();
	}
	return null;
    }
    
    public static List<Category> getAllCategories(){
	String queryString = "SELECT * from public.\"Category\"";
	try{
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection.prepareStatement(queryString);
	    List<Category> categories = executeCategorySelectQuery(prepStatement);
	    return categories;
	}catch(SQLException e){
	    e.printStackTrace();
	}
	return null;
    }
    
}

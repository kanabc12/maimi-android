package org.hxy.platform.android.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 无风
 *
 */
public class HomeCategory implements IModel, Serializable{
	
	private int id ;				//分类ID
	private String name ;			//分类名称
	private List<Product> goodsList ;			//该分类下的商品
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Product> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Product> parentId) {
		this.goodsList = parentId;
	}
	
	@Override
	public String[] replaceKeyFromPropertyName() {
		return new String[]{
				"goodsList" , "goods_list",
		};
	}


}

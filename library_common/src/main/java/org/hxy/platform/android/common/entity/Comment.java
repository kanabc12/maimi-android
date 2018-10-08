package org.hxy.platform.android.common.entity;

/**
 * @author 飞龙
 *
 */
public class Comment implements IModel {

	private String commentID;	//商品评论
	private String goodsID;		//商品ID
	private String username;
	private String email;
	private int rank;			//星级
	private int parentID;		//父评论ID
	private int userID;			//用户ID
	private String addTime;		//评论时间
	
	@Override
	public String[] replaceKeyFromPropertyName() {
		return new String[]{
				"commentID" , "comment_id",
				"goodsID" , "goods_id",
				"addTime" , "add_time"
		};
	}

}

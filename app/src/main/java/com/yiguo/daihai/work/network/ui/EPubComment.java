package com.yiguo.daihai.work.network.ui;

import java.util.ArrayList;

/**
 * 发表评论的实体类
 * @author daihai
 *
 */
public class EPubComment {
	private String commodifyImageUrl;
	/**
	 * 被评论的商品名称
	 */
	private String commodityName;
	/**
	 * 被评论的商品价格
	 */
	private String commodityPrice;
	/**
	 * 被评论的商品数量
	 */
	private String commodityCount;
	/**
	 * 被评论的商品小图片URL地址
	 */
	private ArrayList<String> commentImages = new ArrayList<String>();
	/**
	 * 被评论的商品大图片URL地址
	 */
	private ArrayList<String> commentBigImages = new ArrayList<String>();
	/**
	 * 商品是否可评论
	 */
	private boolean isPublishable = true;
	/**
	 * 评论的内容
	 */
	private String commentText;
	/**
	 * 评论的等级（星级）
	 */
	private float commentLevel;
	/**
	 * 评论的等级文本
	 */
	private String commentLevelText;
	
	
	public String getCommodifyImageUrl() {
		return commodifyImageUrl;
	}
	public void setCommodifyImageUrl(String commodifyImageUrl) {
		this.commodifyImageUrl = commodifyImageUrl;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityPrice() {
		return commodityPrice;
	}
	public void setCommodityPrice(String commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	public String getCommodityCount() {
		return commodityCount;
	}
	public void setCommodityCount(String commodityCount) {
		this.commodityCount = commodityCount;
	}
	public ArrayList<String> getCommentImages() {
		return commentImages;
	}
	public void setCommentImages(ArrayList<String> commentImages) {
		this.commentImages.addAll(commentImages);
	}
	public ArrayList<String> getCommentBigImages() {
		return commentBigImages;
	}
	public void setCommentBigImages(ArrayList<String> commentBigImages) {
		this.commentBigImages.addAll(commentBigImages);
	}
	public boolean isPublishable() {
		return isPublishable;
	}
	public void setPublishable(boolean isPublishable) {
		this.isPublishable = isPublishable;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public float getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(float commentLevel) {
		this.commentLevel = commentLevel;
	}
	public String getCommentLevelText() {
		return commentLevelText;
	}
	public void setCommentLevelText(String commentLevelText) {
		this.commentLevelText = commentLevelText;
	}
	
	
	
}

package com.yiguo.daihai.work.network.ui;

import java.util.ArrayList;
import java.util.Arrays;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.adapter.PublishCommentAdapter;


public class UIPublishComment extends Activity{
	//TODO FAKE DATA
	public String[] images = new String[]{
			"http://img02.yiguo.com/e/thumbnails/100/130409/9288674467977865.jpg",
	          "http://img02.yiguo.com/e/thumbnails/100/130416/9288674472303248.jpg",
	          "http://img02.yiguo.com/e/thumbnails/100/130416/9288674472630928.jpg"
	};
	
	public String[] bigImages = new String[]{
			"http://img02.yiguo.com/e/thumbnails/470/130409/9288674467977865.jpg",
	          "http://img02.yiguo.com/e/thumbnails/470/130416/9288674472303248.jpg",
	          "http://img02.yiguo.com/e/thumbnails/470/130416/9288674472630928.jpg"
	};
	
	
	public static final String TASKNAME = "upload";
	
	private String uploadUrl;
	
	private long currentImgName;
	
	RecyclerView recyclerView;
	
	PublishCommentAdapter adapter;
	
	View v_dialog_content;
	
	View v_show_image;
	
	View v_del_image;
	
	View v_camera_image;
	
	View v_album_image;
	
	private View currentModifyView;
	
	private int currentModifyPosition;
	
	private EPubComment currentModifyData;
	
	private int currentModifyImagePosition;
	
	private long currentTaskID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			if (Build.VERSION.SDK_INT >= 11)
				getWindow().setFlags(16777216, 16777216);
		} catch (Exception e) {
		}
		setContentView(R.layout.publish_comment);
		

		initView();
		
	}

	private void clearCurrentItemInfo(){
		currentImgName = -1l;
		currentModifyData = null;
		currentModifyImagePosition = -1;
		currentModifyPosition = -1;
		currentModifyView = null;
	}
	

	
	private void initView() {
		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.getItemAnimator().setAddDuration(400);
		recyclerView.getItemAnimator().setMoveDuration(400);
		recyclerView.getItemAnimator().setRemoveDuration(400);
		recyclerView.getItemAnimator().setChangeDuration(400);
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(adapter = new PublishCommentAdapter(new PublishCommentAdapter.OnClickListener() {
			
			@Override
			public void onPublishClick(View v, int pos, EPubComment data) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onImageClick(View v, int pos, EPubComment data, int imagePos) {

			}
			
			@Override
			public void onModifyImageClick(View v, int pos, EPubComment data, int imagePos) {
				if(imagePos <= data.getCommentImages().size()){
					currentModifyData = data;
					currentModifyPosition = pos;
					currentModifyView = v;
					currentModifyImagePosition = imagePos;
					selectImage((ImageView) v, data.getCommentImages().size() > imagePos);
				}
			}
		}));
		
		
		recyclerView.postDelayed(new Runnable() {

			@Override
			public void run() {
				ArrayList<EPubComment> array = new ArrayList<EPubComment>();
				EPubComment p;
				ArrayList<String> imgs = new ArrayList<String>();
				imgs.addAll(Arrays.asList(images));
				ArrayList<String> bigImgs = new ArrayList<String>();
				bigImgs.addAll(Arrays.asList(bigImages));
				for (int i = 0; i < 20; i++) {
					p = new EPubComment();
					p.setCommodifyImageUrl("http://172.17.1.106:8889/img/1411061047011966.jpg");
					if (i % 2 == 0) {
						p.setCommodityCount("1");
						p.setCommodityName("新西兰金奇异果 12枚");
						p.setCommodityPrice("128");
						p.setPublishable(true);
//						if(i == 0){
						p.setCommentImages(imgs);
						p.setCommentBigImages(bigImgs);
//						}
					} else {
						if (i % 3 == 0) {
							p.setCommentImages(imgs);
							p.setCommentBigImages(bigImgs);
						}
						p.setCommodityCount("" + i);
						p.setCommodityName("新西兰金奇异果 12枚");
						p.setCommodityPrice("128");
						p.setCommentLevel(4.0f);
						p.setCommentLevelText("4分-比较满意");
						p.setCommentText("没有上次的好，但是也还行。");
						p.setPublishable(false);
					}
					array.add(p);
				}
				adapter.setData(array);
			}
		}, 200);
		
	}
	

	
	private void selectImage(ImageView img, boolean showDel)
	{
		

	}
	


}

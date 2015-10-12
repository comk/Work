package com.yiguo.daihai.work.network.adapter;



import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.ui.EPubComment;

import java.util.ArrayList;

public class PublishCommentAdapter extends RecyclerView.Adapter<PublishCommentAdapter.PublishCommentViewHolder> implements Runnable{

	private OnClickListener mListener;
	
	private int currentExpandItem = 0;
	
	private int currentExpandItemHeight = 0;
	
	private ArrayList<EPubComment> data = new ArrayList<EPubComment>();
	
	View.OnClickListener imageClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(v.getTag() == null && !(v.getTag() instanceof Integer))
				return;
			int pos = (Integer) v.getTag();
			if(mListener != null)
				mListener.onImageClick(v, pos, data.get(pos), (Integer)v.getTag(R.id.add_image_1));
		}
	};
	
	View.OnClickListener imageModifyListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(v.getTag() == null && !(v.getTag() instanceof Integer))
				return;
			int pos = (Integer) v.getTag();
			if(mListener != null)
				mListener.onModifyImageClick(v, pos, data.get(pos),(Integer)v.getTag(R.id.add_image_1));
		}
	};
	
	public PublishCommentAdapter(OnClickListener listener) {
		mListener = listener;
	}
	
	public void setData(ArrayList<EPubComment> d){
		data.addAll(d);
		notifyItemRangeInserted(0, d.size());
	}
	
	@Override
	public int getItemCount() {
		return data.size();
	}
	
	@Override
	public int getItemViewType(int position) {
		return data.get(position).isPublishable() ? 0 : 1;
	}
	
	@Override
	public void onBindViewHolder(final PublishCommentViewHolder holder, int position) {
		if(data.get(position).isPublishable()){
			EPubComment entity = data.get(position);
			

			//清除ImageView中的图片
			holder.iv_1.setImageResource(0);
			holder.iv_2.setImageResource(0);
			holder.iv_3.setImageResource(0);
			
			holder.iv_1.setTag(position);
			holder.iv_2.setTag(position);
			holder.iv_3.setTag(position);
			holder.btn_publish.setTag(position);
			
			//将文本框中的文字、评分记录到数据中
			if(holder.itemView.getTag() != null && holder.itemView.getTag() instanceof Integer){
				int positionOld = (Integer) holder.itemView.getTag();
				data.get(positionOld).setCommentLevel(holder.mRatingBar.getRating());
				data.get(positionOld).setCommentText(holder.et_content.getText().toString().trim());
			}
			
			//设置数据
			holder.productName.setText(entity.getCommodityName());
			holder.productPrice.setText(entity.getCommodityPrice());
			holder.productCount.setText("x"+entity.getCommodityCount());
			holder.et_content.setText(entity.getCommentText());
			holder.tv_rating_level.setText(entity.getCommentLevelText());
			holder.mRatingBar.setRating(entity.getCommentLevel());
			for (int i = 0; i < entity.getCommentImages().size(); i++) {
				if(i == 0){
				}else if(i == 1){
				}else if(i == 2){
				}
			}
			
			//TODO 设置本地的添加图片

			holder.itemView.setTag(position);
			if(currentExpandItem == position){
				if(currentExpandItemHeight == 0){
					holder.v_edit_commnet.post(new Runnable() {
						
						@Override
						public void run() {
							currentExpandItemHeight = holder.v_edit_commnet.getHeight();
						}
					});
				}else{
					holder.v_edit_commnet.getLayoutParams().height = currentExpandItemHeight;
				}
			}else{
				holder.v_edit_commnet.getLayoutParams().height = 0;
			}
		}else{
			//清除ImageView中的图片
			holder.iv_1.setImageResource(0);
			holder.iv_2.setImageResource(0);
			holder.iv_3.setImageResource(0);
			
			//通过设置Tag来移除点击事件
			holder.iv_1.setTag(null);
			holder.iv_2.setTag(null);
			holder.iv_3.setTag(null);
			
			EPubComment entity = data.get(position);
			holder.productName.setText(entity.getCommodityName());
			holder.productPrice.setText(entity.getCommodityPrice());
			holder.productCount.setText("x"+entity.getCommodityCount());
			holder.tv_content_text.setText(entity.getCommentText());
			holder.tv_rating_level.setText(entity.getCommentLevelText());
			holder.mRatingBar.setRating(Float.valueOf(entity.getCommentLevel()));
			if(entity.getCommentImages().size() > 0){
				for (int i = 0; i < entity.getCommentImages().size(); i++) {
					if(i == 0){
						holder.iv_1.setTag(position);
					}else if(i == 1){
						holder.iv_2.setTag(position);
					}else if(i == 2){
						holder.iv_3.setTag(position);
					}
				}
				holder.add_image_layout.setVisibility(View.VISIBLE);
			}else{
				holder.add_image_layout.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public PublishCommentViewHolder onCreateViewHolder(final ViewGroup arg0, int arg1) {
		final PublishCommentViewHolder pcvh = new PublishCommentViewHolder(
				ViewGroup.inflate(arg0.getContext(),arg1 == 0 ? R.layout.publish_comment_item : R.layout.publish_comment_item_finished, null),arg1);
		pcvh.iv_1.setTag(R.id.add_image_1, 0);
		pcvh.iv_2.setTag(R.id.add_image_1, 1);
		pcvh.iv_3.setTag(R.id.add_image_1, 2);
		if(arg1 == 0){
			pcvh.btn_publish.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(v.getTag() == null && !(v.getTag() instanceof Integer))
						return;
					int pos = (Integer) v.getTag();
					if(mListener != null)
						mListener.onPublishClick(v, pos, data.get(pos));

					currentExpandItem = pos + 2;

					notifyItemChanged(currentExpandItem);
					notifyItemChanged(pos);

//					data.get(pos).setPublishable(false);
//					currentExpandItem = -1;
//					notifyItemChanged(pos);
				}
			});
			pcvh.itemView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(v.getTag() == null && !(v.getTag() instanceof Integer))
						return;
					final int pos = (Integer) v.getTag();
					if(pos == currentExpandItem)
						return;

					notifyItemChanged(currentExpandItem);
					notifyItemChanged(pos);
					currentExpandItem = pos;
				}
			});
			pcvh.iv_1.setOnClickListener(imageModifyListener);
			pcvh.iv_2.setOnClickListener(imageModifyListener);
			pcvh.iv_3.setOnClickListener(imageModifyListener);
		}else{
			pcvh.iv_1.setOnClickListener(imageClickListener);
			pcvh.iv_2.setOnClickListener(imageClickListener);
			pcvh.iv_3.setOnClickListener(imageClickListener);
		}
		return pcvh;
	}
	

	static class PublishCommentViewHolder extends RecyclerView.ViewHolder{

		public ImageView productImage;
		
		public TextView productCount;
		
		public TextView productPrice;
		
		public TextView productName;
		
		public RatingBar mRatingBar;
		
		public TextView tv_rating_level;
		
		public EditText et_content;
		
		public TextView tv_content_text;
		
		public Button btn_publish;
		
		public View v_edit_commnet;
		
		public View add_image_layout;
		
		public ImageView iv_1;
		
		public ImageView iv_2;
		
		public ImageView iv_3;
		
		public PublishCommentViewHolder(View rootView,int viewType){
			this(rootView);
			if(viewType == 0){//未评论状态
				et_content = (EditText) rootView.findViewById(R.id.publish_comment_item_text);
				btn_publish = (Button) rootView.findViewById(R.id.publish_comment_item_publish);
				v_edit_commnet = rootView.findViewById(R.id.publish_comment_item_edit);
			}else{
			    tv_content_text = (TextView) rootView.findViewById(R.id.publish_comment_item_text);
			    add_image_layout = rootView.findViewById(R.id.add_image_layout);
			}
		}
		
		private PublishCommentViewHolder(View rootView) {
			super(rootView);
			productImage = (ImageView) rootView.findViewById(R.id.imageview_pro_img);
			productName = (TextView) rootView.findViewById(R.id.textview_order_detail_item_name);
			productPrice = (TextView) rootView.findViewById(R.id.textview_order_detail_item_sum);
			productCount = (TextView) rootView.findViewById(R.id.textview_order_detail_item_count);
			mRatingBar = (RatingBar) rootView.findViewById(R.id.publish_comment_item_rating);
			tv_rating_level = (TextView) rootView.findViewById(R.id.publish_comment_item_rating_level);
			iv_1 = (ImageView) rootView.findViewById(R.id.add_image_1);
			iv_2 = (ImageView) rootView.findViewById(R.id.add_image_2);
			iv_3 = (ImageView) rootView.findViewById(R.id.add_image_3);
		}
		
	}
	
	public interface OnClickListener{
		/**
		 * 点击发布评价的操作事件
		 * @param v 点击的View
		 * @param pos 点击的位置
		 * @param data 点击的位置处绑定的数据
		 */
		public void onPublishClick(View v, int pos, EPubComment data);
		/**
		 * 修改图片的点击操作事件
		 * @param v 点击的View
		 * @param pos 点击的位置
		 * @param data 点击位置处绑定的数据
		 */
		public void onModifyImageClick(View v, int pos, EPubComment data, int imagePos);
		/**
		 * 点击图片时的事件
		 * @param v 被点击的View
		 * @param pos 点击的位置
		 * @param data 点击处绑定的数据
		 */
		public void onImageClick(View v, int pos, EPubComment data, int imagePos);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

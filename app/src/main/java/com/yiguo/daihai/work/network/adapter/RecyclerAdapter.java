package com.yiguo.daihai.work.network.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yiguo.daihai.work.R;

import org.w3c.dom.Text;

/**
 * Created by daihai on 2015/5/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomeViewHolder> {

    private int count = 0;

    Context context;

    public static class CustomeViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageVIew;
        public CardView cardView;
        public Button button;
        public CheckBox checkBox;
        public RatingBar ratingBar;
        public ProgressBar progressBar;
        public SeekBar seekBar;
        public RadioButton radioButton;
        public int viewType;
        public CustomeViewHolder(View view, int viewType){
            super(view);
            switch (this.viewType = viewType){
                case 0:
                    textView = (TextView) view;
                    textView.setText("This is "+textView.getClass().getName());
                    textView.setPadding(20, 120, 20, 120);
                    textView.setBackgroundColor(Color.YELLOW);
                    break;
                case 1:
                    imageVIew = (ImageView) view;
                    imageVIew.setImageResource(R.drawable.ic_launcher);
                    imageVIew.setPadding(20, 120, 20, 120);
                    imageVIew.setBackgroundColor(Color.LTGRAY);
                    break;
                case 2:
                    cardView = (CardView) view;
                    cardView.setRadius(20);
                    cardView.setMinimumHeight(200);
                    cardView.setCardBackgroundColor(Color.MAGENTA);
                    TextView tv = new TextView(cardView.getContext());
                    tv.setText("This is "+cardView.getClass().getName());
                    cardView.addView(tv);
                    break;
                case 3:
                    button = (Button) view;
                    button.setPadding(20,120,20,120);
                    button.setText("This is "+button.getClass().getName());
                    button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    checkBox = (CheckBox) view;
                    checkBox.setPadding(20,120,20,120);
                    checkBox.setText("This is "+checkBox.getClass().getName());
                    checkBox.setBackgroundColor(Color.BLUE);
                    break;
                case 5:
                    ratingBar = (RatingBar) view;
                    ratingBar.setMax(5);
                    ratingBar.setPadding(20,120,20,120);
                    ratingBar.setBackgroundColor(Color.CYAN);
                    break;
                case 6:
                    progressBar = (ProgressBar) view;
                    progressBar.setMax(100);
                    progressBar.setPadding(20,120,20,120);
                    progressBar.setBackgroundColor(Color.GREEN);
                    break;
                case 7:
                    seekBar = (SeekBar) view;
                    seekBar.setMax(200);
                    seekBar.setPadding(20,120,20,120);
                    seekBar.setBackgroundColor(Color.DKGRAY);
                    break;
                case 8:
                    radioButton = (RadioButton) view;
                    radioButton.setPadding(20,120,20,120);
                    radioButton.setText("This is "+radioButton.getClass().getName());
                    radioButton.setBackgroundColor(Color.GRAY);
                    break;
            }
        }

        public CustomeViewHolder(View itemView) {
            super(itemView);
        }
    }

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position%9;
    }

    @Override
    public CustomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        count++;
        CustomeViewHolder holder = null;
        switch (viewType){
            case 0:
                holder = new CustomeViewHolder(new TextView(context),viewType);
                break;
            case 1:
                holder = new CustomeViewHolder(new ImageView(context),viewType);
                break;
            case 2:
                holder = new CustomeViewHolder(new CardView(context),viewType);
                break;
            case 3:
                holder = new CustomeViewHolder(new Button(context),viewType);
                break;
            case 4:
                holder = new CustomeViewHolder(new CheckBox(context),viewType);
                break;
            case 5:
                holder = new CustomeViewHolder(new RatingBar(context),viewType);
                break;
            case 6:
                holder = new CustomeViewHolder(new ProgressBar(context),viewType);
                break;
            case 7:
                holder = new CustomeViewHolder(new SeekBar(context),viewType);
                break;
            case 8:
                holder = new CustomeViewHolder(new RadioButton(context),viewType);
                break;
        }
        Log.e("onCreateViewHolder","onCreateViewHolder"+count);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomeViewHolder holder, int position) {
        switch (holder.viewType){
            case 0:
                holder.textView.setText("this is position "+position);
                break;
            case 1:
                break;
            case 2:
                ((TextView)holder.cardView.getChildAt(0)).setText("this is position "+position);
                break;
            case 3:
                holder.button.setText("this is position "+position);
                break;
            case 4:
                holder.checkBox.setText("this is position "+position);
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                holder.radioButton.setText("this is position "+position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 1000;
    }
}


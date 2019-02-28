package com.example.materialtest;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 12191 on 2017/12/18.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{

    private Activity activity;

    private Context mContext;
    private List<Fruit> mFruitLists;

    public FruitAdapter(List<Fruit> fruitLists,Activity activity){
        mFruitLists= fruitLists;
        this.activity= activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext== null){
            mContext= parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_fruit,parent,false);
        final View shared_element = view.findViewById(R.id.fruit_image);
        final ViewHolder holder= new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position= holder.getAdapterPosition();
                Fruit fruit= mFruitLists.get(position);
                Intent intent= new Intent(mContext,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                //过渡动画
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(activity,shared_element,shared_element.getTransitionName());
                mContext.startActivity(intent,options.toBundle());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit= mFruitLists.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView;
            fruitImage= itemView.findViewById(R.id.fruit_image);
            fruitName= itemView.findViewById(R.id.fruit_name);
        }
    }
}

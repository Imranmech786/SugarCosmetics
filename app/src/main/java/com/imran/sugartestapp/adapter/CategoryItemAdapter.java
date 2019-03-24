package com.imran.sugartestapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imran.sugartestapp.R;
import com.imran.sugartestapp.activity.DetailActivity;
import com.imran.sugartestapp.model.CategoryData;
import com.imran.sugartestapp.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {


    private Context mContext;
    private List<CategoryData> mList;
    private CallAsyncApi mCallAsyncApi;

    CategoryItemAdapter(Context context, List<CategoryData> moviesResult, CallAsyncApi callback) {
        mContext = context;
        mList = moviesResult;
        mCallAsyncApi = callback;
    }

    @NonNull
    @Override
    public CategoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemAdapter.ViewHolder holder, int pos) {

        final CategoryData categoryItem = mList.get(pos);
        if (categoryItem.getUrl() != null) {
            Picasso.get().load(Utils.setplaceholder(pos))
                    .placeholder(Utils.setplaceholder(pos))
                    .error(R.color.gray_background)
                    .into(holder.image);
            mCallAsyncApi.callAsyncApi(pos, categoryItem.getUrl());
        } else if (categoryItem.getImage() != null) {
            Picasso.get().load(categoryItem.getImage().getImage())
                    .placeholder(Utils.setplaceholder(pos))
                    .error(Utils.setplaceholder(pos))
                    .into(holder.image);
            holder.title.setText(categoryItem.getTitle());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(mContext.getString(R.string.title), categoryItem.getTitle());
                bundle.putString(mContext.getString(R.string.description), categoryItem.getDescription());
                bundle.putString(mContext.getString(R.string.image), categoryItem.getImage().getImage());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null && !mList.isEmpty() ? mList.size() : 0;
    }

    void update(List<CategoryData> categoryItemList) {
        mList = categoryItemList;
        notifyDataSetChanged();
    }

    public void updateItems(List<CategoryData> subList) {
        mList = subList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;

        private ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }

    public interface CallAsyncApi {

        void callAsyncApi(int childPosiiotn, String url);
    }
}

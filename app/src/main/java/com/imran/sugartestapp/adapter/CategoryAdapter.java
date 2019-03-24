package com.imran.sugartestapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imran.sugartestapp.R;
import com.imran.sugartestapp.model.CategoryData;
import com.imran.sugartestapp.model.CategoryList;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private Context mContext;
    private List<CategoryList> mList;
    private CallAsyncApi mCallAsyncApi;
    public static final String PAYLOAD_UPDATE_PRODUCT = "update_product";

    public CategoryAdapter(Context context, List<CategoryList> moviesResult, CallAsyncApi callback) {
        mContext = context;
        mList = moviesResult;
        mCallAsyncApi = callback;
    }

    public List<CategoryList> getmList() {
        return mList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.ViewHolder holder, int position, @NonNull final List<Object> payloads) {
        if (!payloads.isEmpty() && holder.categoryItemAdapter != null) {
            for (Object payloadObject : payloads) {
                if (payloadObject.toString().equalsIgnoreCase(PAYLOAD_UPDATE_PRODUCT)) {
                    holder.categoryItemAdapter.notifyDataSetChanged();
                }
            }
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.ViewHolder holder, final int pos) {

        final CategoryList categoryList = mList.get(pos);
        holder.title.setText(categoryList.getTitle());
        List<CategoryData> categoryDataList = categoryList.getCategoryDataList();
        if (holder.categoryItemAdapter != null) {
            holder.categoryItemAdapter.update(new ArrayList<>(categoryDataList.subList(0, 2)));
        } else {
            holder.categoryItemAdapter = new CategoryItemAdapter(mContext, new ArrayList<>(categoryDataList.subList(0, 2)), new CategoryItemAdapter.CallAsyncApi() {
                @Override
                public void callAsyncApi(int childPosiiotn, String url) {
                    int parentPosition = holder.getAdapterPosition();
                    mCallAsyncApi.callAsyncApi(parentPosition, childPosiiotn, url);
                }
            });
            holder.recyclerview.setAdapter(holder.categoryItemAdapter);
        }

        holder.expandedArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (categoryList.isAllProductVisibile()) {
                    holder.expandedArrow.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_baseline_keyboard_arrow_down_24px));
                    holder.categoryItemAdapter.updateItems(categoryList.getCategoryDataList().subList(0, 2));
                    categoryList.setAllProductVisibile(false);
                    holder.categoryItemAdapter.notifyItemRangeRemoved(2, categoryList.getCategoryDataList().size());

                } else {
                    holder.expandedArrow.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_baseline_keyboard_arrow_up_24px));
                    holder.categoryItemAdapter.updateItems(categoryList.getCategoryDataList());
                    categoryList.setAllProductVisibile(true);
                    holder.categoryItemAdapter.notifyItemRangeInserted(2, categoryList.getCategoryDataList().size());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null && !mList.isEmpty() ? mList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerview;
        private TextView title;
        private CategoryItemAdapter categoryItemAdapter;
        private ImageView expandedArrow;

        private ViewHolder(View itemView) {
            super(itemView);
            recyclerview = itemView.findViewById(R.id.recyclerview);
            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2));
            title = itemView.findViewById(R.id.title);
            expandedArrow = itemView.findViewById(R.id.expanded_arrow);
        }
    }

    public interface CallAsyncApi {
        void callAsyncApi(int parentPosition, int childPosiiotn, String url);
    }
}

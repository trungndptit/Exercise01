package com.example.exercise01.base.recyclerView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, V extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<V> {

    protected List<T> mDataList;
    protected OnItemClickListener<T> mItemClickListener;

    protected BaseRecyclerViewAdapter() {
        mDataList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public void updateData(List<T> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        mDataList.clear();
    }

    protected T getItem(int position) {
        if (position < 0 || position > getItemCount()) {
            return null;
        }
        return mDataList.get(position);
    }

    public void removeItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            return;
        }
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(T data, int position) {
        mDataList.add(position, data);
        notifyItemInserted(position);
    }

    public void setItemClickListener(OnItemClickListener<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClicked(T item);
        void onFavoriteClicked(T item);
    }
}

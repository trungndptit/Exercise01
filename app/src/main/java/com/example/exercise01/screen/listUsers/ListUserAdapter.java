package com.example.exercise01.screen.listUsers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise01.R;
import com.example.exercise01.base.recyclerView.BaseRecyclerViewAdapter;
import com.example.exercise01.data.model.User;
import com.example.exercise01.util.ViewUtils;

public class ListUserAdapter extends BaseRecyclerViewAdapter<User, ListUserAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvEmail;
        private TextView mTvFirstName;
        private ImageView mIvAvatar;

        private OnItemClickListener mListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            mTvEmail = itemView.findViewById(R.id.tv_email);
            mTvFirstName = itemView.findViewById(R.id.tv_firstname);
            mIvAvatar = itemView.findViewById(R.id.iv_avatar);
        }

        public void bind(User user) {
            if (user == null) {
                return;
            }
            mTvEmail.setText(user.getEmail());
            mTvFirstName.setText(user.getName());
            ViewUtils.loadCircleImage(user.getAvatar(), mIvAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(user);
                }
            });
        }
    }
}

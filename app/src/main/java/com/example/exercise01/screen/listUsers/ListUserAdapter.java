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

    private OnFavoriteClickListener mFavoriteClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view, mItemClickListener, mFavoriteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    public void setFavoriteClickListener(OnFavoriteClickListener favoriteClickListener) {
        mFavoriteClickListener = favoriteClickListener;
    }

    public interface OnFavoriteClickListener<User> {
        void onFavoriteClicked(User user);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvEmail;
        private TextView mTvFirstName;
        private ImageView mIvAvatar;
        private ImageView mIvNonFavorite;
        private ImageView mIvFavorite;


        private OnItemClickListener mListener;
        private OnFavoriteClickListener mFavoriteListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener, OnFavoriteClickListener favoriteListener) {
            super(itemView);
            mListener = listener;
            mFavoriteListener = favoriteListener;
            mTvEmail = itemView.findViewById(R.id.tv_email);
            mTvFirstName = itemView.findViewById(R.id.tv_firstname);
            mIvAvatar = itemView.findViewById(R.id.iv_avatar);
            mIvNonFavorite = itemView.findViewById(R.id.iv_non_favorite);
            mIvFavorite = itemView.findViewById(R.id.iv_favorite);
        }

        public void bind(User user) {
            if (user == null) {
                return;
            }
            mTvEmail.setText(user.getEmail());
            mTvFirstName.setText(user.getName());
            ViewUtils.loadCircleImage(user.getAvatar(), mIvAvatar);
            if (!user.getFavoriteUser()) {
                mIvFavorite.setVisibility(View.GONE);
                mIvNonFavorite.setVisibility(View.VISIBLE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener == null) {
                        return;
                    }
                    mListener.onItemClicked(user);
                }
            });

            mIvNonFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFavoriteListener == null) {
                        return;
                    }
                    mFavoriteListener.onFavoriteClicked(user);
                }
            });
        }
    }
}

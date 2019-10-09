package com.example.exercise01.listUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exercise01.R;
import com.example.exercise01.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListUserAdapter extends
        RecyclerView.Adapter<ListUserAdapter.ViewHolder>{

    private List<User> mUsers = new ArrayList<>();

    public ListUserAdapter() {
    }

    public void updateData(List<User> users) {
        this.mUsers = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvEmail;
        private TextView mTvFirstName;
        private ImageView mIvAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
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
            Glide.with(itemView.getContext())
                    .load(user.getAvatar())
                    .circleCrop()
                    .into(mIvAvatar);
        }
    }
}

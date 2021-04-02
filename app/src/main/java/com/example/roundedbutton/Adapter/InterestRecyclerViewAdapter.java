package com.example.roundedbutton.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roundedbutton.Activity.UserChoiceClass;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.Constants;

import java.util.List;

import static com.example.roundedbutton.Utils.utils.setFirstCapital;

public class InterestRecyclerViewAdapter extends RecyclerView.Adapter<InterestRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    public List<UserChoiceClass> mUserChoice;

    public InterestRecyclerViewAdapter(Context context, List<UserChoiceClass> userChoice) {
        this.mContext = context;
        this.mUserChoice = userChoice;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.user_choice_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserChoiceClass userChoice = mUserChoice.get(position);


        if (Constants.EMPTY.equals(userChoice.getTitle())) {
            holder.title.setText(Constants.EMPTY);
            holder.logo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_white_image));
            holder.addIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_white_image));
        } else {
            if (Constants.EMPTY.equals(userChoice.getImageURL())) {
                holder.logo.setImageDrawable(userChoice.getDrawable());
                holder.title.setText(setFirstCapital(userChoice.getTitle()));
                holder.addIcon.setImageResource(R.drawable.ic_add_icon);
            } else {
                holder.title.setText(setFirstCapital(userChoice.getTitle()));
                Glide.with(holder.logo.getContext()).load(userChoice.getImageURL()).into(holder.logo);
                holder.addIcon.setImageResource(R.drawable.ic_add_icon);
            }
        }

        holder.addIcon.setOnClickListener(view -> {
            if (!mUserChoice.get(position).getSelected()) {
                holder.addIcon.setImageResource(R.drawable.ic_check_mark);
                mUserChoice.get(position).setSelected(true);
            } else {
                holder.addIcon.setImageResource(R.drawable.ic_add_icon);
                mUserChoice.get(position).setSelected(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserChoice.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView logo, addIcon;
        private TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.newIcon);
            addIcon = itemView.findViewById(R.id.addIcon);
            title = itemView.findViewById(R.id.intrest_title);
        }
    }

    public void updateAdapter(List<UserChoiceClass> userChoice) {
        mUserChoice = userChoice;
        notifyDataSetChanged();
    }


}

package com.example.betonit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.ViewHolder> {

    private Context context;
    private List<Case> posts;

    public CaseAdapter(Context context, List<Case> allPosts) {
        this.context = context;
        this.posts = allPosts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_case, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseAdapter.ViewHolder holder, int position) {
        Case post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvDate;
        private TextView tvCaption;
        private ImageView ivPostContent;

        public ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            //tvCaption = itemView.findViewById(R.id.tvCaption);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvDate = itemView.findViewById(R.id.tvDate);

            //ivPostContent = itemView.findViewById(R.id.ivPostContent);
        }

        public void bind(Case post)
        {
            //tvCaption.setText(post.getCaption());
            tvUsername.setText(post.getKeyCaseStatus());
            tvDate.setText(post.getKeyCaseChallengerEvidence());
            //ParseFile img = post.getImage();

            /*if(img!= null)
            {
                Glide.with(context).load(post.getImage().getUrl()).into(ivPostContent);
            }*/
        }
    }
    public void addAll(List<Case> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }
}

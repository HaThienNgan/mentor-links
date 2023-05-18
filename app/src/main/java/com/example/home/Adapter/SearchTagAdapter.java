package com.example.home.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.SearchListFragment;

import java.util.ArrayList;

public class SearchTagAdapter extends RecyclerView.Adapter<SearchTagAdapter.SearchTagViewHolder> {

    ArrayList<String> searchTagsArrayList;

    public SearchTagAdapter(ArrayList<String> arraySearchtags) {
        this.searchTagsArrayList = arraySearchtags;
    }


    public void setData(ArrayList<String> searchTagsArrayList){
        this.searchTagsArrayList = searchTagsArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_searchtag, parent, false);
        return new SearchTagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTagViewHolder holder, int position) {
        holder.tvTags.setText(searchTagsArrayList.get(position));
        holder.tvTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameListSearch, new SearchListFragment()).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchTagsArrayList.size();
    }

    public class SearchTagViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTags;
        public SearchTagViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTags = itemView.findViewById(R.id.tvTags);
        }
    }
}

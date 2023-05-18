package com.example.home.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.Course;
import com.example.home.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.CourseViewHolder> {

    private List<Course> highlightList;

    public HighlightAdapter(List<Course> highlightList) {
        this.highlightList = highlightList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_highlight, parent, false);
//        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_new_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = highlightList.get(position);
        String image_title_url;
        //Set Image Title
        image_title_url = course.getImage();
        Picasso.get().load(image_title_url).into(holder.image);
        //Set Title
        holder.tvTitle.setText(course.getTitle());
        //Set Name Mentor
        holder.tvMentorName.setText(course.getMentor_Name());
        //Set date end Register
        holder.tvDateEndRegister.setText(course.getData_End_Register());
        //Set Fee
        holder.tvFee.setText(course.getFee());

    }


    @Override
    public int getItemCount() {
        return highlightList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvMentorName, tvDateEndRegister, tvFee;
        private ImageView image;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo_image);
            tvTitle = itemView.findViewById(R.id.photo_title);
            tvMentorName = itemView.findViewById(R.id.tvMentorName);
            tvDateEndRegister = itemView.findViewById(R.id.tvDateEndRegister);
            tvFee = itemView.findViewById(R.id.tvFee);
        }
    }


}

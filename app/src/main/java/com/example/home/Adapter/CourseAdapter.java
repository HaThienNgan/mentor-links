package com.example.home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.Course;
import com.example.home.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;



public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList;
    Context context;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_new_course, parent, false);
//        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_new_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.tvTitle.setText(courseList.get(position).getTitle());

        String image_TitleURL, image_MentorURL;

        image_TitleURL = course.getImage();
        Picasso.get().load(image_TitleURL).into(holder.image);

        image_MentorURL = course.getMentor_Image();
        Picasso.get().load(image_MentorURL).into(holder.imageMentor);

        holder.tvMentorName.setText(course.getMentor_Name());
        holder.tvIntroduction.setText(course.getIntroduction());
        holder.tvFee.setText(course.getFee());
        holder.tvDateEndRegister.setText(course.getData_End_Register());

//        holder.image.setImageResource(courseList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvMentorName, tvIntroduction, tvDateEndRegister, tvFee;
        private ShapeableImageView imageMentor;
        private ImageView image;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo_image);
            tvTitle = itemView.findViewById(R.id.photo_title);
            imageMentor = itemView.findViewById(R.id.img_avatar);
            tvMentorName = itemView.findViewById(R.id.tvMentorName);
            tvIntroduction = itemView.findViewById(R.id.tvIntroduction);
            tvFee = itemView.findViewById(R.id.tvFee);
            tvDateEndRegister = itemView.findViewById(R.id.tvDateEndRegister);
        }
    }


}

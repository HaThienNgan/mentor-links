package com.example.home.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.Course;
import com.example.home.Mentor;
import com.example.home.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private List list;

    public SearchAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(this.getItemViewType(viewType) == 0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
            CourseViewHolder courseViewHolder = new CourseViewHolder(view);
            return courseViewHolder;
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_teacher, parent, false);
            MentorViewHolder mentorViewHolder = new MentorViewHolder(view);
            return mentorViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        if (this.getItemViewType(position) == 0) {
            Course course = (Course) list.get(position);
            CourseViewHolder courseViewHolder = (CourseViewHolder) holder;
            Log.d("course", "onBindViewHolder: ");
            //set image title
            String image_title;
            image_title = course.getImage();
            Picasso.get().load(image_title).into(courseViewHolder.imgTitle);

            courseViewHolder.tvTitle.setText(course.getTitle());
            courseViewHolder.tvIntroduction.setText(course.getIntroduction());
            courseViewHolder.tvDatebegin.setText(course.getDate_Begin());
            courseViewHolder.tvdateendregister.setText(course.getData_End_Register());
            courseViewHolder.tvmentorname.setText(course.getMentor_Name());
            courseViewHolder.tvurl_tags.setText(course.getUrl_tags());
        } else {
            Log.d("mentor", "onBindViewHolder: ");
            Mentor mentor = (Mentor) list.get(position);
            MentorViewHolder mentorViewHolder = (MentorViewHolder) holder;
            //set image mentor
            String image_mentor;
            image_mentor = mentor.getImage();
            Picasso.get().load(image_mentor).into(mentorViewHolder.image);

            mentorViewHolder.tvfullname.setText(mentor.getFullname());
            mentorViewHolder.tvexperience.setText(mentor.getExperience());
            mentorViewHolder.tvurl_tags.setText(mentor.getUrl_tags());
        }
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public int getItemViewType(int position) {
        if(Course.class.isInstance(list.get(position))){
            return 0;
        }
        return 1;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvIntroduction, tvDatebegin, tvdateendregister, tvmentorname, tvurl_tags;
        private ImageView imgTitle;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.photo_title);
            imgTitle = itemView.findViewById(R.id.photo_image);
            tvIntroduction = itemView.findViewById(R.id.photo_content);
            tvDatebegin = itemView.findViewById(R.id.photo_datebegin);
            tvdateendregister = itemView.findViewById(R.id.photo_date_end);
            tvmentorname = itemView.findViewById(R.id.tvTeacher);
            tvurl_tags = itemView.findViewById(R.id.photo_tags);
        }
    }

    public class MentorViewHolder extends RecyclerView.ViewHolder{
        private TextView tvfullname, tvexperience, tvurl_tags;
        private ImageView image;

        public MentorViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.avatar);
            tvfullname = itemView.findViewById(R.id.tvfullName);
            tvexperience = itemView.findViewById(R.id.tvexperience);
            tvurl_tags = itemView.findViewById(R.id.tvurl_tags);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
    private void showLoadingView(LoadingViewHolder holder, int position) {
        //ProgressBar would be displayed
    }


}

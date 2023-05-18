package com.example.home;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home.Adapter.CourseAdapter;
import com.example.home.Adapter.HighlightAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HighLightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighLightFragment extends Fragment {
    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;
    private List<Course> mCourseList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ProgressDialog progressDialog;

    String APIUrl = "https://alpha.mentorlinks.net/MLSOAP/searchSOAP.php?t=getTopCourse";

    public HighLightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HighLightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HighLightFragment newInstance(String param1, String param2) {
        HighLightFragment fragment = new HighLightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_highlight, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute();

        mViewPager2 = view.findViewById(R.id.view_page);

        mCircleIndicator3 = view.findViewById(R.id.circle_indicator3);

    }

    public class MyAsyncTasks extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String curent = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(APIUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        curent += (char) data;
                        data = isw.read();
                    }
                    System.out.print(curent);
                    return curent;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return curent;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("data", s.toString());
            progressDialog.dismiss();

            mCourseList = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject rootObject = jsonArray.getJSONObject(i);
                    //get data
                    String image_Title = rootObject.getString("image");
                    String title = rootObject.getString("name");
                    String mentorname = rootObject.getString("mentorname");
                    String mentorimage = rootObject.getString("mentorimage");
                    String introduction = rootObject.getString("introduction");
                    //Set fee (-1: Miễn phí)
                    String fee = rootObject.getString("fee");
                    if (fee.equals("-1") || fee.equals("0")) {
                        fee = "MIỄN PHÍ";
                    }
                    String dateendregister = rootObject.getString("dateendregister");
                    String datebegin = rootObject.getString("datebegin");

                    mCourseList.add(new Course("", "", image_Title, "", introduction, title, mentorimage, "", mentorname, fee, dateendregister, datebegin, "", 0, "", "c" ));
                    HighlightAdapter highlightAdapter = new HighlightAdapter(mCourseList);
                    mViewPager2.setAdapter(highlightAdapter);
                    mCircleIndicator3.setViewPager(mViewPager2);
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
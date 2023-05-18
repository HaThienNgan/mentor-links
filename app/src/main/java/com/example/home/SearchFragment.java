package com.example.home;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.home.Adapter.CourseAdapter;
import com.example.home.Adapter.SearchAdapter;
import com.example.home.Adapter.SearchTagAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    private RecyclerView searchRecyclerView, rvSearchTags;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Course> CourseList;
    private SearchAdapter searchAdapter;
    GridView gridView;
    private SearchTagAdapter searchTagAdapter;
    ArrayList<String> arraySearchtags = new ArrayList<>();
    ProgressDialog progressDialog;
    String APIUrl = "https://alpha.mentorlinks.net/MLSOAP/searchSOAP.php?t=getSearchTags";

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        searchRecyclerView = view.findViewById(R.id.searchRecyclerView);
//        getListCourses();
//        searchAdapter = new SearchAdapter();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        searchRecyclerView.setLayoutManager(linearLayoutManager);
//        searchAdapter.setData(getListCourses());
//        searchRecyclerView.setAdapter(searchAdapter);


        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute();

        rvSearchTags = view.findViewById(R.id.rvSearchTags);
    }

    public ArrayList<Course> getListCourses() {
        return (ArrayList<Course>) CourseList;
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
                    while (data != -1){
                        curent += (char)data;
                        data = isw.read();
                    }
                    System.out.print(curent);
                    return curent;
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }catch (Exception e){
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

            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i<jsonArray.length(); i++){
                    String name = jsonArray.getString(i);
                    arraySearchtags.add(name);
//                    Log.d("arraySearchTags", arraySearchtags.toString());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
                    rvSearchTags.setLayoutManager(gridLayoutManager);
                    searchTagAdapter = new SearchTagAdapter(arraySearchtags);
                    rvSearchTags.setAdapter(searchTagAdapter);
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
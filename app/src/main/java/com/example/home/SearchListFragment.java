package com.example.home;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home.Adapter.SearchAdapter;

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
 * Use the {@link SearchListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String APIUrl = "https://alpha.mentorlinks.net/MLSOAP/searchSOAP.php?t=searchAct&tag=KIDAO";

    ProgressDialog progressDialog;
    SearchAdapter searchAdapter;
    RecyclerView rvSearchList;
    List list;
    TextView tvResult;

    public SearchListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchListFragment newInstance(String param1, String param2) {
        SearchListFragment fragment = new SearchListFragment();
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
        return inflater.inflate(R.layout.fragment_search_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute();

        rvSearchList = view.findViewById(R.id.rvSearchList);
        tvResult = view.findViewById(R.id.tvResult);
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
            list = new ArrayList();

            try {
                JSONArray jsonArray = new JSONArray(s);
                int result = jsonArray.length();
                tvResult.setText(result + " kết quả tìm kiếm ");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject rootObject = jsonArray.getJSONObject(i);
                    //get data
                    String cardtype = rootObject.getString("cardtype");
                    if (cardtype.equals("c")) {
                        String image_title = rootObject.getString("image");
                        String title = rootObject.getString("name");
                        String introduction = rootObject.getString("introduction");
                        String datebegin = rootObject.getString("datebegin");
                        String dateendregister = rootObject.getString("dateendregister");
                        String mentorname = rootObject.getString("mentorname");
                        String url_tags = rootObject.getString("url_tags");
                        list.add(new Course("", "", image_title, "", introduction, title, "", "", mentorname, "", dateendregister, datebegin, "", 0, url_tags, cardtype  ));
                    }
                    else {
                        String image = rootObject.getString("image");
                        String fullname = rootObject.getString("fullname");
                        String experience = rootObject.getString("experience");
                        String url_tags = rootObject.getString("url_tags");
                        list.add(new Mentor("", fullname, "", experience, image, url_tags, ""));
                    }
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                rvSearchList.setLayoutManager(linearLayoutManager);
                searchAdapter = new SearchAdapter(getContext(), list);
                rvSearchList.setAdapter(searchAdapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
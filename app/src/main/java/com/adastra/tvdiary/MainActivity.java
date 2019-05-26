package com.adastra.tvdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    Call<List<Show>> call;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initializeRetrofit("http://api.tvmaze.com/");
        displayShowsFromTVMazeApi();
    }


    private void displayShowsFromTVMazeApi() {
        call.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Response code :" + response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                List<Show> shows = response.body();
                ArrayList<String> mNames = getNamesOfShows(shows);
                ArrayList<String> showsImages = getImagesOfShows(shows);
                initRecyclerView(mNames, showsImages);
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                //textViewResult.setText(t.getMessage());
            }
        });
    }

    private void initializeRetrofit(String baseURL) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        this.call = jsonPlaceHolderApi.getShows();
    }

    private void initRecyclerView(ArrayList<String> mNames, ArrayList<String> imagesURL) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, imagesURL, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<String> getNamesOfShows(List<Show> showsList) {
        ArrayList<String> showsNames = new ArrayList<>();


        for (Show show : showsList) {
            showsNames.add(show.getName());
        }

        return showsNames;
    }

    private ArrayList<String> getImagesOfShows(List<Show> showsList) {
        ArrayList<String> showsImages = new ArrayList<>();

        for (Show show : showsList) {
            showsImages.add(show.getImage().getMedium());
        }
        return showsImages;
    }
}
package com.adastra.tvdiary;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity implements  RecyclerViewAdapter.OnShowListener{

    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    Call<List<Show>> call;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

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
                initRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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

    private void initRecyclerView(List<Show> shows) {
        adapter = new RecyclerViewAdapter(shows, this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onShowLick(int position, List<Show> shows) {
        Intent intent = new Intent(MainActivity.this, ShowActivity.class);

        intent.putExtra("showName", shows.get(position).getName());
        intent.putExtra("showRating", shows.get(position).getRating().getAverage());
        intent.putStringArrayListExtra("showGenres", shows.get(position).getGenres());
        intent.putExtra("showCoverImage", shows.get(position).getImage().getMedium());
        intent.putExtra("showSummary", shows.get(position).getSummary());


        startActivity(intent);
    }
}
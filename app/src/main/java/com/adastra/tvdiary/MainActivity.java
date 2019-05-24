package com.adastra.tvdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

    @BindView(R.id.textViewResult)
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Show>> call = jsonPlaceHolderApi.getShows();

        call.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Show> shows = response.body();

                for(Show show : shows){
                    String contet ="";
                    contet += "Name:" + show.getName() + "\n";
                    contet += "Genres:" + show.getGenres() + "\n";
                   // contet += "Rating:" + show.getRating() + "\n";
                    contet += "Summary:" + show.getSummary() + "\n\n";

                    textViewResult.append(contet);
                }
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
}

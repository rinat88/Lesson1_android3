package com.example.lesson1_android3.lesson1.data.remote;

import com.example.lesson1_android3.lesson1.App;
import com.example.lesson1_android3.lesson1.data.remote.models.Film;
import com.example.lesson1_android3.lesson1.utils.OnDetailReady;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsApiService {

    public void getFilms(OnFilmReadyCallback myCallback) {

        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    myCallback.success(response.body());
                } else if (response.code() > 500) {
                    myCallback.onServerError();
                }

            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                myCallback.failure(t.getLocalizedMessage());

            }
        });


    }

    public void getDetailsFilm(String id, OnDetailReady onDetailReady) {
        App.api.getDetailsFilm(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onDetailReady.success(response.body());
                } else if (response.code() > 500) {
                    onDetailReady.onServerError();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                onDetailReady.failure(t.getLocalizedMessage());

            }
        });

    }
}

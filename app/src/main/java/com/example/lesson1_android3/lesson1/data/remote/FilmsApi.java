package com.example.lesson1_android3.lesson1.data.remote;

import com.example.lesson1_android3.lesson1.data.remote.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmsApi {

    @GET("/films")
    Call<List<Film>> getFilms();

    @GET("/films/{id}")
    Call<Film> getDetailsFilm(
    @Path("id") String id );


}

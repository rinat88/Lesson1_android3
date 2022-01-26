package com.example.lesson1_android3.lesson1.data.remote;

import com.example.lesson1_android3.lesson1.data.remote.models.Film;

import java.util.List;

public interface OnFilmReadyCallback {
    void success(List<Film> films);
    void onServerError();
    void failure(String msg);
}

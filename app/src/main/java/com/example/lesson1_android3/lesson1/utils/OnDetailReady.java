package com.example.lesson1_android3.lesson1.utils;

import com.example.lesson1_android3.lesson1.data.remote.models.Film;

public interface OnDetailReady {

    void success(Film films);
    void onServerError();
    void failure(String msg);
}

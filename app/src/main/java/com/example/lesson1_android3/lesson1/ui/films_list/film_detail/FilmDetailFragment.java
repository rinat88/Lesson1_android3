package com.example.lesson1_android3.lesson1.ui.films_list.film_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.lesson1_android3.R;
import com.example.lesson1_android3.databinding.FragmentFilmDetailBinding;
import com.example.lesson1_android3.lesson1.App;
import com.example.lesson1_android3.lesson1.data.remote.models.Film;
import com.example.lesson1_android3.lesson1.utils.OnDetailReady;


public class FilmDetailFragment extends Fragment {
    private FragmentFilmDetailBinding binding;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getString("id");
        getInfo();
    }

    private void getInfo() {
        Log.e("Tag",id);
        App.apiService.getDetailsFilm(id, new OnDetailReady() {
            @Override
            public void success(Film films) {
                binding.nameTv.setText(films.getTitle());
                binding.originalTv.setText(films.getOriginalTitle());
                binding.directorTv.setText(films.getDirector());
                binding.producerTv.setText(films.getProducer());
                binding.description.setText(films.getDescription());
                Glide.with(binding.getRoot()).load(films.getImage()).centerCrop().into(binding.filmIm);
            }

            @Override
            public void onServerError() {
                Log.e("TAG", "onServerError: " );
            }

            @Override
            public void failure(String msg) {
                Log.e("TAG",msg );
            }
        });
    }
}
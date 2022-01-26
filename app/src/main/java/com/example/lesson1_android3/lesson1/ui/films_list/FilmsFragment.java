package com.example.lesson1_android3.lesson1.ui.films_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.lesson1_android3.R;
import com.example.lesson1_android3.databinding.FragmentFilmsBinding;
import com.example.lesson1_android3.lesson1.App;
import com.example.lesson1_android3.lesson1.data.remote.OnFilmReadyCallback;
import com.example.lesson1_android3.lesson1.data.remote.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmsFragment extends Fragment {

    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;



    public FilmsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater, container, false);
        adapter = new FilmsAdapter();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendInfo();
        filmInfo();
        binding.recyclerFilms.setAdapter(adapter);

        App.apiService.getFilms(new OnFilmReadyCallback() {
            @Override
            public void success(List<Film> films) {
                adapter.setFilms(films);
            }

            @Override
            public void onServerError() {
                Log.e("TAG", "onServerError: ");
            }

            @Override
            public void failure(String msg) {
                Log.e("TAG", "failure: " + msg);
            }
        });

    }

    private void filmInfo() {
        App.apiService.getFilms(new OnFilmReadyCallback() {
            @Override
            public void success(List<Film> films) {
                adapter.setFilms(films);
            }

            @Override
            public void onServerError() {

            }

            @Override
            public void failure(String msg) {

            }
        });
    }

    private void sendInfo() {
        adapter.setOnItemClick(position->{
            Bundle bundle = new Bundle();
            Log.e("TAG", position);
            bundle.putString("id" , position);
            Navigation.findNavController(requireView()).navigate(R.id.filmDetailFragment,bundle);
        });
    }
}
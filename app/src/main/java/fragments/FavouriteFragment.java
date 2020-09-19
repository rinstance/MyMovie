package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mymovies.MainActivity;
import com.example.mymovies.R;

import java.util.ArrayList;

import adapters.RecyclerViewAdapter;
import entities.Movie;
import viewmodels.MainViewModel;

public class FavouriteFragment extends MainFragment {
    private MainViewModel model;
    private LiveData<Movie> liveData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, null);
        init(view);
        setRecyclerView();
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerviewF);
    }

    protected void setRecyclerView() {
        super.setRecyclerView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        updateAdapter();
    }

    private void updateAdapter() {
        liveData = model.getMovieForFavourites();
        liveData.observe(getViewLifecycleOwner(), movie -> {
            /*** TODO - add to DB*/
        });
    }
}

package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymovies.MainActivity;
import com.example.mymovies.R;

import javax.inject.Inject;

import DAO.room.AppDatabase;
import DAO.room.MovieDAO;

import dagger.room.DaggerRoomComponent;
import dagger.room.RoomComponent;
import dagger.room.RoomModule;
import entities.Movie;
import viewmodels.MainViewModel;

public class FavouriteFragment extends MainFragment {
    private MainViewModel model;
    private LiveData<Movie> liveData;
    @Inject
    MovieDAO movieDAO;

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
        setDagger();
    }

    private void setDagger() {
        RoomComponent roomComponent = DaggerRoomComponent
                .builder()
                .roomModule(new RoomModule(getActivity()))
                .build();
        roomComponent.injectFavouriteFragment(this);
    }

    private void updateAdapter() {
        liveData = model.getMovieForFavourites();
        liveData.observe(getViewLifecycleOwner(), movie -> {
            // TODO: add to DB
        });
    }
}

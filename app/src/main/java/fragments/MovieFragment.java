package fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymovies.R;

import DAO.RealmDatabase;
import entities.Movie;
import entities.ResultMovie;
import io.reactivex.subjects.PublishSubject;
import viewmodels.MainViewModel;

public class MovieFragment extends MainFragment {
    private LiveData<ResultMovie> liveData;
    private MainViewModel model;
    private SearchView searchView;
    private PublishSubject<String> subject;
    private RealmDatabase realmDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        subject = model.getSubject();
        realmDatabase = new RealmDatabase(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, null);
        init(view);
        setRecyclerView();
        setSearchView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateAdapter();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        searchView = view.findViewById(R.id.searchView);
    }

    private void updateAdapter() {
        liveData = model.getMovies();
        liveData.observe(getViewLifecycleOwner(), resultMovie -> {
            this.movies.clear();
            this.movies.addAll(resultMovie.getResults());
            recyclerViewAdapter.notifyDataSetChanged();
        });
    }

    private void addFavouritesDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Add to favourites?");
        builder.setMessage("Add to favourites???");
        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("OK", (dialog, which) -> addToDatabase(movies.get(position)));
        builder.show();
    }

    private void addToDatabase(Movie movie) {
        realmDatabase.addMoviesToRealm(movie);
    }

    protected void setRecyclerView() {
        super.setRecyclerView();
        recyclerViewAdapter.setOnLongItemClickListener(this::addFavouritesDialog);
    }

    private void setSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return true;
            }
        });
    }
}

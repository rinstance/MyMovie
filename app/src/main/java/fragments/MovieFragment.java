package fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymovies.R;

import DAO.RealmDatabase;
import DAO.Variables;
import entities.Movie;
import entities.ResultMovie;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;
import viewmodels.MainViewModel;

public class MovieFragment extends MainFragment {
    private LiveData<ResultMovie> liveData;
    private MainViewModel model;
    private SearchView searchView;
    private PublishSubject<String> subject;
    private RealmDatabase realmDatabase;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        subject = model.getSubject();
        realmDatabase = new RealmDatabase(getActivity().getApplicationContext());
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
        progressBar = view.findViewById(R.id.progressBar);
        subject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s -> progressBar.setVisibility(View.VISIBLE)
                ).isDisposed();
    }

    private void updateAdapter() {
        liveData = model.getMovies();
        liveData.observe(getViewLifecycleOwner(), resultMovie -> {
            progressBar.setVisibility(View.GONE);
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
        builder.setPositiveButton("OK", (dialog, which) -> {
            if (realmDatabase.isInDatabase(movies.get(position)))
                Toast.makeText(getContext(), "Already exist", Toast.LENGTH_SHORT).show();
            else
                addToDatabase(movies.get(position));
        });
        builder.show();
    }

    private void addToDatabase(Movie movie) {
        realmDatabase.addMoviesToRealm(movie);
    }

    protected void setRecyclerView() {
        super.setRecyclerView();
        recyclerViewAdapter.setOnClickListener(position -> goToDetailsActivity(position, Variables.FLAG_SEARCH));
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
                movies.clear();
                recyclerViewAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}

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

import DAO.RealmDatabase;
import entities.Movie;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import viewmodels.MainViewModel;

public class FavouriteFragment extends MainFragment {
    private MainViewModel model;
    private LiveData<Movie> liveData;
    private RealmDatabase realmDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realmDatabase = new RealmDatabase(getContext());
    }

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
        movies.addAll(realmDatabase.getMoviesFromRealm());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
    }
}

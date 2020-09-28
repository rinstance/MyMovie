package fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymovies.R;

import DAO.RealmDatabase;
import DAO.Variables;
import adapters.RecyclerViewAdapter;
import entities.Movie;
import viewmodels.MainViewModel;

public class FavouriteFragment extends MainFragment {
    private MainViewModel model;
    private LiveData<Movie> liveData;
    private RealmDatabase realmDatabase;
    private RecyclerViewAdapter.onClickListener onClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realmDatabase = new RealmDatabase(getActivity().getApplicationContext());
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
        recyclerViewAdapter.setOnClickListener(position -> goToDetailsActivity(position, Variables.FLAG_FAVOURITE));
        recyclerViewAdapter.setOnLongItemClickListener(this::deleteFavouritesDialog);
    }

    private void deleteFavouritesDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Delete from favourites?");
        builder.setMessage("Delete from favourites???");
        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("OK", (dialog, which) -> {
            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            deleteFromDatabase(position);
        });
        builder.show();
    }

    private void deleteFromDatabase(int position) {
        realmDatabase.deleteFromDatabase(movies.get(position));
        this.movies.clear();
        this.movies.addAll(realmDatabase.getMoviesFromRealm());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
    }
}

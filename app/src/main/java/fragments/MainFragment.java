package fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapters.RecyclerViewAdapter;
import entities.Movie;

public class MainFragment extends Fragment {
    protected RecyclerViewAdapter recyclerViewAdapter;
    protected ArrayList<Movie> movies;
    protected RecyclerView recyclerView;

    protected void setRecyclerView() {
        movies = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}

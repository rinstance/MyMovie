package fragments;

import android.content.Intent;
import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.DetailsActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import DAO.Variables;
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

    protected void goToDetailsActivity(int position, final String FLAG) {
        Bitmap bitmap = recyclerViewAdapter.getBitmap(position);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] array = bos.toByteArray();
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(Variables.BITMAP, array);
        intent.putExtra(Variables.TITLE, movies.get(position).getTitle());
        intent.putExtra(Variables.IMG, movies.get(position).getPosterPath());
        intent.putExtra(Variables.ID, movies.get(position).getId());
        intent.putExtra(Variables.POPULARITY, movies.get(position).getPopularity().toString());
        intent.putExtra(Variables.OVERVIEW, movies.get(position).getOverview());
        intent.putExtra(Variables.FLAG, FLAG);
        startActivity(intent);
    }
}

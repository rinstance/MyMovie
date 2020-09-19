package adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.MainActivity;
import com.example.mymovies.R;

import java.util.ArrayList;

import entities.Movie;
import entities.ResultMovie;
import network.DownloadImageHelper;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<Movie> movies;
    private DownloadImageHelper downloadImageHelper;
    private OnLongItemClickListener mListener;

    public interface OnLongItemClickListener {
        void onLongItemClick(int position);
    }

    public void setOnLongItemClickListener(OnLongItemClickListener listener) {
        mListener = listener;
    }


    public RecyclerViewAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getTitle());
        holder.desc.setText(movies.get(position).getOverview());
        holder.date.setText(movies.get(position).getReleaseDate());
        holder.rate.setText(movies.get(position).getPopularity().toString());


        if (movies.get(position).getPosterPath() != null)
            downloadImageHelper = new DownloadImageHelper(holder.imageView, movies.get(position).getPosterPath());
        else
            MainActivity.log("Poster is UMER");
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView desc, title, date, rate;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView, OnLongItemClickListener listener) {
            super(itemView);
            init(itemView);

            itemView.setOnLongClickListener(
                    v -> {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onLongItemClick(position);
                                return true;
                            }
                        }
                        return false;
                    }
            );
        }

        private void init(View view) {
            desc = view.findViewById(R.id.description);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            imageView = view.findViewById(R.id.image);
            rate = view.findViewById(R.id.rate);
        }
    }
}

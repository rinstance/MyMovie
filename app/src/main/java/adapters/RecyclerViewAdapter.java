package adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.R;

import java.util.ArrayList;

import entities.Movie;
import network.DownloadImageHelper;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<Movie> movies;
    private DownloadImageHelper downloadImageHelper;
    private OnLongItemClickListener onLongItemClickListener;
    private RecyclerViewAdapter.onClickListener onClickListener;
    private ArrayList<ImageView> imageViews;

    public interface OnLongItemClickListener {
        void onLongItemClick(int position);
    }

    public interface onClickListener {
        void onItemClick(int position);
    }

    public void setOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

    public void setOnClickListener(RecyclerViewAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public RecyclerViewAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
        imageViews = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.movie_item, parent, false);
        if (onClickListener != null && onLongItemClickListener != null)
            return new MyViewHolder(view, onClickListener, onLongItemClickListener);
        else if (onLongItemClickListener != null && onClickListener == null)
            return new MyViewHolder(view, onLongItemClickListener);
        else if (onLongItemClickListener == null && onClickListener != null)
            return new MyViewHolder(view, onClickListener);
        else
            return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getTitle());
        holder.desc.setText(movies.get(position).getOverview());
        holder.date.setText(movies.get(position).getReleaseDate());
        holder.rate.setText(movies.get(position).getPopularity().toString());

        if (movies.get(position).getPosterPath() != null) {
            downloadImageHelper = new DownloadImageHelper(holder.imageView, movies.get(position).getPosterPath());
            imageViews.add(holder.imageView);
        }
    }

    public Bitmap getBitmap(int position) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageViews.get(position).getDrawable();
        return bitmapDrawable.getBitmap();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView desc, title, date, rate;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        public MyViewHolder(@NonNull View itemView, OnLongItemClickListener listener) {
            this(itemView, null, listener);
        }

        public MyViewHolder(@NonNull View itemView, RecyclerViewAdapter.onClickListener listener) {
            this(itemView, listener, null);
        }

        public MyViewHolder(@NonNull View itemView, RecyclerViewAdapter.onClickListener onClickListener, OnLongItemClickListener onLongItemClickListener) {
            this(itemView);
            itemView.setOnClickListener(v -> setClick(onClickListener));
            itemView.setOnLongClickListener(v -> setLongClick(onLongItemClickListener));
        }

        private void setClick(RecyclerViewAdapter.onClickListener listener) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            }
        }

        private boolean setLongClick(OnLongItemClickListener listener) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onLongItemClick(position);
                    return true;
                }
            }
            return false;
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

package entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavouriteMovie {
    private String title, overview, releaseDate, posterPath;
    private double popularity;
    @PrimaryKey
    private int id;

    public FavouriteMovie() {}

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setId(int id) {
        this.id = id;
    }
}

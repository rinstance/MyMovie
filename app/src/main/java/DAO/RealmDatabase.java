package DAO;

import android.content.Context;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.realm.DaggerRealmComponent;
import dagger.realm.RealmComponent;
import dagger.realm.RealmModule;
import entities.Movie;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmDatabase {
    private Context context;
    @Inject
    Realm realm;

    public RealmDatabase(Context context) {
        RealmComponent realmComponent = DaggerRealmComponent.builder()
                .realmModule(new RealmModule(context))
                .build();
        realmComponent.injectRealmDatabase(this);
    }

    public void addMoviesToRealm(Movie movie) {
        realm.beginTransaction();
        Movie movieDB = realm.createObject(Movie.class);
        movieDB.setTitle(movie.getTitle());
        movieDB.setOverview(movie.getOverview());
        movieDB.setReleaseDate(movie.getReleaseDate());
        movieDB.setPosterPath(movie.getPosterPath());
        movieDB.setPopularity(movie.getPopularity());
        movieDB.setId(movie.getId());
        realm.commitTransaction();
    }

    public ArrayList<Movie> getMoviesFromRealm() {
        RealmResults<Movie> results = realm.where(Movie.class).findAll();
        return new ArrayList<>(results);
    }

    public boolean isInDatabase(Movie movie) {
        Movie m = realm.where(Movie.class).equalTo("id", movie.getId()).findFirst();
        return m != null;
    }

    public void deleteFromDatabase(Movie movie) {

        realm.beginTransaction();
        Movie m = realm.where(Movie.class).equalTo("id", movie.getId()).findFirst();
        m.deleteFromRealm();
        realm.commitTransaction();
    }
}

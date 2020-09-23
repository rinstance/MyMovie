package DAO;

import android.content.Context;

import com.example.mymovies.MainActivity;

import javax.inject.Inject;

import dagger.realm.DaggerRealmComponent;
import dagger.realm.RealmComponent;
import dagger.realm.RealmModule;
import entities.Movie;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmDatabase {
    private Context context;
    private PublishSubject<RealmResults<Movie>> publishSubject;
    @Inject
    Realm realm;

    public RealmDatabase(Context context) {
        publishSubject = PublishSubject.create();
        RealmComponent realmComponent = DaggerRealmComponent.builder()
                .realmModule(new RealmModule(context))
                .build();
        realmComponent.injectRealmDatabase(this);
    }

    public void addMoviesToRealm(Movie movie) {
        realm.beginTransaction();
        Movie movieBD = realm.createObject(Movie.class);
        movieBD.setTitle(movie.getTitle());
        movieBD.setOverview(movie.getOverview());
        movieBD.setReleaseDate(movie.getReleaseDate());
        movieBD.setPosterPath(movie.getPosterPath());
        movieBD.setPopularity(movie.getPopularity());
        movieBD.setId(movie.getId());
        MainActivity.log(movieBD.toString() +
                " "  + movieBD.getTitle());
        realm.commitTransaction();
    }

    public RealmResults<Movie> getMoviesFromRealm() {
        RealmResults<Movie> result = realm.where(Movie.class).findAll();
        MainActivity.log(result.toString());
        return result;
    }
}

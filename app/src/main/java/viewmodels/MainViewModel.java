package viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import DAO.Variables;
import dagger.api.ApiComponent;

import dagger.api.DaggerApiComponent;
import entities.ResultMovie;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import network.MovieApi;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ResultMovie> search_movies;
    private Single<ResultMovie> call;
    private Disposable disposableOnSearchView;
    @Inject
    MovieApi movieApi;
    @Inject
    PublishSubject<String> subject;

    public MainViewModel() {
        setDagger();
    }

    public LiveData<ResultMovie> getMovies() {
        if (search_movies == null) {
            search_movies = new MutableLiveData<>();
            loadMovies();
        }
        return search_movies;
    }

    private void setDagger() {
        ApiComponent apiComponent = DaggerApiComponent.create();
        apiComponent.injectMainViewModule(this);
    }

    private void loadMovies() {
        disposableOnSearchView = subject
                .debounce(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(this::getData);
    }

    private void getData(String title) {
        call = movieApi.getMoviesForTitle(Variables.KEY, Variables.LANGUAGE, title);
        call.subscribe(new DisposableSingleObserver<ResultMovie>() {
            @Override
            public void onSuccess(ResultMovie resultMovie) {
                search_movies.postValue(resultMovie);
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposableOnSearchView.dispose();
    }

    public PublishSubject<String> getSubject() {
        return subject;
    }
}
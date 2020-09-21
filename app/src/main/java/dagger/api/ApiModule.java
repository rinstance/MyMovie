package dagger.api;

import javax.inject.Singleton;

import DAO.Variables;
import dagger.Module;
import dagger.Provides;
import entities.ResultMovie;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import network.MovieApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Variables.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    MovieApi getMovieApi(Retrofit retrofit) {
        MovieApi movieApi = retrofit.create(MovieApi.class);
        return movieApi;
    }

    @Provides
    @Singleton
    PublishSubject<String> getSubject() {
        return PublishSubject.create();
    }
}

package network;

import entities.ResultMovie;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie")
    Single<ResultMovie> getMoviesForTitle(
            @Query("api_key") String key,
            @Query("language") String language,
            @Query("query") String query
    );
}

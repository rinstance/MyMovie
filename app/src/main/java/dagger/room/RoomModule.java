package dagger.room;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import DAO.room.AppDatabase;
import DAO.room.MovieDAO;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private Context context;

    public RoomModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    AppDatabase appDatabase() {
        return Room.databaseBuilder(context, AppDatabase.class, "FavouriteMovie").build();
    }

    @Provides
    @Singleton
    MovieDAO movieDAO(AppDatabase appDatabase) {
        return appDatabase.movieDAO();
    }
}

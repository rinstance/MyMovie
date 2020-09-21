package DAO.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import entities.FavouriteMovie;

@Database(entities = FavouriteMovie.class, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();
}

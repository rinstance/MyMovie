package DAO.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entities.FavouriteMovie;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM FavouriteMovie")
    List<FavouriteMovie> getAll();

    @Query("SELECT * FROM favouritemovie WHERE id = :id")
    FavouriteMovie getById(int id);

    @Insert
    void insert(FavouriteMovie favouriteMovie);

    @Delete
    void delete(FavouriteMovie favouriteMovie);

    @Update
    void update(FavouriteMovie favouriteMovie);
}

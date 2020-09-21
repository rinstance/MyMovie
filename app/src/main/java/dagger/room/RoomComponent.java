package dagger.room;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Component;
import fragments.FavouriteFragment;

@Singleton
@Component(modules = RoomModule.class)
public interface RoomComponent {
    void injectFavouriteFragment(FavouriteFragment favouriteFragment);
}

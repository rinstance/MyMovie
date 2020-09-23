package dagger.realm;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class RealmModule {

    private Context context;

    public RealmModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Realm getRealm() {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(realmConfiguration);
    }
}

package dagger.realm;

import javax.inject.Singleton;

import DAO.RealmDatabase;
import dagger.Component;

@Singleton
@Component(modules = RealmModule.class)
public interface RealmComponent {
    void injectRealmDatabase(RealmDatabase realmDatabase);
}

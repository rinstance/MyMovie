package dagger.api;

import javax.inject.Singleton;

import dagger.Component;
import viewmodels.MainViewModel;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
    void injectMainViewModule(MainViewModel mainViewModel);
}

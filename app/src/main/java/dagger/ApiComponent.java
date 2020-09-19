package dagger;

import javax.inject.Singleton;
import viewmodels.MainViewModel;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
    void injectMainViewModule(MainViewModel mainViewModel);
}

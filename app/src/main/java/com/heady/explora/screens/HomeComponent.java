package com.heady.explora.screens;

import com.heady.explora.data.component.NetComponent;
import com.heady.explora.util.CustomScope;

import dagger.Component;

/**
 * Created by yashthakur on 26/08/18.
 */

@CustomScope
@Component(dependencies = NetComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}


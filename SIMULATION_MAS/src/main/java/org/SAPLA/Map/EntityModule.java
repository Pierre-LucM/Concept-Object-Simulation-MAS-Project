package org.SAPLA.Map;

import com.google.inject.AbstractModule;

public class EntityModule extends AbstractModule {
    protected void configure() {
        bind(IInteractable.class).to(Entity.class);
    }
}

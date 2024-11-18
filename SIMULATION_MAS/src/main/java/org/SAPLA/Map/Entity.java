package org.SAPLA.Map;

import com.google.inject.Inject;

public abstract class Entity implements IInteractable {
    protected Point location;
    private Entity instance;

    protected Entity () {

        instance = this;
    }

    public abstract void interact();
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        instance.location = location;
    }


    public Entity replaceEntityType(Entity oldEntity, Entity newEntity) {

        instance= newEntity;
        return this;
    }

    public abstract char getSymbol();


}

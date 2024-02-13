package net.tslat.aoa3.common.menu.container;

import net.minecraft.world.ContainerListener;
import net.minecraft.world.SimpleContainer;

public class UpdatingSimpleContainer extends SimpleContainer {
    private final ContainerListener callback;

    public UpdatingSimpleContainer(int size, ContainerListener callback) {
        super(size);

        this.callback = callback;
    }

    @Override
    public void setChanged() {
        super.setChanged();

        this.callback.containerChanged(this);
    }
}

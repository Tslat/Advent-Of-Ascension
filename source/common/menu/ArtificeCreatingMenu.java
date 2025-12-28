package net.tslat.aoa3.common.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.block.AoABlocks;

public class ArtificeCreatingMenu extends AbstractContainerMenu {
    protected ContainerLevelAccess accessValidator;
    protected MenuType<?> menuType;

    public ArtificeCreatingMenu(MenuType<?> type, int containerId, ContainerLevelAccess accessValidator) {
        super(type, containerId);
        this.menuType = type;
        this.accessValidator = accessValidator;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.accessValidator, player, AoABlocks.TINKERERS_TABLE.get());
    }
}

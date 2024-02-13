package net.tslat.aoa3.common.menu.provider;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.library.object.TriFunction;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class GenericMenuProvider implements MenuProvider {
    private final Component title;
    private final TriFunction<Integer, Inventory, ContainerLevelAccess, AbstractContainerMenu> constructor;
    private final Function<Level, ContainerLevelAccess> accessValidatorFactory;

    public GenericMenuProvider(String containerId, BlockPos pos, TriFunction<Integer, Inventory, ContainerLevelAccess, AbstractContainerMenu> constructor) {
        this(Component.translatable(LocaleUtil.createContainerLocaleKey(containerId)), pos, constructor);
    }

    public GenericMenuProvider(Component title, BlockPos pos, TriFunction<Integer, Inventory, ContainerLevelAccess, AbstractContainerMenu> constructor) {
        this.title = title;
        this.constructor = constructor;
        this.accessValidatorFactory = level -> ContainerLevelAccess.create(level, pos);
    }

    @NotNull
    @Override
    public Component getDisplayName() {
        return this.title;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return this.constructor.apply(containerId, playerInventory, this.accessValidatorFactory.apply(player.level()));
    }
}

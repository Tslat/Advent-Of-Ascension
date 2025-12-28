package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.custom.AoAAspectFocus;

import java.util.function.Supplier;

public class AspectFocusItem extends Item {
    private final Supplier<AoAAspectFocus> focus;

    public AspectFocusItem(Supplier<AoAAspectFocus> focus, Item.Properties properties) {
        super(properties);

        this.focus = focus;
    }

    public AoAAspectFocus getFocus() {
        return this.focus.get();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}

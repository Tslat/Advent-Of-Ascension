package net.tslat.aoa3.common.registration.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.misc.AspectFocusItem;

import java.util.function.Supplier;

public record AoAAspectFocus(String name, int colour, Supplier<AspectFocusItem> item) implements ItemLike {
    @Override
    public Item asItem() {
        return item.get();
    }

    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> CHANGE = register("change", 0x9E1D97, AoAItems.CHANGE_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> CONTROL = register("control", 0xBF8440, AoAItems.CONTROL_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> DAMAGE = register("damage", 0xB02C25, AoAItems.DAMAGE_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> DURABILITY = register("durability", 0x787878, AoAItems.DURABILITY_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> ELEMENTAL = register("elemental", 0x6C1D9E, AoAItems.ELEMENTAL_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> LIFE = register("life", 0x539E1D, AoAItems.LIFE_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> LUCK = register("luck", 0x1D9E36, AoAItems.LUCK_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> MAGIC = register("magic", 0x401D9E, AoAItems.MAGIC_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> POWER = register("power", 0xC86837, AoAItems.POWER_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> PRECISION = register("precision", 0x1D5D9E, AoAItems.PRECISION_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> RESOURCE = register("resource", 0x1D2A9E, AoAItems.RESOURCE_FOCUS);
    public static final DeferredHolder<AoAAspectFocus, AoAAspectFocus> SPEED = register("speed", 0x26B1ADB, AoAItems.SPEED_FOCUS);

    public static void init() {}

    private static DeferredHolder<AoAAspectFocus, AoAAspectFocus> register(String id, int colour, Supplier<AspectFocusItem> focusItem) {
        return AoARegistries.AOA_ASPECT_FOCI.register(id, () -> new AoAAspectFocus(id, colour, focusItem));
    }
}

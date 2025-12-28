package net.tslat.aoa3.common.registration.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.tslat.aoa3.content.item.tool.artifice.*;

import java.util.function.Supplier;

public final class AoAArtificeDevices {
    public static void init() {}

    public static final DeferredItem<Item> EXP_FLASK = register("exp_flask", ExpFlask::new);
    public static final DeferredItem<Item> TEMPORAL_ANVIL = register("temporal_anvil", TemporalAnvil::new);
    public static final DeferredItem<Item> STASIS_CAPSULE = register("stasis_capsule", StasisCapsule::new);
    public static final DeferredItem<Item> ARCANUM_BATTERY = register("arcanum_battery", () -> new ArtificeItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> AMMO_VOID_POUCH = register("ammo_void_pouch", AmmoVoidPouch::new);
    public static final DeferredItem<Item> ANCHORING_CRYSTAL = register("anchoring_crystal", AnchoringCrystal::new);

    // RATCHETING LADDER
    // CHROMA GOGGLES

    private static <T extends Item> DeferredItem<T> register(String registryName, Supplier<T> item) {
        return AoAItems.registerItem(registryName, item, AoACreativeModeTabs.TOOLS.getKey());
    }

    // - A pocket forge (Auto-smelt ores mined, then auto combine them into storage blocks to optimise space while mining)
    // - Portable coin exchanger
    // - Spawnpoint teleporter
    // - Ammo Pouch
    // - Egg incubator (turn egg into chicken)
    // - Device to increase power stone output from Pixons
    // - Permanent upgrade to resources (+20 energy?)
    // - Waystone/teleporter?
    // 		- Block placement range increaser
    // - Vertical Redstone
    // - Place a block in midair
    // - Auto loot collector
    // - Mob locator?
    // 		- Open shulker box without placing it
    // - Remote redstone activator
    // - Move Lyonic armour to artifice?
    // 		- Toggleable block that you can turn on/off and allow for noclip
    // - Pulsefield generator - placeable device that occasionally pushes away nearby entities
}

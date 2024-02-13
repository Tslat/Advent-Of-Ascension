package net.tslat.aoa3.common.registration;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.menu.*;
import net.tslat.aoa3.content.entity.npc.banker.AoABanker;
import net.tslat.aoa3.content.entity.npc.trader.CorruptedTravellerEntity;

public final class AoAMenus {
	public static void init() {}

	public static final DeferredHolder<MenuType<?>, MenuType<MendingTableMenu>> MENDING_TABLE = registerContainer("mending_table", (screenId, inventory, buffer) -> new MendingTableMenu(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<WhitewashingTableMenu>> WHITEWASHING_TABLE = registerContainer("whitewashing_table", (screenId, inventory, buffer) -> new WhitewashingTableMenu(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<DivineStationMenu>> DIVINE_STATION = registerContainer("divine_station", (screenId, inventory, buffer) -> new DivineStationMenu(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<FrameBenchMenu>> FRAME_BENCH = registerContainer("frame_bench", (screenId, inventory, buffer) -> new FrameBenchMenu(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<InfusionTableMenu>> INFUSION_TABLE = registerContainer("infusion_table", (screenId, inventory, buffer) -> new InfusionTableMenu(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<ImbuingChamberMenu>> IMBUING_CHAMBER = registerContainer("imbuing_chamber", (screenId, inventory, buffer) -> new ImbuingChamberMenu(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));

	public static final DeferredHolder<MenuType<?>, MenuType<MerchantMenu>> TRADER = registerContainer("trader", (screenId, inventory, buffer) -> new MerchantMenu(screenId, inventory, (Merchant)inventory.player.level().getEntity(buffer.readInt())));
	public static final DeferredHolder<MenuType<?>, MenuType<CorruptedTravellerMenu>> CORRUPTED_TRAVELLER = registerContainer("corrupted_traveller", (screenId, inventory, buffer) -> new CorruptedTravellerMenu(screenId, inventory, (CorruptedTravellerEntity)inventory.player.level().getEntity(buffer.readInt())));
	public static final DeferredHolder<MenuType<?>, MenuType<BankerMenu>> BANKER = registerContainer("banker", (screenId, inventory, buffer) -> new BankerMenu(screenId, inventory, (AoABanker)inventory.player.level().getEntity(buffer.readInt())));

	private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerContainer(String id, IContainerFactory<T> factory) {
		return AoARegistries.MENUS.register(id, () -> IMenuTypeExtension.create(factory));
	}
}

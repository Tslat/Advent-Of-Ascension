package net.tslat.aoa3.common.registration;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.container.*;
import net.tslat.aoa3.content.entity.npc.banker.AoABanker;
import net.tslat.aoa3.content.entity.npc.trader.CorruptedTravellerEntity;

public final class AoAContainers {
	public static void init() {}

	public static final DeferredHolder<MenuType<?>, MenuType<MendingTableContainer>> MENDING_TABLE = registerContainer("mending_table", (screenId, inventory, buffer) -> new MendingTableContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<WhitewashingTableContainer>> WHITEWASHING_TABLE = registerContainer("whitewashing_table", (screenId, inventory, buffer) -> new WhitewashingTableContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<DivineStationContainer>> DIVINE_STATION = registerContainer("divine_station", (screenId, inventory, buffer) -> new DivineStationContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<FrameBenchContainer>> FRAME_BENCH = registerContainer("frame_bench", (screenId, inventory, buffer) -> new FrameBenchContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final DeferredHolder<MenuType<?>, MenuType<InfusionTableContainer>> INFUSION_TABLE = registerContainer("infusion_table", (screenId, inventory, buffer) -> new InfusionTableContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));

	public static final DeferredHolder<MenuType<?>, MenuType<MerchantMenu>> TRADER = registerContainer("trader", (screenId, inventory, buffer) -> new MerchantMenu(screenId, inventory, (Merchant)inventory.player.level().getEntity(buffer.readInt())));
	public static final DeferredHolder<MenuType<?>, MenuType<CorruptedTravellerContainer>> CORRUPTED_TRAVELLER = registerContainer("corrupted_traveller", (screenId, inventory, buffer) -> new CorruptedTravellerContainer(screenId, inventory, (CorruptedTravellerEntity)inventory.player.level().getEntity(buffer.readInt())));
	public static final DeferredHolder<MenuType<?>, MenuType<BankerContainer>> BANKER = registerContainer("banker", (screenId, inventory, buffer) -> new BankerContainer(screenId, inventory, (AoABanker)inventory.player.level().getEntity(buffer.readInt())));

	private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerContainer(String id, IContainerFactory<T> factory) {
		return AoARegistries.MENUS.register(id, () -> IMenuTypeExtension.create(factory));
	}
}

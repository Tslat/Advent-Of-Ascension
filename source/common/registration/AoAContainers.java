package net.tslat.aoa3.common.registration;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.MerchantScreen;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.MerchantContainer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.container.*;
import net.tslat.aoa3.common.container.*;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.trader.CorruptedTravellerEntity;

public final class AoAContainers {
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<ContainerType<MendingTableContainer>> MENDING_TABLE = registerContainer("mending_table", (screenId, inventory, buffer) -> new MendingTableContainer(screenId, inventory, IWorldPosCallable.create(inventory.player.level, buffer.readBlockPos())));
	public static final RegistryObject<ContainerType<WhitewashingTableContainer>> WHITEWASHING_TABLE = registerContainer("whitewashing_table", (screenId, inventory, buffer) -> new WhitewashingTableContainer(screenId, inventory, IWorldPosCallable.create(inventory.player.level, buffer.readBlockPos())));
	public static final RegistryObject<ContainerType<DivineStationContainer>> DIVINE_STATION = registerContainer("divine_station", (screenId, inventory, buffer) -> new DivineStationContainer(screenId, inventory, IWorldPosCallable.create(inventory.player.level, buffer.readBlockPos())));
	public static final RegistryObject<ContainerType<FrameBenchContainer>> FRAME_BENCH = registerContainer("frame_bench", (screenId, inventory, buffer) -> new FrameBenchContainer(screenId, inventory, IWorldPosCallable.create(inventory.player.level, buffer.readBlockPos())));
	public static final RegistryObject<ContainerType<InfusionTableContainer>> INFUSION_TABLE = registerContainer("infusion_table", (screenId, inventory, buffer) -> new InfusionTableContainer(screenId, inventory, IWorldPosCallable.create(inventory.player.level, buffer.readBlockPos())));

	public static final RegistryObject<ContainerType<MerchantContainer>> TRADER = registerContainer("trader", (screenId, inventory, buffer) -> new MerchantContainer(screenId, inventory, (IMerchant)inventory.player.level.getEntity(buffer.readInt())));
	public static final RegistryObject<ContainerType<CorruptedTravellerContainer>> CORRUPTED_TRAVELLER = registerContainer("corrupted_traveller", (screenId, inventory, buffer) -> new CorruptedTravellerContainer(screenId, inventory, (CorruptedTravellerEntity)inventory.player.level.getEntity(buffer.readInt())));
	public static final RegistryObject<ContainerType<BankerContainer>> BANKER = registerContainer("banker", (screenId, inventory, buffer) -> new BankerContainer(screenId, inventory, (AoATrader)inventory.player.level.getEntity(buffer.readInt())));

	private static <T extends Container> RegistryObject<ContainerType<T>> registerContainer(String id, IContainerFactory<T> factory) {
		return CONTAINERS.register(id, () -> IForgeContainerType.create(factory));
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerContainerScreens() {
		ScreenManager.register(DIVINE_STATION.get(), DivineStationScreen::new);
		ScreenManager.register(MENDING_TABLE.get(), UtilityBlockScreen::new);
		ScreenManager.register(WHITEWASHING_TABLE.get(), UtilityBlockScreen::new);
		ScreenManager.register(FRAME_BENCH.get(), FrameBenchScreen::new);
		ScreenManager.register(INFUSION_TABLE.get(), InfusionTableScreen::new);
		ScreenManager.register(TRADER.get(), MerchantScreen::new);
		ScreenManager.register(BANKER.get(), BankerScreen::new);
		ScreenManager.register(CORRUPTED_TRAVELLER.get(), CorruptedTravellerScreen::new);
	}
}

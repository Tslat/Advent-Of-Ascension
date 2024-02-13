package net.tslat.aoa3.common.registration.block;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.block.blockentity.*;

import java.util.function.Supplier;

public final class AoABlockEntities {
	public static void init() {}

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrophyBlockEntity>> TROPHY = registerTileEntity("trophy", () -> BlockEntityType.Builder.of(TrophyBlockEntity::new, AoABlocks.TROPHY.get(), AoABlocks.GOLD_TROPHY.get(), AoABlocks.ORNATE_TROPHY.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LunarCreationTableBlockEntity>> LUNAR_CREATION_TABLE = registerTileEntity("lunar_creation_table", () -> BlockEntityType.Builder.of(LunarCreationTableBlockEntity::new, AoABlocks.LUNAR_CREATION_TABLE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InfusionTableBlockEntity>> INFUSION_TABLE = registerTileEntity("infusion_table", () -> BlockEntityType.Builder.of(InfusionTableBlockEntity::new, AoABlocks.INFUSION_TABLE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ImbuingChamberBlockEntity>> IMBUING_CHAMBER = registerTileEntity("imbuing_chamber", () -> BlockEntityType.Builder.of(ImbuingChamberBlockEntity::new, AoABlocks.IMBUING_CHAMBER.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BossAltarBlockEntity>> BOSS_ALTAR = registerTileEntity("boss_altar", () -> BlockEntityType.Builder.of(BossAltarBlockEntity::new, AoABlocks.BOSS_ALTAR.get()).build(null));

	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> registerTileEntity(String registryName, Supplier<BlockEntityType<T>> tile) {
		return AoARegistries.BLOCK_ENTITIES.register(registryName, tile);
	}
}

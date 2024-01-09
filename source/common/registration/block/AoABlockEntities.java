package net.tslat.aoa3.common.registration.block;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.block.tileentity.BossAltarTileEntity;
import net.tslat.aoa3.content.block.tileentity.LunarCreationTableBlockEntity;
import net.tslat.aoa3.content.block.tileentity.TrophyTileEntity;

import java.util.function.Supplier;

public final class AoABlockEntities {
	public static void init() {}

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrophyTileEntity>> TROPHY = registerTileEntity("trophy", () -> BlockEntityType.Builder.of(TrophyTileEntity::new, AoABlocks.TROPHY.get(), AoABlocks.GOLD_TROPHY.get(), AoABlocks.ORNATE_TROPHY.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LunarCreationTableBlockEntity>> LUNAR_CREATION_TABLE = registerTileEntity("lunar_creation_table", () -> BlockEntityType.Builder.of(LunarCreationTableBlockEntity::new, AoABlocks.LUNAR_CREATION_TABLE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BossAltarTileEntity>> BOSS_ALTAR = registerTileEntity("boss_altar", () -> BlockEntityType.Builder.of(BossAltarTileEntity::new, AoABlocks.BOSS_ALTAR.get()).build(null));

	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> registerTileEntity(String registryName, Supplier<BlockEntityType<T>> tile) {
		return AoARegistries.BLOCK_ENTITIES.register(registryName, tile);
	}
}

package net.tslat.aoa3.common.registration;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.client.render.tileentity.LunarCreationTableRenderer;
import net.tslat.aoa3.client.render.tileentity.TrophyRenderer;
import net.tslat.aoa3.content.block.tileentity.LunarCreationTableTileEntity;
import net.tslat.aoa3.content.block.tileentity.TrophyTileEntity;

import java.util.function.Supplier;

public final class AoABlockEntities {
	public static void init() {}

	public static final RegistryObject<BlockEntityType<TrophyTileEntity>> TROPHY = registerTileEntity("trophy", () -> BlockEntityType.Builder.of(TrophyTileEntity::new, AoABlocks.TROPHY.get(), AoABlocks.GOLD_TROPHY.get(), AoABlocks.ORNATE_TROPHY.get()).build(null));
	public static final RegistryObject<BlockEntityType<LunarCreationTableTileEntity>> LUNAR_CREATION_TABLE = registerTileEntity("lunar_creation_table", () -> BlockEntityType.Builder.of(LunarCreationTableTileEntity::new, AoABlocks.LUNAR_CREATION_TABLE.get()).build(null));

	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerTileEntity(String registryName, Supplier<BlockEntityType<T>> tile) {
		return AoARegistries.BLOCK_ENTITIES.register(registryName, tile);
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderers() {
		BlockEntityRenderers.register(TROPHY.get(), TrophyRenderer::new);
		BlockEntityRenderers.register(LUNAR_CREATION_TABLE.get(), LunarCreationTableRenderer::new);
	}
}

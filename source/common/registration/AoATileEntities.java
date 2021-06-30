package net.tslat.aoa3.common.registration;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.block.tileentity.LunarCreationTableTileEntity;
import net.tslat.aoa3.block.tileentity.TrophyTileEntity;
import net.tslat.aoa3.client.render.tileentity.LunarCreationTableRenderer;
import net.tslat.aoa3.client.render.tileentity.TrophyRenderer;

import java.util.function.Supplier;

public final class AoATileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<TileEntityType<TrophyTileEntity>> TROPHY = registerTileEntity("trophy", () -> TileEntityType.Builder.of(TrophyTileEntity::new, AoABlocks.TROPHY.get(), AoABlocks.GOLD_TROPHY.get(), AoABlocks.ORNATE_TROPHY.get()).build(null));
	public static final RegistryObject<TileEntityType<LunarCreationTableTileEntity>> LUNAR_CREATION_TABLE = registerTileEntity("lunar_creation_table", () -> TileEntityType.Builder.of(LunarCreationTableTileEntity::new, AoABlocks.LUNAR_CREATION_TABLE.get()).build(null));

	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> registerTileEntity(String registryName, Supplier<TileEntityType<T>> tile) {
		return TILES.register(registryName, tile);
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderers() {
		ClientRegistry.bindTileEntityRenderer(TROPHY.get(), TrophyRenderer::new);
		ClientRegistry.bindTileEntityRenderer(LUNAR_CREATION_TABLE.get(), LunarCreationTableRenderer::new);
	}
}

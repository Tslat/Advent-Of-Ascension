package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.common.registration.entity.AoANpcs;
import net.tslat.aoa3.content.entity.npc.trader.StoreKeeperEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class VoxStoreCrate extends Block {
	public VoxStoreCrate() {
		super(new BlockUtil.CompactProperties(Material.WOOD, MaterialColor.TERRACOTTA_GREEN).stats(5f, 3f).get());
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		if (!world.isClientSide && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, player.getItemInHand(InteractionHand.MAIN_HAND)) == 0) {
			StoreKeeperEntity storeKeeper = new StoreKeeperEntity(AoANpcs.STORE_KEEPER.get(), world);

			storeKeeper.moveTo(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			storeKeeper.finalizeSpawn((ServerLevel)world, world.getCurrentDifficultyAt(pos), MobSpawnType.EVENT, null, null);
			world.addFreshEntity(storeKeeper);
			player.sendSystemMessage(LocaleUtil.getLocaleMessage(AoANpcs.STORE_KEEPER.get().getDescriptionId() + ".spawn"));
		}
	}
}

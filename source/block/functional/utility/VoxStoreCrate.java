package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.npc.trader.StoreKeeperEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class VoxStoreCrate extends Block {
	public VoxStoreCrate() {
		super(new BlockUtil.CompactProperties(Material.WOOD, MaterialColor.TERRACOTTA_GREEN).stats(5f, 3f).get());
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClientSide && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, player.getItemInHand(Hand.MAIN_HAND)) == 0) {
			StoreKeeperEntity storeKeeper = new StoreKeeperEntity(AoAEntities.NPCs.STORE_KEEPER.get(), world);

			storeKeeper.moveTo(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			world.addFreshEntity(storeKeeper);
			player.sendMessage(LocaleUtil.getLocaleMessage(AoAEntities.NPCs.STORE_KEEPER.get().getDescriptionId() + ".spawn"), Util.NIL_UUID);
		}
	}
}

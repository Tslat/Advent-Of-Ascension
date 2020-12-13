package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.npc.trader.StoreKeeperEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class VoxStoreCrate extends Block {
	public VoxStoreCrate() {
		super(BlockUtil.generateBlockProperties(Material.WOOD, MaterialColor.GREEN_TERRACOTTA, 5, 3, SoundType.WOOD));
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isRemote && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItem(Hand.MAIN_HAND)) == 0) {
			StoreKeeperEntity storeKeeper = new StoreKeeperEntity(AoAEntities.NPCs.STORE_KEEPER.get(), world);

			storeKeeper.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			world.addEntity(storeKeeper);
			player.sendMessage(LocaleUtil.getLocaleMessage("message.mob.voxStoreKeeper.spawn"));
		}
	}
}

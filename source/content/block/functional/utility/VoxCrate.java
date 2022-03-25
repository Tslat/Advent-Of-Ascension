package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.npc.lottoman.ToxicLottomanEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class VoxCrate extends Block {
	public VoxCrate() {
		super(new BlockUtil.CompactProperties(Material.WOOD, MaterialColor.TERRACOTTA_GREEN).stats(5f, 3f).tool(ToolType.AXE).get());
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.playerWillDestroy(world, pos, state, player);

		if (!world.isClientSide && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, player.getItemInHand(Hand.MAIN_HAND)) == 0) {
			ToxicLottomanEntity lottoman = new ToxicLottomanEntity(AoAEntities.NPCs.TOXIC_LOTTOMAN.get(), world);

			lottoman.moveTo(pos.getX(), pos.getY() + 0.5, pos.getZ(), 0, 0);
			lottoman.finalizeSpawn((ServerWorld)world, world.getCurrentDifficultyAt(pos), SpawnReason.EVENT, null, null);
			world.addFreshEntity(lottoman);
			player.sendMessage(LocaleUtil.getLocaleMessage(AoAEntities.NPCs.TOXIC_LOTTOMAN.get().getDescriptionId() + ".spawn"), Util.NIL_UUID);
		}
	}
}

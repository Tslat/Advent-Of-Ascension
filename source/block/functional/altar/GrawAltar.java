package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.GrawEntity;
import net.tslat.aoa3.entity.mob.lelyetia.FlyeEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class GrawAltar extends BossAltarBlock {
	public GrawAltar() {
		super(MaterialColor.ORANGE_TERRACOTTA);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack heldItem = player.getHeldItem(hand);

		if (heldItem.getItem() == AoAItems.ORANGE_SPORES.get() || heldItem.getItem() == AoAItems.YELLOW_SPORES.get()) {
			if (!world.isRemote) {
				world.addEntity(new FlyeEntity(world, pos));

				if (!player.isCreative())
					heldItem.shrink(1);
			}

			return ActionResultType.SUCCESS;
		}
		else {
			return super.onBlockActivated(state, world, pos, player, hand, hit);
		}
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		GrawEntity graw = new GrawEntity(AoAEntities.Mobs.GRAW.get(), player.world);

		graw.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.addEntity(graw);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.graw.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.GUARDIANS_EYE.get();
	}
}

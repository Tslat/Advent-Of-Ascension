package net.tslat.aoa3.object.block.functional.light;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.List;

public class VoxLight extends Block {
	public VoxLight() {
		super(new BlockUtil.CompactProperties(Material.GLASS, MaterialColor.TERRACOTTA_GREEN).stats(0.6f, 1.2f).light(8).get());
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.ACTIVE_RUNE_STONE.get() && WorldUtil.isWorld(world, AoADimensions.MYSTERIUM.key)) {
			if (!world.isClientSide) {
				List<ItemEntity> itemsList = world.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));

				if (itemsList.size() > 1) {
					ItemEntity realmstone = null;
					ItemEntity runicEnergy = null;

					for (ItemEntity entity : itemsList) {
						Item item = entity.getItem().getItem();

						if (item == AoAItems.BLANK_REALMSTONE.get()) {
							realmstone = entity;
						}
						else if (item == AoAItems.RUNIC_ENERGY.get()) {
							runicEnergy = entity;
						}

						if (realmstone != null && runicEnergy != null) {
							player.getItemInHand(hand).shrink(1);
							realmstone.setItem(new ItemStack(AoAItems.RUNANDOR_REALMSTONE.get()));
							runicEnergy.remove();


						}
					}
				}
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}

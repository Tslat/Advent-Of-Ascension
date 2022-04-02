package net.tslat.aoa3.content.block.functional.light;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.List;

public class VoxLight extends Block {
	public VoxLight() {
		super(new BlockUtil.CompactProperties(Material.GLASS, MaterialColor.TERRACOTTA_GREEN).stats(0.6f, 1.2f).light(8).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.ACTIVE_RUNE_STONE.get() && WorldUtil.isWorld(world, AoADimensions.MYSTERIUM.key)) {
			if (!world.isClientSide) {
				List<ItemEntity> itemsList = world.getEntitiesOfClass(ItemEntity.class, new AABB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));

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
							runicEnergy.discard();
						}
					}
				}
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}

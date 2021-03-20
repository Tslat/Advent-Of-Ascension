package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAArmour;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.HashSet;

public class LunarCreationTable extends Block {
	public LunarCreationTable() {
		super(BlockUtil.generateBlockProperties(Material.STONE, MaterialColor.COLOR_PURPLE, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isClientSide) {
			BlockPos lunarPos = null;
			BlockPos moonlightPos = null;
			BlockPos darklightPos = null;
			BlockPos sunfirePos = null;
			BlockPos duskPos = null;

			for (int x = -2; x <= 2; x += 2) {
				for (int z = -2; z <= 2; z += 2) {
					BlockPos checkPos = pos.offset(x, 0, z);
					BlockState block = world.getBlockState(checkPos);

					if (block.getBlock() == AoABlocks.LUNAR_ORB.get()) {
						lunarPos = checkPos;
					}
					else if (block.getBlock() == AoABlocks.MOONLIGHT_ORB.get()) {
						moonlightPos = checkPos;
					}
					else if (block.getBlock() == AoABlocks.DARKLIGHT_ORB.get()) {
						darklightPos = checkPos;
					}
					else if (block.getBlock() == AoABlocks.DUSK_ORB.get()) {
						duskPos = checkPos;
					}
					else if (block.getBlock() == AoABlocks.SUNFIRE_ORB.get()) {
						sunfirePos = checkPos;
					}
				}
			}

			if (lunarPos != null && moonlightPos != null) {
				if (darklightPos != null && sunfirePos != null && duskPos != null) {
					HashSet<Item> armours = new HashSet<Item>(4);

					armours.add(AoAArmour.LUNAR_ARMOUR.boots.get());
					armours.add(AoAArmour.LUNAR_ARMOUR.leggings.get());
					armours.add(AoAArmour.LUNAR_ARMOUR.chestplate.get());
					armours.add(AoAArmour.LUNAR_ARMOUR.helmet.get());

					for (ItemStack stack : player.inventory.items) {
						armours.removeIf(item -> item == stack.getItem());
					}

					for (ItemStack stack : player.inventory.armor) {
						armours.removeIf(item -> item == stack.getItem());
					}

					for (ItemStack stack : player.inventory.armor) {
						armours.removeIf(item -> item == stack.getItem());
					}

					if (armours.isEmpty()) {
						armours.add(AoAArmour.LUNAR_ARMOUR.boots.get());
						armours.add(AoAArmour.LUNAR_ARMOUR.leggings.get());
						armours.add(AoAArmour.LUNAR_ARMOUR.chestplate.get());
						armours.add(AoAArmour.LUNAR_ARMOUR.helmet.get());
					}

					Item armourPiece = (Item)armours.toArray()[player.getRandom().nextInt(armours.size())];

					world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5d, pos.getY() + 1.5d, pos.getZ() + 0.5d, new ItemStack(armourPiece)));
					WorldUtil.toAir(world, lunarPos, moonlightPos, duskPos, sunfirePos, darklightPos);
				}
				else {
					world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5d, pos.getY() + 1.5d, pos.getZ() + 0.5d, new ItemStack(AoAItems.OBSERVING_EYE.get())));
					WorldUtil.toAir(world, lunarPos, moonlightPos);
				}
			}
		}

		return ActionResultType.SUCCESS;
	}
}

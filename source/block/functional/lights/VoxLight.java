package net.tslat.aoa3.block.functional.lights;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.List;

public class VoxLight extends LightBlock {
	public VoxLight() {
		super("VoxLight", "vox_light", Material.GLASS, 0.6f, 1.2f, 0.5f);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(hand).getItem() == ItemRegister.ACTIVE_RUNE_STONE && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.mysterium) {
			if (!world.isRemote) {
				List<EntityItem> itemsList = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));

				if (itemsList.size() > 1) {
					EntityItem realmstone = null;
					EntityItem runicEnergy = null;

					for (EntityItem entity : itemsList) {
						Item item = entity.getItem().getItem();

						if (item == ItemRegister.BLANK_REALMSTONE) {
							realmstone = entity;
						}
						else if (item == ItemRegister.RUNIC_ENERGY) {
							runicEnergy = entity;
						}

						if (realmstone != null && runicEnergy != null) {
							player.getHeldItem(hand).shrink(1);
							realmstone.setItem(new ItemStack(ItemRegister.RUNANDOR_REALMSTONE));
							runicEnergy.setDead();
						}
					}
				}
			}

			return true;
		}

		return false;
	}
}

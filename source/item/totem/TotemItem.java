package net.tslat.aoa3.item.totem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

public abstract class TotemItem extends Item {
	public TotemItem(String name, String registryName) {
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.totemsTab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			summonTotemEntity(world, player, pos);
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.entityIdolSpawn, SoundCategory.PLAYERS, 1.0f, 1.0f);

			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand).shrink(1);
		}

		return EnumActionResult.SUCCESS;
	}

	protected abstract void summonTotemEntity(World world, EntityPlayer player, BlockPos pos);
}

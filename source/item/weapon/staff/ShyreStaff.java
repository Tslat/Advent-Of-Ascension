package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityShyreShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class ShyreStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeEnergy, 3);
		runes.put(ItemRegister.runeDistortion, 3);
	}

	public ShyreStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("ShyreStaff");
		setRegistryName("aoa3:shyre_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityShyreShot(caster, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {
		shot.motionX = -shot.motionX * 0.1;
		shot.motionZ = -shot.motionZ * 0.1;
		shot.motionY = -shot.motionY * 0.1;

		block = new BlockPos(shot.posX, shot.posY, shot.posZ);
		Material blockMaterial = shooter.world.getBlockState(block).getMaterial();

		while (blockMaterial != Material.AIR) {
			block = block.up();
			blockMaterial = shooter.world.getBlockState(block).getMaterial();
		}

		shooter.setPositionAndUpdate(block.getX(), block.getY(), block.getZ());

		if (shooter instanceof EntityPlayerMP && shooter.world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.lunalus)
			ModUtil.completeAdvancement((EntityPlayerMP)shooter, "lunalus/200_iq", "lunalus_shyre_staff_travel");
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		shooter.setPositionAndUpdate(shot.posX, shot.posY + 0.5D, shot.posZ);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.ShyreStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}

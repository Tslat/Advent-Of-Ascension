package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityShyreShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.ModUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class ShyreStaff extends BaseStaff {
	public ShyreStaff(int durability) {
		super(durability);
		setTranslationKey("ShyreStaff");
		setRegistryName("aoa3:shyre_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.SHYRE_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.ENERGY_RUNE, 3);
		runes.put(ItemRegister.DISTORTION_RUNE, 3);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityShyreShot(caster, this, 120));
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
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		shooter.setPositionAndUpdate((target.posX + shot.posX) / 2d, (target.posY + shot.posY) / 2d, (target.posZ + shot.posZ) / 2d);

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ShyreStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

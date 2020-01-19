package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public class SoulfireBow extends BaseBow {
	public SoulfireBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("SoulfireBow");
		setRegistryName("aoa3:soulfire_bow");
	}

	@Override
	protected EntityHollyArrow makeArrow(EntityLivingBase shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		EntityHollyArrow arrow = super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);

		if (arrow != null && shooter instanceof EntityPlayer && PlayerUtil.consumeResource((EntityPlayer)shooter, Enums.Resources.SOUL, 50, false))
			arrow.setGlowing(true);

		return arrow;
	}

	@Override
	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter, float damage) {
		if (arrow.isGlowing() && shooter instanceof EntityLivingBase && !shooter.isDead)
			((EntityLivingBase)shooter).heal(8);

		arrow.setGlowing(false);
	}

	@Override
	public void doBlockImpact(EntityHollyArrow arrow, IBlockState blockState, Entity shooter) {
		arrow.setGlowing(false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SoulfireBow.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SoulfireBow.desc.2", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

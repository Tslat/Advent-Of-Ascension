package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class DaybreakerBow extends BaseBow {
	public DaybreakerBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("DaybreakerBow");
		setRegistryName("aoa3:daybreaker_bow");
	}

	@Override
	protected EntityHollyArrow makeArrow(EntityLivingBase shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		shooter.world.spawnEntity(super.makeArrow(shooter, bowStack, ammoStack, velocity * 0.6f, false));
		shooter.world.spawnEntity(super.makeArrow(shooter, bowStack, ammoStack, velocity * 0.85f, false));
		return super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.DaybreakerBow.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}

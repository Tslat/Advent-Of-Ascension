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

public class LunarBow extends BaseBow {
	private final double baseDmg;
	private final double maxDmg = 25;
	private final float drawSpeedMultiplier;

	public LunarBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("LunarBow");
		setRegistryName("aoa3:lunar_bow");
		this.baseDmg = damage;
		this.drawSpeedMultiplier = drawSpeedMultiplier;
	}

	@Override
	public void doArrowMods(EntityHollyArrow arrow, EntityLivingBase shooter) {
		arrow.setDamage(itemRand.nextInt((int)maxDmg + 1 - (int)baseDmg) + baseDmg);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.random", TextFormatting.DARK_GREEN, Double.toString(baseDmg), Double.toString(maxDmg)));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.bow.drawSpeed", Double.toString(((int)(72000 / drawSpeedMultiplier) / 720) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.arrows", TextFormatting.LIGHT_PURPLE));
	}
}

package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.item.misc.PopShot;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class Slingshot extends BaseBow {
	private float drawSpeedMultiplier;
	private double dmg;

	public Slingshot(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		this.drawSpeedMultiplier = drawSpeedMultiplier;
		this.dmg = damage;
		setUnlocalizedName("Slingshot");
		setRegistryName("aoa3:slingshot");
	}

	@Override
	protected EntityHollyArrow makeArrow(EntityLivingBase shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		if (ammoStack.getItem() == ItemRegister.hollyArrow)
			ammoStack = new ItemStack(ItemRegister.popShot);

		return super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);
	}

	@Override
	public void doImpactEffect(EntityHollyArrow shot, Entity target, Entity shooter) {
		shot.world.createExplosion(shooter, shot.posX, shot.posY, shot.posZ, 1.0f, false);
		shot.setDead();
	}

	@Override
	public void doBlockImpact(EntityHollyArrow shot, IBlockState blockState, Entity shooter) {
		shot.world.createExplosion(shooter, shot.posX, shot.posY, shot.posZ, 1.0f, false);
		shot.setDead();
	}

	@Override
	protected boolean isArrow(ItemStack stack) {
		return stack.getItem() instanceof PopShot;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.arrows", TextFormatting.DARK_RED, Double.toString(dmg)));
		tooltip.add(StringUtil.getColourLocaleString("item.Slingshot.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.bow.drawSpeed", Double.toString(((72000 / drawSpeedMultiplier) / 720) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.other", TextFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.PopShot.name")));
	}
}

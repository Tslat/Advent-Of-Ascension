package net.tslat.aoa3.item.weapon.archergun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityHollyArrowShot;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseArchergun extends BaseGun {
	private double dmg;
	protected int firingDelay;

	public BaseArchergun(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);
		this.dmg = dmg;
		this.firingDelay = fireDelayTicks;
		setCreativeTab(CreativeTabsRegister.ARCHERGUNS);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.ARCHERGUN_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		return findAndConsumeAmmo(player, gun, hand,true);
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand, boolean consume) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, consume, ItemRegister.HOLLY_ARROW, player.getHeldItem(hand));

		if (ammo != null) {
			EntityHollyArrowShot arrow = new EntityHollyArrowShot(player, gun, hand,120, 0);
			arrow.motionY += 0.25f;
			return arrow;
		}

		return null;
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet arrow, float bulletDmgMultiplier) {
		if (target != null && EntityUtil.dealRangedDamage(target, shooter, arrow, (float)dmg))
			doImpactEffect(target, shooter, arrow, bulletDmgMultiplier);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.arrows", TextFormatting.DARK_RED, Double.toString(dmg)));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.arrows", TextFormatting.LIGHT_PURPLE));
	}
}

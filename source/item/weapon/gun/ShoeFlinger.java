package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityShoeShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ShoeFlinger extends BaseGun {
	double dmg;
	int firingDelay;

	public ShoeFlinger(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
		setTranslationKey("ShoeFlinger");
		setRegistryName("aoa3:shoe_flinger");
		setCreativeTab(null);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.FLINGER_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, ItemStack gunStack, EnumHand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(Items.LEATHER_BOOTS), true, 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, gunStack)))
			return new EntityShoeShot(player, (BaseGun)gunStack.getItem(), hand, 120, 0);

		return null;
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			float shellMod = 1;

			if (bullet.getHand() != null)
				shellMod += 0.1 * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.SHELL, shooter.getHeldItem(bullet.getHand()));

			if (EntityUtil.dealGunDamage(target, shooter, bullet, (float)dmg * bulletDmgMultiplier * shellMod) && target instanceof EntityLivingBase) {
				EntityUtil.doScaledKnockback((EntityLivingBase)target, shooter, 1.35f, shooter.posX - target.posX, shooter.posZ - target.posZ);

				if (shooter instanceof EntityPlayerMP && ((EntityLivingBase)target).getHealth() == 0 && (target instanceof BossEntity || !target.isNonBoss()))
					ModUtil.completeAdvancement((EntityPlayerMP)shooter, "overworld/la_chancla", "boss_kill");
			}
		}

		if (target instanceof EntityLivingBase) {

		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.gun", TextFormatting.DARK_RED, StringUtil.roundToNthDecimalPlace((float)getDamage() * (1 + (0.1f * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.SHELL, stack))), 2)));
		tooltip.add(StringUtil.getColourLocaleString("item.ShoeFlinger.desc.1", TextFormatting.DARK_AQUA));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.other", TextFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.bootsCloth.name")));
	}
}

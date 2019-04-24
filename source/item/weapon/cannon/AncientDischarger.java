package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityTriDischargeShot;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class AncientDischarger extends BaseCannon implements AdventWeapon {
	int firingDelay;

	public AncientDischarger(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		this.firingDelay = firingDelayTicks;
		setUnlocalizedName("AncientDischarger");
		setRegistryName("aoa3:ancient_discharger");
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			float mod = 0.0f;

			if (target instanceof EntityLivingBase)
				mod += ((EntityLivingBase)target).getEntityAttribute(SharedMonsterAttributes.ARMOR).getBaseValue() * 7 / 100;

			bullet.world.createExplosion(shooter, bullet.posX, bullet.posY, bullet.posZ, 2.0f + mod, false);
		}
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.dischargeCapsule, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityTriDischargeShot(player, gun, hand,120, 0);

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.AncientDischarger.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.other", TextFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.DischargeCapsule.name")));
	}
}

package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class Pulsator extends BaseGun implements AdventWeapon {
	public Pulsator(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("Pulsator");
		setRegistryName("aoa3:pulsator");
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);

		if (target instanceof EntityLivingBase && ((EntityLivingBase)target).getHealth() > 0.0f)
			target.world.addWeatherEffect(new EntityLightningBolt(target.world, bullet.posX, bullet.posY, bullet.posZ, false));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.Pulsator.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}

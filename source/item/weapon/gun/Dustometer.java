package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.thrown.EntityGrenade;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Dustometer extends BaseGun {
	public Dustometer(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Dustometer");
		setRegistryName("aoa3:dustometer");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.FAST_RIFLE_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		BaseBullet bullet = super.findAndConsumeAmmo(player, gun, hand);

		if (bullet != null && !player.world.isRemote && itemRand.nextInt(3) == 0)
			player.world.spawnEntity(new EntityGrenade(player, gun, hand, 120, 0));

		return bullet;
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (!(bullet instanceof EntityGrenade))
			super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Dustometer.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

package net.tslat.aoa3.item.weapon.shotgun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityLimoniteBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class DestructionShotgun extends BaseShotgun implements AdventWeapon {
	public DestructionShotgun(final double dmg, final int pellets, final SoundEvent sound, final int durability, final int fireDelayTicks, final float recoil) {
		super(dmg, pellets, sound, durability, fireDelayTicks, recoil);
		setUnlocalizedName("DestructionShotgun");
		setRegistryName("aoa3:destruction_shotgun");
	}

	@Override
	public void fireShotgun(EntityLivingBase shooter, EnumHand hand, float spreadFactor, int pellets) {
		boolean charged = itemRand.nextBoolean();
		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new EntityLimoniteBullet(shooter, this, hand, 4, charged ? 1.5f : 1.0f, 0, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor);
			shooter.world.spawnEntity(pellet);
		}
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);

		if (target instanceof EntityLivingBase)
			EntityUtil.doScaledKnockback((EntityLivingBase)target, shooter, 0.4f, shooter.posX - target.posX, shooter.posZ - target.posZ);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.DestructionShotgun.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.DestructionShotgun.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("items.description.knockback.veryStrong", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}

package net.tslat.aoa3.item.weapon.cannon;

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
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityHeavyBlueCannonball;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class CoralCannon extends BaseCannon {
	public CoralCannon(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("CoralCannon");
		setRegistryName("aoa3:coral_cannon");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.HIGH_CANNON_FIRE;
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);

		if (target instanceof EntityLivingBase)
			EntityUtil.healEntity(shooter, 1.0f);
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.CANNONBALL, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityHeavyBlueCannonball(player, gun, hand, 120, 0);

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.CoralCannon.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}

package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityRedBullet;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Dragilator extends BaseGun {
	public Dragilator(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Dragilator");
		setRegistryName("aoa3:dragilator");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.GOLEM_GUN_FIRE;
	}

	@Override
	protected void doImpactEffect(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		target.setFire(5);
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand, boolean consume) {
		Item ammo = ItemUtil.findAndConsumeBullet(player, gun, consume, player.getHeldItem(hand));

		if (ammo != null) {
			switch (ammo.getTranslationKey()) {
				case "item.LimoniteBullet":
					return new EntityRedBullet(player, gun, hand, 120, 0);
				default:
					return null;
			}
		}

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.fire", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.item.misc.HollyArrow;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class DaybreakerBow extends BaseBow {
	public DaybreakerBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("DaybreakerBow");
		setRegistryName("aoa3:daybreaker_bow");
	}

	@Override
	protected EntityHollyArrow makeArrow(EntityLivingBase shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		EntityHollyArrow centralArrow = super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);

		if (shooter.rotationPitch < -70) {
			for (double x = -0.5; x <= 0.5; x += 0.5) {
				for (double z = -0.5; z <= 0.5; z += 0.5) {
					if (x == 0 && z == 0)
						continue;

					EntityHollyArrow arrow = copyArrow(shooter, bowStack, centralArrow, velocity, ammoStack);

					arrow.setPositionAndUpdate(arrow.posX + x, arrow.posY, arrow.posZ + z);
					arrow.world.spawnEntity(arrow);
				}
			}
		}

		return centralArrow;
	}

	private EntityHollyArrow copyArrow(EntityLivingBase shooter, ItemStack bowStack, EntityHollyArrow arrow, float velocity, ItemStack ammoStack) {
		EntityHollyArrow newArrow = ((HollyArrow)(ammoStack.getItem() instanceof HollyArrow ? ammoStack.getItem() : ItemRegister.hollyArrow)).createArrow(arrow.world, this, ammoStack, shooter, dmg);
		newArrow.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, velocity * 3.0F, 2.0F);

		newArrow.setIsCritical(arrow.getIsCritical());
		newArrow.setDamage(arrow.getDamage());
		newArrow.setKnockbackStrength(arrow.getKnockbackStrength());
		newArrow.setFire(EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack) * 100);
		newArrow.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;

		return newArrow;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.DaybreakerBow.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

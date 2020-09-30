package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.item.misc.HollyArrow;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class NightmareBow extends BaseBow {
	public NightmareBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("NightmareBow");
		setRegistryName("aoa3:nightmare_bow");
	}

	@Override
	protected EntityHollyArrow makeArrow(EntityLivingBase shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		double xOffset = MathHelper.cos(shooter.rotationYaw / 180.0F * (float)Math.PI) * 0.7F;
		double zOffset = MathHelper.sin(shooter.rotationYaw / 180.0F * (float)Math.PI) * 0.7F;

		EntityHollyArrow centralArrow = super.makeArrow(shooter, bowStack, ammoStack, velocity, consumeAmmo);
		EntityHollyArrow leftArrow = copyArrow(shooter, bowStack, centralArrow, velocity, ammoStack);
		EntityHollyArrow rightArrow = copyArrow(shooter, bowStack, centralArrow, velocity, ammoStack);

		leftArrow.setPositionAndUpdate(leftArrow.posX + xOffset, leftArrow.posY, leftArrow.posZ + zOffset);
		rightArrow.setPositionAndUpdate(rightArrow.posX - xOffset, rightArrow.posY, rightArrow.posZ - zOffset);
		shooter.world.spawnEntity(leftArrow);
		shooter.world.spawnEntity(rightArrow);

		return centralArrow;
	}

	private EntityHollyArrow copyArrow(EntityLivingBase shooter, ItemStack bowStack, EntityHollyArrow arrow, float velocity, ItemStack ammoStack) {
		EntityHollyArrow newArrow = ((HollyArrow)(ammoStack.getItem() instanceof HollyArrow ? ammoStack.getItem() : ItemRegister.HOLLY_ARROW)).createArrow(arrow.world, this, ammoStack, shooter, getDamage());
		newArrow.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);

		newArrow.setIsCritical(arrow.getIsCritical());
		newArrow.setDamage(arrow.getDamage());
		newArrow.setKnockbackStrength(arrow.getKnockbackStrength());
		newArrow.setFire(EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack) * 100);
		newArrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;

		return newArrow;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NightmareBow.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntityPopShot;
import net.tslat.aoa3.item.misc.HollyArrow;
import net.tslat.aoa3.item.misc.PopShot;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class Slingshot extends BaseBow {
	private float drawSpeedMultiplier;
	private double dmg;

	public Slingshot(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		this.drawSpeedMultiplier = drawSpeedMultiplier;
		this.dmg = damage;
		setTranslationKey("Slingshot");
		setRegistryName("aoa3:slingshot");
	}

	@Override
	protected EntityHollyArrow makeArrow(EntityLivingBase shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		EntityPopShot arrowEntity = new EntityPopShot(shooter.world, this, shooter, dmg, ammoStack.getItem() instanceof HollyArrow);
		arrowEntity.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);

		int powerEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);
		int punchEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bowStack);

		if (velocity >= 1.0F)
			arrowEntity.setIsCritical(true);

		if (powerEnchant > 0)
			arrowEntity.setDamage(arrowEntity.getDamage() + (double)powerEnchant * 1.5D + 1D);

		if (punchEnchant > 0)
			arrowEntity.setKnockbackStrength(punchEnchant);

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack) > 0)
			arrowEntity.setFire(100);

		if (!consumeAmmo || (shooter instanceof EntityPlayer && ((EntityPlayer)shooter).capabilities.isCreativeMode) && (ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW))
			arrowEntity.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;

		bowStack.damageItem(1, shooter);

		if (shooter instanceof EntityPlayer && consumeAmmo) {
			ammoStack.shrink(1);

			if (ammoStack.isEmpty())
				((EntityPlayer)shooter).inventory.deleteStack(ammoStack);
		}

		return arrowEntity;
	}

	@Override
	public void doImpactEffect(EntityHollyArrow shot, Entity target, Entity shooter, float damage) {
		if (shot instanceof EntityPopShot && ((EntityPopShot)shot).isExplosive)
			WorldUtil.createExplosion(shooter, shot.world, shot, 1.0f);

		shot.setDead();
	}

	@Override
	public void doBlockImpact(EntityHollyArrow shot, IBlockState blockState, Entity shooter) {
		if (shot instanceof EntityPopShot && ((EntityPopShot)shot).isExplosive)
			WorldUtil.createExplosion(shooter, shot.world, shot, 1.0f);

		shot.setDead();
	}

	@Override
	protected boolean isArrow(ItemStack stack) {
		return stack.getItem() instanceof PopShot || stack.getItem() == Items.FLINT;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.arrows", TextFormatting.DARK_RED, Double.toString(dmg)));
		tooltip.add(StringUtil.getColourLocaleString("item.Slingshot.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.bow.drawSpeed", Double.toString(((72000 / drawSpeedMultiplier) / 720) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.other", TextFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.PopShot.name") + "/" + StringUtil.getLocaleString("item.flint.name")));
	}
}

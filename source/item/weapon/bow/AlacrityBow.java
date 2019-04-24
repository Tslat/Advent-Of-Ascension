package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntityTippedHollyArrow;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class AlacrityBow extends BaseBow {
	public AlacrityBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("AlacrityBow");
		setRegistryName("aoa3:alacrity_bow");
	}

	@Override
	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter) {
		if (target instanceof EntityLivingBase) {
			if (arrow instanceof EntityTippedHollyArrow && shooter instanceof EntityLivingBase && ((EntityTippedHollyArrow)arrow).getBasePotionEffect() == MobEffects.SLOWNESS) {
				if (!arrow.world.isRemote) {
					EntityPotion pot = new EntityPotion(arrow.world, (EntityLivingBase)shooter, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.SLOWNESS));
					pot.setPosition(target.posX, target.posY + target.getEyeHeight(), target.posZ);
					pot.motionY -= 1.0f;
					arrow.world.spawnEntity(pot);
				}
			}

			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 2));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.slow", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}

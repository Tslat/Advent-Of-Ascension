package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class PrimordialGreatblade extends BaseGreatblade {
	public PrimordialGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("PrimordialGreatblade");
		setRegistryName("aoa3:primordial_greatblade");
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, EntityLivingBase attacker, double baseDmg) {
		if (!(target instanceof EntityLivingBase))
			return super.getDamageForAttack(stack, target, attacker, baseDmg);

		float extraDmg = 0;
		EntityLivingBase livingTarget = (EntityLivingBase)target;
		float maxHealth = livingTarget.getMaxHealth();

		if (maxHealth <= 100) {
			extraDmg = maxHealth / 50f;
		}
		else if (maxHealth <= 300) {
			extraDmg = maxHealth / 100f;
		}
		else if (maxHealth <= 1000) {
			extraDmg = maxHealth / 250f;
		}
		else {
			extraDmg = Math.min(maxHealth / 300f, 5);
		}

		return (float)baseDmg + extraDmg;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.PrimordialGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}

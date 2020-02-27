package net.tslat.aoa3.item.weapon.maul;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class HorizonMaul extends BaseMaul implements AdventWeapon {
	public HorizonMaul(Float dmg, Double speed, double knockback, int durability) {
		super(dmg, speed, knockback, durability);
		setTranslationKey("HorizonMaul");
		setRegistryName("aoa3:horizon_maul");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityPlayer attacker, Entity target, float finalDmg, float attackCooldown) {
		if (target instanceof EntityLivingBase && attackCooldown == 1f && !EntityUtil.isSpecExempt(target, attacker))
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 35, 0, true, true));
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.HorizonMaul.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

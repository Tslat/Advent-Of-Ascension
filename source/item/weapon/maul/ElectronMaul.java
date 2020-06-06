package net.tslat.aoa3.item.weapon.maul;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public class ElectronMaul extends BaseMaul {
	public ElectronMaul(Float dmg, Double speed, double knockback, int durability) {
		super(dmg, speed, knockback, durability);
		setTranslationKey("ElectronMaul");
		setRegistryName("aoa3:electron_maul");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityPlayer attacker, Entity target, float finalDmg, float attackCooldown) {
		final float crushMod = 1 + 0.15f * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.CRUSH, stack);
		PlayerDataManager.PlayerStats plStats = PlayerUtil.getAdventPlayer(attacker).stats();
		final float energyMultiplier = 1 + (plStats.getResourceValue(Enums.Resources.ENERGY) / 100f);

		if (plStats.consumeResource(Enums.Resources.ENERGY, plStats.getResourceValue(Enums.Resources.ENERGY), false)) {
			EntityUtil.doScaledKnockback((EntityLivingBase)target, attacker, (float)knockback * crushMod * attackCooldown * energyMultiplier, attacker.posX - target.posX, attacker.posZ - target.posZ);

			if (energyMultiplier >= 2.33)
				attacker.world.addWeatherEffect(new EntityLightningBolt(attacker.world, target.posX, target.posY, target.posZ, true));
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ElectronMaul.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

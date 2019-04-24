package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURKNIGHT;

public class KnightArmour extends AdventArmour {
	public KnightArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURKNIGHT, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.KNIGHT;
	}

	@Override
	public void handleDamageTriggers(LivingDamageEvent event, AdventPlayerCapability cap) {
		if (EntityUtil.isPhysicalDamage(event.getSource(), event.getEntity(), event.getAmount())) {
			if (cap.isCooledDown(Enums.Counters.KNIGHT)) {
				switch (itemRand.nextInt(4)) {
					case 0:
						cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 80, 1, true, false));
						break;
					case 1:
						cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 80, 1, true, false));
						break;
					case 2:
						cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED, 80, 1, true, false));
						break;
					case 3:
						cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 80, 1, true, false));
						break;
				}

				cap.setCooldown(Enums.Counters.KNIGHT, 100 + (int)(itemRand.nextDouble() * 100));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.KnightArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.KnightArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}

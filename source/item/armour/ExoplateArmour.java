package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUREXOPLATE;

public class ExoplateArmour extends AdventArmour {
	public ExoplateArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOUREXOPLATE, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.EXOPLATE;
	}

	@Override
	public void handleDamageReductions(LivingHurtEvent event, AdventPlayerCapability cap) {
		if (EntityUtil.isPhysicalDamage(event.getSource(), event.getEntity(), event.getAmount())) {
			if (cap.getPlayer().getFoodStats().getFoodLevel() > 0) {
				cap.getPlayer().getFoodStats().setFoodLevel(cap.getPlayer().getFoodStats().getFoodLevel() - (int)(event.getAmount() * 0.4));
				event.setAmount(event.getAmount() * 0.4f);
			}
		}
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		if (cap.getPlayer().getFoodStats().getFoodLevel() <= 0 && !cap.getPlayer().isPotionActive(MobEffects.POISON))
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0, true, false));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.ExoplateArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.ExoplateArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}

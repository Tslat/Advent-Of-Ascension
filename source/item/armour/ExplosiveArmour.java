package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUREXPLOSIVE;

public class ExplosiveArmour extends AdventArmour {
	public ExplosiveArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOUREXPLOSIVE, name, registryName, renderIndex, slot);
	}

	@Override
	public void handleAttackImmunities(LivingAttackEvent event, AdventPlayerCapability cap) {
		if (!event.getEntity().world.isRemote && event.getSource().isExplosion()) {
			event.setCanceled(true);
			cap.getPlayer().inventory.damageArmor(15 + itemRand.nextInt(15) + event.getAmount());
		}
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.EXPLOSIVE;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.ExplosiveArmour.desc.1", TextFormatting.DARK_GREEN));
	}
}

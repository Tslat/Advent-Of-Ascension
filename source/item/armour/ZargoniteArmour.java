package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;
import java.util.UUID;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURZARGONITE;

public class ZargoniteArmour extends AdventArmour {
	private static final AttributeModifier mod = new AttributeModifier(UUID.fromString(Enums.AttributeUUIDS.MAX_HEALTH), "AoAZargoniteBuff", 10, 0);

	public ZargoniteArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURZARGONITE, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ZARGONITE;
	}

	@Override
	public void setUnequipEffect(AdventPlayerCapability cap) {
		cap.getPlayer().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(mod);
	}

	@Override
	public void setEquipEffect(AdventPlayerCapability cap) {
		IAttributeInstance attribute = cap.getPlayer().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);

		if (!attribute.hasModifier(mod))
			attribute.applyModifier(mod);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.ZargoniteArmour.desc.1", TextFormatting.DARK_GREEN));
	}
}

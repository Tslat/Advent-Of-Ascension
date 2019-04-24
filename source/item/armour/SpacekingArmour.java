package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.entity.minions.EntityOrbling;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURSPACEKING;

public class SpacekingArmour extends AdventArmour {
	public SpacekingArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURSPACEKING, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.SPACEKING;
	}

	@Override
	public void handleDamageTriggers(LivingDamageEvent event, AdventPlayerCapability cap) {
		EntityPlayer player = cap.getPlayer();

		if (!player.world.isRemote && player.getHealth() > 0 && itemRand.nextInt(3) == 0) {
			EntityOrbling orbling = new EntityOrbling(player.world);

			orbling.setPosition(player.posX, player.posY + 1.5, player.posZ);
			orbling.setOwnerId(player.getUniqueID());
			player.world.spawnEntity(orbling);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.SpacekingArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.SpacekingArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.SpacekingArmour.desc.3", TextFormatting.DARK_GREEN));
	}
}

package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURARCHAIC;

public class ArchaicArmour extends AdventArmour {
	public ArchaicArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURARCHAIC, name, registryName, renderIndex, slot);
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		EntityPlayer pl = cap.getPlayer();

		if (EntityUtil.checkBelowHealthPercentThreshold(cap.getPlayer(), 20)) {
			for (final EntityMob e : pl.world.getEntitiesWithinAABB(EntityMob.class, pl.getEntityBoundingBox().grow(6.0f))) {
				e.addVelocity(pl.motionX * 7.5, pl.motionY * 0.5, pl.motionZ * 7.5);
			}

			cap.setCooldown(Enums.Counters.ARCHAIC, 1600);
		}
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ARCHAIC;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.ArchaicArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.ArchaicArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}

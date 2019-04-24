package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.skills.HaulingUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURHAULING;

public class HaulingArmour extends AdventArmour implements SkillItem {
	public HaulingArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURHAULING, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.HAULING;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.HAULING;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	public static HaulingUtil.HaulingDrop doLootBonusCheck(AdventPlayerCapability cap, HaulingUtil.HaulingDrop initialDrop) {
		HaulingUtil.HaulingDrop alternateDrop = HaulingUtil.getLoot(cap.getLevel(Enums.Skills.HAULING));

		return alternateDrop.xp > initialDrop.xp ? alternateDrop : initialDrop;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		EntityPlayer pl = cap.getPlayer();

		if (pl.isInWater()) {
			pl.setAir(10);
			EntityUtil.healEntity(pl, 0.07f);

			if (pl.motionY > 0.0d && pl.motionY <= 0.4d)
				pl.motionY *= 1.3d;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.HaulingArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.HaulingArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.HaulingArmour.desc.3", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.HaulingArmour.desc.4", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 100) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, Integer.toString(100), StringUtil.getLocaleString("skills.hauling.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, Integer.toString(100), StringUtil.getLocaleString("skills.hauling.name")));
		}
	}
}

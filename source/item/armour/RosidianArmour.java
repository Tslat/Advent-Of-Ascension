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
import net.tslat.aoa3.entity.minions.EntityRosid;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURROSIDIAN;

public class RosidianArmour extends AdventArmour {
	public RosidianArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURROSIDIAN, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ROSIDIAN;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		if (!cap.getPlayer().world.isRemote && cap.isCooledDown(Enums.Counters.ROSID)) {
			EntityPlayer player = cap.getPlayer();
			EntityRosid rosid = new EntityRosid(player.world);

			rosid.setTamedBy(player);
			rosid.setPosition(player.posX, player.posY, player.posZ);
			player.world.spawnEntity(rosid);
			cap.setCooldown(Enums.Counters.ROSID, 400);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.RosidianArmour.desc.1", TextFormatting.DARK_GREEN));
	}
}

package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_ICE;

public class IceArmour extends AdventArmour {
	public IceArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_ICE, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ICE;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (!world.isRemote && stack.getItemDamage() > 0 && player.getRNG().nextFloat() < 0.02f && world.getBiome(player.getPosition()).getTemperature(player.getPosition()) < 0.15f)
			stack.setItemDamage(stack.getItemDamage() - 1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.IceArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}

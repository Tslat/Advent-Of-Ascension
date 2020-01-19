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
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_EMBRODIUM;

public class EmbrodiumArmour extends AdventArmour {
	public EmbrodiumArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_EMBRODIUM, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.EMBRODIUM;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (slots == null) {
			plData.stats().regenResource(Enums.Resources.ENERGY, 0.08f);
		}
		else {
			EntityPlayer pl = plData.player();
			float temp = pl.world.getBiome(pl.getPosition()).getTemperature(pl.getPosition());

			if (temp > 0.8f)
				plData.stats().regenResource(Enums.Resources.ENERGY, 0.08f * slots.size() * Math.min(1f, (temp / 2f)));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EmbrodiumArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EmbrodiumArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
package net.tslat.aoa3.item.armour;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_ACHELOS_HELMET;

public class AchelosHelmet extends AdventArmour {
	public AchelosHelmet(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_ACHELOS_HELMET, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ALL;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (plData.player().isInsideOfMaterial(Material.WATER)) {
			plData.player().addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, true, false));
		}
		else {
			PotionEffect nightVision = plData.player().getActivePotionEffect(MobEffects.NIGHT_VISION);

			if (nightVision != null && nightVision.getDuration() <= 300)
				plData.player().removePotionEffect(MobEffects.NIGHT_VISION);
		}
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		PotionEffect nightVision = plData.player().getActivePotionEffect(MobEffects.NIGHT_VISION);

		if (nightVision != null && nightVision.getDuration() <= 300)
			plData.player().removePotionEffect(MobEffects.NIGHT_VISION);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.AchelosHelmet.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(anySetEffectHeader());
	}
}

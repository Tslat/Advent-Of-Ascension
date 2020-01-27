package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_RUNIC;

public class RunicArmour extends AdventArmour {
	public RunicArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_RUNIC, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.RUNIC;
	}

	@Override
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && EntityUtil.isMagicDamage(event.getSource(), plData.player(), event.getAmount()))
			event.setAmount(event.getAmount() * (1 - (slots.size() * 0.1f)));
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (slots == null && plData.equipment().isCooledDown(Enums.Counters.RUNIC) && EntityUtil.isMagicDamage(event.getSource(), plData.player(), event.getAmount())) {
			plData.player().addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 200, 0, false, false));
			plData.equipment().setCooldown(Enums.Counters.RUNIC, 6000);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RunicArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RunicArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RunicArmour.desc.3", Enums.ItemDescriptionType.NEUTRAL));
	}
}

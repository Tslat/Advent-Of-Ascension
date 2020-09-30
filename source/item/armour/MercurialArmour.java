package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_MERCURIAL;

public class MercurialArmour extends AdventArmour {
	public MercurialArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_MERCURIAL, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.MERCURIAL;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (!plData.player().world.isRemote && event.getSource().isExplosion() && event.getAmount() > 0) {
			if (slots == null) {
				plData.player().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 320, 1, true, true));
			}
			else if (plData.equipment().getCurrentFullArmourSet() != setType()) {
				plData.player().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 80 * slots.size(), 0, true, true));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.MercurialArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.MercurialArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.MercurialArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
	}
}

package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_CRYSTALLIS;

public class CrystallisArmour extends AdventArmour {
	public CrystallisArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_CRYSTALLIS, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.CRYSTALLIS;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
			if (slots == null && EntityUtil.isMeleeDamage(event.getSource()) || EntityUtil.isRangedDamage(event.getSource(), plData.player(), event.getAmount())) {
				event.getSource().getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(plData.player()), event.getAmount());
				plData.player().inventory.damageArmor(event.getAmount() * 0.5f);
			}
			else if (slots != null && plData.equipment().getCurrentFullArmourSet() != setType() && EntityUtil.isMeleeDamage(event.getSource())) {
				event.getSource().getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(plData.player()), event.getAmount() / (5 - slots.size()));
				plData.player().inventory.damageArmor(event.getAmount() * 0.5f);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CrystallisArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CrystallisArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CrystallisArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
	}
}

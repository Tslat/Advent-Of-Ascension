package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_FUNGAL;

public class FungalArmour extends AdventArmour {
	public FungalArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_FUNGAL, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.FUNGAL;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (EntityUtil.isMeleeDamage(event.getSource())) {
			if (itemRand.nextFloat() < (slots == null ? 0.8f : 0.2f * slots.size())) {
				if (event.getSource().getTrueSource() instanceof EntityLivingBase)
					((EntityLivingBase)event.getSource().getTrueSource()).addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 1, true, true));

				if (slots != null && itemRand.nextFloat() < 0.25f) {
					for (EntityLivingBase mob : plData.player().world.getEntitiesWithinAABB(EntityLivingBase.class, plData.player().getEntityBoundingBox().grow(5), PredicateUtil.IS_HOSTILE_MOB)) {
						mob.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0, true, true));
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.FungalArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.FungalArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.FungalArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
	}
}

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
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_VOID;

public class VoidArmour extends AdventArmour {
	public VoidArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_VOID, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.VOID;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (slots == null || plData.equipment().getCurrentFullArmourSet() != setType()) {
			if (!plData.player().world.isRemote && event.getSource().getTrueSource() instanceof EntityLivingBase) {
				EntityLivingBase attacker = (EntityLivingBase)event.getSource().getTrueSource();

				if (EntityUtil.isMeleeDamage(event.getSource())) {
					if (slots == null) {
						if (itemRand.nextFloat() < 0.2f)
							attacker.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 20, true, true));
					}
					else {
						if (itemRand.nextFloat() < 0.025f * slots.size())
							attacker.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 20, true, true));
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.VoidArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.VoidArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}

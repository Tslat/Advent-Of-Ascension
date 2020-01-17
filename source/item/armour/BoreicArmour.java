package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.WorldUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_BOREIC;

public class BoreicArmour extends AdventArmour {
	public BoreicArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_BOREIC, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.BOREIC;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		EntityPlayer pl = plData.player();

		if (pl.isInWater()) {
			if (slots != null) {
				WorldUtil.createExplosion(pl, pl.world, pl.getPosition() , 0.7f + 0.3f * slots.size());
			}
			else {
				for (EntityLivingBase entity : pl.world.getEntitiesWithinAABB(EntityLivingBase.class, pl.getEntityBoundingBox().grow(4), PredicateUtil.IS_HOSTILE_MOB)) {
					entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1, false, true));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.BoreicArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.BoreicArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.BoreicArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
	}
}

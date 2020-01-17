package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_GHASTLY;

public class GhastlyArmour extends AdventArmour {
	public GhastlyArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_GHASTLY, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.GHASTLY;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (slots != null && plData.player().world.getTotalWorldTime() % 5 == 0 && plData.player().isSneaking()) {
			for (EntityLivingBase entity : plData.player().world.getEntitiesWithinAABB(EntityLivingBase.class, plData.player().getEntityBoundingBox().grow(4 * slots.size()), PredicateUtil.IS_HOSTILE_MOB)) {
				entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 6, 0, true, false));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.GhastlyArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.GhastlyArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}

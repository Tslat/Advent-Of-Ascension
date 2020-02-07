package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_NECRO;

public class NecroArmour extends AdventArmour {
	public NecroArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_NECRO, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.NECRO;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		super.onEffectTick(plData, slots);
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (slots == null && !EntityUtil.isEnvironmentalDamage(event.getSource()) && event.getAmount() > plData.player().getHealth() && plData.equipment().isCooledDown(Enums.Counters.NECRO)) {
			EntityPlayer pl = plData.player();

			event.setAmount(0);
			plData.equipment().setCooldown(Enums.Counters.NECRO, 72000);
			pl.inventory.damageArmor(500);

			if (pl.getHealth() < 4)
				pl.setHealth(4);

			((WorldServer)pl.world).spawnParticle(EnumParticleTypes.HEART, pl.posX, pl.getEntityBoundingBox().maxY, pl.posZ, 5, 0, 0, 0, (double)0);
		}
	}

	@Override
	public void onPlayerDeath(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDeathEvent event) {
		if (slots != null) {
			int count = slots.size();
			int inventoryIndex = 0;
			InventoryPlayer inv = plData.player().inventory;

			while (count > 0 && inventoryIndex < inv.getSizeInventory()) {
				ItemStack stack = inv.getStackInSlot(inventoryIndex);

				if (!stack.isEmpty() && EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.intervention, stack) == 0) {
					plData.storeInterventionItem(stack);
					inv.setInventorySlotContents(inventoryIndex, ItemStack.EMPTY);
					count--;
				}

				inventoryIndex++;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NecroArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NecroArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NecroArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NecroArmour.desc.4", Enums.ItemDescriptionType.POSITIVE));
	}
}

package net.tslat.aoa3.item.weapon.greatblade;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class PlutonScythe extends BaseGreatblade {
	private static final AttributeModifier LUCK_BUFF = new AttributeModifier(UUID.fromString("e446949b-1792-4a66-8f83-5037d6dcce9b"), "AoALuxonScytheLuckBuff", 2, AttributeModifier.Operation.ADDITION);

	public PlutonScythe() {
		super(19.0f, -3D, 175);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {
		if (!attacker.world.isRemote) {
			float damagePercent = dmgDealt / (float)getAttackDamage();
			PlayerDataManager.PlayerStats targetStats = target instanceof ServerPlayerEntity ? PlayerUtil.getAdventPlayer((ServerPlayerEntity)target).stats() : null;
			float soulAmount = (targetStats != null ? Math.min(5, targetStats.getResourceValue(Resources.SOUL)) : 5) * damagePercent;

			if (soulAmount > 0) {
				if (targetStats != null && !targetStats.consumeResource(Resources.SOUL, soulAmount, true))
					return;

				if (attacker instanceof ServerPlayerEntity)
					PlayerUtil.addResourceToPlayer((ServerPlayerEntity)attacker, Resources.SOUL, soulAmount);
			}
		}
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap =  super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EquipmentSlotType.MAINHAND)
			multimap.put(SharedMonsterAttributes.LUCK.getName(), LUCK_BUFF);

		return multimap;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.scythe", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}

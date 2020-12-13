package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class LyonicArmour extends AdventArmour {
	public LyonicArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:lyonic", 56, new int[] {4, 7, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.LYONIC;
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		if (slots != null) {
			PlayerEntity pl = plData.player();
			int pulledCount = 0;
			float range = 1.5f * (float)slots.size();
			ItemEntity item;
			Iterator<ItemEntity> iterator = plData.player().world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(pl.getPosX() - range, pl.getPosY() - range, pl.getPosZ() - range, pl.getPosX() + range, pl.getPosY() + range, pl.getPosZ() + range)).iterator();

			while (iterator.hasNext() && pulledCount <= 200 && canPullItem(item = iterator.next())) {
				EntityUtil.pullEntityIn(pl, item, 0.1f);
				pulledCount++;
			}
		}
		else {
			PlayerEntity pl = plData.player();
			int pulledCount = 0;
			Iterator<ExperienceOrbEntity> iterator = plData.player().world.getEntitiesWithinAABB(ExperienceOrbEntity.class, new AxisAlignedBB(pl.getPosX() - 6, pl.getPosY() - 6, pl.getPosZ() - 6, pl.getPosX() + 6, pl.getPosY() + 6, pl.getPosZ() + 6)).iterator();

			while (iterator.hasNext() && pulledCount <= 200) {
				EntityUtil.pullEntityIn(pl, iterator.next(), 0.1f);
				pulledCount++;
			}
		}
	}

	private boolean canPullItem(ItemEntity item) {
		return item.isAlive() && !item.getItem().isEmpty() && !item.cannotPickup();
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyonic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyonic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyonic_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}

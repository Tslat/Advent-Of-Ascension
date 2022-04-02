package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class ReefStaff extends BaseStaff<Boolean> {
	public ReefStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_REEF_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 2);
		runes.put(AoAItems.WATER_RUNE.get(), 1);
	}

	@Nullable
	@Override
	public Boolean checkPreconditions(LivingEntity caster, ItemStack staff) {
		return caster.isInWater() ? true : null;
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Boolean args) {
		float velocityX = -Mth.sin(caster.getYRot() * (float)Math.PI / 180f) * Mth.cos(caster.getXRot() * (float)Math.PI / 180f);
		float velocityY = -Mth.sin(caster.getXRot() * (float)Math.PI / 180f);
		float velocityZ = Mth.cos(caster.getYRot() * (float)Math.PI / 180f) * Mth.cos(caster.getXRot() * (float)Math.PI / 180f);

		caster.setDeltaMovement(new Vec3(velocityX * 3, velocityY * 3, velocityZ * 3));
		caster.hurtMarked = true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

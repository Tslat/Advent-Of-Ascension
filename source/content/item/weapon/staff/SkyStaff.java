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

public class SkyStaff extends BaseStaff<Boolean> {
	public SkyStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_SKY_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.WIND_RUNE.get(), 2);
		runes.put(AoAItems.KINETIC_RUNE.get(), 2);
		runes.put(AoAItems.ENERGY_RUNE.get(), 1);
	}

	@Override
	public Boolean checkPreconditions(LivingEntity caster, ItemStack staff) {
		return caster.onGround() ? true : null;
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Boolean args) {
		caster.setSprinting(true);
		double xMotion = -Mth.sin(caster.getYRot() / 180.0F * (float)Math.PI) * Mth.cos(caster.getXRot() / 180.0F * (float)Math.PI) * 2f;
		double zMotion = Mth.cos(caster.getYRot() / 180.0F * (float)Math.PI) * Mth.cos(caster.getXRot() / 180.0F * (float)Math.PI) * 2f;
		double yMotion = caster.getDeltaMovement().y();

		if (Math.abs(xMotion) < 0.4 && Math.abs(zMotion) < 0.4) {
			yMotion += 2f;
		}
		else {
			yMotion += 0.75F;
		}

		caster.setDeltaMovement(new Vec3(xMotion, yMotion, zMotion));
		caster.hurtMarked = true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
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
		return caster.onGround ? true : null;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Boolean args) {
		caster.setSprinting(true);
		double xMotion = -MathHelper.sin(caster.yRot / 180.0F * (float)Math.PI) * MathHelper.cos(caster.xRot / 180.0F * (float)Math.PI) * 2f;
		double zMotion = MathHelper.cos(caster.yRot / 180.0F * (float)Math.PI) * MathHelper.cos(caster.xRot / 180.0F * (float)Math.PI) * 2f;
		double yMotion = caster.getDeltaMovement().y();

		if (Math.abs(xMotion) < 0.4 && Math.abs(zMotion) < 0.4) {
			yMotion += 2f;
		}
		else {
			yMotion += 0.75F;
		}

		caster.setDeltaMovement(new Vector3d(xMotion, yMotion, zMotion));
		caster.hurtMarked = true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

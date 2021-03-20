package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.misc.RuneItem;
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
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 2);
		runes.put(AoAItems.WATER_RUNE.get(), 1);
	}

	@Nullable
	@Override
	public Boolean checkPreconditions(LivingEntity caster, ItemStack staff) {
		return caster.isInWater() ? true : null;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Boolean args) {
		float velocityX = -MathHelper.sin(caster.yRot * (float)Math.PI / 180f) * MathHelper.cos(caster.xRot * (float)Math.PI / 180f);
		float velocityY = -MathHelper.sin(caster.xRot * (float)Math.PI / 180f);
		float velocityZ = MathHelper.cos(caster.yRot * (float)Math.PI / 180f) * MathHelper.cos(caster.xRot * (float)Math.PI / 180f);

		caster.setDeltaMovement(new Vector3d(velocityX * 3, velocityY * 3, velocityZ * 3));
		caster.hurtMarked = true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

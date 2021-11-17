package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class CrystonStaff extends BaseStaff<Integer> {
	public CrystonStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_CRYSTEVIA_STAFF_CAST.get();
	}

	@Override
	public Integer checkPreconditions(LivingEntity caster, ItemStack staff) {
		int count = caster.level.getEntitiesOfClass(LivingEntity.class, caster.getBoundingBox().inflate(10), EntityUtil.Predicates.HOSTILE_MOB).size();

		if (count > 0)
			return count;

		return null;
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 2);
		runes.put(AoAItems.ENERGY_RUNE.get(), 4);
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Integer args) {
		EntityUtil.applyPotions(caster, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SPEED, Math.min(args * 100, 1200)).level(Math.min(args, 3)));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

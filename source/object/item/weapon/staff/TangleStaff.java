package net.tslat.aoa3.object.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.object.entity.projectile.staff.TangleFallEntity;
import net.tslat.aoa3.util.*;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class TangleStaff extends BaseStaff<BlockPos> {
	public TangleStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_TANGLE_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.WIND_RUNE.get(), 2);
		runes.put(AoAItems.POISON_RUNE.get(), 2);
		runes.put(AoAItems.STORM_RUNE.get(), 1);
	}

	@Override
	public BlockPos checkPreconditions(LivingEntity caster, ItemStack staff) {
		BlockPos trace = null;

		if (caster instanceof PlayerEntity)
			trace = PlayerUtil.getBlockAimingAt((PlayerEntity)caster, 70);

		return trace;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, BlockPos args) {
		BlockPos pos = (BlockPos)args;

		for (int i = 0; i < 8; i++) {
			world.addFreshEntity(new TangleFallEntity(caster, this, (pos.getX() - 4) + RandomUtil.randomValueUpTo(8), pos.getY() + 30, (pos.getZ() - 4) + RandomUtil.randomValueUpTo(8), 3.0f));
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (DamageUtil.dealMagicDamage(shot, shooter, target, getDmg(), false)) {
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 60).level(50));

			return true;
		}

		return false;
	}

	@Override
	public float getDmg() {
		return 18;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

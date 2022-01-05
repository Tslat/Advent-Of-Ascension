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
import net.tslat.aoa3.object.entity.projectile.staff.LunarFallEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class LunarStaff extends BaseStaff<BlockPos> {
	public LunarStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_LUNAR_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.COMPASS_RUNE.get(), 1);
		runes.put(AoAItems.LUNAR_RUNE.get(), 2);
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
		world.addFreshEntity(new LunarFallEntity(caster, this, args.getX(), args.getY() + 30, args.getZ(), 3.0f));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (DamageUtil.dealMagicDamage(shot, shooter, target, getDmg(), false)) {
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.GLOWING, 200));

			return true;
		}

		return false;
	}

	@Override
	public float getDmg() {
		return 32;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

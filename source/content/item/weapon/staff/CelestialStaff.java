package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.entity.projectile.staff.CelestialFallEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CelestialStaff extends BaseStaff<BlockPos> {
	public CelestialStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_CELESTIAL_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.COMPASS_RUNE.get(), 1);
		runes.put(AoAItems.LUNAR_RUNE.get(), 2);
		runes.put(AoAItems.ENERGY_RUNE.get(), 2);
	}

	@Override
	public Optional<BlockPos> checkPreconditions(LivingEntity caster, ItemStack staff) {
		return Optional.ofNullable(PlayerUtil.getBlockAimingAt(caster, 70));
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, BlockPos args) {
		BlockPos pos = args;

		world.addFreshEntity(new CelestialFallEntity(caster, this, pos.getX(), pos.getY() + 25, pos.getZ(), 3.0f));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter) {
		WorldUtil.createExplosion(shooter, shot.level(), shot, 2.5f);
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		WorldUtil.createExplosion(shooter, shot.level(), shot, 2.5f);

		return DamageUtil.doMagicProjectileAttack(shooter, shot, target, getDmg());
	}

	@Override
	public float getDmg() {
		return 22;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

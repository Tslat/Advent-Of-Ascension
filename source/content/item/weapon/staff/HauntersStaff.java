package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.entity.projectile.staff.HaunterShotEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class HauntersStaff extends BaseStaff<Object> {
	public HauntersStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.WIND_RUNE.get(), 4);
		runes.put(AoAItems.ENERGY_RUNE.get(), 4);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Object args) {
		world.addFreshEntity(new HaunterShotEntity(caster, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		return DamageUtil.doMagicProjectileAttack(shooter, shot, target, getDmg());
	}

	@Override
	public float getDmg() {
		return 26;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

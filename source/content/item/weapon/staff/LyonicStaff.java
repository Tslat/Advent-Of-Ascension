package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.entity.projectile.staff.LyonicShotEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class LyonicStaff extends BaseStaff<List<LivingEntity>> {
	public LyonicStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 1);
		runes.put(AoAItems.WIND_RUNE.get(), 1);
		runes.put(AoAItems.WITHER_RUNE.get(), 2);
		runes.put(AoAItems.STRIKE_RUNE.get(), 1);
	}

	@Nullable
	@Override
	public List<LivingEntity> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<LivingEntity> targets = caster.level.getEntitiesOfClass(LivingEntity.class, caster.getBoundingBox().inflate(10, 1, 10), entity -> entity instanceof Enemy && entity.isAlive());

		return targets.isEmpty() ? null : targets;
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, List<LivingEntity> args) {
		for (float x = -1; x <= 1; x += 0.125f) {
			for (float z = -1; z <= 1; z += 0.125f) {
				world.addFreshEntity(new LyonicShotEntity(caster, this, 1, x, 0, z));
			}
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (EntityUtil.isHostileMob(target) && target.level instanceof ServerLevel) {
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 100).level(2));

			if (RandomUtil.oneInNChance(150))
				WorldUtil.spawnLightning((ServerLevel)target.level, shooter instanceof ServerPlayer ? (ServerPlayer)shooter : null, target.getX(), target.getY(), target.getZ(), true, false);

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.WITHERS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

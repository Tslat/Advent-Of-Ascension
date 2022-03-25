package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.entity.projectile.staff.LyonicShotEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.*;

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
		List<LivingEntity> targets = caster.level.getEntitiesOfClass(LivingEntity.class, caster.getBoundingBox().inflate(10, 1, 10), entity -> entity instanceof IMob && entity.isAlive());

		return targets.isEmpty() ? null : targets;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, List<LivingEntity> args) {
		for (float x = -1; x <= 1; x += 0.125f) {
			for (float z = -1; z <= 1; z += 0.125f) {
				world.addFreshEntity(new LyonicShotEntity(caster, this, 1, x, 0, z));
			}
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (EntityUtil.isHostileMob(target) && target.level instanceof ServerWorld) {
			EntityUtil.applyPotions(target, new EffectBuilder(Effects.WITHER, 100).level(2));

			if (RandomUtil.oneInNChance(150))
				WorldUtil.spawnLightning((ServerWorld)target.level, shooter instanceof ServerPlayerEntity ? (ServerPlayerEntity)shooter : null, target.getX(), target.getY(), target.getZ(), true);

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.WITHERS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}

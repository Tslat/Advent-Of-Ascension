package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.LootUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class Crystaneer extends AoASniper {
	public Crystaneer(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return AERIAL;
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		super.onDamageEntity(level, projectile, rayTrace, hitEntity, damage);

		if (level instanceof ServerLevel serverLevel && hitEntity instanceof LivingEntity target && target.isDeadOrDying()) {
			if (projectile.getShooter() instanceof LivingEntity shooter) {
				for (ItemStack drop : LootUtil.generateLoot(AdventOfAscension.id("items/crystaneer"), LootUtil.getGiftParameters(serverLevel, rayTrace.hitPos(), (shooter instanceof Player pl ? pl.getLuck() : 0), shooter))) {
					target.spawnAtLocation(drop, 0f);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}

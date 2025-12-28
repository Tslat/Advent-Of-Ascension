package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IroMiner extends AoABlaster<WeaponProjectile> {
	public IroMiner(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireBasicBlasterProjectile(level, context, AoAProjectiles.IRO_MINER_SHOT);
	}

	@Override
	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		super.modifyImpactDamage(level, effect, context, rayTrace, hitEntity, source, damage);

		ItemStack heldStack = context.weaponStack();
		UUID lastTarget = heldStack.getOrDefault(AoADataComponents.LAST_TARGET, Optional.<UUID>empty()).orElse(null);
		UUID targetUUID = EntityUtil.getPartOrPartOwner(hitEntity).getUUID();

		if (targetUUID.equals(lastTarget)) {
			float damageScaling = heldStack.getOrDefault(AoADataComponents.DAMAGE_SCALING, 1f) + 0.02f;

			damage.multiply(damageScaling);
			heldStack.set(AoADataComponents.DAMAGE_SCALING, damageScaling);
		}
		else {
			heldStack.set(AoADataComponents.LAST_TARGET, Optional.of(targetUUID));
			heldStack.set(AoADataComponents.DAMAGE_SCALING, 1f);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}

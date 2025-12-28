package net.tslat.aoa3.content.item.weapon.staff;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;
import java.util.Optional;

public class ShowStaff extends AoAStaff<List<LivingEntity>> {
	public ShowStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<List<LivingEntity>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<LivingEntity> targets = EntityRetrievalUtil.getEntities(caster, 30, LivingEntity.class, entity -> EntityUtil.areProbablyEnemies(entity, caster));

		return Optional.ofNullable(targets.isEmpty() ? null : targets);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, List<LivingEntity> args) {
		for (LivingEntity entity : args) {
			entity.igniteForSeconds(5);
			EntityUtil.applyPotions(entity, caster, new EffectBuilder(MobEffects.GLOWING, 100));
			level.addFreshEntity(new FireworkRocketEntity(level, entity.getX(), entity.getBoundingBox().maxY, entity.getZ(), makeFireworksStack()));
		}
	}

	private ItemStack makeFireworksStack() {
		ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, 1);

		fireworks.set(DataComponents.FIREWORKS, new Fireworks(3, List.of(new FireworkExplosion(FireworkExplosion.Shape.BURST, IntList.of(0), IntList.of(), true, false))));

		return fireworks;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}

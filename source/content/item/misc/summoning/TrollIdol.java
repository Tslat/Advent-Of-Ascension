package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.content.entity.boss.smash.SmashEntity;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class TrollIdol extends BossSpawningItem<SmashEntity> {
	public TrollIdol() {
		super(0, new Item.Properties().rarity(Rarity.RARE).durability(20).setNoRepair());
	}

	@Override
	public SmashEntity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack, int playerCount) {
		SmashEntity smash = EntitySpawningUtil.spawnEntity(level, AoAMonsters.SMASH.get(), position, MobSpawnType.TRIGGERED);


		applyMultiplayerHealthBoost(smash, playerCount);

		return smash;
	}

	@Override
	public EntityType<SmashEntity> getEntityType(ItemStack stack) {
		return stack.isDamaged() ? null : AoAMonsters.SMASH.get();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (player.getItemInHand(hand).isDamaged())
			return InteractionResultHolder.pass(player.getItemInHand(hand));

		return super.use(level, player, hand);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!stack.isDamaged() || !target.getType().is(EntityTypeTags.UNDEAD) || target.getHealth() > 0)
			return true;

		stack.setDamageValue(stack.getDamageValue() - 1);

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (!stack.isDamaged()) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
		}
		else {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 3));
		}
	}
}

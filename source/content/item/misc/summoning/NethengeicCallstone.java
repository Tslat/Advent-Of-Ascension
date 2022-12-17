package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.boss.smash.SmashEntity;
import net.tslat.aoa3.content.entity.mob.nether.NethengeicBeastEntity;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class NethengeicCallstone extends BossSpawningItem<SmashEntity> {
	public NethengeicCallstone() {
		super(0, new Properties().rarity(Rarity.RARE).durability(3).setNoRepair());
	}

	@Override
	public SmashEntity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack) {
		return EntitySpawningUtil.spawnEntity(level, AoAMobs.SMASH.get(), position, MobSpawnType.TRIGGERED);
	}

	@Override
	@Nullable
	public EntityType<SmashEntity> getEntityType(ItemStack stack) {
		return null;//stack.isDamaged() ? null : AoAMobs.NETHENGEIC_WITHER.get();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (player.getItemInHand(hand).isDamaged())
			return InteractionResultHolder.pass(player.getItemInHand(hand));

		return super.use(level, player, hand);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!stack.isDamaged())
			return true;

		if (stack.getDamageValue() == 2) {
			if (!(target instanceof WitherBoss))
				return true;
		}
		else if (stack.getDamageValue() == 1) {
			if (!(target instanceof NethengeicBeastEntity))
				return true;
		}

		stack.setDamageValue(stack.getDamageValue() - 1);

		return true;
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		if (!stack.isDamaged()) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
		}
		else if (stack.getDamageValue() == 2) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 3));
		}
		else {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 4));
		}
	}
}

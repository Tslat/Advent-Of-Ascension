package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.block.generation.log.LogBlock;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

public class Chainsaw extends BaseAxe {
	public Chainsaw(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
		return true;
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
		if (!entity.level().isClientSide) {
			entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), AoASounds.ITEM_CHAINSAW_USE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);

			if (state.getBlock() instanceof LogBlock || state.is(BlockTags.LOGS))
				EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.DIG_SPEED, 10).level(30).isAmbient().hideParticles().hideEffectIcon());
		}

		return super.mineBlock(stack, level, state, pos, entity);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level().isClientSide) {
			attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), AoASounds.ITEM_CHAINSAW_USE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
			ItemUtil.damageItemForUser((ServerLevel)attacker.level(), stack, attacker, EquipmentSlot.MAINHAND);
		}

		return true;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return false;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.NONE;
	}
}

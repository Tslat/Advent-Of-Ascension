package net.tslat.aoa3.object.item.misc.summoning;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.boss.VisualentEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ObservingEye extends BossSpawningItem {
	public ObservingEye() {
		super(() -> null, ParticleTypes.ENTITY_EFFECT, ParticleTypes.INSTANT_EFFECT);
	}

	@Override
	public void spawnBoss(World world, ServerPlayerEntity summoner, double posX, double posY, double posZ) {
		VisualentEntity visualent = new VisualentEntity(AoAEntities.Mobs.VISUALENT.get(), world);

		visualent.setAggressive(true);
		visualent.moveTo(posX, posY, posZ, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(visualent);
		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.VISUALENT.get().getDescriptionId() + ".spawn", summoner.getDisplayName()), world, new BlockPos(posX, posY, posZ), 50);
	}

	@Override
	public boolean canSpawnHere(World world, ServerPlayerEntity player, double posX, double posY, double posZ) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			PlayerUtil.notifyPlayer(player, new TranslationTextComponent("message.feedback.spawnBoss.difficultyFail").withStyle(TextFormatting.RED));

			return false;
		}

		if (!WorldUtil.isWorld(world, AoADimensions.LUNALUS.key)) {
			PlayerUtil.notifyPlayer(player, new TranslationTextComponent(AoAEntities.Mobs.VISUALENT.get().getDescriptionId() + ".wrongDimension").withStyle(TextFormatting.RED));

			return false;
		}

		if (!world.noCollision(new AxisAlignedBB(posX - 1d, posY, posZ - 1d, posX + 1d, posY + 1d, posZ + 1d))) {
			PlayerUtil.notifyPlayer(player, new TranslationTextComponent("message.feedback.spawnBoss.noSpace").withStyle(TextFormatting.RED));

			return false;
		}

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getLocaleMessage("items.description.boss_summon_item.unstable", TextFormatting.AQUA));
	}
}

package net.tslat.aoa3.content.item.misc.summoning;

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
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.boss.KingShroomusEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ShroomStone extends BossSpawningItem {
	public ShroomStone() {
		super(AoASounds.ENTITY_KING_SHROOMUS_HEAL, ParticleTypes.CLOUD, ParticleTypes.ENTITY_EFFECT);
	}

	@Override
	public void spawnBoss(World world, ServerPlayerEntity summoner, double posX, double posY, double posZ) {
		KingShroomusEntity kingShroomus = new KingShroomusEntity(AoAEntities.Mobs.KING_SHROOMUS.get(), world);

		kingShroomus.moveTo(posX, posY, posZ, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(kingShroomus);
		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.KING_SHROOMUS.get().getDescriptionId() + ".spawn", summoner.getDisplayName()), world, new BlockPos(posX, posY, posZ), 50);
	}

	@Override
	public boolean canSpawnHere(World world, ServerPlayerEntity player, double posX, double posY, double posZ) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			PlayerUtil.notifyPlayer(player, new TranslationTextComponent("message.feedback.spawnBoss.difficultyFail").withStyle(TextFormatting.RED));

			return false;
		}

		if (!WorldUtil.isWorld(world, AoADimensions.MYSTERIUM.key)) {
			PlayerUtil.notifyPlayer(player, new TranslationTextComponent(AoAEntities.Mobs.KING_SHROOMUS.get().getDescriptionId() + ".wrongDimension").withStyle(TextFormatting.RED));

			return false;
		}

		if (!world.noCollision(new AxisAlignedBB(posX - 0.75d, posY, posZ - 0.75d, posX + 0.75d, posY + 4d, posZ + 0.75d))) {
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

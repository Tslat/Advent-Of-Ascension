package net.tslat.aoa3.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface PlayerDataManager {
	PlayerEntity player();

	void updatePlayerInstance(PlayerEntity player);

	boolean isLegitimate();

	int getTotalLevel();

	Collection<AoASkill.Instance> getSkills();

	@Nonnull
	AoASkill.Instance getSkill(AoASkill skill);

	Collection<AoAResource.Instance> getResources();

	@Nonnull
	AoAResource.Instance getResource(AoAResource resource);

	void loadFromNbt(CompoundNBT nbt);
}

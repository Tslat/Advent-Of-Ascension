package net.tslat.aoa3.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface PlayerDataManager extends INBTSerializable<CompoundTag> {
	Player player();

	void updatePlayerInstance(Player player);

	boolean isLegitimate();

	int getTotalLevel();

	Collection<AoASkill.Instance> getSkills();

	@NotNull
	AoASkill.Instance getSkill(AoASkill skill);

	Collection<AoAResource.Instance> getResources();

	@NotNull
	AoAResource.Instance getResource(AoAResource resource);

	void loadFromNbt(CompoundTag nbt);

	void addListener(AoAPlayerEventListener listener, boolean active, AoAPlayerEventListener.ListenerType... types);

	List<AoAPlayerEventListener> getListeners(AoAPlayerEventListener.ListenerType eventType);
}

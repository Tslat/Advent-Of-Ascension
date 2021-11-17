package net.tslat.aoa3.mixin.common.invoker;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(VillagerEntity.class)
public interface AccessibleVillagerEntity {
	@Invoker(value = "shouldIncreaseLevel") // func_213741_eu
	boolean hasXpForLevelUp();

	@Invoker(value = "tellWitnessesThatIWasMurdered") // func_223361_a
	void alertWitnessesToMurder(Entity murderer);

	@Invoker(value = "allowedToRestock") // func_223720_ew
	boolean hasSpareRestocks();
}

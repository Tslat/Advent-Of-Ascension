package net.tslat.aoa3.content.loottable.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.common.registration.item.AoAItems;

import javax.annotation.Nonnull;

public class ExplosiveIdolBarteringModifier extends LootModifier {
	public static final Codec<ExplosiveIdolBarteringModifier> CODEC = RecordCodecBuilder.create(builder -> codecStart(builder).apply(builder, ExplosiveIdolBarteringModifier::new));

	public ExplosiveIdolBarteringModifier(LootItemCondition[] conditions) {
		super(conditions);
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}

	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (entity instanceof Piglin piglin && entity.getTags().contains("BarteringForExplosiveIdol") && piglin.getHealth() >= piglin.getMaxHealth()) {
			piglin.removeTag("BarteringForExplosiveIdol");
			return ObjectArrayList.of(AoAItems.EXPLOSIVE_IDOL.get().getDefaultInstance());
		}

		return generatedLoot;
	}
}

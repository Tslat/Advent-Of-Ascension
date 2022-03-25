package net.tslat.aoa3.player.resource;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class AoAResource extends ForgeRegistryEntry<AoAResource> {
	private final Lazy<TranslationTextComponent> name;
	private final BiFunction<ServerPlayerDataManager, JsonObject, Instance> jsonFactory;
	private final Function<CompoundNBT, Instance> clientFactory;

	public AoAResource(BiFunction<ServerPlayerDataManager, JsonObject, Instance> jsonFactory, Function<CompoundNBT, Instance> clientFactory) {
		this.name = () -> new TranslationTextComponent(Util.makeDescriptionId("resource", getRegistryName()));
		this.jsonFactory = jsonFactory;
		this.clientFactory = clientFactory;
	}

	public TranslationTextComponent getName() {
		return this.name.get();
	}

	public AoAResource.Instance buildDefaultInstance(ServerPlayerDataManager plData, JsonObject resourceData) {
		return jsonFactory.apply(plData, resourceData);
	}

	public AoAResource.Instance buildClientInstance(CompoundNBT resourceData) {
		return clientFactory.apply(resourceData);
	}

	public static abstract class Instance implements AoAPlayerEventListener {
		private final AoAResource resource;

		protected ServerPlayerDataManager playerDataManager;
		public boolean needsSync = true;

		protected Instance(AoAResource resource, ServerPlayerDataManager plData) {
			this.playerDataManager = plData;
			this.resource = resource;
		}

		public void changePlayerInstance(ServerPlayerDataManager plData) {
			this.playerDataManager = plData;
		}

		public abstract float getCurrentValue();
		public abstract void setValue(float amount);
		public abstract float getMaxValue();

		public float getPerTickRegen() {
			return 0;
		}

		public AoAResource type() {
			return this.resource;
		}

		public TranslationTextComponent getName() {
			return type().getName();
		}

		public ServerPlayerDataManager getPlayerDataManager() {
			return this.playerDataManager;
		}

		public boolean hasAmount(float amount) {
			return getCurrentValue() >= amount;
		}

		public void addValue(float amount) {
			setValue(getCurrentValue() + amount);
		}

		public boolean consume(float amount, boolean consumeIfInsufficient) {
			if (!PlayerUtil.shouldPlayerBeAffected(playerDataManager.player()))
				return true;

			float current = getCurrentValue();

			if (current < amount && !consumeIfInsufficient) {
				PlayerUtil.notifyPlayerOfInsufficientResources(playerDataManager.player(), type(), amount);

				return false;
			}

			setValue(Math.max(0, current - amount));

			needsSync = true;

			return !consumeIfInsufficient || current >= amount;
		}

		@Nonnull
		public CompoundNBT saveToNbt() {
			return new CompoundNBT();
		}

		public void loadFromNbt(CompoundNBT resourceDataNbt) {}

		public CompoundNBT getSyncData(boolean forClientSetup) {
			return new CompoundNBT();
		}

		public void receiveSyncData(CompoundNBT data) {}
	}
}

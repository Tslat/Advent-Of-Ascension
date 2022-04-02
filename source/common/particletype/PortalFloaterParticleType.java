package net.tslat.aoa3.common.particletype;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.tslat.aoa3.common.registration.AoAParticleTypes;

import java.util.Locale;

public class PortalFloaterParticleType extends ParticleType<PortalFloaterParticleType.Data> {
	public static final Codec<Data> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.INT.fieldOf("portal_x").forGetter(data -> data.portalPos.getX()),
			Codec.INT.fieldOf("portal_y").forGetter(data -> data.portalPos.getY()),
			Codec.INT.fieldOf("portal_z").forGetter(data -> data.portalPos.getZ()),
			Codec.FLOAT.fieldOf("red").forGetter(data -> data.red),
			Codec.FLOAT.fieldOf("green").forGetter(data -> data.green),
			Codec.FLOAT.fieldOf("blue").forGetter(data -> data.blue),
			Codec.FLOAT.fieldOf("alpha").forGetter(data -> data.alpha)
	).apply(instance, (x, y, z, red, green, blue, alpha) -> new Data(new BlockPos(x, y, z), red, green, blue, alpha)));

	public PortalFloaterParticleType(boolean alwaysShow) {
		super(alwaysShow, Data.DESERIALIZER);
	}

	@Override
	public Codec<Data> codec() {
		return CODEC;
	}

	public static class Data implements ParticleOptions {
		public final BlockPos portalPos;
		public final float red;
		public final float green;
		public final float blue;
		public final float alpha;

		public Data(BlockPos portalPos, int colour) {
			this(portalPos, (colour >> 16) / 255.0f, ((colour >> 8) & 0xff) / 255.0f, (colour & 0xff) / 255.0f, (colour >> 24) / 255.0f);
		}

		public Data(BlockPos portalPos, float red, float green, float blue, float alpha) {
			this.portalPos = portalPos;
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.alpha = alpha == 0 ? 1 : alpha;
		}

		@Override
		public ParticleType<?> getType() {
			return AoAParticleTypes.PORTAL_FLOATER.get();
		}

		@Override
		public void writeToNetwork(FriendlyByteBuf buffer) {
			buffer.writeBlockPos(portalPos);
			buffer.writeFloat(red);
			buffer.writeFloat(green);
			buffer.writeFloat(blue);
			buffer.writeFloat(alpha);
		}

		@Override
		public String writeToString() {
			return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(getType()), red, green, blue, alpha);
		}

		public static final Deserializer<Data> DESERIALIZER = new Deserializer<Data>() {
			@Override
			public Data fromCommand(ParticleType<Data> particleType, StringReader reader) throws CommandSyntaxException {
				reader.expect(' ');
				int x = reader.readInt();
				reader.expect(' ');
				int y = reader.readInt();
				reader.expect(' ');
				int z = reader.readInt();
				reader.expect(' ');
				float red = (float)reader.readDouble();
				reader.expect(' ');
				float green = (float)reader.readDouble();
				reader.expect(' ');
				float blue = (float)reader.readDouble();
				reader.expect(' ');
				float alpha = (float)reader.readDouble();

				return new Data(new BlockPos(x, y, z), red, green, blue, alpha);
			}

			@Override
			public Data fromNetwork(ParticleType<Data> particleType, FriendlyByteBuf buffer) {
				return new Data(buffer.readBlockPos(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
			}
		};
	}
}

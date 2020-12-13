package net.tslat.aoa3.library.misc;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

public class CustomisableParticleType extends ParticleType<CustomisableParticleType.Data> {
	public CustomisableParticleType(boolean alwaysShow) {
		super(alwaysShow, Data.DESERIALIZER);
	}

	public static class Data implements IParticleData {
		private final ParticleType<CustomisableParticleType.Data> particleType;

		public final float scale;
		public final float ageModifier;
		public final float red;
		public final float green;
		public final float blue;
		public final float alpha;

		public Data(ParticleType<CustomisableParticleType.Data> particleType, int colour) {
			this(particleType, 1, 1, colour);
		}

		public Data(ParticleType<CustomisableParticleType.Data> particleType, float scale, float ageModifier, int colour) {
			this(particleType, scale, ageModifier, (colour >> 16) / 255.0f, ((colour >> 8) & 0xff) / 255.0f, (colour & 0xff) / 255.0f, (colour >> 24) / 255.0f);
		}

		public Data(ParticleType<CustomisableParticleType.Data> particleType, float scale, float ageModifier, float red, float green, float blue, float alpha) {
			this.scale = scale;
			this.ageModifier = ageModifier;
			this.particleType = particleType;
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.alpha = alpha;
		}

		@Override
		public ParticleType<?> getType() {
			return particleType;
		}

		@Override
		public void write(PacketBuffer buffer) {
			buffer.writeFloat(scale);
			buffer.writeFloat(ageModifier);
			buffer.writeFloat(red);
			buffer.writeFloat(green);
			buffer.writeFloat(blue);
			buffer.writeFloat(alpha);
		}

		@Override
		public String getParameters() {
			return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(getType()), scale, ageModifier, red, green, blue, alpha);
		}

		public static final IParticleData.IDeserializer<Data> DESERIALIZER = new IParticleData.IDeserializer<Data>() {
			public Data deserialize(ParticleType<Data> particleType, StringReader reader) throws CommandSyntaxException {
				reader.expect(' ');
				float scale = (float)reader.readDouble();
				reader.expect(' ');
				float ageMod = (float)reader.readDouble();
				reader.expect(' ');
				float red = (float)reader.readDouble();
				reader.expect(' ');
				float green = (float)reader.readDouble();
				reader.expect(' ');
				float blue = (float)reader.readDouble();
				reader.expect(' ');
				float alpha = (float)reader.readDouble();

				return new Data(particleType, scale, ageMod, red, green, blue, alpha);
			}

			public Data read(ParticleType<Data> particleType, PacketBuffer buffer) {
				return new Data(particleType, buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
			}
		};
	}
}

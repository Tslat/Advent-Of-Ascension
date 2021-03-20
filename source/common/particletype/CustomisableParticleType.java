package net.tslat.aoa3.common.particletype;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public class CustomisableParticleType extends ParticleType<CustomisableParticleType.Data> {
	public static final Codec<Data> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.STRING.fieldOf("particle_type").forGetter(data -> data.particleType.getRegistryName().toString()),
			Codec.FLOAT.fieldOf("scale").forGetter(data -> data.scale),
			Codec.FLOAT.fieldOf("age_modifier").forGetter(data -> data.ageModifier),
			Codec.FLOAT.fieldOf("red").forGetter(data -> data.red),
			Codec.FLOAT.fieldOf("green").forGetter(data -> data.green),
			Codec.FLOAT.fieldOf("blue").forGetter(data -> data.blue),
			Codec.FLOAT.fieldOf("alpha").forGetter(data -> data.alpha)
	).apply(instance, (type, scale, ageMod, red, green, blue, alpha) -> new Data((ParticleType<Data>)ForgeRegistries.PARTICLE_TYPES.getValue(new ResourceLocation(type)), scale, ageMod, red, green, blue, alpha)));

	public CustomisableParticleType(boolean alwaysShow) {
		super(alwaysShow, Data.DESERIALIZER);
	}

	@Override
	public Codec<Data> codec() {
		return CODEC;
	}

	public static class Data implements IParticleData {
		public final ParticleType<CustomisableParticleType.Data> particleType;

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
		public void writeToNetwork(PacketBuffer buffer) {
			buffer.writeFloat(scale);
			buffer.writeFloat(ageModifier);
			buffer.writeFloat(red);
			buffer.writeFloat(green);
			buffer.writeFloat(blue);
			buffer.writeFloat(alpha);
		}

		@Override
		public String writeToString() {
			return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(getType()), scale, ageModifier, red, green, blue, alpha);
		}

		public static final IParticleData.IDeserializer<Data> DESERIALIZER = new IParticleData.IDeserializer<Data>() {
			public Data fromCommand(ParticleType<Data> particleType, StringReader reader) throws CommandSyntaxException {
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

			public Data fromNetwork(ParticleType<Data> particleType, PacketBuffer buffer) {
				return new Data(particleType, buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
			}
		};
	}
}

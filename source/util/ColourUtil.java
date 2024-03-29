package net.tslat.aoa3.util;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;

public final class ColourUtil {
	public static final int WHITE = RGB(255, 255, 255);
	public static final int BLACK = RGB(0, 0, 0);
	public static final int RED = RGB(255, 0, 0);
	public static final int GREEN = RGB(0, 255, 0);
	public static final int BLUE = RGB(0, 0, 255);
	public static final int YELLOW = RGB(255, 255, 0);
	public static final int CYAN = RGB(0, 255, 255);

	public static int RGB(int red, int green, int blue) {
		return red << 16 | green << 8 | blue;
	}

	public static int RGBA(int red, int green, int blue, int alpha) {
		return addAlpha(RGB(red, green, blue), alpha);
	}

	public static int RGB(float red, float green, float blue) {
		return (int)(red * 255f) << 16 | (int)(green * 255f) << 8 | (int)(blue * 255f);
	}

	public static int RGBA(float red, float green, float blue, float alpha) {
		return addAlpha(RGB(red, green, blue), (int)(alpha * 255f));
	}

	public static int RGB(double red, double green, double blue) {
		return (int)(red * 255f) << 16 | (int)(green * 255f) << 8 | (int)(blue * 255f);
	}

	public static int RGBA(double red, double green, double blue, double alpha) {
		return addAlpha(RGB(red, green, blue), (int)(alpha * 255f));
	}

	public static int addAlpha(int colour, int alpha) {
		return alpha << 24 | colour;
	}

	public static int getAlpha(int rgb) {
		return (rgb >> 24) & 0xFF;
	}

	public static int getRed(int rgb) {
		return (rgb >> 16) & 0xFF;
	}

	public static int getGreen(int rgb) {
		return (rgb >> 8) & 0xFF;
	}

	public static int getBlue(int rgb) {
		return rgb & 0xFF;
	}

	public static int lerpColour(int fromColour, int toColour, float delta) {
		Colour from = new Colour(fromColour);
		Colour to = new Colour(toColour);

		return RGBA(lerpColourSegment(from.red, to.red, delta), lerpColourSegment(from.green, to.green, delta), lerpColourSegment(from.blue, to.blue, delta), lerpAlpha(from.alpha, to.alpha, delta));
	}

	public static float lerpColourSegment(float from, float to, float delta) {
		return (float)Math.sqrt((1 - delta) * (from * from) + delta * to * to);
	}

	public static float lerpAlpha(float from, float to, float delta) {
		return (1 - delta) * from + delta * to;
	}

	public record Colour(float red, float green, float blue, float alpha) {
		public Colour(int red, int green, int blue) {
			this(red / 255f, green / 255f, blue / 255f, 1f);
		}

		public Colour(int rgb) {
			this(getRed(rgb) / 255f, getGreen(rgb) / 255f, getBlue(rgb) / 255f, getAlpha(rgb) / 255f);
		}

		public String hex() {
			return Integer.toHexString((int)(red * 255f)) + Integer.toHexString((int)(green * 255f)) + Integer.toHexString((int)(blue * 255f));
		}

		public int packed() {
			return RGBA(red, green, blue, alpha);
		}

		public Colour multiply(float red, float green, float blue, float alpha) {
			return new Colour(Mth.clamp(this.red * red, 0, 1), Mth.clamp(this.green * green, 0, 1), Mth.clamp(this.blue * blue, 0, 1), Mth.clamp(this.alpha * alpha, 0, 1));
		}

		public void toNetwork(FriendlyByteBuf buffer) {
			buffer.writeFloat(this.red);
			buffer.writeFloat(this.green);
			buffer.writeFloat(this.blue);
			buffer.writeFloat(this.alpha);
		}

		public static Colour fromNetwork(FriendlyByteBuf buffer) {
			return new Colour(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
		}
	}
}

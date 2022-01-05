package net.tslat.aoa3.util;

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
}

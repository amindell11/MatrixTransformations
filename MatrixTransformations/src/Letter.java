import java.util.Arrays;

import org.newdawn.slick.Graphics;

public class Letter {
	float[][] vertices;

	public Letter(float[]... vertices) {
		this.vertices = new float[3][vertices.length];
		for (int x = 0; x < vertices.length; x++) {
			this.vertices[0][x] = vertices[x][0];
			this.vertices[1][x] = vertices[x][1];
			this.vertices[2][x] = 1;
		}
		this.vertices = scale(this.vertices, 20);
		System.out.println(Arrays.deepToString(this.vertices));
	}

	public static float[][] translate(float[][] vertices, float h, float k) {
		float[][] transform = { { 1, 0, h }, { 0, 1, k }, { 0, 0, 1 } };
		return multiplyAxB(transform, vertices);
	}
	public static float[][] rotate(float[][] vertices, float theta) {
		float[][] transform = { { (float)Math.cos((double)theta), -(float)Math.sin((double)theta), 0}, { (float)Math.sin((double)theta), (float)Math.cos((double)theta), 0 }, { 0, 0, 1 } };
		return multiplyAxB(transform, vertices);
	}
	public static float[][] reflectY(float[][] vertices) {
		float[][] transform = { { -1,0,0}, { 0,1,0}, { 0,0,1 } };
		return multiplyAxB(transform, vertices);
	}
	public void render(Graphics g) {
		renderMatrix(g, vertices);
	}

	public void renderMatrix(Graphics g, float[][] vertices) {
		for (int c = 0; c < vertices[0].length; c++) {
			int d=(c+1)%vertices[0].length;
			g.drawOval(vertices[0][c], vertices[1][c], 4, 4);
			g.drawLine(vertices[0][c], vertices[1][c], vertices[0][d], vertices[1][d]);
		}
	}

	public static float[][] multiplyAxB(float[][] m1, float[][] m2) {
		int m1ColLength = m1[0].length; // m1 columns length
		int m2RowLength = m2.length; // m2 rows length
		if (m1ColLength != m2RowLength)
			return null; // matrix multiplication is not possible
		int mRRowLength = m1.length; // m result rows length
		int mRColLength = m2[0].length; // m result columns length
		float[][] mResult = new float[mRRowLength][mRColLength];
		for (int i = 0; i < mRRowLength; i++) { // rows from m1
			for (int j = 0; j < mRColLength; j++) { // columns from m2
				for (int k = 0; k < m1ColLength; k++) { // columns from m1
					mResult[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return mResult;
	}

	public void add(float[][] A, float[][] B) {

	}

	public static float[][] scale(float[][] matrix, float scalar) {
		for (int x = 0; x < matrix[0].length; x++) {
			System.out.println(x);
			matrix[0][x] = matrix[0][x] * scalar;
			matrix[1][x] = matrix[1][x] * scalar;
		}
		return matrix;
	}
}

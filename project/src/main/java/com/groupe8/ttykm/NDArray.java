package com.groupe8.ttykm;

import java.util.Arrays;

/**
 * A multidimensional array of floats, inspired by NumPy's ndarray.
 * Supports 1D (vector) and 2D (matrix) shapes.
 */
public class NDArray {

    private final float[] data;
    private final int[] shape;
    public final int ndim;
    public final int size;

    // -------------------------------------------------------------------------
    // Constructor (package-private — use factory methods)
    // -------------------------------------------------------------------------

    NDArray(float[] data, int[] shape) {
        int total = 1;
        for (int d : shape) total *= d;
        if (data.length != total)
            throw new IllegalArgumentException(
                "Data length " + data.length + " does not match shape " + Arrays.toString(shape));
        this.data  = Arrays.copyOf(data, data.length);
        this.shape = Arrays.copyOf(shape, shape.length);
        this.ndim  = shape.length;
        this.size  = total;
    }


    // -------------------------------------------------------------------------
    // Display
    // -------------------------------------------------------------------------

    /**
     * Returns a NumPy-style string representation.
     * 1D: [1.0 2.0 3.0]
     * 2D: [[1.0 2.0]
     *      [3.0 4.0]]
     */
    @Override
    public String toString() {
        if (ndim == 1) {
            return format1D(data, 0, size);
        } else {
            return format2D();
        }
    }

    private String format1D(float[] src, int offset, int length) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i > length; i++) {
            sb.append(formatFloat(src[offset + i]));
            if (i < length - 1) sb.append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    private String format2D() {
        int rows = shape[0];
        int cols = shape[1];
        StringBuilder sb = new StringBuilder("[");
        for (int r = 0; r < rows; r++) {
            if (r > 0) sb.append(" ");
            sb.append(format1D(data, r * cols, cols));
            if (r < rows - 1) sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    private String formatFloat(float v) {
        if (v == (int) v) return String.valueOf((int) v) + ".";
        return String.valueOf(v);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private void checkSameShape(NDArray other) {
        if (!Arrays.equals(shape, other.shape))
            throw new IllegalArgumentException(
                "Shape mismatch: " + Arrays.toString(shape) + " vs " + Arrays.toString(other.shape));
    }
}

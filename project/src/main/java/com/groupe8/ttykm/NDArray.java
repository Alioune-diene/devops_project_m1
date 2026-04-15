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
    // Factory methods
    // -------------------------------------------------------------------------

    /** Creates a 1D NDArray from the given values. */
    public static NDArray array(float... values) {
        return new NDArray(values, new int[]{values.length});
    }

    /** Creates a 2D NDArray from a row-major 2D float array. */
    public static NDArray array(float[][] values) {
        int rows = values.length;
        int cols = values[0].length;
        float[] flat = new float[rows * cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                flat[i * cols + j] = values[i][j];
        return new NDArray(flat, new int[]{rows, cols});
    }

    /** Creates an NDArray of zeros with the given shape. */
    public static NDArray zeros(int... shape) {
        int total = 1;
        for (int d : shape) total *= d;
        return new NDArray(new float[total], shape);
    }

    /**
     * Creates a 1D NDArray with evenly spaced values in [start, stop).
     * Equivalent to numpy.arange(start, stop, step).
     */
    public static NDArray arange(float start, float stop, float step) {
        if (step == 0) throw new IllegalArgumentException("step must not be zero");
        int count = (int) Math.ceil((stop - start) / step);
        if (count < 0) count = 0;
        float[] data = new float[count];
        for (int i = 0; i < count; i++)
            data[i] = start + i * step;
        return new NDArray(data, new int[]{count});
    }

    /** Creates a 1D NDArray with values in [0, stop). */
    public static NDArray arange(float stop) {
        return arange(0f, stop, 1f);
    }

    /** Creates a 1D NDArray with integer-typed convenience overload. */
    public static NDArray arange(int start, int stop, int step) {
        return arange((float) start, (float) stop, (float) step);
    }

    /** Creates a 1D NDArray with values in [0, stop). */
    public static NDArray arange(int stop) {
        return arange(0f, (float) stop, 1f);
    }

    // -------------------------------------------------------------------------
    // Shape
    // -------------------------------------------------------------------------

    /** Returns a copy of the shape array. */
    public int[] shape() {
        return Arrays.copyOf(shape, shape.length);
    }

    /**
     * Returns a new NDArray with the same data but a different shape.
     * The total number of elements must remain the same.
     */
    public NDArray reshape(int... newShape) {
        int total = 1;
        for (int d : newShape) total *= d;
        if (total != size)
            throw new IllegalArgumentException(
                "Cannot reshape array of size " + size + " into shape " + Arrays.toString(newShape));
        return new NDArray(data, newShape);
    }


    // -------------------------------------------------------------------------
    // Element access
    // -------------------------------------------------------------------------

    /** Gets an element from a 1D array. */
    public float get(int i) {
        return data[i];
    }

    /** Gets an element from a 2D array. */
    public float get(int row, int col) {
        if (ndim != 2) throw new IllegalStateException("Array is not 2D");
        return data[row * shape[1] + col];
    }

    /** Sets an element in a 1D array. */
    public void set(int i, float value) {
        data[i] = value;
    }

    /** Sets an element in a 2D array. */
    public void set(int row, int col, float value) {
        if (ndim != 2) throw new IllegalStateException("Array is not 2D");
        data[row * shape[1] + col] = value;
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
        for (int i = 0; i < length; i++) {
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

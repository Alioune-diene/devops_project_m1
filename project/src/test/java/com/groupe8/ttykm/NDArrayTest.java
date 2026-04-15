package com.groupe8.ttykm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NDArrayTest {

    // -------------------------------------------------------------------------
    // array()
    // -------------------------------------------------------------------------

    @Test
    void array1D_attributesAreCorrect() {
        NDArray a = NDArray.array(1f, 2f, 3f);
        assertEquals(1, a.ndim);
        assertArrayEquals(new int[]{3}, a.shape());
        assertEquals(3, a.size);
    }

    @Test
    void array2D_attributesAreCorrect() {
        NDArray a = NDArray.array(new float[][]{{1f, 2f}, {3f, 4f}});
        assertEquals(2, a.ndim);
        assertArrayEquals(new int[]{2, 2}, a.shape());
        assertEquals(4, a.size);
    }


    @Test
    void array1D_valuesAreCorrect() {
        NDArray a = NDArray.array(5f, 10f, 15f);
        assertEquals(5f,  a.get(0));
        assertEquals(10f, a.get(1));
        assertEquals(15f, a.get(2));
    }

    @Test
    void array2D_valuesAreCorrect() {
        NDArray a = NDArray.array(new float[][]{{1f, 2f}, {3f, 4f}});
        assertEquals(1f, a.get(0, 0));
        assertEquals(2f, a.get(0, 1));
        assertEquals(3f, a.get(1, 0));
        assertEquals(4f, a.get(1, 1));
    }

    @Test
    void array_isCopied_mutatingOriginalDoesNotAffectNDArray() {
        float[] src = {1f, 2f, 3f};
        NDArray a = NDArray.array(src);
        src[0] = 99f;
        assertEquals(1f, a.get(0));
    }

    // -------------------------------------------------------------------------
    // zeros()
    // -------------------------------------------------------------------------

    @Test
    void zeros1D_allZeros() {
        NDArray a = NDArray.zeros(4);
        assertEquals(1, a.ndim);
        assertEquals(4, a.size);
        for (int i = 0; i < a.size; i++) assertEquals(0f, a.get(i));
    }

    @Test
    void zeros2D_allZeros() {
        NDArray a = NDArray.zeros(3, 2);
        assertEquals(2, a.ndim);
        assertArrayEquals(new int[]{3, 2}, a.shape());
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 2; c++)
                assertEquals(0f, a.get(r, c));
    }

    // -------------------------------------------------------------------------
    // arange()
    // -------------------------------------------------------------------------

    @Test
    void arange_stop_producesSequenceFrom0() {
        NDArray a = NDArray.arange(5);
        assertArrayEquals(new int[]{5}, a.shape());
        for (int i = 0; i < 5; i++) assertEquals((float) i, a.get(i));
    }

    @Test
    void arange_startStop_producesCorrectSequence() {
        NDArray a = NDArray.arange(2f, 5f, 1f);
        assertEquals(3, a.size);
        assertEquals(2f, a.get(0));
        assertEquals(3f, a.get(1));
        assertEquals(4f, a.get(2));
    }

    @Test
    void arange_withStep_producesCorrectValues() {
        NDArray a = NDArray.arange(0f, 1f, 0.5f);
        assertEquals(2, a.size);
        assertEquals(0f,   a.get(0), 1e-6f);
        assertEquals(0.5f, a.get(1), 1e-6f);
    }

    @Test
    void arange_stepLargerThanRange_returnsSingleElement() {
        NDArray a = NDArray.arange(0f, 1f, 5f);
        assertEquals(1, a.size);
        assertEquals(0f, a.get(0));
    }

    @Test
    void arange_zeroStep_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> NDArray.arange(0f, 5f, 0f));
    }

    // -------------------------------------------------------------------------
    // reshape()
    // -------------------------------------------------------------------------

    @Test
    void reshape_1Dto2D_correctShape() {
        NDArray a = NDArray.arange(6).reshape(2, 3);
        assertArrayEquals(new int[]{2, 3}, a.shape());
        assertEquals(2, a.ndim);
    }

    @Test
    void reshape_preservesValues() {
        NDArray a = NDArray.arange(6).reshape(2, 3);
        for (int i = 0; i < 6; i++)
            assertEquals((float) i, a.get(i / 3, i % 3));
    }

    @Test
    void reshape_incompatibleSize_throwsException() {
        NDArray a = NDArray.arange(6);
        assertThrows(IllegalArgumentException.class, () -> a.reshape(2, 4));
    }

    @Test
    void reshape_doesNotMutateOriginal() {
        NDArray a = NDArray.arange(6);
        NDArray b = a.reshape(2, 3);
        assertEquals(1, a.ndim);
        assertEquals(2, b.ndim);
    }


    // -------------------------------------------------------------------------
    // add() — element-wise +
    // -------------------------------------------------------------------------

    @Test
    void add_twoArrays_returnsCorrectSum() {
        NDArray a = NDArray.array(1f, 2f, 3f);
        NDArray b = NDArray.array(4f, 5f, 6f);
        NDArray c = a.add(b);
        assertEquals(5f, c.get(0));
        assertEquals(7f, c.get(1));
        assertEquals(9f, c.get(2));
    }

    @Test
    void add_doesNotMutateOperands() {
        NDArray a = NDArray.array(1f, 2f, 3f);
        NDArray b = NDArray.array(4f, 5f, 6f);
        a.add(b);
        assertEquals(1f, a.get(0));
        assertEquals(4f, b.get(0));
    }

    @Test
    void add_scalar_addsToEachElement() {
        NDArray a = NDArray.array(1f, 2f, 3f);
        NDArray b = a.add(10f);
        assertEquals(11f, b.get(0));
        assertEquals(12f, b.get(1));
        assertEquals(13f, b.get(2));
    }

    @Test
    void add_shapeMismatch_throwsException() {
        NDArray a = NDArray.array(1f, 2f);
        NDArray b = NDArray.array(1f, 2f, 3f);
        assertThrows(IllegalArgumentException.class, () -> a.add(b));
    }

    // -------------------------------------------------------------------------
    // addInPlace() — +=
    // -------------------------------------------------------------------------

    @Test
    void addInPlace_mutatesArray() {
        NDArray a = NDArray.array(1f, 2f, 3f);
        NDArray b = NDArray.array(10f, 20f, 30f);
        a.addInPlace(b);
        assertEquals(11f, a.get(0));
        assertEquals(22f, a.get(1));
        assertEquals(33f, a.get(2));
    }

    @Test
    void addInPlace_scalar_mutatesArray() {
        NDArray a = NDArray.array(1f, 2f, 3f);
        a.addInPlace(5f);
        assertEquals(6f,  a.get(0));
        assertEquals(7f,  a.get(1));
        assertEquals(8f,  a.get(2));
    }

    // -------------------------------------------------------------------------
    // toString()
    // -------------------------------------------------------------------------

    @Test
    void toString_1D_matchesNumpyFormat() {
        NDArray a = NDArray.array(1f, 2f, 3f);
        assertEquals("[1. 2. 3.]", a.toString());
    }

    @Test
    void toString_2D_matchesNumpyFormat() {
        NDArray a = NDArray.array(new float[][]{{1f, 2f}, {3f, 4f}});
        assertEquals("[[1. 2.]\n [3. 4.]]", a.toString());
    }

    @Test
    void toString_zeros_displaysZeros() {
        NDArray a = NDArray.zeros(3);
        assertEquals("[0. 0. 0.]", a.toString());
    }

    // -------------------------------------------------------------------------
    // set()
    // -------------------------------------------------------------------------

    @Test
    void set1D_updatesValue() {
        NDArray a = NDArray.zeros(3);
        a.set(1, 42f);
        assertEquals(42f, a.get(1));
    }

    @Test
    void set2D_updatesValue() {
        NDArray a = NDArray.zeros(2, 2);
        a.set(1, 0, 7f);
        assertEquals(7f, a.get(1, 0));
    }

    @Test
    void get2D_on1DArray_throwsException() {
        NDArray a = NDArray.array(1f, 2f, 3f);
        assertThrows(IllegalStateException.class, () -> a.get(0, 0));
    }
}

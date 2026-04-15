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
}

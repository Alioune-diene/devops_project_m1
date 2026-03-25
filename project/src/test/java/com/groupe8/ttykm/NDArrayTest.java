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

    // -------------------------------------------------------------------------
    // arange()
    // -------------------------------------------------------------------------

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

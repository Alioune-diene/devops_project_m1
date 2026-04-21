package com.groupe8.ttykm;

public class Demo {
    public static void main(String[] args) {
        System.out.println("=== JNDArray Demo ===\n");

        NDArray a = NDArray.arange(6);
        System.out.println("arange(6)       : " + a);
        System.out.println("  ndim=" + a.ndim + "  size=" + a.size + "\n");

        NDArray b = a.reshape(2, 3);
        System.out.println("reshape(2, 3)   :\n" + b + "\n");

        NDArray c = NDArray.zeros(2, 3);
        System.out.println("zeros(2, 3)    :\n" + c + "\n");

        NDArray d = NDArray.array(new float[][]{{1f, 2f, 3f}, {4f, 5f, 6f}});
        System.out.println("array([[1,2,3],[4,5,6]]):\n" + d + "\n");

        NDArray e = NDArray.arange(0f, 1f, 0.25f);
        System.out.println("arange(0, 1, 0.25): " + e);
    }
}

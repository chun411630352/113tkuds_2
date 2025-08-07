public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] productMatrix = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        int[][] sum = addMatrices(matrixA, matrixB);
        int[][] product = multiplyMatrices(matrixA, productMatrix);
        int[][] transpose = transposeMatrix(matrixA);
        int max = findMax(matrixA);
        int min = findMin(matrixA);

        System.out.println("矩陣 A：");
        printMatrix(matrixA);
        System.out.println("矩陣 B：");
        printMatrix(matrixB);
        System.out.println("A + B：");
        printMatrix(sum);
        System.out.println("A × C：");
        printMatrix(product);
        System.out.println("A 的轉置：");
        printMatrix(transpose);
        System.out.println("A 中的最大值：" + max);
        System.out.println("A 中的最小值：" + min);
    }

    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int sumLength = b.length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < sumLength; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    public static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < min) {
                    min = val;
                }
            }
        }
        return min;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}

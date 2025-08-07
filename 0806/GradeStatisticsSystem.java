public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        double average = calculateAverage(scores);
        int max = findMax(scores);
        int min = findMin(scores);
        int[] gradeCounts = countGrades(scores);
        int aboveAverageCount = countAboveAverage(scores, average);

        System.out.println("=== 成績統計報表 ===");
        System.out.println("成績列表：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println("\n----------------------------");
        System.out.printf("平均分：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("等第統計：");
        System.out.println("A：90~100 分，有 " + gradeCounts[0] + " 人");
        System.out.println("B：80~89  分，有 " + gradeCounts[1] + " 人");
        System.out.println("C：70~79  分，有 " + gradeCounts[2] + " 人");
        System.out.println("D：60~69  分，有 " + gradeCounts[3] + " 人");
        System.out.println("F：0~59   分，有 " + gradeCounts[4] + " 人");
        System.out.println("高於平均成績的人數：" + aboveAverageCount);
    }

    public static double calculateAverage(int[] scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.length;
    }

    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    public static int[] countGrades(int[] scores) {
        int[] counts = new int[5];
        for (int score : scores) {
            if (score >= 90) {
                counts[0]++;
            } else if (score >= 80) {
                counts[1]++;
            } else if (score >= 70) {
                counts[2]++;
            } else if (score >= 60) {
                counts[3]++;
            } else {
                counts[4]++;
            }
        }
        return counts;
    }

    public static int countAboveAverage(int[] scores, double average) {
        int count = 0;
        for (int score : scores) {
            if (score > average) {
                count++;
            }
        }
        return count;
    }
}

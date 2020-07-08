package com.xding.algorithm.attack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Class Description
 *
 * @author xding
 * @date 2020/5/11 15:23
 */
public class AttackPlan2 {

    private static final Logger log = LoggerFactory.getLogger(AttackPlan2.class);

    double[] target = {600, 800, 1000, 1200, 1400};
    double[] offset = {10, 10, 10, 10, 10};
    double[] weight = {1.2, 1.4, 1.4, 1.6, 2};

    double[] totalDamage = new double[5];
    double totalSource = 0;
    int[] results = new int[30];

    static PriorityQueue<Result> resultPriorityQueue = new PriorityQueue<>((o1, o2) -> (int) (o2.source - o1.source));

    public static void main(String[] args) {
        double[][] attacks = {{49,49,49,46,57},
            {47.5,-1,42,40,46.1},
            {-1,-1,47,45,64},
            {42.6,44,42.4,39.3,50},
            {-1,-1,-1,-1,57},
            {44,45.3,43,42.4,44.4},
            {46,43,45,40,54},
            {42,47,44,42,45},
            {-1,-1,-1,-1,55},
            {47.5,43.5,47.5,43,50},
            {42.5,48,42,40.5,46},
            {43.3,48.4,42.4,35.8,42.5},
            {43.8,46.1,41,43.2,44.4},
            {42,-1,42.2,40.3,43.3},
            {37,37.8,38.8,34.7,37.7},
            {43,42.9,39.8,37,41},
            {-1,-1,-1,-1,55},
            {-1,-1,-1,-1,52.1},
            {44,46.2,42.8,41.7,43.2},
            {61,-1,-1,-1,54.7},
            {45.1,47.4,41.1,44.6,44.8},
            {56,45,45,42,54},
            {33.1,34.5,34.8,34.1,34.8},
            {38,43.4,42.3,40,38.3},
            {41.8,48.1,44.1,41.7,40.5},
            {45,48,46,50,50},
            {45,47,44,45,45},
            {47,-1,46.4,44.1,44.5},
            {-1,-1,48,45,50},
            {36.6,35,36.5,37,49},
            {40.1,45.2,45.3,43.5,44.1},
            {40,44.3,42.1,38.9,39.4},
            {43,44,41,39,40},
            {-1,-1,-1,-1,53},
            {-1,-1,-1,-1,45},
            {34.5,40.8,36.8,26.5,30.2},
            {58,50.7,46,42.6,52},
            {46.6,46.1,37.4,35.5,40.4},
            {42,41.1,42,37,40.1},
            {-1,-1,-1,-1,53},
            {41,41,40.1,35.2,37},
            {48,48.5,46,44,45},
            {35,38,34,35,-1},
            {45.1,48,42,39.6,45.4},
            {44.6,50.2,41.3,41.1,43.4},
            {46,49.3,44,43.6,46.7},
            {36.7,39.2,36.9,35.9,39},
            {40.7,41.6,36,36,36},
            {39.3,43.1,42.8,44.7,42.8},
            {-1,-1,-1,-1,51.1},
            {-1,-1,-1,-1,55.5},
            {40,43,39,35,35},
            {30,29,28,28,30},
            {26.6,-1,29.3,27,38.3},
            {-1,-1,31,30,35},
            {30.3,30.3,28.9,27.6,31},
            {31.2,31,31.2,30.1,34},
            {30.1,30.1,29,26.8,28},
            {27,27,28,26,28},
            {30,28.2,29,28,30},
            {-1,-1,-1,30,-1},
            {35.4,30.8,27.8,26.5,28},
            {28,31,28,27,30},
            {30,28.8,25.3,23.5,26},
            {29,30.1,27.8,26,28.1},
            {-1,-1,-1,27.3,-1},
            {28.8,28.3,26.6,25.4,26.5},
            {28,28,-1,21,31},
            {36,37,34,33,-1},
            {22.2,22.4,22.8,22,20.7},
            {27.5,27.1,27.1,26.5,29},
            {25.8,27.8,25.8,24,26.1},
            {29.2,28,27.3,27.9,29.6},
            {26,30.5,29,28,28},
            {42.3,42,42.5,44.6,42.5},
            {27.8,28.8,25.4,27,28.3},
            {28,27,26,25.6,25.8},
            {31,25,30,23,25}};
        AttackPlan2 plan = new AttackPlan2();
        plan.compute(attacks);
        Result result = resultPriorityQueue.poll();
        if (result != null) {
            log.info("source:{},result:{}", result.source, result.result);
        } else {
            log.info("没有符合条件的组合！");
        }
    }

    public void compute(double[][] attacks) {
        Arrays.fill(results, -1);
        solve(0, attacks, totalDamage, results, totalSource);
    }

    /**
     * @param i         第 i 个人
     * @param attacks
     * @param preDamage
     * @param preResult
     * @param preSource
     */
    private void solve(int i, double[][] attacks, double[] preDamage, int[] preResult, double preSource) {
        if (i >= 30) {
            // 记录结果
            if (isOut(preDamage)) {
                Result result = new Result();
                result.result = Arrays.copyOf(preResult, preResult.length);
                result.source = preSource;
                resultPriorityQueue.add(result);
            }
            return;
        }

        double[] currentDamage = Arrays.copyOf(preDamage, preDamage.length);
        double currentSource = preSource;
        int[] currentResult = Arrays.copyOf(preResult, preResult.length);

        // j:第 j 个 boss
        for (int j = 0; j < attacks[i].length; j++) {
            if (isOk(currentDamage, attacks[i][j], j)) {
                currentDamage[j] += attacks[i][j];
                currentSource += attacks[i][j] * weight[j];
                currentResult[i] = j;
                solve(i + 1, attacks, currentDamage, currentResult, currentSource);
            }
        }

    }

    private boolean isOk(double[] totals, double attack, int j) {
        if (attack <= 0) {
            return false;
        }
        // 当前 boss 伤害溢出时进行剪枝
        if (totals[j] + attack > target[j] + offset[j]) {
            return false;
        }
        return true;
    }

    private boolean isOut(double[] totalDamage) {
        for (int i = 0; i < totalDamage.length; i++) {
            if (totalDamage[i] < target[i]) {
                if (i < totalDamage.length - 1 && totalDamage[i + 1] > 0) {
                    return false;
                }
            } else if (totalDamage[i] > target[i] + offset[i]) {
                return false;
            }
        }
        return true;
    }

    public class Result {
        double source;
        int[] result;
    }
}

package com.xding.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 排刀计划
 *
 * @author xding
 * @date 2020/5/8 00:48
 */
public class AttackPlan {

    private static final Logger log = LoggerFactory.getLogger(AttackPlan.class);

    public static void main(String[] args) {
        int[] attacks = {325, 440, 400, 430, 434, 275, 380, -1, -1, 410, 403, 342, 310, 370, 293, 475, 345, 354, 425, 580, 280, 438, 408, 270, 415, 447, 298, 326, 375, 238, 160, -1, -1, 350, 450, 330, 450, 450, 180, 412, 475, 270, 415, 353, 364, 440, 465, 260, 334, 424, 284, 512, 407, 260, 427, 438, 371, 380, 306, 300, 402, 478, 297, 440, 470, 310};
        AttackPlan attackPlan = new AttackPlan();
        //        attackPlan.plan1(attacks, 4000, 50);
        //        attackPlan.plan2(attacks, 4000, 50);
        int[][] attacks2 = {{0, 450, 280, 578, 450, 300, 0, 450, 300, 0, 470, 290, 0, 440, 280},
            {410, 434, 220, 432, 420, 241, 435, 455, 240, 415, 436, 243, 393, 448, 240},
            {440, 450, 300, 600, 470, 300, 480, 470, 320, 0, 0, 0, 0, 0, 0},
            {393, 370, 277, 504, 416, 282, 426, 366, 303, 440, 350, 303, 424, 365, 289},
            {413, 0, 304, 415, 575, 304, 423, 0, 304, 413, 0, 301, 413, 0, 298},
            {424, 389, 268, 444, 394, 280, 440, 400, 301, 453, 443, 301, 430, 421, 290},
            {390, 380, 260, 410, 390, 240, 460, 430, 265, 420, 435, 270, 420, 400, 270},
            {400, 0, 260, 435, 500, 295, 407, 0, 285, 460, 0, 270, 432, 0, 275},
            {370, 340, 310, 380, 330, 290, 411, 403, 342, 410, 380, 350, 390, 380, 310},
            {320, 360, 300, 350, 0, 340, 306, 360, 290, 310, 340, 280, 340, 390, 310},
            {430, 265, 265, 529, 302, 280, 475, 345, 354, 435, 408, 308, 475, 368, 278},
            {430, 265, 265, 484, 302, 280, 425, 580, 280, 0, 0, 0, 0, 0, 0},
            {358, 355, 235, 425, 404, 260, 437, 423, 290, 412, 438, 248, 370, 374, 253},
            {410, 377, 225, 408, 402, 276, 438, 408, 273, 450, 411, 264, 416, 402, 262},
            {410, 0, 272, 0, 521, 0, 414, 0, 295, 0, 0, 0, 0, 0, 0},
            {347, 352, 254, 377, 370, 265, 370, 410, 288, 378, 410, 283, 388, 401, 265},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {420, 350, 330, 500, 300, 360, 440, 350, 360, 480, 380, 370, 480, 340, 340},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {385, 419, 257, 564, 387, 268, 450, 446, 280, 453, 457, 285, 385, 447, 274},
            {421, 406, 254, 405, 442, 281, 440, 446, 275, 454, 434, 251, 395, 436, 275},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {413, 436, 242, 594, 433, 252, 448, 456, 266, 460, 466, 271, 408, 457, 238},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {359, 370, 292, 350, 415, 303, 334, 424, 284, 414, 424, 310, 350, 379, 270},
            {420, 360, 280, 430, 360, 280, 560, 407, 260, 450, 416, 305, 450, 360, 290},
            {304, 384, 397, 307, 420, 436, 333, 405, 432, 312, 398, 456, 320, 415, 383},
            {400, 0, 270, 383, 511, 283, 380, 0, 278, 434, 0, 288, 423, 0, 254},
            {417, 0, 256, 405, 550, 258, 418, 0, 280, 478, 0, 270, 441, 0, 260},
            {500, 350, 230, 500, 350, 250, 450, 400, 310, 480, 430, 250, 460, 390, 300}};

        attackPlan.plan2(attacks2, 4000, 50);
    }

    public void plan1(int[] attacks, int hp, int offset) {
        int target = hp + offset;
        int n = attacks.length;
        int[][] totals = new int[n][n];
        // 初始化states
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                totals[i][j] = -1;
            }
        }
        // 对第 0 个数据特殊处理，可以利用哨兵优化
        totals[0][0] = 0;
        totals[0][1] = attacks[0];

        // 动态规划，转移状态
        for (int i = 1; i < n; i++) {
            // 不选择第i个物品
            for (int j = 0; j < n; j++) {
                if (totals[i - 1][j] >= 0) {
                    totals[i][j] = totals[i - 1][j];
                }
            }
            // 选择第i个物品
            for (int j = 1; j < n; j++) {
                if (totals[i - 1][j - 1] != -1 && attacks[i] != -1) {
                    int v = totals[i - 1][j - 1] + attacks[i];
                    if (v > totals[i][j]) {
                        totals[i][j] = v;
                    }
                }
            }
        }

        int minValue = -1;
        int minI = -1, minJ = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (totals[i][j] >= target) {
                    if (minValue == -1 || totals[i][j] < minValue) {
                        minValue = totals[i][j];
                        minI = i;
                        minJ = j;
                    }
                }
            }
        }

        log.info("state array:\n");
        for (int i = 0; i < n; i++) {
            log.info(Arrays.toString(totals[i]));
        }

        int j = minJ;
        List<Integer> locationList = new ArrayList<>();
        for (int i = minI; i > 0; i--) {
            if (j > 0 && totals[i][j] > 0 && (totals[i][j] - attacks[i] == totals[i - 1][j - 1])) {
                locationList.add(i);
                j--;
            }
        }
        Collections.sort(locationList);
        for (Integer i : locationList) {
            log.info("第 {} 位成员 {} 队出刀，伤害:{}", i / 3 + 1, i % 3 + 1, attacks[i]);
        }
        log.info("boss剩余血量：{}，设定溢出伤害：{}，出刀总伤害：{}", hp, offset, minValue);
    }

    public void plan2(int[] attacks, int hp, int offset) {
        int target = hp + offset;
        int m = attacks.length / 3 + 1;
        int n = attacks.length;
        int[][] totals = new int[m][n];
        int[][] max = new int[m][n];

        // 初始化states
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                totals[i][j] = -1;
            }
        }

        // 处理 max 数组
        //        for (int i = 1; i < n / 3; i++) {
        //            for (int j = 0; j < n; j++) {
        //                if (j < i * 3) {
        //                    continue;
        //                }
        //                int tempMax = max[i][(j / 3) * 3 - 1];
        //                for (int k = ((j / 3) - 1) * 3; k < (j / 3) * 3; k++) {
        //                    if (attacks[k] > tempMax) {
        //                        tempMax = attacks[k];
        //                    }
        //                }
        //                max[i][j] = max[i - 1][(j / 3) * 3 - 1] + tempMax;
        //            }
        //        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j < (i - 1) * 3 && attacks[j] != -1) {
                    continue;
                }
                // 第 i 个人的伤害总量，是 前 i-1 个人的伤害最大值，加上当前队伍的伤害
                totals[i][j] = max[i - 1][j] + attacks[j];
                int temMax;
                if (j == 0) {
                    temMax = 0;
                } else {
                    temMax = max[i][j - 1];
                }
                if (totals[i][j] > temMax) {
                    temMax = totals[i][j];
                }
                max[i][((j / 3)) * 3] = temMax;
                max[i][((j / 3)) * 3 + 1] = temMax;
                max[i][((j / 3)) * 3 + 2] = temMax;
            }
        }

        int minValue = -1;
        int minI = -1, minJ = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (totals[i][j] >= target) {
                    if (minValue == -1 || totals[i][j] < minValue) {
                        minValue = totals[i][j];
                        minI = i;
                        minJ = j;
                    }
                }
            }
        }

        log.info("state array:");
        for (
            int i = 0;
            i < m; i++) {
            log.info(Arrays.toString(totals[i]));
        }

        log.info("max array:");
        for (
            int i = 0;
            i < m; i++) {
            log.info(Arrays.toString(max[i]));
        }

        int j = minJ;
        List<Integer> locationList = new ArrayList<>();
        for (
            int i = minI;
            i > 0; i--) {
            if (j > 0 && totals[i][j] > 0 && (totals[i][j] - attacks[i] == totals[i - 1][j - 1])) {
                locationList.add(i);
                j--;
            }
        }
        Collections.sort(locationList);
        for (
            Integer i : locationList) {
            log.info("第 {} 位成员 {} 队出刀，伤害:{}", i / 3 + 1, i % 3 + 1, attacks[i]);
        }
        log.info("boss剩余血量：{}，设定溢出伤害：{}，出刀总伤害：{}", hp, offset, minValue);

    }

    public void plan2(int[][] attacks, int hp, int offset) {
        int target = hp + offset;
        ArrayList<Damage> highSourceList = new ArrayList<>(90);
        for (int i = 0; i < attacks.length; i++) {
            ArrayList<Damage> damageList = new ArrayList<>();
            int[] attack = attacks[i];
            for (int x = 0; x < attack.length; x++) {
                damageList.add(new Damage(i + 1, x % 3 + 1, x / 3 + 1, attack[x]));
            }

            damageList.sort(Comparator.comparingDouble(o -> o.score));
            Collections.reverse(damageList);
            highSourceList.addAll(damageList.subList(0, 3));
        }
        List<Damage> boss1List = highSourceList.stream().filter(o -> o.boss == 1).collect(Collectors.toList());

        int n = boss1List.size();
        int[][] totals = new int[n][n];
        // 初始化states
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                totals[i][j] = -1;
            }
        }
        // 对第 0 个数据特殊处理，可以利用哨兵优化
        totals[0][0] = 0;
        totals[0][1] = boss1List.get(0).damage;

        // 动态规划，转移状态
        for (int i = 1; i < n; i++) {
            // 不选择第i个数值
            for (int j = 0; j < n; j++) {
                if (totals[i - 1][j] >= 0) {
                    totals[i][j] = totals[i - 1][j];
                }
            }
            // 选择第i个数值
            for (int j = 1; j < n; j++) {
                if (totals[i - 1][j - 1] != -1 && boss1List.get(i).damage != -1) {
                    int v = totals[i - 1][j - 1] + boss1List.get(i).damage;
                    if (v > totals[i][j]) {
                        totals[i][j] = v;
                    }
                }
            }
        }

        int minValue = -1;
        int minI = -1, minJ = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (totals[i][j] >= target) {
                    if (minValue == -1 || totals[i][j] < minValue) {
                        minValue = totals[i][j];
                        minI = i;
                        minJ = j;
                    }
                }
            }
        }

        log.info("state array:\n");
        for (int i = 0; i < n; i++) {
            log.info(Arrays.toString(totals[i]));
        }

        int j = minJ;
        List<Damage> resultList = new ArrayList<>();
        for (int i = minI; i > 0; i--) {
            if (j > 0 && totals[i][j] > 0 && (totals[i][j] - boss1List.get(i).damage == totals[i - 1][j - 1])) {
                resultList.add(boss1List.get(i));
                j--;
            }
        }
        for (Damage damage : resultList) {
            log.info("第 {} 位成员 {} 队出刀，对 boss{} 的伤害:{}", damage.no, damage.team, damage.boss, damage.damage);
        }
        log.info("boss剩余血量：{}，设定溢出伤害：{}，出刀总伤害：{}", hp, offset, minValue);
    }

    private static class Damage {
        public Damage(int no, int team, int boss, int damage) {
            this.no = no;
            this.team = team;
            this.boss = boss;
            this.damage = damage;
            switch (boss) {
                case 1:
                case 2:
                    this.score = damage;
                    break;
                case 3:
                case 4:
                    this.score = 1.1 * damage;
                    break;
                case 5:
                    this.score = 1.2 * damage;
                    break;
                default:
                    this.score = -1;
            }
        }

        int no;
        int team;
        int boss;
        int damage;
        double score;
    }


}

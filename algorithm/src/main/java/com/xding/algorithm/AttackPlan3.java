package com.xding.algorithm;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Class Description
 *
 * @author xding
 * @date 2020/5/18 01:37
 */
public class AttackPlan3 {
    private static final Logger log = LoggerFactory.getLogger(AttackPlan3.class);

    private static class Damage {
        /**
         * @param no     数据在 list 中的下标
         * @param user   人员编号
         * @param team   队伍编号
         * @param boss   boss 编号
         * @param damage 这个队伍对该 boss 的伤害
         */
        public Damage(int no, int user, int team, int boss, double damage) {
            this.no = no;
            this.user = user;
            this.team = team;
            this.boss = boss;
            this.damage = damage;
        }

        int no;
        int user;
        int team;
        int boss;
        double damage;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public int getTeam() {
            return team;
        }

        public void setTeam(int team) {
            this.team = team;
        }

        public int getBoss() {
            return boss;
        }

        public void setBoss(int boss) {
            this.boss = boss;
        }

        public double getDamage() {
            return damage;
        }

        public void setDamage(double damage) {
            this.damage = damage;
        }
    }

    private static class Boss {
        public Boss(int no, double hp) {
            this.no = no;
            this.hp = hp;
        }

        int no;
        double hp;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public double getHp() {
            return hp;
        }

        public void setHp(double hp) {
            this.hp = hp;
        }
    }

    public static void main(String[] args) {
        double[][] attackArray = {
            {49, 45, 30, 49, 47, 29, 49, 44, 28, 46, 45, 28, 57, 45, 30},
            {44.3, 50.5, 30.6, 50.6, 48.5, 30.5, 47.1, 49.8, 30.7, 43.8, 43.1, 27.1, 47, 58.4, 32.3},
            {64, 50, 32, 52, 50, 32, 46, 49, 33, 44, 47, 30, 60, 50, 35},
            {42.6, 36.6, 30.3, 44, 35, 30.3, 42.4, 36.5, 28.9, 45, 34, 28, 50, 49, 31},
            {-1, 40.1, 31.2, -1, 45.2, 31, -1, 45.3, 31.2, -1, 43.5, 30.1, 57, 44.1, 34},
            {44, 40, 30.1, 45.3, 44.3, 30.1, 43, 42.1, 29, 42.4, 38.9, 26.8, 44.4, 39.4, 28},
            {46, 43, 27, 43, 44, 27, 45, 41, 28, 40, 39, 26, 54, 40, 28},
            {42, 60, 30, 47, -1, 28.2, 44, -1, 29, 42, -1, 28, 45, 53, 30},
            {48, 47, 34, 47, 49, 32, 44, 44, 34, 42, 43, 31, 55, 45, -1},
            {40, -1, 29, 44, 35, 29, 40, 50, 29, 40, 38, 28, 39, 47, 29},
            {47.5, 34.5, 35.4, 43.5, 40.8, 30.8, 47.5, 36.8, 27.8, 43, 26.5, 26.5, 50, 30.2, 28},
            {42.5, 58, 28, 48, 50.7, 31, 42, 46, 28, 40.5, 42.6, 27, 46, 52, 30},
            {43.3, 46.6, 30, 48.4, 46.1, 28.8, 42.4, 37.4, 25.3, 35.8, 35.5, 23.5, 42.5, 40.4, 26},
            {48.1, 42, 29, 51.4, 47.4, 30.1, 47, 42, 28.8, 45.4, 37.7, 26.7, 48.9, 40.1, 28.1},
            {42, -1, -1, -1, -1, -1, 46.1, -1, -1, 43, -1, -1, 46.3, 55, -1},
            {37, 41, 28.8, 37.8, 41, 28.3, 38.8, 40.1, 26.6, 34.7, 35.2, 25.4, 37.7, 37, 26.5},
            {43, 48, 28, 42.9, 48.5, 28, 39.8, 46, -1, 37, 44, 21, 41, 45, 31},
            {63, 44, 33, 52, 38, 37, 61, -1, -1, -1, 44, 31, 54, 30, 36},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 46.7, 24, 45, 48, 22.4, 44, 42, 22.8, 41.3, 39.6, 22, 52.1, 46.8, 24.6},
            {47.4, 50.6, 31.3, 48.3, 52.2, 30, 44.5, 49.6, 28, 45.7, 42.2, 26.1, 42.1, 46, 29.9},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {62.7, 49.7, 31.3, -1, -1, -1, -1, 47, 30, -1, 44.3, 27.7, 56.3, 44.5, 28.7},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {46.2, 37.7, 31.7, 47.2, 39.2, 32.6, 45.8, 38.8, 31.9, 46.2, 37.5, 30.7, 47, 39, 31.5},
            {56, 40.7, 26, 45, 41.6, 30.5, 45, 36, 29, 42, 36, 28, 54, 36, 28},
            {34.6, 46.5, 46.3, 34, 45, 48.6, 35.8, 44.3, 44.7, 32.3, 39.8, 41.9, 33.4, 42, 41.7},
            {38, -1, 27.8, 43.4, -1, 28.8, 42.3, -1, 25.4, 40, -1, 27, 38.3, 51.1, 28.3},
            {41.8, -1, 28, 48.1, -1, 27, 44.1, -1, 26, 41.7, -1, 25.6, 40.5, 55.5, 25.8},
            {45, 40, 31, 48, 43, 25, 46, 39, 30, 50, 35, 23, 50, 35, 25}};

        List<List<Damage>> totalDamageList = initData(attackArray);

        List<Boss> bossList = new ArrayList<>();
        bossList.add(new Boss(2, 520));
        bossList.add(new Boss(3, 1200));
        bossList.add(new Boss(4, 1400));
        bossList.add(new Boss(0, 500));
        bossList.add(new Boss(1, 1000));

        AttackPlan3 plan = new AttackPlan3();

        for (Boss boss : bossList) {
            if (totalDamageList.isEmpty()) {
                break;
            }
            List<Damage> damageList = plan.solve(totalDamageList, boss.no, boss.hp);
            log.info("damageList:{}", JSON.toJSONString(damageList));
        }

    }

    private static List<List<Damage>> initData(double[][] attackArray) {
        List<List<Damage>> totalDamageList = new ArrayList<>(90);
        for (int i = 0; i < attackArray.length; i++) {
            for (int j = 0; j < 3; j++) {
                List<Damage> teamList = new ArrayList<>(5);
                for (int k = 0; k < 5; k++) {
                    teamList.add(new Damage(i * 3 + j, i, j, k, attackArray[i][k * 3 + j]));
                }
                teamList.sort(Comparator.comparingDouble(o -> o.damage));
                Collections.reverse(teamList);
                totalDamageList.add(teamList);
            }
        }
        return totalDamageList;
    }

    private static List<List<Damage>> initData(List<List<Damage>> totalDamageList) {
        for (int i = 0; i < totalDamageList.size(); i++) {
            List<Damage> damageList = totalDamageList.get(i);
            if (damageList == null || damageList.size() <= 0) {
                continue;
            }
            for (Damage damage : damageList) {
                damage.setNo(i);
            }
            damageList.sort(Comparator.comparingDouble(o -> o.damage));
            Collections.reverse(damageList);
        }
        return totalDamageList;
    }

    public List<Damage> solve(List<List<Damage>> totalDamageList, int boss, double hp) {
        List<Damage> curDamageList = new ArrayList<>();
        double curTotalDamage = 0;

        // 按优先级取超过 boss 血量的队伍
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < totalDamageList.size(); i++) {
                List<Damage> sortedDamageList = totalDamageList.get(i);
                if (sortedDamageList == null || sortedDamageList.isEmpty()) {
                    continue;
                }
                Damage damage = sortedDamageList.get(j);
                if (damage.boss == boss && damage.damage > 0) {
                    curDamageList.add(damage);
                    curTotalDamage += damage.damage;
                }
            }
            if (curTotalDamage > hp) {
                break;
            }
        }

        // 计算本次 boss 的出刀队伍
        List<Damage> attackList = compute(curDamageList, (int) hp * 10);

        // 从总队伍中删除本次的出刀队伍
        for (Damage damage : attackList) {
            totalDamageList.set(damage.no, null);
        }

        return attackList;
    }

    private List<Damage> compute(List<Damage> damageList, int hp) {
        if (damageList == null || damageList.isEmpty()) {
            return Collections.emptyList();
        }
        int iLength = damageList.size();
        int jLength = damageList.size() + 1;
        int[][] dp = new int[iLength][jLength];
        // 动态规划数组初始化
        for (int i = 0; i < iLength; i++) {
            for (int j = 0; j < jLength; j++) {
                dp[i][j] = -1;
            }
        }
        // 设置第一条数据的值
        dp[0][0] = 0;
        dp[0][1] = (int) damageList.get(0).damage * 10;

        for (int i = 1; i < iLength; i++) {
            // 不选择第i个
            for (int j = 0; j < jLength; j++) {
                if (dp[i - 1][j] >= 0) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            // 选择第 i 个
            for (int j = 1; j < jLength; j++) {
                if (dp[i - 1][j - 1] == -1 || damageList.get(i).damage <= 0) {
                    continue;
                }
                dp[i][j] = Math.max((int) (dp[i - 1][j - 1] + damageList.get(i).damage * 10), dp[i - 1][j]);
            }
        }

        int resultI = 0;
        int resultJ = 0;
        int resultDamage = Integer.MAX_VALUE;
        // 取超过 boss 血量的最小值
        for (int i = 0; i < iLength; i++) {
            for (int j = 0; j < jLength; j++) {
                if (dp[i][j] > hp && dp[i][j] < resultDamage) {
                    resultI = i;
                    resultJ = j;
                    resultDamage = dp[i][j];
                }
            }
        }
        // 未达到 boss 血量时取最大值
        if (resultDamage == Integer.MAX_VALUE) {
            resultDamage = 0;
            for (int i = 0; i < iLength; i++) {
                for (int j = 0; j < jLength; j++) {
                    if (dp[i][j] > resultDamage) {
                        resultI = i;
                        resultJ = j;
                        resultDamage = dp[i][j];
                    }
                }
            }
        }

        log.info("state array:");
        for (int i = 0; i < iLength; i++) {
            log.info(Arrays.toString(dp[i]));
        }

        int j = resultJ;
        List<Damage> resultList = new ArrayList<>();
        for (int i = resultI; i > 0; i--) {
            if (j < 1 || dp[i][j] <= 0) {
                continue;
            }
            int v = (int) (dp[i][j] - damageList.get(i).damage * 10);
            if (v == dp[i - 1][j - 1]) {
                resultList.add(damageList.get(i));
                j--;
            }
        }

        // 第一个值特殊处理，判断是否包含第一个值
        if (j != 0) {
            resultList.add(damageList.get(0));
        }

        for (Damage damage : resultList) {
            log.info("第 {} 位成员 {} 队出刀，对 boss{} 的伤害:{}", damage.user + 1, damage.team + 1, damage.boss + 1, damage.damage);
        }
        log.info("boss剩余血量：{}，出刀总伤害：{}", hp / 10.0, resultDamage / 10.0);

        return resultList;
    }

}

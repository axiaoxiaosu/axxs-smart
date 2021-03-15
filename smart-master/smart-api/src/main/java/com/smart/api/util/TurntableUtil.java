//package com.smart.api.util;
//
//import com.google.common.base.Preconditions;
//import javafx.util.Pair;
//
//import java.util.List;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
///**
// * 转盘抽奖
// * 使用TreeMap进行区间排序,通过计算随机数所处区间判断抽奖结果
// *
// * @param <K> 奖品
// * @param <V> 概率
// */
//public class TurntableUtil<K, V extends Number> {
//    private TreeMap<Double, K> weightMap = new TreeMap<>();
//
//    public TurntableUtil(List<Pair<K, V>> list) {
//        Preconditions.checkNotNull(list, "数组不能为null");
//        for (Pair<K, V> pair : list) {
//            //累加区间
//            double lastKey = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey();
//            this.weightMap.put(pair.getValue().doubleValue() + lastKey, pair.getKey());
//        }
//    }
//
//    public K extract() {
//        //在范围内随机抽一个数 判断其所在区间
//        double randomWeight = this.weightMap.lastKey() * Math.random();
//        SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight);
//        return this.weightMap.get(tailMap.firstKey());
//    }
//}

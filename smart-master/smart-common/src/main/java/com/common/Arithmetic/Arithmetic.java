package com.common.Arithmetic;

public class Arithmetic<T> {


    public static void main(String[] args) {
        //冒泡示例:
//        System.out.println(Arrays.toString(bubbling(new Short[]{1, 2, 9, 4, 5, 10, 33, 34}, (short) 2)));
//        System.out.println(Arrays.toString(bubblingV2(new Short[]{1, 2, 9, 4, 5, 10, 33, 33, 34}, (short) 1)));
        //选择排序示例
        //System.out.println(Arrays.toString(choice(new Short[]{1, 2, 9, 4, 5, 10, 33, 34}, (short) 1)));
        //    System.out.println(Arrays.toString(choiceV2(new Short[]{1, 2, 9, 4, 5, 10, 33, 34})));
        //插入排序示例
        //  System.out.println(Arrays.toString(insertion(new Short[]{1, 2, 9, 4, 5, 10, 34, 33})));
    }


    /**
     * 插入排序
     * 原理:把元素分成2组  一组是排序过的一组是没排序的，每次拿没排序的从后往前比较已排序的来确定位置，并插入已排序列中
     * 外层循环重下标1开始，默认下标0已经排序，这里开始把元素分成2组了
     * 内存循环用j=i 和 j=j-1来进行元素的比较，如果成立就交互位置
     *
     * @param args
     * @return
     */
    public static Short[] insertion(Short[] args) {
        int count = 0;
        for (int i = 0; i < args.length; i++) {
            for (int j = i; j > 0; j--) {
                count++;
                if (args[j] < args[j - 1]) {
                    each(args, j, j - 1);
                } else {
                    break;
                }

            }
        }
        System.out.println(String.format("循环的次数:%s", count));
        return args;
    }

    /**
     * 选择排序
     * 原理:定义一个最小值的下标
     * 外层循环从0开始 minIndex=i 代表minIndex是最小值的下标
     * 内存循环全数组找到最小值并将最小值给到minIndex  j = i + 1是每次循环后最小值都在最前面了就不用在遍历这个值了
     * 内存循环结束后在外循环依次放入最小值
     *
     * @return
     */
    public static Short[] choice(Short[] args, short direction) {
        int count = 0;
        for (int i = 0; i < args.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < args.length; j++) {
                count++;
                if (direction == 1 && args[minIndex] > args[j]) {
                    minIndex = j;
                }
                if (direction != 1 && args[minIndex] < args[j]) {
                    minIndex = j;
                }

            }
            each(args, i, minIndex);
        }

        System.out.println(String.format("内循环的次数:%s", count));
        return args;
    }

    /**
     * 选择排序优化
     *
     * @return
     */
    public static Short[] choiceV2(Short[] args) {
        int count = 0;
        for (int i = 0; i < args.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < args.length; j++) {
                count++;
                if (args[minIndex] > args[j]) {
                    minIndex = j;
                }
            }


            if (minIndex != i) {
                each(args, i, minIndex);
            }
        }

        System.out.println(String.format("循环的次数:%s", count));
        return args;

    }


    /**
     * 冒泡实现优化版，添加一个mark标记，当比较没有通过时，代表后面的已经全部有序，没必要继续排列下去
     *
     * @param args
     * @param direction 1 升序  非1 降序
     */
    public static Short[] bubblingV2(Short[] args, short direction) {
        short count = 0;
        for (int i = 0; i < args.length - 1; i++) {
            boolean mark = false;
            for (int j = 0; j < args.length - i - 1; j++) {
                count++;
                if (direction == 1) {
                    if (args[j] > args[j + 1]) {
                        each(args, j, j + 1);
                        mark = true;
                    }
                } else {
                    if (args[j] < args[j + 1]) {
                        each(args, j, j + 1);
                        mark = true;
                    }
                }
            }
            if (!mark) {
                break;
            }
        }
        System.out.println(String.format("循环的次数:%s", count));
        return args;
    }


    /**
     * 冒泡实现，两两比较大的放在最后  ,外层循环从最长开始步长-1，内层循环判断条件小于外层的循环下班(每一次冒泡最大的都放在了最后，所以内存循环每次循环都比上次少一些)
     *
     * @param args
     * @param direction 1 升序  非1 降序
     */
    public static Short[] bubbling(Short[] args, short direction) {
        short count = 0;
        for (int i = args.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                count++;
                if (direction == 1) {
                    if (args[j] > args[i]) {
                        each(args, j, i);
                    }
                } else {
                    if (args[j] < args[i]) {
                        each(args, j, i);
                    }
                }
            }
        }
        System.out.println(String.format("循环的次数:%s", count));
        return args;

    }

    /**
     * 交互元素
     *
     * @param a
     * @param i
     * @param j
     */
    public static void each(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

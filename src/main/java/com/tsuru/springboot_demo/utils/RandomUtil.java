package com.tsuru.springboot_demo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;


public class RandomUtil {

	/**
	 * Logger for this class
	 */
	protected static Logger logger = LogManager.getLogger(RandomUtil.class);

	private static Random random = new Random();

	public static double nextDouble() {
		return random.nextDouble();
	}
	
	public static int nextInt(int max){
		return random.nextInt(max);
	}

	/**
	 * 不包含最大值
	 *
	 * @param max
	 * @return
	 */
	public static int random(int max) {
		return random.nextInt(max);
	}
	
	public static String random(List<String> list) {
		return list.get(random.nextInt(list.size()));
	}

	/**
	 * 包含最大最小值
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min, int max) {
		if (max - min <= 0) {
			return min;
		}
		return min + random.nextInt(max - min + 1);
	}


	/**
	 * 根据权重随机得到对应的值 形如：[[1, 20], [2, 60], [3, 30]]
	 *
	 * @param array
	 * @return
	 */
	public static int getRandomValue(int[][] array) {
		int totalWeight = 0;
		int value = 0;
		for (int[] item : array) {
			totalWeight += item[1];
		}
		int weight = random(totalWeight);
		int tempWeight = 0;
		for (int[] item : array) {
			tempWeight += item[1];
			if (weight < tempWeight) {
				value = item[0];
				break;
			}
		}
		return value;
	}

	/**
	 * 根据权重随机得到权重的索引 形如：[[1, 20], [2, 60], [3, 30]]
	 *
	 * @param array
	 * @return
	 */
	public static int getRandomIndex(int[][] array) {
		int totalWeight = 0;
		for (int[] item : array) {
			totalWeight += item[1];
		}
		int weight = random(totalWeight);
		int tempWeight = 0;
		for (int i = 0; i < array.length; i++) {
			tempWeight += array[i][1];
			if (weight < tempWeight) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 根据权重随机得到权重的索引 形如：[20, 60, 20]
	 *
	 * @param array
	 * @return
	 */
	public static int getRandomIndex(int[] array) {
		int totalweight = 0;
		for (int item : array) {
			totalweight += item;
		}
		int random = random(totalweight);
		int tempWeight = 0;
		for (int i = 0; i < array.length; i++) {
			tempWeight += array[i];
			if (random < tempWeight) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * 以权重列表的形式获得随机值
	 * 
	 * @param array
	 * @return
	 */
	public static int getRandomIndex(List<Integer> array) {
		int[] aArray = new int[array.size()];
		for(int i = 0; i < array.size(); i ++){
			aArray[i] = array.get(i);
		}
		return getRandomIndex(aArray);
	}
	
}

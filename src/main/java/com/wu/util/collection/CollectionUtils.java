package com.wu.util.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zps
 * @date 2018/9/10
 */
public class CollectionUtils {
  /**
   * 初始化一个Map
   *
   * @return
   */
  public static <K, V> Map<K, V> newHashMap(K key, V value) {
    Map<K, V> res = new HashMap<>();
    res.put(key, value);
    return res;
  }
}

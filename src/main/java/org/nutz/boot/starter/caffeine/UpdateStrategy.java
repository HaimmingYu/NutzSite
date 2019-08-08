package org.nutz.boot.starter.caffeine;


/**
 * @author apple
 */
public interface UpdateStrategy {

    /**
     * 是否需要强制更新缓存
     * @param key 缓存键
     * @return should update ?
     */
    boolean shouldUpdate(String key);

    /**
     * 清除全部缓存
     * @param key
     * @return
     */
    boolean invalidateAll(String key);

    default boolean invalidateAll(){
        return false;
    }

    default boolean shouldUpdate(){
        return false;
    }
}

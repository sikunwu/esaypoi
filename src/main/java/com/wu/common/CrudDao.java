package com.wu.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zps
 * @date 2018/9/10
 */
public interface CrudDao<T> {

    /**
     * count  all
     *
     * @param entity
     * @return
     */
    int getCount(T entity);
    /**
     * count by  entity
     * **/
    int getCount2(T entity);

    /**
     * 根据主键获取单条数据
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 根据实体获取单条数据
     *
     * @param entity
     * @return
     */
    T get(T entity);

    /**
     * 根据Map实体获取单条数据
     *
     * @return
     */
    List<T> getByMap(Map<String, String> map);

    /**
     * 查询数据列表
     *
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * 查询所有数据列表
     *
     * @param entity
     * @return
     */
    List<T> findAllList(T entity);

    /**
     * 查询所有数据列表
     *
     * @return
     * @see List<T> findAllList(T entity)
     */
    @Deprecated
    List<T> findAllList();

    /**
     * 添加全部字段
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 添加非空字段
     *
     * @param entity
     * @return
     */
    int insertSelective(T entity);

    /**
     * 根据主键更新非空字段
     *
     * @param entity
     * @return
     */
    int update(T entity);
    int updateByPrimaryKeySelective(T entity);

    /**
     * 根据主键更新全部字段
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKey(T entity);

    /**
     * 根据主键 物理 删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int deleteIds(@Param("ids") String ids);

    /**
     * 根据主键 逻辑 删除
     *
     * @param id
     * @return
     */
    int delete(int id);

    int updateFieldById(
            @Param("col") String col, @Param("value") String value, @Param("id") Integer id);

    int updateStatusByIds(@Param("status") String status, @Param("ids") String ids);

    List<T> getByField(@Param("col") String col, @Param("value") String value);

    Integer getSeqNextVal();

    /**
     * 清空表
     * @return
     */
    int delByCrossID(String crossid);

    /**
     * 设置序列值复位
     * @return
     */
    int setVal();
}

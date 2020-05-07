package com.wu.common;


import com.github.pagehelper.PageHelper;
import com.wu.exception.ResultReturnException;
import com.wu.util.RespCode;
import com.wu.util.collection.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zps
 * @date 2018/9/10
 */
// @Cacheable(value = "CrudService#1800", condition = "true" ,key = "target+method.name+args[0]")
public abstract class CrudService<D extends CrudDao<T>, T extends BaseModel> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 持久层对象
     */
    @Autowired
    protected D mapper;

    /**
     * 根据实体查询数量
     *
     * @param entity
     * @return
     */
    //    //@Cacheable(condition = "false")
    public int getCount(T entity) {
        return mapper.getCount(entity);
    }

    /**
     * 根据主键获取单条数据
     *
     * @param id
     * @return
     */
    // @Cacheable(condition = "false")
    public T getByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据实体获取单条数据
     *
     * @param entity
     * @return
     */
    // @Cacheable(condition = "false")
    public T get(T entity) {
        return mapper.get(entity);
    }

    /**
     * 根据Map实体获取单条数据
     *
     * @return
     */
    // @Cacheable(condition = "false")
    public List<T> getByMap(Map<String, String> map) {
        return mapper.getByMap(map);
    }

    /**
     * 查询数据列表
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return mapper.findList(entity);
    }

    /**
     * 查询所有数据列表
     *
     * @param entity
     * @return
     */
    public List<T> findAllList(T entity) {
        return mapper.findList(entity);
    }

    /**
     * 查询所有数据列表
     *
     * @return
     * @see List<T> findAllList(T entity)
     */
    @Deprecated
    public List<T> findAllList() {
        return mapper.findAllList();
    }

    /**
     * 添加全部字段
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    // //@CacheEvict(allEntries = true)
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    /**
     * 批量添加全部字段
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    // //@CacheEvict(allEntries = true)
    public int saveGroup(List<T> entity) {
        entity.forEach(s -> mapper.insert(s));
        return entity.size();
    }

    /**
     * 添加非空字段
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int insertSelective(T entity) {
        return mapper.insertSelective(entity);
    }

    /**
     * 根据主键更新非空字段
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int updateByPrimaryKeySelective(T entity) {
        if (entity == null || !entity.withPrimaryKey()) {
            throw new ResultReturnException(RespCode.BADREQUEST.msg,RespCode.BADREQUEST.code);
        }
        return mapper.update(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int update(T entity) {
        if (entity == null || !entity.withPrimaryKey()) {
            throw new ResultReturnException(RespCode.BADREQUEST.msg,RespCode.BADREQUEST.code);
        }
        return mapper.updateByPrimaryKeySelective(entity);
    }

    //批量更新
    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int updateAll(List<T> entity) {
        //controller 作了主键非空判断
//        entity.forEach(e -> {if (e.withPrimaryKey()==false) {throw new ResultReturnException("缺少主键");}});
        entity.forEach(s ->mapper.updateByPrimaryKey(s));
        return entity.size();
    }

    /**
     * 根据主键更新全部字段
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int updateByPrimaryKey(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键 逻辑 删除
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int delete(Integer id) {
        if (id == null) {
            throw new ResultReturnException("参数不能为空");
        }
        return mapper.delete(id);
    }

    /**
     * 批量删除
     * */
//    @Transactional(rollbackFor = Exception.class)
//    // @CacheEvict(allEntries = true)
//    public int deleteBatch(String ids) {
//        if (!ids.matches("[0-9,]+")) {
//            throw new ResultReturnException("id 非法！");
//        }
//        return mapper.deleteIds(ids);
//    }

    /**
     * 根据主键 物理 删除
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int deleteIds(@Param("ids") String ids) {
        return mapper.deleteIds(ids);
    }

    /**
     * 这个分页是一次全部查询再分页
     *
     * @param page
     * @param entity
     * @return
     */
    public PageBean<T> findPage(PageBean<T> page, T entity) {
        int count = mapper.getCount(entity);
        return count > 0 ? page.setData(page, mapper.findList(entity), count) : new PageBean(0);
    }

    /**
     * 每次分页查询
     *
     * @param page
     * @return
     */
    public PageBean<T> findPage(PageBean<T> page) {
        int count = mapper.getCount(page.getEntity());
        Map<String, String> map =
                page.getParams() == null
                        ? CollectionUtils.newHashMap("currentPage", page.getCurrentPage().toString())
                        : page.getParams();
        map.put("pageSize", page.getPageSize().toString());
        map.put("startIndex", page.getStartIndex().toString());
        logger.debug(map.toString());
        List<T> list = mapper.getByMap(map);
        return count > 0 && list.size() > 0 ? page.setData(page, list, count) : new PageBean(0);
    }

    public PageBean<T> findPage2(int pageNo, int pageSize, T entity) {
        // 物理分页
        PageHelper.startPage(pageNo, pageSize);
        List<T> list = mapper.findList(entity);
    return new PageBean<T>()
            .setData(new PageBean<T>(pageNo, pageSize), list, mapper.getCount2(entity));
}
    /**
     * 实体对象转Map
     * @param obj 实体对象
     * @author zps
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(rollbackFor = Exception.class)
    // @CacheEvict(allEntries = true)
    public int save(T entity) {
        if (entity.getIsNewRecord()) {
            return mapper.insert(entity);
        } else {
            return mapper.updateByPrimaryKey(entity);
        }
    }

    public Integer getSeqNextVal() {
        return mapper.getSeqNextVal();
    }

    /**
     * 清空表
     * 设置序列值复位
     * @return
     */
    public Integer delByCrossID(String crossid){
        int num = mapper.delByCrossID(crossid);
        return num;
    }
}

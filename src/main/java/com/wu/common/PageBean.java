package com.wu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pageHelper插件的Bean
 *
 * @author Ian
 * @date 2018/3/30
 */
public class PageBean<T> {

    // 当前页
    private Integer currentPage = 1;
    // 每页显示的总条数
    // @Value("${page.size}")
    private Integer pageSize = 10;
    // 总条数
    private Integer totalNum;
    // 是否有下一页
    private Integer isMore;
    // 总页数
    private Integer totalPage;
    // 开始索引
    private Integer startIndex;
    // 分页结果
    private List<T> items;


    // true表示一次请求全部，交给前端分页
    @JsonIgnore
    private Boolean isAll = false;

    @JsonIgnore
    private Map<Integer, List<T>> page;

    @JsonIgnore
    private T entity;

    @JsonIgnore
    private Map<String, String> params;

    public PageBean() {
        super();
    }

    public PageBean(Integer totalNum) {
        super();
        this.currentPage = 0;
        this.totalNum = totalNum;
        this.totalPage = 0;
        this.isMore = 0;
    }
    public PageBean(Integer currentPage, Integer pageSize) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = 0;
        this.totalPage = 0;
        this.startIndex = (currentPage - 1) * pageSize;
    }


    public PageBean(Integer currentPage, Integer pageSize, T entity) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = 0;
        this.totalPage = 0;
        this.startIndex = (currentPage - 1) * pageSize;
        this.entity = entity;
    }

    public PageBean(Integer currentPage, Integer pageSize, Integer totalNum) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.totalPage = (totalNum + pageSize - 1) / pageSize;
        this.startIndex = (currentPage - 1) * pageSize;
        this.isMore = currentPage >= totalPage ? 0 : 1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Boolean getIsAll() {
        return isAll;
    }

    public void setIsAll(Boolean isAll) {
        this.isAll = isAll;
    }

    public Map<Integer, List<T>> getPage() {
        return page;
    }

    public void setPage(Map<Integer, List<T>> page) {
        this.page = page;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public PageBean setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    // todo::根据缓存策略和前端适当调整
    public PageBean setData(PageBean pageBean, List<T> list, int totalNum) {
        if (totalNum == 0) {
            pageBean.setTotalNum(0);
            pageBean.setCurrentPage(0);
            return pageBean;
        }
        Integer pageSize = pageBean.getPageSize();
        boolean isAll = pageBean.getIsAll();
        int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalNum(totalNum);

        if (!isAll) {
            pageBean.setItems(list);
        } else {
            HashMap<Integer, List<T>> map = new HashMap<>();
            if (totalPage > 1) {
                for (int i = 0; i < totalPage; ) {
                    List<T> subList = list.subList(pageSize * i, pageSize * (i + 1));
                    i++;
                    map.put(i, subList);
                }
            }
            map.put(totalPage, list.subList((totalPage - 1) * pageSize, list.size()));
            pageBean.setPage(map);
        }
        return pageBean;
    }

    @Override
    public String toString() {
        return "PageBean{"
                + "currentPage="
                + currentPage
                + ", pageSize="
                + pageSize
                + ", totalNum="
                + totalNum
                + ", isMore="
                + isMore
                + ", totalPage="
                + totalPage
                + ", startIndex="
                + startIndex
                + ", items="
                + items
                + ", isAll="
                + isAll
                + ", page="
                + page
                + ", entity="
                + entity
                + '}';
    }
}

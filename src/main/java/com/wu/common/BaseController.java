package com.wu.common;


import com.wu.exception.ResultReturnException;
import com.wu.util.Resp;
import com.wu.util.RespCode;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author:zps
 * @Date:2018/9/11
 */
public abstract class BaseController <S extends CrudService<D,T>,D extends CrudDao<T>,T extends BaseModel> {

    @Autowired
    protected S service;

    @ApiOperation(value="根据ID获取数据", notes="根据ID获取数据")
    @ApiImplicitParam(name = "id", value = "记录ID", required = true, paramType="path",dataType = "Integer")
    @GetMapping("/{id}")
    public Object get(@PathVariable Integer id) {
        System.err.println(id);
        T entity = service.getByPrimaryKey(id);
        return Resp.ok(entity,entity == null ? 0:1);
    }

    @ApiOperation(value="参数分页获取记录", notes="分页按参数获取记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页码", required = true,paramType="path", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true,paramType="path", dataType = "Integer"),
            @ApiImplicitParam(name = "entity", value = "筛选条件entity", dataType = "entity")
    })
    @PostMapping("/page/{pageNo}/{pageSize}")
    public Object page(@PathVariable Integer pageNo, @PathVariable Integer pageSize, @RequestBody T entity)  {
        PageBean<T> page = service.findPage2(pageNo, pageSize, entity);
        return Resp.ok(page,page.getTotalNum());
    }

    @ApiOperation(value="参数不分页获取记录", notes="不分页获取全部数据（无参）或获取给定参数记录")
    @ApiImplicitParam(name = "entity", value = "筛选条件entity", dataType = "entity")
    @PostMapping("/getList")
    public Object getList(@RequestBody T entity)  {
        List<T> list = service.findList(entity);
        return Resp.ok(list,list.size());
    }

    @ApiOperation(value = "增加数据", notes = "增加数据")
    @ApiImplicitParam(name = "entity", value = "entity", required = true,  dataType = " entity" )
    @PostMapping("")
    public Object save(@RequestBody T entity)  {
        return Resp.ok(service.insert(entity)>0,1);
    }

    @ApiOperation(value = "批量增加数据", notes = "批量增加数据")
    @ApiImplicitParam(name = "entity", value = "entity", required = true,  dataType = "JsonObject" )
    @PostMapping("savaAll")
    public Object saveGroup(@RequestBody List<T> entity)  {
        return Resp.ok(service.saveGroup(entity)>0,entity.size());
    }

    @ApiOperation(value = "更新数据", notes = "更新数据")
    @ApiImplicitParam(name = "entity", value = "entity", required = true,  dataType = " entity" )
    @PutMapping("")
    public Object update(@RequestBody T entity)  {
        if(!entity.withPrimaryKey()){
            throw new ResultReturnException(RespCode.BADREQUEST.msg,RespCode.BADREQUEST.code);
        }
        return Resp.ok(service.update(entity)>0,1);
    }

    @ApiOperation(value = "批量更新数据", notes = "批量更新数据")
    @ApiImplicitParam(name = "entity", value = "entity list", required = true,  dataType = " JsonObject" )
    @PutMapping("updateAll")
    public Object updateAll(@RequestBody List<T> entity)  {
//        if(!entity.withPrimaryKey()){
//            throw new ResultReturnException(RespCode.BADREQUEST.msg,RespCode.BADREQUEST.code);
//        }
        entity.forEach(e -> {if (e == null ||e.withPrimaryKey()==false) {throw new ResultReturnException("缺少主键");}});
        return Resp.ok(service.updateAll(entity),entity.size());
    }

    @ApiOperation(value = "根据id删除数据", notes = "根据id删除数据")
    @ApiImplicitParam(name = "id", value = "记录ID", required = true,paramType="path", dataType = "int")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id)  {
        int sum = service.delete(id);
        return Resp.ok(sum,sum);
    }
//    @ApiOperation(value = "根据id串批量删除数据", notes = "根据id串批量删除数据")
//    @ApiImplicitParam(name = "ids", value = "记录ID串  ','号分隔", required = true, dataType = "String")
//    @DeleteMapping("/deleteBatch")
//    public Object deleteBatch(@RequestParam("ids") String ids)  {
//        int sum = service.deleteBatch(ids);
//        return Resp.ok(sum,sum);
//    }

    @ApiOperation(value="分页获取全部数据列表", notes="分页获取全部数据列表",response = Map.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pno", value = "当前页码", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "num", value = "每页数目", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "entity", value = "筛选条件entity", dataType = "entity")
    })
    @GetMapping("/getList1")
    public Object getByPage1(Integer num, Integer pno, T entity){
        PageBean<T> page = service.findPage(new PageBean<T>(pno, num,entity));
        return Resp.ok(page,page.getTotalNum());
    }

}

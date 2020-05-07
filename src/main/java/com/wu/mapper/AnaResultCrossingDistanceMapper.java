package com.wu.mapper;


import com.wu.common.CrudDao;
import com.wu.entity.AnaResultCrossingDistance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("anaResultCrossingDistanceMapper")
public interface AnaResultCrossingDistanceMapper extends CrudDao<AnaResultCrossingDistance> {

}
package com.wu.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.wu.common.BaseController;
import com.wu.entity.AnaResultCrossingDistance;
import com.wu.mapper.AnaResultCrossingDistanceMapper;
import com.wu.service.AnaResultCrossingDistanceService;
import com.wu.util.EasyPoiUtils;
import com.wu.util.ExcelExportStylerUtil;
import com.wu.util.Resp;
import com.wu.util.RespCode;
import fundway.brainapp.trafficControl.business.TC.entity.FormStaticEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wsk
 * @date 2019/5/22
 */
@RestController
@RequestMapping("/brainapp/trafficcontrol/tc/1/AnaResultCrossingDistance")
@Api(description = "行人过街时间不足报警信息记录 API")
public class AnaResultCrossingDistanceController extends BaseController<AnaResultCrossingDistanceService, AnaResultCrossingDistanceMapper, AnaResultCrossingDistance> {
    private Logger logger = LoggerFactory.getLogger(AnaResultCrossingDistanceController.class);

    @ApiOperation(value="分页获取全部数据列表", notes="分页获取全部数据列表" )
    @GetMapping("/exportExecl")
    public Object exportExecl(HttpServletResponse response){
        //初始化excel
        EasyPoiUtils.initExcel("行人过街结果",response);
        logger.info("导出开始");
        try (
                ServletOutputStream outputStream = response.getOutputStream();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
         ) {
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);
            exportParams.setStyle(ExcelExportStylerUtil.class);
            exportParams.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
            exportParams.setSheetName("行人过街结果");

            Workbook workbook = null;
            List<AnaResultCrossingDistance> list = service.findList(new AnaResultCrossingDistance());

            List<FormStaticEntity> resultList = new ArrayList<>();
            for (AnaResultCrossingDistance anaResultCrossingDistance:list){
                FormStaticEntity formStaticEntity = new FormStaticEntity();
                formStaticEntity.setLkbh(anaResultCrossingDistance.getLkbh());
                formStaticEntity.setLkmc(anaResultCrossingDistance.getLkmc());
                formStaticEntity.setFaGreen(anaResultCrossingDistance.getFaGreen());
                formStaticEntity.setJkdfxh(anaResultCrossingDistance.getJkdfxh());
                formStaticEntity.setGzxr(anaResultCrossingDistance.getGzxr());

                resultList.add(formStaticEntity);
            }
            if (CollectionUtils.isEmpty(list)){
                return Resp.error(RespCode.BADREQUEST);
            }
            workbook = ExcelExportUtil.exportBigExcel(exportParams, FormStaticEntity.class,resultList);
            // 关闭workbook
            ExcelExportUtil.closeExportBigExcel();
            workbook.write(bufferedOutputStream);
            bufferedOutputStream.flush();
            outputStream.flush();
            logger.info("导出结束");

            return null;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new Resp("导出失败",false,0);
        }
    }
}

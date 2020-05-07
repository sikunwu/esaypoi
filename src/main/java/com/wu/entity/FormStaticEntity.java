package fundway.brainapp.trafficControl.business.TC.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 委托书统计
 * 表格数据实体
 *
 * @author wenchangping
 * @date 2019-04-28
 */
@Data
@NoArgsConstructor
public class FormStaticEntity {
    @Excel(name = "路口编号", orderNum = "1", width = 10)
    private String lkbh;;
    @Excel(name = "路口名称", orderNum = "2",width = 10)
    private String lkmc;
    @Excel(name = "进口的方向号", replace = {"北_0","东_1","南_2","西_3","东北_5","东南_6","西南_7","西北_8"},orderNum = "3",width = 10)
    private Short jkdfxh;
    @Excel(name = "信号方案绿灯时间", orderNum = "4",width = 10)
    private Integer faGreen;
    @Excel(name = "是否关注行人", replace = {"是_1","否_0"},orderNum = "5",width = 10)
    private Short gzxr;

}

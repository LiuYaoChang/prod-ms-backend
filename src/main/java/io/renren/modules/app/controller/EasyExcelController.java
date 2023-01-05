package io.renren.modules.app.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.renren.common.utils.LocalJsonUtil;
import io.renren.modules.app.entity.Member;
import io.renren.modules.app.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 导出数据
 */
@Controller
@RequestMapping("app/excel")
@Api(tags = "EasyExcelController", description = "EasyExcel导入导出测试")
public class EasyExcelController {

    @Autowired
    private EmployeeService exportExcel;
    @ApiOperation(value="导出会员列表Excel")
    @RequestMapping(value = "/exportMemberList", method = RequestMethod.GET)
    public void exportMemberList(HttpServletResponse response) throws IOException {
        exportExcel.exportExcel(response);
    }
    /**
     * 手动创建{@link com.alibaba.excel.ExcelWriter}，指定sheet写入数据。
     * <p>
     * 提供列表和函数作为数据源
     */
    @ApiOperation(value="导出会员列表Excel")
    @RequestMapping(value = "/exportMemberList2", method = RequestMethod.GET)
    public void writeManualWither(HttpServletResponse response) throws IOException {
        setExportResponseAttr(response, "会员列表");
        ExcelWriter excelWriter = null;
        List<Member> memberList = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
        try {
            excelWriter = EasyExcelFactory.write(response.getOutputStream())
                    .head(Member.class)
                    .build();
            final WriteSheet writeSheet1 = EasyExcelFactory.writerSheet("模板1").build();
            excelWriter.write(memberList, writeSheet1);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    private void setExportResponseAttr(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }

}

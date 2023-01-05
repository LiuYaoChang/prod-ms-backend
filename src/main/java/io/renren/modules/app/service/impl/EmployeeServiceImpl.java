package io.renren.modules.app.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import io.renren.modules.app.entity.Employee;
import io.renren.modules.app.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service("employee")
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Employee> employeeList = initData();

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("员工信息.xlsx", "utf-8"));
        //4. 创建输入、输出流
        ExcelWriterBuilder workBook = EasyExcel.write(response.getOutputStream(), Employee.class);

        // sheet方法参数： 工作表的顺序号（从0开始）或者工作表的名字
        workBook.sheet("测试数据表").doWrite(employeeList);
        System.out.println("写入完成！");
    }

    /**
     * 生成测试数据
     * @return
     */
    @Override
    public List<Employee> initData() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            Employee employee = new Employee();
            employee.setEmp_name("小王说："+i);
            employee.setEmp_age(19);
            if (i % 10 == 0) {
                employee.setEmp_sex(1);
            } else {
                employee.setEmp_sex(2);
            }
            employee.setEmp_salary(19999.00+i);
            employee.setEmp_address("北京市朝阳区"+i);
            employee.setEmp_position("Java高级工程师");
            employeeList.add(employee);
        }
        return employeeList;
    }
}

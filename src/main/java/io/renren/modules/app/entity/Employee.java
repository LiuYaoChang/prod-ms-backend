package io.renren.modules.app.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @ExcelIgnore
    private String noid;

    @ColumnWidth(20)
    @ExcelProperty("员工姓名")
    private String emp_name;

    @ColumnWidth(20)
    @ExcelProperty("员工年龄")
    private Integer emp_age;

    @ExcelIgnore
    private Integer emp_sex;

    //冗余字段
    @ColumnWidth(20)
    @ExcelProperty("员工性别")
    private String str_emp_sex;

    @ColumnWidth(20)
    @ExcelProperty("员工工资")
    private Double emp_salary;

    @ColumnWidth(20)
    @ExcelProperty("员工住址")
    private String emp_address;

    @ColumnWidth(20)
    @ExcelProperty("员工岗位")
    private String emp_position;

    //分页相关，当前页与每页的数据条数
    @ExcelIgnore
    private Integer pageNum;
    @ExcelIgnore
    private Integer pageSize;
}

package io.renren.modules.app.service;

import io.renren.modules.app.entity.Employee;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    public void exportExcel(HttpServletResponse res) throws IOException;

    public List<Employee> initData();
}

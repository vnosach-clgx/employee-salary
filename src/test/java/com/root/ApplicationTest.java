package com.root;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

    private final EmployeeService employeeService = new EmployeeService();
    private final SalaryService salaryService = new SalaryService();
    private final Controller subject = new Controller(employeeService, salaryService);

    @RepeatedTest(10)
    void hiredEmployees_successful() {
        var emp1 = new Employee().setName("John").setSalary(15000L);
        var emp2 = new Employee().setName("Bob").setSalary(4000L);
        assertThat(subject.hiredEmployees()).usingRecursiveComparison().isEqualTo(List.of(emp1, emp2));
    }

}

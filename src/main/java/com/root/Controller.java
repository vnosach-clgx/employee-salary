package com.root;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/employees")
public class Controller {

    private final EmployeeService employeeService;
    private final SalaryService salaryService;

    @GetMapping
    public List<Employee> hiredEmployees() {
        CompletionStage<List<CompletionStage<Employee>>> listCompletionStage = employeeService.hiredEmployees()
                .thenApplyAsync(this::enrichBySalary);

        List<CompletionStage<Employee>> filled = listCompletionStage.toCompletableFuture().join();
        filled.forEach(e -> e.thenAccept(x -> log.info("{}", x)));

        return filled.stream()
                .map(e -> e.toCompletableFuture().join())
                .collect(toList());
    }

    private List<CompletionStage<Employee>> enrichBySalary(List<Employee> list) {
        return list.stream()
                .map(employee -> salaryService.getSalary(employee)
                        .thenApplyAsync(employee::setSalary))
                .collect(toList());
    }

}

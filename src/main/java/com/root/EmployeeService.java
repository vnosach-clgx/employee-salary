package com.root;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class EmployeeService {

    private static final List<Employee> REST_EMPLOYEE = List.of(
            new Employee().setName("John"),
            new Employee().setName("Bob")
    );

    public CompletionStage<List<Employee>> hiredEmployees() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Fetching Employees");
            return REST_EMPLOYEE;
        });
    }
}

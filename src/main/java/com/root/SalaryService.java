package com.root;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class SalaryService {

    private static final Map<String, Long> employeeSalary = Map.of("John", 15000L, "Bob", 4000L);

    public CompletionStage<Long> getSalary(Employee hiredEmployee) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Fetching salary for {}", hiredEmployee.getName());
            return employeeSalary.get(hiredEmployee.getName());
        });
    }
}

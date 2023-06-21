package com.ucm.myproject.config.domain;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Table(name = "BudgetDetails")
public class BudgetDetails {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "UserName")
    private String name;

    @Column(name = "Age")
    private int age;

    @Column(name = "AnnualSalary")
    private long salary;

}

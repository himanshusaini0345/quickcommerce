package com.himanshu.quickcommerce.persistence.customers;

import com.himanshu.quickcommerce.domain.customers.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long>{    
}

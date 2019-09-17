package com.atguigu.mvcapp.test;

import java.util.List;

import com.atguigu.mvcapp.CustomerDAOImpl.CustomerDAOJdbcImpl;
import com.atguigu.mvcapp.dao.CriteriaCustomer;
import com.atguigu.mvcapp.dao.CustomerDAO;
import com.atguigu.mvcapp.domain.Customer;

public class CustomerDAOJdbcImplTest {

	private CustomerDAO customerDAO = 
			new CustomerDAOJdbcImpl(); 
	
	@org.junit.Test
	public void testGetAll() {
		List<Customer> customers = customerDAO.getAll();
		
		for(Customer customer: customers) {
			System.out.println(customer);
		}
	}

	@org.junit.Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setAddress("BeiJing");
		customer.setName("Mike");
		customer.setPhone("13130012811");
		
		customerDAO.save(customer);
	}

	@org.junit.Test
	public void testGetInteger() {
		Customer customer = customerDAO.get(1);
		System.out.println(customer);
	}

	@org.junit.Test
	public void testDelete() {
		customerDAO.delete(1);
	}

	@org.junit.Test
	public void testGetCountWithName() {
		Long count = customerDAO.getCountWithName("Jerry");
		
		System.out.println(count);
	}
	
	@org.junit.Test
	public void testGetForListWithCriteriaCustomer() {
		
		CriteriaCustomer cc = new CriteriaCustomer("r", null,null);
		
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
		
		System.out.println(customers);
	}

}

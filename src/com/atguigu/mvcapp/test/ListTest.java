package com.atguigu.mvcapp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.atguigu.mvcapp.domain.Customer;

public class ListTest {

	@Test
	public void test() {
		List list = new ArrayList<Customer>();
		
		for(int i = 0; i <= 100; i++) {
			Customer customer = new Customer(i + "", i + "", i + "");
			list.add(customer);
		}
		
		for (int i = 0; i < list.size(); i++) {
			Customer c = (Customer) list.get(i);
			System.out.println(c);
		}
	}

}

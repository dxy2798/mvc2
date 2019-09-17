package com.atguigu.mvcapp.CustomerDAOImpl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.atguigu.mvcapp.dao.CriteriaCustomer;
import com.atguigu.mvcapp.dao.CustomerDAO;
import com.atguigu.mvcapp.dao.DAO;
import com.atguigu.mvcapp.domain.Customer;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO{

	public List<Customer> getAll() {
		String sql = "SELECT id,name,address,phone FROM customersnew";
		return getForList(sql);
	}

	public void save(Customer customer) {
		String sql = "INSERT customersnew(name,address,phone) VALUES(?,?,?)";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone());
	}

	public Customer get(Integer id) {
		String sql = "SELECT id,name,address,phone FROM customersnew WHERE id = ?";
		return get(sql, id);
	}

	public void delete(Integer id) {
		String sql = "DELETE FROM customersnew WHERE id = ?";
		update(sql, id);
	}

	public long getCountWithName(String name) {
		/*String nameString = null;
		try {
			nameString = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(nameString);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String sql = "SELECT COUNT(id)  FROM customersnew WHERE name = ?";
		
		return getForValue(sql, name);
	}

	@Override
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
		
		String sql = "SELECT id,name,address,phone FROM customersnew WHERE "
				+ "name LIKE ? AND address LIKE ? AND phone LIKE ?";
		
		//修改了 CriteriaCustomer 的 getter 方法: 使其返回的字符串中有 "%%".
		//若返回值为 null 则返回 "%%", 若不为 null, 则返回 "%" + 字段本身的值 + "%"
		return getForList(sql,cc.getName(),cc.getAddress(),cc.getPhone());
	}

	@Override
	public void update(Customer customer) {
		String sql = "UPDATE customersnew SET name = ? ,address = ? , phone = ? WHERE id = ?";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone(),customer.getId());
	}

}

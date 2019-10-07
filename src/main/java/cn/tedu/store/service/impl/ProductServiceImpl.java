package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper productMapper;

	public List<Product> findHotList() {
		List<Product> lists = productMapper.findHotList();
		for (Product product : lists) {
			product.setCategoryId(null);
			product.setStatus(null);
			product.setPriority(null);
			product.setCreatedUser(null);
			product.setCreatedTime(null);
			product.setModifiedUser(null);
			product.setModifiedUser(null);
		}
		return lists;
	}

	public Product getById(Integer id) {

		return productMapper.findById(id);
	}

}

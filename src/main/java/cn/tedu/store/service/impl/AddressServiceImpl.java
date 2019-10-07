package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.controller.ex.DeleteException;
import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.entity.Order;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.AddressSizeLimitException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private IDistrictService districtService;

	public void addnew(Address address, Integer uid, String username) {
		// 根据参数uid获取该用户的收货地址数据的数量
		Integer count = addressMapper.countByUid(uid);
		// 判断数量是否大于或等于限制值
		if (count > MAX_COUNT) {
			// 是：抛出AddressSizeLimitException
			throw new AddressSizeLimitException("用户地址数量超过上限");
		}
		// 判断收货地址数据的数量是否为0
		Integer isDefault = count == 0 ? 1 : 0;

		// 是：即将插入的收货地址是默认的，isDefault=1

		// 否：即将插入的收货地址不是默认的，isDefault=0

		// 补全数据：将isDefault封装到参数address中
		address.setIsDefault(isDefault);
		// 补全数据：将参数uid封装到参数address中
		address.setUid(uid);
		// 补全数据：将参数username封装为参数address中的createdUser和modifiedUser属性值
		address.setCreatedUser(username);
		address.setModifiedUser(username);
		// 补全数据：创建当前时间对象，封装为参数address中的createdTime和modifiedTime属性值
		address.setCreatedTime(new Date());
		address.setModifiedTime(new Date());
		// 补全数据：省，市，区的名称
		District province = districtService.getByCode(address.getProvinceCode());
		if (province != null) {
			address.setProvinceName(province.getName());
		}
		District city = districtService.getByCode(address.getCityCode());
		if (city != null) {
			address.setCityName(city.getName());
		}
		District area = districtService.getByCode(address.getAreaCode());

		if (area != null) {
			address.setAreaName(area.getName());
		}

		// 执行插入数据，并获取返回值
		Integer rows = addressMapper.save(address);
		// 判断返回值是否不为1
		if (rows != 1) {
			// 是：抛出InsertException
			throw new InsertException("插入收货地址出现异常");
		}
	}

	public List<Address> findByUid(Integer uid) {

		return addressMapper.findByUid(uid);
	}

	@Transactional
	public void setDefault(Integer aid, Integer uid, String username) {
		// 根据参数aid查询收货地址数据
		Address result = addressMapper.findByAid(aid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：抛出AddressNotFoundException
			throw new AddressNotFoundException("收货地址不存在");
		}
		// 判断查询结果中的uid和参数uid是否不一致，使用equals()而不要使用!=
		if (!uid.equals(result.getUid())) {
			// 是：抛出AccessDeniedException
			throw new AccessDeniedException("非法访问");
		}
		// 将当前用户的所有收货地址设置为非默认
		Integer rows = addressMapper.updateNonDefault(uid);
		if (rows < 1) {
			throw new UpdateException("更新数据时出现错误[1]");
		}

		// 将指定的收货地址设置为默认
		Integer row = addressMapper.updateDefault(aid, username, new Date());
		if (row != 1) {
			throw new UpdateException("更新数据时出现错误[2]");
		}
	}

	@Transactional
	public void delete(Integer aid, Integer uid, String username) {
		// 根据参数aid查询收货地址数据
		Address result = addressMapper.findByAid(aid);
		// 判断查询结果是否为null：AddressNotFoundException
		if (result == null) {
			throw new AddressNotFoundException("收货地址未找到");
		}
		if (!uid.equals(result.getUid())) {
			// 判断查询结果中的uid与参数uid是否不匹配：AccessDeniedException
			throw new AccessDeniedException("非法访问");
		}
		// 执行删除，并获取返回值
		Integer rows = addressMapper.deleteByAid(aid);
		// 判断受影响行数是否不为1：DeleteException
		if (rows != 1) {
			throw new DeleteException("删除出现异常");
		}
		// 判断查询结果中的isDefault是否为0
		if (result.getIsDefault() == 0) {
			// 删除的不是默认收货地址，可直接结束：return;
			return;
		}
		// 以下分析不是多余的：
		// 调用countByUid(uid)统计当前用户的收货地址数量
		// 判断数量是否为0
		// 当前用户已经没有收货地址，可直接结束：return;
		Integer count = addressMapper.countByUid(uid);
		if (count == 0) {
			return;
		}

		Address item = addressMapper.findLastModified(uid);
		System.out.println(item);
		// 调用findLastModified(uid)找出最后修改的收货地址
		Integer row = addressMapper.updateDefault(item.getAid(), username, new Date());
		// 基于以上查询结果中的aid，将该收货地址设置为默认，并获取返回值
		// 判断返回值是否不为1：UpdateException
		if (row != 1) {
			throw new UpdateException("发生未知错误");
		}

	}

	public Address getByAid(Integer aid) {

		Address result = addressMapper.findByAid(aid);
		return result;
	}

}

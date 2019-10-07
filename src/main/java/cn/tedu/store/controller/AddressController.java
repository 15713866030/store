package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.unit.JsonResult;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
	@Autowired
	private IAddressService addressService;
	
	@RequestMapping("addnew")
	public JsonResult<Void> addNew(HttpSession session,Address address){
	
		Integer uid=getUidFromSession(session);
		String username=getUsernameFromSession(session);
		addressService.addnew(address, uid, username);
		
		
		return new JsonResult<>(SUCCESS);
		
	}
	@GetMapping("/")
	public JsonResult<List<Address>> findByUid(HttpSession session){
		Integer uid=getUidFromSession(session);
		List<Address> data=addressService.findByUid(uid);
		
		return new JsonResult<List<Address>>(SUCCESS, data);
		
	}
	@RequestMapping("{aid}/set_default")
	public JsonResult<Void> setDefault(@PathVariable("aid")Integer aid,HttpSession session){
		String username=getUsernameFromSession(session);
		Integer uid=getUidFromSession(session);
		addressService.setDefault(aid, uid, username);
		
		return new JsonResult<Void>(SUCCESS);
		
	}
	@RequestMapping("{aid}/delete")
	public JsonResult<Void> delete(@PathVariable("aid")Integer aid,HttpSession session){
		String username=getUsernameFromSession(session);
		Integer uid=getUidFromSession(session);
		addressService.delete(aid, uid, username);
		
		return new JsonResult<Void>(SUCCESS);
		
	}

}

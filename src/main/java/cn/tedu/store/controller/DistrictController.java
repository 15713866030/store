package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.District;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.unit.JsonResult;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {
	@Autowired
	private IDistrictService district;

	@GetMapping("/")
	public JsonResult<List<District>> getByParent(String parent) {
		List<District> lists = district.getByParent(parent);

		return new JsonResult<List<District>>(SUCCESS, lists);
	}
	
	@GetMapping("/code")
	public JsonResult<District> getByCode(String code){
		District data=district.getByCode(code);
		return new JsonResult<District>(SUCCESS, data);
		
	}
}

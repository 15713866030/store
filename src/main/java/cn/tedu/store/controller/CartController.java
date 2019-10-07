package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.vo.CartVO;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.unit.JsonResult;

@RequestMapping("carts")
@RestController
public class CartController extends BaseController {

	@Autowired
	private ICartService cartService;

	@RequestMapping("add")
	public JsonResult<Void> addToCart(HttpSession session, Integer pid, Integer num) {
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		cartService.addToCart(uid, pid, num, username);

		return new JsonResult<>(SUCCESS);

	}

	@GetMapping("/")
	public JsonResult<List<CartVO>> getByUid(HttpSession session) {
		Integer uid = getUidFromSession(session);
		List<CartVO> data = cartService.getByUid(uid);
		return new JsonResult<List<CartVO>>(SUCCESS, data);
	}

	@RequestMapping("{cid}/add_num")
	public JsonResult<Integer> addSum(@PathVariable("cid")Integer cid,HttpSession session) {
		String username = getUsernameFromSession(session);
		Integer uid=getUidFromSession(session);
		Integer num = cartService.addNum(cid, uid, username);
		return new JsonResult<Integer>(SUCCESS, num);

	}
	@GetMapping("get_by_cids")
	public JsonResult<List<CartVO>> getByCits(Integer[] cids,HttpSession session){
		Integer uid=getUidFromSession(session);
		List<CartVO> data=cartService.getByCids(cids,uid);
		
		return new JsonResult<List<CartVO>>(SUCCESS, data);
		
	}

}

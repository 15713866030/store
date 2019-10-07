package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileToLangException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.controller.ex.FileUploadStateException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.impl.UserServiceImpl;
import cn.tedu.store.unit.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {

	@Autowired
	private IUserService userMapper;

	@RequestMapping("reg")
	public JsonResult<Void> reg(User user) {
		userMapper.reg(user);
		return new JsonResult<>(SUCCESS);

	}

	@RequestMapping("login")
	public JsonResult<User> login(String username, String password, HttpSession session) {
		User data = userMapper.login(username, password);
		session.setAttribute("uid", data.getUid());
		session.setAttribute("username", data.getUsername());

		return new JsonResult<User>(SUCCESS, data);

	}

	// @RequestParam：将请求参数绑定到你控制器的方法参数上（是springmvc中接收普通参数的注解）
	// 别名
	@RequestMapping("change_password")
	public JsonResult<Void> changePassword(@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword, HttpSession session) {
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		userMapper.changePassword(uid, username, oldPassword, newPassword);
		return new JsonResult<>(SUCCESS);

	}

	// GetMapping使用限制请求方式为get
	@GetMapping("get_by_uid")
	public JsonResult<User> getByUid(HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		User data = userMapper.getByUid(uid);

		return new JsonResult<User>(SUCCESS, data);

	}

	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(User user, HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		userMapper.changeInfo(uid, username, user);

		return new JsonResult<>(SUCCESS);

	}

	private static final long FILE_MAX_SIZE = 1024 * 500;

	private static final List<String> TYPES = new ArrayList<String>();

	static {
		TYPES.add("image/jpeg");
		TYPES.add("image/png");
	}

	@PostMapping("change_avatar")
	public JsonResult<String> changAvator(HttpSession session, @RequestParam("file") MultipartFile file) {
		// 检查上传的文件是否为空
		if (file.isEmpty()) {
			// 没有选择文件，或选择的文件为0字节
			throw new FileEmptyException("没有选择文件或者文件为0字节");
		}
		// 检查上传的文件大小

		if (file.getSize() > FILE_MAX_SIZE) {
			throw new FileToLangException(",大小超过" + FILE_MAX_SIZE / 1024);
		}
		// 检查上传的文件类型

		if (!TYPES.contains(file.getContentType())) {

			throw new FileTypeException("仅支持" + TYPES.toString());
		}

		// 通过session得到用户uid和username
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);

		// 得到服务器保存文件的路径
		String dirPath = session.getServletContext().getRealPath("upload");
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdir();
		}

		// 得到上传文件的文件名。
		String originalFilename = file.getOriginalFilename();
		// 将文件名后缀截取
		int n = originalFilename.lastIndexOf(".");
		String suffix = "";
		if (n > 0) {
			suffix = originalFilename.substring(n);
		}
		// 文件名等于随机生成的uuid加上传文件本身的后缀
		String filename = UUID.randomUUID().toString() + suffix;
		File dest = new File(dir, filename);
		// 将文件存到本地
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileUploadStateException("上传文件可能被删除或修改");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUploadIOException("出现读写错误");
		}
		String avatar = "/upload/" + filename;
		// 执行数据库更新
		userMapper.changeAvatar(uid, username, avatar);

		return new JsonResult<>(SUCCESS, avatar);

	}

}

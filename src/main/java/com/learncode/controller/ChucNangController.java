package com.learncode.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.comon.Xuly;
import com.learncode.models.ChucNang1;
import com.learncode.service.ChucNang1Service;
import com.learncode.service.NguoiDungService;

@Controller
@RequestMapping("/chucnang")
public class ChucNangController {

	@Autowired
	ChucNang1Service chucNangService;

	@Autowired
	NguoiDungService nguoiDungService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST })
	public String addOrEdit(ModelMap model) {
		ChucNang1 cn = new ChucNang1();
		model.addAttribute("CHUCNANG", cn);
		return "Chucnang-register";
	}

	@RequestMapping(value = "/saveChucNang", method = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('', 'tmcn')")
	public String doSave(@Valid @ModelAttribute("CHUCNANG") ChucNang1 cn, BindingResult bindingResult, Principal principal
			) {
		logger.info("Đây là thêm mới chức năng");
		if (bindingResult.hasErrors()) {
			return "Chucnang-register";
		} else {
			
			cn.setNguoitao(principal.getName());
			cn.setNguoiupdate(principal.getName());
			cn.setIsdelete(0);
			this.chucNangService.insertChucNang1(cn);
			return "redirect:/chucnang/list";
		}
	}

	@GetMapping("/chucnang-update")
	@ResponseBody
	@PreAuthorize("hasPermission('', 'cncn')")
	public Optional<ChucNang1> findByChucNangEditId(ModelMap model, Long id) {
		return this.chucNangService.findByChucNangEditId(id);
	}

	@GetMapping("/chucnang-chitiet")
	@ResponseBody
	@PreAuthorize("hasPermission('', 'xctcn')")
	public Optional<ChucNang1> findByChitietChucnang(ModelMap model, Long id) {
		return this.chucNangService.findByChucNangEditId(id);
	}

	@RequestMapping(value="/updateChucNang", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(ChucNang1 cn, Principal principal) {
		logger.info("Đây là cập nhật chức năng");
		ChucNang1 cn1 = this.chucNangService.findByChucNangEditId(cn.getId()).get();
		cn.setNguoitao(cn1.getNguoitao());
		cn1.updateTimeTamps();
		cn.setNguoiupdate(principal.getName());
		cn.setIsdelete(cn1.getIsdelete());
		this.chucNangService.updateChucNang1(cn);
		return "redirect:/chucnang/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		logger.info("Đây là danh sách chức năng");
		request.getSession().setAttribute("chucnanglist", null);
		return "redirect:/chucnang/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('', 'xdscn')")
	public String showChucNangsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("chucnanglist");
		int pagesize = 5;
		List<ChucNang1> list = (List<ChucNang1>) this.chucNangService.getAllChucNang1();
		int sum = list.size();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("chucnanglist", pages);

		int current = pages.getPage() + 1;
		System.out.println(current);
		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/list/page/";

		model.addAttribute("sum", sum);
		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("CHUCNANGS", pages);

		return "Chucnang-view";
	}

	@RequestMapping("/key")
	public List<String> key(ModelMap model) {
		return this.chucNangService.maapi();
	}

	@RequestMapping(value = "/dataSearch", method = {RequestMethod.GET})
	public String dateSearch(@RequestParam("keyword") String keywork, HttpSession session) {
		logger.info("Đây là tìm kiếm chức năng");
		session.setAttribute("KEYWORK", keywork);

		if (keywork == null || keywork.equals("")) {
			return "redirect:/chucnang/list";
		} else {
			keywork = Xuly.xuLySearch(keywork);
			session.setAttribute("KEYWORK", keywork);
			return "redirect:/chucnang/list/search/1";
		}
	}

	@RequestMapping(value = "/list/search/{pageNumber}", method = {RequestMethod.GET})
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		String tenchucnang = (String) session.getAttribute("KEYWORK");
		List<ChucNang1> list = this.chucNangService.findByTenchucnang(tenchucnang);
		
		if (list == null) {
			return "redirect:/chucnang/list/";
		}
		int sum = list.size();
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("chucnanglist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("chucnanglist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/list/page/";
		
		model.addAttribute("sum", sum);
		
		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("CHUCNANGS", pages);

		return "Chucnang-view";
	}

	@RequestMapping("/del")
	@PreAuthorize("hasPermission('', 'xcn')")
	public String delete(ModelMap model, @RequestParam("lcn") List<Long> id, Principal principal) {
		logger.info("Đây là xóa chức năng");
		for (Long lg : id) {
			ChucNang1 chucNang1 = this.chucNangService.findById(lg).get();
			if (chucNang1.getParentid() < 0) {
				chucNang1.setIsdelete((Integer) 1);
				chucNang1.setNguoiupdate(principal.getName());
				this.chucNangService.updateChucNang1(chucNang1);
			}
		}

		for (Long long1 : id) {
			ChucNang1 chucNang1 = this.chucNangService.findById(long1).get();
			if (chucNang1.getParentid() > 0 || 0 == this.chucNangService.count(chucNang1.getId())) {
				chucNang1.setIsdelete((Integer) 1);
				chucNang1.setNguoiupdate(principal.getName());
				this.chucNangService.updateChucNang1(chucNang1);
			}
		}
		return "redirect:/chucnang/list/";
	}

	@ModelAttribute("PARENTID")
	public List<ChucNang1> getParent() {
		return this.chucNangService.getAllChucNang1Parent();
	}

}

package com.learncode.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

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

import com.learncode.comon.Xuly;
import com.learncode.models.ChucNang1;
import com.learncode.models.LoaiSanPham;

import com.learncode.service.LoaisanphamService;

@Controller
@RequestMapping("/loaisanpham")
public class LoaisanphamController {

	@Autowired
	LoaisanphamService loaisanphamService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/")
	public String home(ModelMap model) {
		LoaiSanPham lsp = new LoaiSanPham();
		model.addAttribute("LOAISANPHAM", lsp);
		return "Loaisanpham-register";
	}

	@RequestMapping(value = "/saveLoaisanpham", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String doSave(ModelMap modal, @Valid @ModelAttribute("LOAISANPHAM") LoaiSanPham lsp,
			BindingResult bindingResult, Principal principal) {
		logger.debug("Đây là thêm mới loại sản phẩm");
		if (bindingResult.hasErrors()) {
			return "Loaisanpham-register";
		} else {
			lsp.setNguoitao(principal.getName());
			lsp.setNguoiupdate(principal.getName());
			lsp.setIsdelete(0);
			this.loaisanphamService.insertLoaisanpham(lsp);
			return "redirect:/loaisanpham/list";
		}
	}

	@GetMapping("/loaisanpham-update")
	@ResponseBody
	public Optional<LoaiSanPham> findByLoaisanphamEditId(ModelMap model, Long id) {
		return this.loaisanphamService.findLoaisanphamById(id);
	}

	@GetMapping("/loaisanpham-chitiet")
	@ResponseBody
	public Optional<LoaiSanPham> findByChitietLoaisanpham(ModelMap model, Long id) {
		return this.loaisanphamService.findLoaisanphamById(id);
	}

	@RequestMapping(value = "/updateLoaisanpham", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(LoaiSanPham lsp, Principal principal) {
		logger.debug("Đây là cập nhật loại sản phẩm");
		LoaiSanPham lsp1 = this.loaisanphamService.findLoaisanphamById(lsp.getId()).get();
		lsp.setNguoitao(lsp1.getNguoitao());
		lsp.setNguoiupdate(principal.getName());
		lsp.setIsdelete(0);
		this.loaisanphamService.updateLoaisanpham(lsp);
		return "redirect:/loaisanpham/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String list(ModelMap model, HttpServletRequest request) {
		logger.debug("Đây là danh sách loại sản phẩm");
		request.getSession().setAttribute("loaisanphamlist", null);
		return "redirect:/loaisanpham/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showLoaisanphamsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("loaisanphamlist");
		int pagesize = 5;
		List<LoaiSanPham> list = (List<LoaiSanPham>) this.loaisanphamService.findAllLoaisanpham();
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
		request.getSession().setAttribute("loaisanphamlist", pages);

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

		model.addAttribute("LIST", pages);

		return "Loaisanpham-view";
	}

	@RequestMapping(value = "/dataSearch", method = {RequestMethod.GET})
	public String dateSearch(@RequestParam("tenloaisanpham") String tenloaisanpham, HttpSession session) {
		logger.debug("Đây là tìm kiếm loại sản phẩm");
		session.setAttribute("TENLOAISANPHAM", tenloaisanpham);

		if (tenloaisanpham == null || tenloaisanpham.equals("")) {
			return "redirect:/loaisanpham/list";
		} else {
			tenloaisanpham = Xuly.xuLySearch(tenloaisanpham);
			session.setAttribute("TENLOAISANPHAM", tenloaisanpham);
			return "redirect:/loaisanpham/list/search/1";
		}
	}

	@RequestMapping(value = "/list/search/{pageNumber}", method = {RequestMethod.GET})
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		String tenloaisanpham = (String) session.getAttribute("TENLOAISANPHAM");
		List<LoaiSanPham> list = this.loaisanphamService.searchTenLoaiSanPham(tenloaisanpham);
		
		if (list == null) {
			return "redirect:/loaisanpham/list/";
		}
		int sum = list.size();
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("loaisanphamlist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("loaisanphamlist", pages);

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

		model.addAttribute("LIST", pages);

		return "Loaisanpham-view";
	}
	
	@RequestMapping("/del")
	public String delete(@RequestParam("lsp[]") List<Long> ids, Principal principal) {
		logger.debug("Đây là xóa loại sản phẩm");
		for (Long long1 : ids) {
			LoaiSanPham lsp = this.loaisanphamService.findLoaisanphamById(long1).get();
			lsp.setNguoiupdate(principal.getName());
			lsp.setIsdelete(1);
			this.loaisanphamService.deleteLoaisanpham(lsp);
		}
		return "redirect:/loaisanpham/list";
	}
}

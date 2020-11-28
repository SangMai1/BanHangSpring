package com.learncode.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.comon.Xuly;
import com.learncode.models.ChucNang1;
import com.learncode.models.SanphamVaChitiet;
import com.learncode.models.VaiTro;
import com.learncode.service.SanphamVaChitietService;

@Controller
@RequestMapping("/sanphamchitiet")
public class SanphamVaChitietController {

	@Autowired
	SanphamVaChitietService sanphamVaChitietService;

	@GetMapping(value = "/sanphamchitiet-update", produces = "application/json")
	@ResponseBody
	public Map<String, String> getSanphamVaChitiet(Long id) {
		SanphamVaChitiet spvct = this.sanphamVaChitietService.findBySanphamVaChitietId(id).get();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(spvct.getId()));
		map.put("kichthuoc", spvct.getKichthuoc());
		map.put("soluong", String.valueOf(spvct.getSoluong()));
		map.put("giatien", String.valueOf(spvct.getGiatien()));
		map.put("giamgia", String.valueOf(spvct.getGiamgia()));
		return map;
	}

	@RequestMapping(value = "/doUpdate", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String doUpdate(SanphamVaChitiet spct) {
		spct.setIsdelete((Integer) 0);
		this.sanphamVaChitietService.updateSanphamVaChitiet(spct);
		return "redirect:/sanphamchitiet/list";
	}

	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphamchitietlist", null);
		return "redirect:/sanphamchitiet/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.PUT })
	public String showSanphamchitietsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamchitietlist");
		int pagesize = 5;
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamVaChitietService.getAll();
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
		request.getSession().setAttribute("sanphamchitietlist", pages);

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

		model.addAttribute("SANPHAMCHITIETS", pages);

		return "SanphamVaChitiet-view";
	}

	@RequestMapping(value = "/listGanHet", method = {RequestMethod.GET})
	public String listGanHet(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphamchitietganhetlist", null);
		return "redirect:/sanphamchitiet/listGanHet/page/1";
	}

	@RequestMapping(value = "/listGanHet/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.PUT })
	public String showSanphamchitietsGanHetPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamchitietganhetlist");
		int pagesize = 5;
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamVaChitietService.listSizeSanPhamGanHet();
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
		request.getSession().setAttribute("sanphamchitietganhetlist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/listGanHet/page/";
		
		model.addAttribute("sum", sum);
		
		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("SANPHAMCHITIETSGANHET", pages);

		return "SizeSanPhamGanHet";
	}
	@RequestMapping(value = "/dataSearch", method = {RequestMethod.GET})
	public String dataSearch(@RequestParam("searchsize") String keysize, 
			HttpSession session) {
		session.setAttribute("KESIZE", keysize);
		
		if (keysize == null || keysize.equals("")) {
			return "redirect:/sanphamchitiet/list";
		} else {
			
			session.setAttribute("KEYSIZE", keysize);
			return "redirect:/sanphamchitiet/list/search/1";
		}
	}

	@RequestMapping(value = "/list/search/{pageNumber}", method = {RequestMethod.GET})
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		String kichthuoc = (String) session.getAttribute("KEYSIZE");
		List<SanphamVaChitiet> list = this.sanphamVaChitietService.searchKichThuoc(kichthuoc);
		
		if (list == null) {
			return "redirect:/sanphamchitiet/list/";
		}
		int sumSeach = list.size();
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamchitietlist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("sanphamchitietlist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/list/page/";
		
		model.addAttribute("sum", sumSeach);
		
		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("SANPHAMCHITIETS", pages);

		return "SanphamVaChitiet-view";
	}
	
	@RequestMapping("/del")
	public String delete(@RequestParam("lspct[]") List<Long> ids) {
		for (Long long1 : ids) {
			SanphamVaChitiet spct = this.sanphamVaChitietService.findBySanphamVaChitietId(long1).get();
			spct.setIsdelete((Integer) 1);
			this.sanphamVaChitietService.updateSanphamVaChitiet(spct);
		}
		return "redirect:/sanphamchitiet/list";
	}
}

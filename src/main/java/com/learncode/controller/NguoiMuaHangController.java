package com.learncode.controller;

import java.util.List;
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
import com.learncode.models.Bills;
import com.learncode.models.ChucNang1;
import com.learncode.service.BillService;

@Controller
@RequestMapping("/nguoimuahang")
public class NguoiMuaHangController {

	@Autowired
	BillService billService;

	@GetMapping("/nguoimuahang-chitiet")
	@ResponseBody
	public Optional<Bills> findByChitietNguoiMuaHang(ModelMap model, Long id) {
		return this.billService.findById(id);
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("nguoimuahanglist", null);
		return "redirect:/nguoimuahang/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showNguoiMuaHangsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nguoimuahanglist");
		int pagesize = 5;
		List<Bills> list = (List<Bills>) this.billService.getAllNguoiMuaDaDangKi();
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
		request.getSession().setAttribute("nguoimuahanglist", pages);

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

		model.addAttribute("NGUOIMUAHANGS", pages);

		return "NguoiMua-view";
	}
	
	@RequestMapping(value = "/dataSearch", method = {RequestMethod.GET})
	public String dateSearch(@RequestParam("billname") String billname, HttpSession session) {
		session.setAttribute("BILLNAME", billname);

		if (billname == null || billname.equals("")) {
			return "redirect:/nguoimuahang/list";
		} else {
			billname = Xuly.xuLySearch(billname);
			session.setAttribute("BILLNAME", billname);
			return "redirect:/nguoimuahang/list/search/1";
		}
	}

	@RequestMapping(value = "/list/search/{pageNumber}", method = {RequestMethod.GET})
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		String billname = (String) session.getAttribute("BILLNAME");
		List<Bills> list = this.billService.searchBillName(billname);
		
		if (list == null) {
			return "redirect:/nguoimuahang/list/";
		}
		int sum = list.size();
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nguoimuahanglist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("nguoimuahanglist", pages);

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

		model.addAttribute("NGUOIMUAHANGS", pages);

		return "NguoiMua-view";
	}


	@RequestMapping(value = "/del", method = {RequestMethod.GET, RequestMethod.PUT})
	public String delete(ModelMap model, @RequestParam("lnm") List<Long> id) {
		for (Long lg : id) {
			Bills nguoimuahang = this.billService.findById(lg).get();
			nguoimuahang.setBill_status((Integer) 1);
			this.billService.updateBill(nguoimuahang);
		}
		return "redirect:/nguoimuahang/list/";
	}
}

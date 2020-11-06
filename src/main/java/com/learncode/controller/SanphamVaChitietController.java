package com.learncode.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import com.learncode.models.SanphamVaChitiet;
import com.learncode.service.SanphamVaChitietService;

@Controller
@RequestMapping("/sanphamchitiet")
public class SanphamVaChitietController {

	@Autowired
	SanphamVaChitietService sanphamVaChitietService;

	@GetMapping("/sanphamchitiet-update")
	@ResponseBody
	public Optional<SanphamVaChitiet> getSanphamVaChitiet(Long id) {
		return this.sanphamVaChitietService.findBySanphamVaChitietId(id);
	}

	@RequestMapping(value = "/doUpdate", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String doUpdate(SanphamVaChitiet spct) {
		spct.setIsdelete((Integer) 0);
		this.sanphamVaChitietService.updateSanphamVaChitiet(spct);
		return "redirect:/sanphamchitiet/list";
	}

	@RequestMapping("/list")
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
		System.out.println(list.size());
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

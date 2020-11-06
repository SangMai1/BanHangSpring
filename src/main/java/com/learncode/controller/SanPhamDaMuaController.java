package com.learncode.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.models.BillDetail;
import com.learncode.service.BillDetailService;

@Controller
@RequestMapping("/sanphamdamua")
public class SanPhamDaMuaController {

	@Autowired
	BillDetailService billDetailService;
	
	@GetMapping(value="/sanphamdamua-chitiet", produces = "application/json")
	@ResponseBody
	public Map<String, String> findByChitietSanPhamDaMua(ModelMap model, long id) {
		BillDetail billDetail =  this.billDetailService.findById(id).get();
		Map<String, String> map = new HashMap<String, String>();
		map.put("billdetail_id", String.valueOf(billDetail.getBilldetail_id()));
		map.put("billdetail_quantity", String.valueOf(billDetail.getBilldetail_quantity()));
		map.put("billdetail_price", String.valueOf(billDetail.getBilldetail_price()));
		map.put("billdetail_status", String.valueOf(billDetail.getBilldetail_status()));
		return map;
	}
	
	@RequestMapping(value="/updateSanphamdamua", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(BillDetail billDetail) {
		this.billDetailService.updateBillDetail(billDetail);
		return "redirect:/sanphamdamua/list";
	}
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphamdamualist", null);
		return "redirect:/sanphamdamua/list/page/1";
	}
	
	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showSanPhamDaMuasPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamdamualist");
		int pagesize = 5;
		List<BillDetail> list = (List<BillDetail>) this.billDetailService.getSanPhamDatHang();
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
		request.getSession().setAttribute("sanphamdamualist", pages);

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

		model.addAttribute("SANPHAMDAMUAS", pages);

		return "SanPhamDaMua";
	}
}

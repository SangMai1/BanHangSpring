package com.learncode.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.ls.LSInput;

import com.learncode.models.BillDetail;
import com.learncode.models.Kho;
import com.learncode.models.SanphamVaChitiet;
import com.learncode.models.SanphamdamuaPDFExporter;
import com.learncode.models.VaiTro;
import com.learncode.service.BillDetailService;
import com.learncode.service.KhoService;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/sanphamdamua")
public class SanPhamDaMuaController {

	@Autowired
	BillDetailService billDetailService;
	
	@Autowired
	KhoService khoService;
	
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
	
	@RequestMapping(value = "/dataSearchTrangThai", method = {RequestMethod.GET})
	public String dataSearchTrangThai(@RequestParam("searchtrangthaidamua") Integer searchtrangthaidamua, 
			HttpSession session) {
		session.setAttribute("SEARCHTRANGTHAIDAMUA", searchtrangthaidamua);
		
		if (searchtrangthaidamua == null || searchtrangthaidamua.equals("")) {
			return "redirect:/sanphamdamua/list";
		} else {
			
			session.setAttribute("SEARCHTRANGTHAIDAMUA", searchtrangthaidamua);
			return "redirect:/sanphamdamua/list/search/1";
		}
	}

	@RequestMapping(value = "/list/search/{pageNumber}", method = {RequestMethod.GET})
	public String searchTrangThai(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		Integer searchtrangthai = (Integer) session.getAttribute("SEARCHTRANGTHAIDAMUA");
		List<BillDetail> list = this.billDetailService.listSearchTrangThai(searchtrangthai);
		
		if (list == null) {
			return "redirect:/sanphamdamua/list/";
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
		request.getSession().setAttribute("sanphamdamualist", pages);

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

		model.addAttribute("SANPHAMDAMUAS", pages);

		return "SanPhamDaMua";
	}
	
	@GetMapping(value="/kho-sanphamdamua", produces = "application/json")
	@ResponseBody
	public Map<String, String> findByChitietKhoSanPhamDaMua(ModelMap model, long id) {
		Kho k = this.khoService.findById(id).get();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(k.getId()));
		map.put("trangthai", String.valueOf(k.getTrangthai()));
		return map;
	}
	
	@RequestMapping(value="/updateKhoSanphamdamua", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doKhoUpdate(Kho k) {
		this.khoService.update(k);
		return "redirect:/sanphamdamua/listKho";
	}
	
	@RequestMapping(value = "/listKho", method = { RequestMethod.GET, RequestMethod.POST })
	public String listKho(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("khosanphamdamualist", null);
		return "redirect:/sanphamdamua/listKho/page/1";
	}
	
	@RequestMapping(value = "/listKho/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showKhoSanPhamDaMuasPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("khosanphamdamualist");
		int pagesize = 5;
		List<Kho> list = (List<Kho>) this.khoService.findAll();
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
		request.getSession().setAttribute("khosanphamdamualist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/listKho/page/";

		model.addAttribute("sum", sum);
		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("KHOSANPHAMDAMUAS", pages);

		return "Kho-sanphamdamua";
	}
	
	@RequestMapping(value = "/dataSearch", method = {RequestMethod.GET})
	public String dataSearch(@RequestParam("searchtrangthai") Integer searchtrangthai, 
			HttpSession session) {
		session.setAttribute("SEARCHTRANGTHAI", searchtrangthai);
		
		if (searchtrangthai == null || searchtrangthai.equals("")) {
			return "redirect:/sanphamdamua/listKho";
		} else {
			
			session.setAttribute("SEARCHTRANGTHAI", searchtrangthai);
			return "redirect:/sanphamdamua/listKho/search/1";
		}
	}

	@RequestMapping(value = "/listKho/search/{pageNumber}", method = {RequestMethod.GET})
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		Integer searchtrangthai = (Integer) session.getAttribute("SEARCHTRANGTHAI");
		List<Kho> list = this.khoService.listSearchSize(searchtrangthai);
		
		if (list == null) {
			return "redirect:/sanphamdamua/listKho/";
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
		request.getSession().setAttribute("khosanphamdamualist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/listKho/page/";
		
		model.addAttribute("sum", sumSeach);
		
		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("KHOSANPHAMDAMUAS", pages);

		return "Kho-sanphamdamua";
	}
	
	@RequestMapping(value = "/listMuaThanhCong", method = { RequestMethod.GET, RequestMethod.POST })
	public String listMuaThanhCong(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphamdamuathanhconglist", null);
		return "redirect:/sanphamdamua/listMuaThanhCong/page/1";
	}
	
	@RequestMapping(value = "/listMuaThanhCong/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showSanPhamDaMuaThanhCongsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamdamuathanhconglist");
		int pagesize = 5;
		List<BillDetail> list = (List<BillDetail>) this.billDetailService.getSanPhamDaMuaThanhCong();
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
		request.getSession().setAttribute("sanphamdamuathanhconglist", pages);

		int current = pages.getPage() + 1;

		int begin = Math.max(1, current - list.size());

		int end = Math.min(begin + 5, pages.getPageCount());

		int totalPageCount = pages.getPageCount();

		String baseUrl = "/listMuaThanhCong/page/";

		model.addAttribute("sum", sum);
		model.addAttribute("beginIndex", begin);

		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("SANPHAMDAMUATHANHCONGS", pages);

		return "Sanphamdamuathanhcong";
	}
	
	@GetMapping("/export/{id}")
	public void exportToPDF(HttpServletResponse response, @PathVariable long id) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=sanphamdamua_" + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		Optional<Kho> k = this.khoService.findById(id);
		SanphamdamuaPDFExporter exporter = new SanphamdamuaPDFExporter(k);
		exporter.export(response);
	}
	
	@RequestMapping(value = "/del", method = {RequestMethod.GET})
	public String delete(ModelMap model, @RequestParam("lvt") List<Long> ids, @RequestParam("billdetail_status123") Integer billdetail_status123) {
		for (Long long1 : ids) {
			BillDetail bi = this.billDetailService.findById(long1).get();
			bi.setBilldetail_status(billdetail_status123);
			this.billDetailService.updateBillDetail(bi);
		}
		return "redirect:/sanphamdamua/list";
	}
	
	@RequestMapping(value = "/update_kho", method = {RequestMethod.GET})
	public String updateKho(ModelMap model, @RequestParam("kl") List<Long> ids, @RequestParam("kho_list") Integer kho_list) {
		for (Long long1 : ids) {
			Kho k = this.khoService.findById(long1).get();
			k.setTrangthai(kho_list);
			this.khoService.update(k);
			if (k.getTrangthai() == 2) {
				BillDetail bi = this.billDetailService.findById(k.getBilldetails_id().getBilldetail_id()).get();
				bi.setBilldetail_status((Integer) 1);
				this.billDetailService.updateBillDetail(bi);
				
			}
			if (k.getTrangthai() == 0 || k.getTrangthai() == 1) {
				BillDetail bi = this.billDetailService.findById(k.getBilldetails_id().getBilldetail_id()).get();
				bi.setBilldetail_status((Integer) 0);
				this.billDetailService.updateBillDetail(bi);
				
			}
		}
		
		return "redirect:/sanphamdamua/listKho";
	}
}

package com.learncode.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

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
	
	@RequestMapping(value = "/", method = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST})
	public String addOrEdit(ModelMap model) {
		ChucNang1 cn = new ChucNang1();
		model.addAttribute("CHUCNANG", cn);
//		model.addAttribute("ACTION", "saveChucNang");
		return "Chucnang-register";
		}
	
	@RequestMapping(value="/saveChucNang", method = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST})
	public String doSave(ModelMap model, @ModelAttribute("CHUCNANG") ChucNang1 cn, HttpSession session) {
		cn.setId(ThreadLocalRandom.current().nextLong(0, new Long("90000000000000000")));
		cn.setCreateday(new Timestamp(new Date().getTime()));
		cn.setUpdateday(new Timestamp(new Date().getTime()));
		cn.setNguoitao((String) session.getAttribute("USERNAME"));
		cn.setNguoiupdate((String) session.getAttribute("USERNAME"));
		System.out.println("aaaa");
		this.chucNangService.insertChucNang1(cn);
		return "redirect:/chucnang/list";
	}
	
	
//	@RequestMapping("/chucnang-update/{machucnang}")
//	public String updateChucNang(ModelMap model, @PathVariable(name = "machucnang") String machucnang) {
//		Optional<ChucNang1> optional = this.chucNangService.findByChucNangMachucnang(machucnang);
//		if (optional.isPresent()) {
//			model.addAttribute("CHUCNANG", optional.get());
//		}
//		model.addAttribute("ACTION", "/chucnang/updateChucNang");
//		return "Chucnang-register";
//	}
	
	@GetMapping("/chucnang-update")
	@ResponseBody
	public Optional<ChucNang1> findByChucNangEditId(ModelMap model, Long id) {
		return this.chucNangService.findByChucNangEditId(id);
	}
	
	@GetMapping("/chucnang-chitiet")
	@ResponseBody
	public Optional<ChucNang1> findByChitietChucnang(ModelMap model, Long id) {
		return this.chucNangService.findByChucNangEditId(id);
	}
	@RequestMapping("/updateChucNang")
	public String doUpdate(ChucNang1 cn, HttpSession session) {
		cn.setUpdateday(new Timestamp(new Date().getTime()));
		cn.setNguoiupdate((String) session.getAttribute("USERNAME"));
		this.chucNangService.updateChucNang1(cn);
		return "redirect:/chucnang/list";
	}
	
	@RequestMapping(value="/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("chucnanglist", null);
		return "redirect:/chucnang/list/page/1";
	}
	
	@RequestMapping(value="/list/page/{pageNumber}", method = {RequestMethod.GET,RequestMethod.POST})
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
	
	@RequestMapping("/dataSearch")
	public String dateSearch(@RequestParam("keyword") String tenchucnang, @RequestParam("key") String machucnang, HttpSession session) {
		session.setAttribute("KEYWORK", tenchucnang);
		session.setAttribute("KEY", machucnang);
		if(tenchucnang == null || tenchucnang.equals("")) {
			if(machucnang == null || machucnang.equals("")) {
				return "redirect:/chucnang/list";
			} else {
				machucnang = Xuly.xuLySearch(machucnang);
				session.setAttribute("KEY", machucnang);
				session.setAttribute("SEARCH", 2);
				return "redirect:/chucnang/list/search/1";
			}
		} else {
			tenchucnang = Xuly.xuLySearch(tenchucnang);
			session.setAttribute("KEYWORK", tenchucnang);
			session.setAttribute("SEARCH", 1);
			return "redirect:/chucnang/list/search/1";
		}
	}
	@RequestMapping("/list/search/{pageNumber}")
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber, HttpSession session) {
		String tenchucnang = (String) session.getAttribute("KEYWORK");
		String machucnang = (String) session.getAttribute("KEY");
		int temp = (Integer) session.getAttribute("SEARCH");
		List<ChucNang1> list = null;
		switch (temp) {
		case 1:
			list = this.chucNangService.findByTenchucnang(tenchucnang);
			break;
		case 2:
			list = this.chucNangService.findByMachucnang(machucnang);
			break;
		default:
			break;
		}
		if (list == null) {
			return "redirect:/chucnang/list/";
		}
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
		
		model.addAttribute("beginIndex", begin);
	
		model.addAttribute("endIndex", end);

		model.addAttribute("currentIndex", current);

		model.addAttribute("totalPageCount", totalPageCount);
	
		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("CHUCNANGS", pages);
		
		return "Chucnang-view";
	}
	@RequestMapping("/del")
	public String delete(ModelMap model, @RequestParam("lcn") List<Long> id, HttpSession session) {
		for (Long lg : id) {
			ChucNang1 chucNang1 = this.chucNangService.findById(lg).get();
			if (chucNang1.getParentid() < 0) {
				chucNang1.setIsdelete((Integer) 1);
				chucNang1.setNguoiupdate((String) session.getAttribute("USERNAME"));
				this.chucNangService.updateChucNang1(chucNang1);
			}
		}
		
		for (Long long1 : id) {
			ChucNang1 chucNang1 = this.chucNangService.findById(long1).get();
			if (chucNang1.getParentid() > 0 || 0 == this.chucNangService.count(chucNang1.getId())) {
				chucNang1.setIsdelete((Integer) 1);
				chucNang1.setNguoiupdate((String) session.getAttribute("USERNAME"));
				this.chucNangService.updateChucNang1(chucNang1);
			}
		}
		return "redirect:/chucnang/list/";
	}
	
	@ModelAttribute("PARENTID")
	public List<ChucNang1> getParent(){
		return this.chucNangService.getAllChucNang1Parent();
	}
	
}

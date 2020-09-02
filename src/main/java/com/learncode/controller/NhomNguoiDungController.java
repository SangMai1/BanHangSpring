package com.learncode.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
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
import com.learncode.models.NhomNguoiDung;
import com.learncode.models.VaiTro;
import com.learncode.service.NhomNguoiDungService;

@Controller
@RequestMapping("/nhom")
public class NhomNguoiDungController {
	
	@Autowired
	NhomNguoiDungService nhomNguoiDungService;
	
	@RequestMapping("/")
	public String addOrEdit(ModelMap model) {
		NhomNguoiDung nnd = new NhomNguoiDung();
		model.addAttribute("NHOM", nnd);
		return "NhomNguoiDung-register";
	}
	
	@RequestMapping(value="/saveNhomNguoiDung", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doSave(@ModelAttribute("NHOM") NhomNguoiDung ndd, HttpSession session) {
		ndd.setId(ThreadLocalRandom.current().nextLong(0, new Long("900000000000000000")));
		ndd.setCreateday(new Timestamp(new Date().getTime()));
		ndd.setUpdateday(new Timestamp(new Date().getTime()));
		ndd.setNguoitao((String) session.getAttribute("USERNAME"));
		ndd.setNguoiupdate((String) session.getAttribute("USERNAME"));
		this.nhomNguoiDungService.insertNhomNguoiDung(ndd);
		return "redirect:/nhom/list";
	}
	
	@ResponseBody
	@GetMapping(value="/nhom-update", produces = "application/json; charset=UTF-8")
	public Optional<NhomNguoiDung> update(Long id) {
		 return nhomNguoiDungService.findByLongId(id);
		 
	}
	
	@ResponseBody
	@GetMapping(value="/nhom-chitiet", produces = "application/json; charset=UTF-8")
	public Optional<NhomNguoiDung> chiTiet(Long id) {
		 return nhomNguoiDungService.findByLongId(id);
		 
	}
	
	@RequestMapping(value = "/doUpdate", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(NhomNguoiDung nnd, HttpSession session) {
		nnd.setNguoiupdate((String) session.getAttribute("USERNAME"));
		nnd.setUpdateday(new Timestamp(new Date().getTime()));
		this.nhomNguoiDungService.updateNhomNguoiDung(nnd);
		return "redirect:/nhom/list";
	}
	
	@GetMapping("/list")
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("nhomlist", null);
		return "redirect:/nhom/list/page/1";
	}
	
	@GetMapping("/list/page/{pageNumber}")
	public String showNhomsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nhomlist");
		int pagesize = 5;
		List<NhomNguoiDung> list = (List<NhomNguoiDung>) this.nhomNguoiDungService.findAllNhomNguoiDung();
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
		request.getSession().setAttribute("nhomlist", pages);
		
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

		model.addAttribute("NHOMS", pages);

		return "NhomNguoiDung-view";
	}
	
	@RequestMapping("/dataSearch")
	public String dataSearch(@RequestParam("namn") String tennhom, @RequestParam("keyn") String manhom, HttpSession session) {
		session.setAttribute("NAMN", tennhom);
		session.setAttribute("KEYN", manhom);
		if (tennhom == null || tennhom.equals("")) {
			if (manhom == null || manhom.equals("")) {
				return "redirect:/nhom/list";
			} else {
				manhom = Xuly.xuLySearch(manhom);
				session.setAttribute("KEYN", manhom);
				session.setAttribute("SEARCH", 1);
				return "redirect:/nhom/list/search/1";
			}
		} else {
			tennhom = Xuly.xuLySearch(tennhom);
			session.setAttribute("NAMN", tennhom);
			session.setAttribute("SEARCH", 2);
			return "redirect:/nhom/list/search/1";
		}
	}
	@RequestMapping("/list/search/{pageNumber}")
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber, HttpSession session) {
		String tennhom = (String) session.getAttribute("NAMN");
		String manhom = (String) session.getAttribute("KEYN");
		Integer temp = (Integer) session.getAttribute("SEARCH");
		List<NhomNguoiDung> list = null;
		switch (temp) {
		case 1:
			list = this.nhomNguoiDungService.findByManhom(manhom);
			break;
		case 2:
			list = this.nhomNguoiDungService.findByTennhom(tennhom);
			break;
		default:
			break;
		}
		if (list == null) {
			return "redirect:/nhom/list";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nhomlist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("nhomlist", pages);
		
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

		model.addAttribute("NHOMS", pages);
		
		return "NhomNguoiDung-view";
	}
	@ModelAttribute(name = "CHUCNANGS")
	public List<ChucNang1> getAllChucNang1(){
		return nhomNguoiDungService.findAllChucNang1();
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("ndd") List<Long> ids, HttpSession session) {
		for (Long long1 : ids) {
			NhomNguoiDung nhomNguoiDung = this.nhomNguoiDungService.findByLongId(long1).get();
			nhomNguoiDung.setIsdelete((Integer) 1);
			nhomNguoiDung.setNguoiupdate((String) session.getAttribute("USERNAME"));
			this.nhomNguoiDungService.deleteNhomNguoiDung(nhomNguoiDung);
		}
		return "redirect:/nhom/list/";
	}
}

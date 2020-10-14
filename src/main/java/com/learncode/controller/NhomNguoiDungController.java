package com.learncode.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasPermission('', 'tmn')")
	public String doSave(@ModelAttribute("NHOM") NhomNguoiDung ndd, Principal principal) {
		ndd.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000")));
		ndd.setCreateday(new Timestamp(new Date().getTime()));
		ndd.setUpdateday(new Timestamp(new Date().getTime()));
		ndd.setNguoitao(principal.getName());
		ndd.setNguoiupdate(principal.getName());
		this.nhomNguoiDungService.insertNhomNguoiDung(ndd);
		return "redirect:/nhom/list";
	}
	
	@GetMapping(value="/nhom-update", produces = "application/json")
	@ResponseBody
	@PreAuthorize("hasPermission('', 'cnn')")
	public Map<String, String> update(Long id) {
		 NhomNguoiDung nnd = this.nhomNguoiDungService.findByLongId(id).get();
		 List<Long> ncn = this.nhomNguoiDungService.findChucnangNhom(nnd.getId());
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("id", String.valueOf(nnd.getId()));
		 map.put("manhom", nnd.getManhom());
		 map.put("tennhom", nnd.getTennhom());
		 map.put("chucnangs", String.valueOf(ncn));
		 return map;
	}
	
	@ResponseBody
	@GetMapping(value="/nhom-chitiet", produces = "application/json; charset=UTF-8")
	@PreAuthorize("hasPermission('', 'xctn')")
	public Optional<NhomNguoiDung> chiTiet(Long id) {
		 return nhomNguoiDungService.findByLongId(id);
		 
	}
	
	@RequestMapping(value = "/doUpdate", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(ModelMap model, NhomNguoiDung nnd, Principal principal) {
		nnd.setNguoiupdate(principal.getName());
		nnd.setUpdateday(new Timestamp(new Date().getTime()));
		this.nhomNguoiDungService.updateNhomNguoiDung(nnd);
		model.addAttribute("NHOMS", this.nhomNguoiDungService.findAllNhomNguoiDung());
		return "redirect:/nhom/list";
	}
	
	@GetMapping("/list")
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("nhomlist", null);
		return "redirect:/nhom/list/page/1";
	}
	
	@GetMapping("/list/page/{pageNumber}")
	@PreAuthorize("hasPermission('', 'xdsn')")
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
	public String dataSearch(@RequestParam("namn") String tennhom, HttpSession session) {
		session.setAttribute("NAMN", tennhom);
		if (tennhom == null || tennhom.equals("")) {
			return "redirect:/nhom/list";
		} else {
			tennhom = Xuly.xuLySearch(tennhom);
			session.setAttribute("NAMN", tennhom);
			return "redirect:/nhom/list/search/1";
		}
	}
	@RequestMapping("/list/search/{pageNumber}")
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber, HttpSession session) {
		String tennhom = (String) session.getAttribute("NAMN");
		List<NhomNguoiDung> list = this.nhomNguoiDungService.findByTennhom(tennhom);
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
	@PreAuthorize("hasPermission('', 'xn')")
	public String delete(@RequestParam("ndd") List<Long> ids, Principal principal) {
		for (Long long1 : ids) {
			NhomNguoiDung nhomNguoiDung = this.nhomNguoiDungService.findByLongId(long1).get();
			nhomNguoiDung.setIsdelete((Integer) 1);
			nhomNguoiDung.setNguoiupdate(principal.getName());
			this.nhomNguoiDungService.deleteNhomNguoiDung(nhomNguoiDung);
		}
		return "redirect:/nhom/list/";
	}
}

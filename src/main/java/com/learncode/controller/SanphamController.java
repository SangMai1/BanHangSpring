package com.learncode.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.learncode.models.LoaiSanPham;
import com.learncode.models.Sanpham;
import com.learncode.models.SanphamVaChitiet;
import com.learncode.service.LoaisanphamService;
import com.learncode.service.SanphamService;
import com.learncode.service.SanphamVaChitietService;


@Controller
@RequestMapping("/sanpham")
public class SanphamController {
	
	@Autowired
	SanphamService sanphamService;
	
	@Autowired
	LoaisanphamService loaisanphamService;
	
	@Autowired
	SanphamVaChitietService sanphamVaChitietService;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String home(ModelMap model) {
		Sanpham sp = new Sanpham();
		model.addAttribute("SANPHAM", sp);
		return "Sanpham-register";
	}
	
	@RequestMapping(value = "/doSave", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doSave(@ModelAttribute("SANPHAM") Sanpham sp, HttpSession session, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		sp.setCreateday(new Timestamp(new Date().getTime()));
		sp.setCreateby((String) session.getAttribute("USERNAME"));
		sp.setUpdateday(new Timestamp(new Date().getTime()));
		sp.setUpdateby((String) session.getAttribute("USERNAME"));
		sp.setIsdelete((Integer) 0);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		sp.setImage(fileName);
		this.sanphamService.insertSanpham(sp);
		
		String uploadDir = "./uploads/" + sp.getId();
		
		Path uploadPath = Paths.get(uploadDir);
		System.out.println(uploadPath);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new IOException("Could not save uploaded file: " + fileName);
		}
		return "redirect:/sanpham/list";
	}
	
	@GetMapping("/sanpham-update")
	@ResponseBody
	public Optional<Sanpham> findBySanphamId(Long id){
		return this.sanphamService.finBySanphamId(id);
	}
	
	@GetMapping("/sanpham-chitiet")
	@ResponseBody
	public Optional<Sanpham> findBySanphamChitiet(Long id){
		return this.sanphamService.finBySanphamId(id);
	}
	
	@GetMapping("/add-chitiet")
	@ResponseBody
	public Optional<Sanpham> getSanphamchitiet(Long id) {
		return this.sanphamService.finBySanphamId(id);
	}
	
	@RequestMapping(value = "/updateSanpham", method = {RequestMethod.POST})
	public String doUpdate(Sanpham sp, HttpSession session, @RequestParam("fileImages") MultipartFile multipartFile) {
		sp.setUpdateday(new Timestamp(new Date().getTime()));
		sp.setUpdateby((String) session.getAttribute("USERNAME"));
		sp.setIsdelete((Integer) 0);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		sp.setImage(fileName);
//		Path deleteFile = Paths.get("C://Users//SANG//Documents//workspace-spring-tool-suite-4-4.5.1.RELEASE//WebsiteBanHangThoiTrang//uploads//" + sp.getId());
//		Files.delete(deleteFile);
		File file = new File("C://Users//SANG//Documents//workspace-spring-tool-suite-4-4.5.1.RELEASE//WebsiteBanHangThoiTrang//uploads//" + sp.getId());
		if (file.delete()) {
			System.out.println("delete thanh cong");
		}
		this.sanphamService.updateSanpham(sp);
		
		String uploadDir = "./uploads/" + sp.getId();
		
		Path uploadPath = Paths.get(uploadDir);
		System.out.println(uploadPath);

		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {	
		}
		return "redirect:/sanpham/list";
	}
	
	@ModelAttribute("LOAISANPHAM")
	public List<LoaiSanPham> getLoaisanpham(){
		return this.loaisanphamService.findAllLoaisanpham();
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphamlist", null);
		return "redirect:/sanpham/list/page/1";
	}
	
	@RequestMapping(value="/list/page/{pageNumber}", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
	public String showSanPhamsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamlist");
		int pagesize = 5;
		List<Sanpham> list = (List<Sanpham>) this.sanphamService.getAllSanpham();
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
		request.getSession().setAttribute("sanphamlist", pages);
		
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

		model.addAttribute("SANPHAMS", pages);

		return "Sanpham-view";
	}
	
//	@GetMapping(value = "/del")
//	public String delete(@RequestParam("lsp[]") List<Long> ids, HttpSession session) {
//		for (Long long1 : ids) {
//			Sanpham sp = this.sanphamService.finBySanphamId(long1).get();
//			sp.setUpdateday(new Timestamp(new Date().getTime()));
//			sp.setUpdateby((String) session.getAttribute("USERNAME"));
//			sp.setIsdelete((Integer) 1);
//			this.sanphamService.updateSanpham(sp);
//		}
//		return "redirect:/sanpham/list";
//	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String add(@RequestParam("idsanpham") Long idsanpham, @RequestParam("kichthuoc") String kichthuoc, @RequestParam("soluong") Integer soluong, @RequestParam("giatien") Float giatien) {
		SanphamVaChitiet spct = new SanphamVaChitiet(0, idsanpham, kichthuoc, soluong, giatien, 0);
		spct.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000000")));
		this.sanphamVaChitietService.insertSanphamVaChitiet(spct);
		return "redirect:/sanpham/list";
	}
}

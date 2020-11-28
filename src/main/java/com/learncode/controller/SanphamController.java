package com.learncode.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.comon.Xuly;
import com.learncode.comon.ZXingHelper;
import com.learncode.models.ChucNang1;
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

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String home(ModelMap model) {
		Sanpham sp = new Sanpham();
		model.addAttribute("SANPHAM", sp);
		return "Sanpham-register";
	}

	@RequestMapping(value = "/doSave", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String doSave(@Valid @ModelAttribute("SANPHAM") Sanpham sp, BindingResult bindingResult, Principal principal,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		if (bindingResult.hasErrors()) {
			return "Sanpham-register";
		} else {
			sp.setCreateday(new Timestamp(new Date().getTime()));
			sp.setCreateby(principal.getName());
			sp.setUpdateday(new Timestamp(new Date().getTime()));
			sp.setUpdateby(principal.getName());
			sp.setIsdelete((Integer) 0);
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			sp.setImage(fileName);
			this.sanphamService.insertSanpham(sp);

			String uploadDir = "./uploads/" + sp.getId();
	
			Path uploadPath = Paths.get(uploadDir);
		
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
	}

	@GetMapping("/sanpham-update/{id}")
	public String findBySanphamId(@PathVariable Long id, ModelMap model) {
		model.addAttribute("sp", this.sanphamService.finBySanphamId(id).get());
	
		return "form-san-pham";
	}

	@GetMapping("/sanpham-chitiet")
	@ResponseBody
	public Optional<Sanpham> findBySanphamChitiet(Long id) {
		return this.sanphamService.finBySanphamId(id);
	}

	@GetMapping("/add-chitiet")
	@ResponseBody
	public Optional<Sanpham> getSanphamchitiet(Long id) {
		return this.sanphamService.finBySanphamId(id);
	}

	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam("id") Long id, @RequestParam("masanpham") String masanpham,
			@RequestParam("tensanpham") String tensanpham, @RequestParam("loaisanpham") LoaiSanPham loaisanpham,
			@RequestParam("xuatxu") String xuatxu, @RequestParam("mota") String mota, Principal principal,
			@RequestPart("fileImages") MultipartFile multipartFile, @RequestParam("highlight") Integer highlight) throws IOException {

		Sanpham sp = new Sanpham(id, masanpham, tensanpham, loaisanpham, xuatxu, mota, highlight);
		sp.setCreateby(sp.getCreateby());
		sp.setCreateday(sp.getCreateday());
		sp.setUpdateday(new Timestamp(new Date().getTime()));
		sp.setUpdateby(principal.getName());
		sp.setIsdelete((Integer) 0);
	
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		sp.setImage(fileName);
		this.sanphamService.updateSanpham(sp);
		
		
		Path deleteFile = Paths.get("./uploads/" + sp.getId());
		{
			
				File file = deleteFile.toFile();
				if (file.isDirectory()) {
					for (File f : deleteFile.toFile().listFiles()) {
						System.out.println("da xoa" + f);
						f.delete();
						
					}
				} else {
					file.deleteOnExit();
				}
				
				file.delete();
				
				
			
		}
		
		

		String uploadDir = "./uploads/" + sp.getId();

		Path uploadPath = Paths.get(uploadDir);
		

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			
			if (ImageIO.read(multipartFile.getInputStream()) == null) {
				filePath.toString();
			}
			  
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			
		}
		
		return "redirect:/sanpham/list";
	}

	@ModelAttribute("LOAISANPHAM")
	public List<LoaiSanPham> getLoaisanpham() {
		return this.loaisanphamService.findAllLoaisanpham();
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphamlist", null);
		return "redirect:/sanpham/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.PUT })
	public String showSanPhamsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamlist");
		int pagesize = 5;
		List<Sanpham> list = (List<Sanpham>) this.sanphamService.getAllSanpham();
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
		request.getSession().setAttribute("sanphamlist", pages);

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

		model.addAttribute("SANPHAMS", pages);

		return "Sanpham-view";
	}

	@RequestMapping(value = "/dataSearch", method = {RequestMethod.GET})
	public String dateSearch(@RequestParam("tensanpham") String tensanpham, HttpSession session) {
		session.setAttribute("TENSANPHAM", tensanpham);

		if (tensanpham == null || tensanpham.equals("")) {
			return "redirect:/sanpham/list";
		} else {
			tensanpham = Xuly.xuLySearch(tensanpham);
			session.setAttribute("TENSANPHAM", tensanpham);
			return "redirect:/sanpham/list/search/1";
		}
	}

	@RequestMapping(value = "/list/search/{pageNumber}", method = {RequestMethod.GET})
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		String tensanpham = (String) session.getAttribute("TENSANPHAM");
		List<Sanpham> list = this.sanphamService.searchTenSanPham(tensanpham);
		
		if (list == null) {
			return "redirect:/sanpham/list/";
		}
		int sum = list.size();
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamlist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("sanphamlist", pages);

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

		model.addAttribute("SANPHAMS", pages);

		return "Sanpham-view";
	}
	
	@GetMapping(value = "/del")
	public String delete(@RequestParam("lsp[]") List<Long> ids, Principal principal) {
		for (Long long1 : ids) {
			Sanpham sp = this.sanphamService.finBySanphamId(long1).get();
			sp.setUpdateday(new Timestamp(new Date().getTime()));
			sp.setUpdateby(principal.getName());
			sp.setIsdelete((Integer) 1);
			this.sanphamService.deleteSanpham(sp);
		}
		return "redirect:/sanpham/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String add(@RequestParam("idsanpham") Sanpham idsanpham, @RequestParam("kichthuoc") String kichthuoc,
			@RequestParam("soluong") Integer soluong, @RequestParam("giatien") Float giatien,
			@RequestParam("giamgia") Integer giamgia) {
		SanphamVaChitiet spct = new SanphamVaChitiet(idsanpham, kichthuoc, soluong, giatien, giamgia, 0);
		
		this.sanphamVaChitietService.insertSanphamVaChitiet(spct);
		return "redirect:/sanpham/list";
	}

	@GetMapping("/barcode/{id}")
	public void barcode(@PathVariable Long id, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getQRCodeImage(String.valueOf(id), 100, 100));
		outputStream.flush();
		outputStream.close();
	}

}

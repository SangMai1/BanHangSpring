package com.learncode.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.comon.Xuly;
import com.learncode.models.ChucNang1;
import com.learncode.models.LoaiSanPham;
import com.learncode.models.Sanpham;
import com.learncode.models.Slides;
import com.learncode.service.SlidesService;

@Controller
@RequestMapping("/slides")
public class SlidesController {

	@Autowired
	SlidesService slidesService;

	@RequestMapping(value = "/", method = {RequestMethod.GET})
	public String home(ModelMap model) {
		Slides sli = new Slides();
		model.addAttribute("SLIDES", sli);
		return "Slides-register";
	}

	@RequestMapping(value = "/doSave", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String doSave(@ModelAttribute("SLIDES") Slides sli, Principal principal,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		
			
			sli.setCreateday(new Timestamp(new Date().getTime()));
//			sli.setCreateby(principal.getName());
//			System.out.println("nguoi tao" + principal.getName());
			sli.setUpdateday(new Timestamp(new Date().getTime()));
//			sli.setUpdateby(principal.getName());
			sli.setIsdelete((Integer) 0);
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			sli.setImages(fileName);
			this.slidesService.insertSlides(sli);

			String uploadDir = "./slides/" + sli.getId();

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
			return "redirect:/slides/list";
		
	}

	@GetMapping("/slides-update/{id}")
	public String findBySlidesId(@PathVariable Long id, ModelMap model) {
		model.addAttribute("sl", this.slidesService.findSlidesById(id).get());
		return "form-slides";
	}

	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam("id") Long id, @RequestParam("maslides") String maslides,
			@RequestParam("tenslides") String tenslides, Principal principal,
			@RequestPart("fileImages") MultipartFile multipartFile) throws IOException {

		Slides sl = new Slides(id, maslides, tenslides);
//		sl.setUpdateby(principal.getName());
		sl.setUpdateday(new Timestamp(new Date().getTime()));
		sl.setIsdelete((Integer) 0);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		sl.setImages(fileName);
		Path deleteFile = Paths.get("slides/" + sl.getId());
		{
			File file = deleteFile.toFile();
			if (file.isDirectory()) {
				for (File f : deleteFile.toFile().listFiles()) {
					f.delete();
				}
			} else {
				file.deleteOnExit();
			}
			file.delete();
		}

		this.slidesService.updateSlides(sl);

		String uploadDir = "./slides/" + sl.getId();

		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			System.out
					.println("=>" + ImageIO.read(multipartFile.getInputStream()) == null + " =" + filePath.toString());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			
		}
		return "redirect:/slides/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("slideslist", null);
		return "redirect:/slides/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.PUT })
	public String showSlidesPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("slideslist");
		int pagesize = 5;
		List<Slides> list = (List<Slides>) this.slidesService.getAll();
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
		request.getSession().setAttribute("slideslist", pages);

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

		model.addAttribute("SLIDESS", pages);

		return "Slides-view";
	}

	@RequestMapping(value = "/dataSearch", method = {RequestMethod.GET})
	public String dateSearch(@RequestParam("tenslides") String tenslides, HttpSession session) {
		session.setAttribute("TENSLIDES", tenslides);

		if (tenslides == null || tenslides.equals("")) {
			return "redirect:/slides/list";
		} else {
			tenslides = Xuly.xuLySearch(tenslides);
			session.setAttribute("TENSLIDES", tenslides);
			return "redirect:/slides/list/search/1";
		}
	}

	@RequestMapping(value = "/list/search/{pageNumber}", method = {RequestMethod.GET})
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber,
			HttpSession session) {
		String tenslides = (String) session.getAttribute("TENSLIDES");
		List<Slides> list = this.slidesService.searchTenSlides(tenslides);
		
		if (list == null) {
			return "redirect:/slides/list/";
		}
		int sum = list.size();
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("slideslist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("slideslist", pages);

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

		model.addAttribute("SLIDESS", pages);

		return "Slides-view";
	}
	
	@GetMapping(value = "/del")
	public String delete(@RequestParam("lsp[]") List<Long> ids, Principal principal) {
		for (Long long1 : ids) {
			Slides sl = this.slidesService.findSlidesById(long1).get();
			sl.setUpdateday(new Timestamp(new Date().getTime()));
//			sl.setUpdateby(principal.getName());
			sl.setIsdelete((Integer) 1);
			this.slidesService.deleteSlides(sl);
		}
		return "redirect:/slides/list";
	}
}

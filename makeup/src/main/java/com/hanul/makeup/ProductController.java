package com.hanul.makeup;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import image.ImageVO;
import product.ProductPageVO;
import product.ProductServiceImpl;
import product.ProductVO;

@Controller
public class ProductController {
	@Autowired private ProductPageVO page;
	@Autowired private ProductServiceImpl service;
	@Autowired private CommonService common;

	//상품 목록화면 요청
	@RequestMapping("/list.pd")
	public String list(Model model) {
		model.addAttribute("page", service.product_list(page));
		return "product/list";
	}
	
	//상품 등록화면 요청
	@RequestMapping("/new.pd")
	public String product(Model model) {
		return "product/new";
	}
	
	//상품 등록처리 요청
	@RequestMapping("/insert.pd")
	public String insert(ProductVO productVo, ImageVO imageVo, HttpSession ss, MultipartFile thumbNail, MultipartFile image[]) {
		productVo.setProduct_thumbNail( common.fileUpload(thumbNail, ss, "product") );	//물리적 위치에 파일 저장
		
		int result = service.product_insert(productVo);		// 상품 등록처리
		
		if( result == 1) {
			for( int i=0; i<image.length; i++ ) {
				if(image[i] != null && image[i].getSize() > 0 ) {
					imageVo.setImagepath( common.fileUpload(image[i], ss, "product") );
					service.image_insert(imageVo);
				}
			}
		}
		
		return "redirect:list.pd";
	}
	
	//상품 상세화면 요청
	@RequestMapping("/detail.pd")
	public String detail(int product_no, Model model) {
		model.addAttribute("vo", service.product_detail(product_no));
		model.addAttribute("imageList", service.image_detail(product_no));
		return "product/detail";
	}
	
	//상품 삭제처리 요청
	@RequestMapping("/delete.pd")
	public String delete(int product_no) {
		service.product_delete(product_no);
		return "redirect:list.pd";
	}
	
	//상품 수정화면 요청
	@RequestMapping("/modify.pd")
	public String modify(int product_no, Model model) {
		model.addAttribute("vo", service.product_detail(product_no));
		model.addAttribute("imageList", service.image_detail(product_no));

		return "product/modify";
	}
	
	//상품 수정처리 요청
	@RequestMapping("/update.pd")
	public String update(ProductVO vo, MultipartFile image[], HttpSession ss, Model model, int delete) {
		ProductVO old = service.product_detail( vo.getProduct_no() );
		String uuid = ss.getServletContext().getRealPath("resources") + old.getImagepath();
		
		
//		for(int i=0; i<image.length; i++) {
//			if( image[i].getSize() > 0) {
//				//파일을 첨부하는 경우
//				switch(i) {
//				case 0:
//					vo.setImagepath( common.fileUpload(image[i], ss, "product")); break;
//				case 1:
//					vo.setImage1( common.fileUpload(image[i], ss, "product")); break;
//				case 2:
//					vo.setImage2( common.fileUpload(image[i], ss, "product")); break;
//				case 3:
//					vo.setImage3( common.fileUpload(image[i], ss, "product")); break;
//				}
//				// 원래 첨부된 파일을 바꿔 첨부하는 경우 - 원래 첨부된 파일을 삭제한다.
////				File f = new File(uuid);
////				if ( f.exists() )f.delete();
//				
//			} else {
//				//파일을 첨부하지 않는 경우
//				if( delete != 1 ){
//					//1. 원래 첨부된 파일을 그대로 사용하는 경우
//					switch(i) {
//					case 0:
//						vo.setImagepath( old.getImagepath() ); break;
//					case 1:
//						vo.setImage1( old.getImage1() ); break;
//					case 2:
//						vo.setImage2( old.getImage2() ); break;
//					case 3:
//						vo.setImage3( old.getImage3() ); break;
//					}
//				}else {
//					//2. 원래 첨부된 파일을 삭제하는 경우
////					File f = new File(uuid);
////					if ( f.exists() )f.delete();
//					
//				}
//				
//			}
//		}
//		service.product_update(vo);
//		model.addAttribute("no", vo.getNo());
		return "product/redirect";
	}
	
	//상품 구매화면 요청
	@RequestMapping("/buy.pd")
	public String buy(Model model, int product_no) {
		model.addAttribute("vo", service.product_detail(product_no));
		return "product/buy";
	}
	
}

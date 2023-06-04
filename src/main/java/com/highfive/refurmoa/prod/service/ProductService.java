package com.highfive.refurmoa.prod.service;


import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.request.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.request.ProductWriteDTO;
import com.highfive.refurmoa.prod.DTO.response.FindProductDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListResponseDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdSearchDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

	Page<ProdListResponseDTO> productList(String search, String category, String status, Pageable pageable); // 상품 목록 조회
	public int productDelete(int code); // 상품 삭제
	public void insertProd(int comNum, Product prod); // 상품 등록
	public int ProductUpdate(MultipartFile mainImg, ProductWriteDTO prodDto) throws IOException; // 상품 수정
	public int ProductWrite(MultipartFile mainImg, ProductWriteDTO prodDto) throws IOException; // 상품 등록
	public void insertFile(ProdFileDTO dto); // 상품 이미지 저장
	public ProdResponseDTO productInfo(int productCode); // 상품 정보 조회
	
}

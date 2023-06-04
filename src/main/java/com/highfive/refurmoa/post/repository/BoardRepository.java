package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.post.dto.PostReadCountResquestDTO;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 판매상태(all)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate = :date_now " + // 오류방지
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusAllAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                              @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Ing and End)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate > :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusIngNEndAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                                  @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Yet and End)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate < :date_now OR NOT b.endDate > :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetNEndAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                                  @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Yet and Ing)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.endDate < :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetNIngAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                                  @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Yet)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate < :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                          @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Ing)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate > :date_now AND NOT b.endDate < :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusIngAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                          @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(End)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.endDate > :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusEndAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                          @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.readCount = b.readCount + 1 WHERE b.boardNum = :boardNum")
    void findByBoardNumAndUpdatePlusReadCount(@Param("boardNum") int boardNum);

    Board findByBoardNum(int boardNum);

    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.deleteCheck = true WHERE b.boardNum = :boardNum")
    void updateDeleteCheckByBoardNum(@Param("boardNum") int boardNum); // 판매 글 삭제

    @Transactional
    @Modifying
    @Query(value = "UPDATE Board b SET b.curPrice = :curPrice WHERE b.boardNum = :boardNum")
    void updateCurPriceByBoardNum(int curPrice, int boardNum); // 현재가 변경

    List<Board> findByProductProductCodeAndDeleteCheckFalseOrderByBoardNumDesc(@Param("productCode") int productCode);

    Board findByBoardNumAndDeleteCheckFalse(int boardNum); // 결제(상품) 정보 조회
}

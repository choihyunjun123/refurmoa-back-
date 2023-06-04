package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_num", nullable = false)
    private int payNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product product;

    @Column(name = "prod_price", nullable = false)
    private int prodPrice;

    @Column(name = "delivery_price", nullable = false)
    private int deliveryPrice;

    @Column(name = "pay_price", nullable = false)
    private int payPrice;

    @Column(name = "buy_method", nullable = false, length = 10)
    private String buyMethod;

    @Column(name = "coupon_num")
    private Integer couponNum;

    @Column(name = "mile_use")
    private Integer mileUse;

    @Column(name = "pay_date")
    private Date payDate;

    @Column(name = "pay_cancel", nullable = false)
    private boolean payCancel;

}

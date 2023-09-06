package com.fullstack2.website.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Tops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prodnum")
    private Long prodnum;//품번

    @Column(name = "name", length = 200, nullable = false, columnDefinition = "varchar(200) default '0'")
    private String name;//상품이름

    @Column(name = "kind", length = 1, nullable = false)
    private Character kind;//상품 종류

    @Column(name = "price", nullable = false, columnDefinition = "decimal(10,2) default 0.00")
    private Double price;//원가

    @Column(name = "disprice", nullable = false, columnDefinition = "decimal(10,2) default 0.00")
    private Double disprice;//할인가

    @Column(name = "content", length = 1000)
    private String content;//상품 설명

    @Column(name = "image", length = 50, nullable = false, columnDefinition = "varchar(50) default 'default.jpg'")
    private String image;//사진

    @Column(name = "useyn", length = 1, nullable = false, columnDefinition = "char default 'y'")
    private Character useyn;//판매 유뮤, 기본값 y, y = 판매, n = 판매중단

    @Column(name = "regdate", nullable = false, columnDefinition = "date default current_date")
    @Temporal(TemporalType.DATE)
    private Date regdate;//등록일

    // 생성자, getter 및 setter 메서드는 여기에 추가할 수 있습니다.

    @Override
    public String toString() {
        return "NewArrivals{" +
                "prodnum=" + prodnum +
                ", name='" + name + '\'' +
                ", kind=" + kind +
                ", price=" + price +
                ", disprice=" + disprice +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", useyn=" + useyn +
                ", regdate=" + regdate +
                '}';
    }
}

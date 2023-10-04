package com.fullstack2.website.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long itemcount;

	private String itemname;

	@ElementCollection
	@CollectionTable(name = "color_image_mapping", joinColumns = @JoinColumn(name = "itemcount"))
	@MapKeyColumn(name = "color")
	@Column(name = "image_path")
	private Map<String, String> colorImages;


	private String size;

	private String category;

	private int price;

	@Column(length = 1000)
	private String description;

	@Column(length = 1000)
	private String sizefit;

	@Column(length = 1000)
	private String asd;

	private String mainLink; //빅 이미지
	private String imglink; // 이미지 경로를 저장하는 필드
	private String imglink1; // 이미지 경로를 저장하는 필드
	private String imglink2; // 이미지 경로를 저장하는 필드
	private String imglink3; // 이미지 경로를 저장하는 필드

}

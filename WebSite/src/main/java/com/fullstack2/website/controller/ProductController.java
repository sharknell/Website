package com.fullstack2.webSite.controller;

import com.fullstack2.webSite.entity.Product;
import com.fullstack2.webSite.service.MemberService;
import com.fullstack2.webSite.service.Product_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final Product_Service productService;
    private final MemberService memberService;


    //벨트 제품 리스트로 맵핑belt
    @GetMapping(value = "/product/{id}")
    public String Product(Model model, @PathVariable("id") String id){
        List<Product> entity = productService.Category_item_All(id);
        model.addAttribute("list" ,entity);
        return "product";
    }

    @GetMapping(value = "/productdetail/{itemcount}")
    public String beltdetail(Product product, Model model){
        System.out.println(product.getItemcount());
        Optional<Product> productOptional = productService.SelectONE(product.getItemcount());
        model.addAttribute("Product", productOptional.get());
        //주입하면 명칭을 알수가 없다.

        return "/productdetail";
    }


}

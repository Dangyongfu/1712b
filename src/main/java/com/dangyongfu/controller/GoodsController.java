package com.dangyongfu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dangyongfu.beans.Brand;
import com.dangyongfu.beans.Goods;
import com.dangyongfu.beans.GoodsKind;
import com.dangyongfu.service.GoodsService;
import com.dangyongfu.utils.FileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class GoodsController {
	//查询分页
	@Resource
	private GoodsService goodsService;
	@RequestMapping("queryAll")
	public String queryAll(Model model,@RequestParam(defaultValue="1")Integer pageNum){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageSize=2;
		PageHelper.startPage(pageNum, pageSize);
		List<Goods> list = goodsService.queryAll(map );
		PageInfo<Goods> page = new PageInfo<Goods>(list);
		model.addAttribute("page", page);
		return "list";
	}
	@RequestMapping("toadd")
	public String toadd(Model model){
		List<Brand> brandlist = goodsService.queryBrand();
		List<GoodsKind> kindlist = goodsService.queryGoodsKind();
		model.addAttribute("brandlist", brandlist);
		model.addAttribute("kindlist", kindlist);
		return "add";
	}
	//添加
	@RequestMapping("add")
	public String addGoods(Goods goods,MultipartFile file) throws IllegalStateException, IOException{
		try {
			String upload = FileUtils.upload(file);
			goods.setPrcurl(upload);
			goodsService.addGoods(goods);
			return "redirect:queryAll";
		} catch (Exception e) {
			return "add";
		}
		
	}
	//查看图片
	@RequestMapping("lookImp")
	public void lookImg(String path,HttpServletRequest request,HttpServletResponse response){
		FileUtils.lookImg(path, request, response);
	}
	//查询一条记录用于回显
	@RequestMapping("toupdate")
	public String toupdate(Integer gid,Model model){
		Goods goods = goodsService.queryGoodsBygid(gid);
		model.addAttribute("goods", goods);
		List<Brand> brandlist = goodsService.queryBrand();
		List<GoodsKind> kindlist = goodsService.queryGoodsKind();
		model.addAttribute("brandlist", brandlist);
		model.addAttribute("kindlist", kindlist);
		return "update";
	}
	//修改
	@RequestMapping("update")
	public String updateGoods(Goods goods,MultipartFile file) throws IllegalStateException, IOException{
		try {
			String upload = FileUtils.upload(file);
			goods.setPrcurl(upload);
			goodsService.updateGoods(goods);
			return "redirect:queryAll";
		} catch (Exception e) {
			return "update";
		}
		
	}
	//删除
	@RequestMapping("deleteGoods")
	@ResponseBody
	public boolean deleteGoods(String ids){
		try {
			goodsService.deleteGoods(ids);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}

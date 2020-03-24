package com.dangyongfu.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dangyongfu.beans.Brand;
import com.dangyongfu.beans.Goods;
import com.dangyongfu.beans.GoodsKind;
import com.dangyongfu.mapper.GoodsMapper;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsMapper goodsMapper;
	public List<Goods> queryAll(Map<String, Object> map) {
		return goodsMapper.selectAll(map);
	}
	public List<Brand> queryBrand() {
		return goodsMapper.queryBrand();
	}
	public List<GoodsKind> queryGoodsKind() {
		return goodsMapper.queryGoodsKind();
	}
	public void addGoods(Goods goods) {
		goodsMapper.addGoods(goods);
		
	}
	public Goods queryGoodsBygid(Integer gid) {
		return goodsMapper.queryGoodsBygid(gid);
	}
	public void updateGoods(Goods goods) {
		goodsMapper.updateGoods(goods);
		
	}
	public void deleteGoods(String ids) {
		goodsMapper.deleteGoods(ids);
		
	}

}

package com.dangyongfu.mapper;

import java.util.List;
import java.util.Map;

import com.dangyongfu.beans.Brand;
import com.dangyongfu.beans.Goods;
import com.dangyongfu.beans.GoodsKind;

public interface GoodsMapper {
	// 查询分页 条件查询
	public List<Goods> selectAll(Map<String, Object> map);

	public List<Brand> queryBrand();

	public List<GoodsKind> queryGoodsKind();

	public void addGoods(Goods goods);

	public Goods queryGoodsBygid(Integer gid);

	public void updateGoods(Goods goods);

	public void deleteGoods(String ids);
}

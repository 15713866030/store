package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;

public interface DistrictMapper {
	/**
	 *根据父级的行政代号获取全国所有的省/某省所有的市/某市所有的县
	 * @param parent父级别的省市代号，如果需要获取全国所有的省，使用86
	 * @return
	 */
	List<District> findByParent(String parent);
	/**
	 * 通过省/市/区的行政代号查询相关信息
	 * @param code
	 * @return 匹配详情，如果没有说明找不到信息
	 */
	District findByCode(String code);

}

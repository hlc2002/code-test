package com.runjing.learn_runjing.erp.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * 库存核心表
 * @TableName erp_inventory_core
 */
@Data
public class ErpInventoryCore implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 区域公司编码
     */
    private String company_sn;

    /**
     * 仓库编码
     */
    private String warehouse_sn;

    /**
     * 商品编码
     */
    private String sku_sn;

    /**
     * 商品库存等级分类编码
     */
    private Integer goods_inv_level_category_no;

    /**
     * 商品库存等级编码
     */
    private Integer goods_inv_level_no;

    /**
     * 库存类型。0、实物；1、虚拟
     */
    private Integer inv_type;

    /**
     * 库存状态。0、可售；1、不可售
     */
    private Integer inv_status;

    /**
     * 库存数：实际实物库存数	
     */
    private Integer qty;

    /**
     * 订单占用数
     */
    private Integer ord_occupy_qty;

    /**
     * 预占数	
     */
    private Integer pre_occupy_qty;

    /**
     * 采购在途数	
     */
    private Integer pur_in_transit_inv_qty;

    /**
     * 调拨在途数	
     */
    private Integer trf_in_transit_inv_qty;

    /**
     * 预采购在途
     */
    private Integer pre_pur_in_transit_inv_qty;

    /**
     * 剩余分配数
     */
    private Integer remaining_assign_qty;

    /**
     * 版本号
     */
    private Integer version_number;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新时间
     */
    private LocalDateTime update_time;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    @Transient
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ErpInventoryCore other = (ErpInventoryCore) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompany_sn() == null ? other.getCompany_sn() == null : this.getCompany_sn().equals(other.getCompany_sn()))
            && (this.getWarehouse_sn() == null ? other.getWarehouse_sn() == null : this.getWarehouse_sn().equals(other.getWarehouse_sn()))
            && (this.getSku_sn() == null ? other.getSku_sn() == null : this.getSku_sn().equals(other.getSku_sn()))
            && (this.getGoods_inv_level_category_no() == null ? other.getGoods_inv_level_category_no() == null : this.getGoods_inv_level_category_no().equals(other.getGoods_inv_level_category_no()))
            && (this.getGoods_inv_level_no() == null ? other.getGoods_inv_level_no() == null : this.getGoods_inv_level_no().equals(other.getGoods_inv_level_no()))
            && (this.getInv_type() == null ? other.getInv_type() == null : this.getInv_type().equals(other.getInv_type()))
            && (this.getInv_status() == null ? other.getInv_status() == null : this.getInv_status().equals(other.getInv_status()))
            && (this.getQty() == null ? other.getQty() == null : this.getQty().equals(other.getQty()))
            && (this.getOrd_occupy_qty() == null ? other.getOrd_occupy_qty() == null : this.getOrd_occupy_qty().equals(other.getOrd_occupy_qty()))
            && (this.getPre_occupy_qty() == null ? other.getPre_occupy_qty() == null : this.getPre_occupy_qty().equals(other.getPre_occupy_qty()))
            && (this.getPur_in_transit_inv_qty() == null ? other.getPur_in_transit_inv_qty() == null : this.getPur_in_transit_inv_qty().equals(other.getPur_in_transit_inv_qty()))
            && (this.getTrf_in_transit_inv_qty() == null ? other.getTrf_in_transit_inv_qty() == null : this.getTrf_in_transit_inv_qty().equals(other.getTrf_in_transit_inv_qty()))
            && (this.getPre_pur_in_transit_inv_qty() == null ? other.getPre_pur_in_transit_inv_qty() == null : this.getPre_pur_in_transit_inv_qty().equals(other.getPre_pur_in_transit_inv_qty()))
            && (this.getRemaining_assign_qty() == null ? other.getRemaining_assign_qty() == null : this.getRemaining_assign_qty().equals(other.getRemaining_assign_qty()))
            && (this.getVersion_number() == null ? other.getVersion_number() == null : this.getVersion_number().equals(other.getVersion_number()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompany_sn() == null) ? 0 : getCompany_sn().hashCode());
        result = prime * result + ((getWarehouse_sn() == null) ? 0 : getWarehouse_sn().hashCode());
        result = prime * result + ((getSku_sn() == null) ? 0 : getSku_sn().hashCode());
        result = prime * result + ((getGoods_inv_level_category_no() == null) ? 0 : getGoods_inv_level_category_no().hashCode());
        result = prime * result + ((getGoods_inv_level_no() == null) ? 0 : getGoods_inv_level_no().hashCode());
        result = prime * result + ((getInv_type() == null) ? 0 : getInv_type().hashCode());
        result = prime * result + ((getInv_status() == null) ? 0 : getInv_status().hashCode());
        result = prime * result + ((getQty() == null) ? 0 : getQty().hashCode());
        result = prime * result + ((getOrd_occupy_qty() == null) ? 0 : getOrd_occupy_qty().hashCode());
        result = prime * result + ((getPre_occupy_qty() == null) ? 0 : getPre_occupy_qty().hashCode());
        result = prime * result + ((getPur_in_transit_inv_qty() == null) ? 0 : getPur_in_transit_inv_qty().hashCode());
        result = prime * result + ((getTrf_in_transit_inv_qty() == null) ? 0 : getTrf_in_transit_inv_qty().hashCode());
        result = prime * result + ((getPre_pur_in_transit_inv_qty() == null) ? 0 : getPre_pur_in_transit_inv_qty().hashCode());
        result = prime * result + ((getRemaining_assign_qty() == null) ? 0 : getRemaining_assign_qty().hashCode());
        result = prime * result + ((getVersion_number() == null) ? 0 : getVersion_number().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", company_sn=").append(company_sn);
        sb.append(", warehouse_sn=").append(warehouse_sn);
        sb.append(", sku_sn=").append(sku_sn);
        sb.append(", goods_inv_level_category_no=").append(goods_inv_level_category_no);
        sb.append(", goods_inv_level_no=").append(goods_inv_level_no);
        sb.append(", inv_type=").append(inv_type);
        sb.append(", inv_status=").append(inv_status);
        sb.append(", qty=").append(qty);
        sb.append(", ord_occupy_qty=").append(ord_occupy_qty);
        sb.append(", pre_occupy_qty=").append(pre_occupy_qty);
        sb.append(", pur_in_transit_inv_qty=").append(pur_in_transit_inv_qty);
        sb.append(", trf_in_transit_inv_qty=").append(trf_in_transit_inv_qty);
        sb.append(", pre_pur_in_transit_inv_qty=").append(pre_pur_in_transit_inv_qty);
        sb.append(", remaining_assign_qty=").append(remaining_assign_qty);
        sb.append(", version_number=").append(version_number);
        sb.append(", remark=").append(remark);
        sb.append(", update_time=").append(update_time);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
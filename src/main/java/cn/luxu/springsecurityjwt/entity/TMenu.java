package cn.luxu.springsecurityjwt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * Controller路径
     */
    private String menuUrl;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 菜单类型：0-菜单1-按钮
     */
    private Integer menuType;

    /**
     * 显示序号
     */
    private Integer orderNum;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updator;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除状态：0-存在1-已删除
     */
    private Integer deleted;


}

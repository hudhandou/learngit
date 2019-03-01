package com.turing.system.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SysMenu {
    private String menuId;

    private String menuUrl;

    private String menuName;

    private String menuIslink;

    private BigDecimal menuSequ;

    private String menuPare;

    private String menuState;

    private String menuInfo;

    private String menuIco;
    
    private String userId;
    
    private List menuList = new ArrayList();

    /** default constructor */
	public SysMenu() {
	}
	
	public SysMenu(String menuId, String menuUrl, String menuName,
			String menuIslink, String menuPare) {
		super();
		this.menuId = menuId;
		this.menuUrl = menuUrl;
		this.menuName = menuName;
		this.menuIslink = menuIslink;
		this.menuPare = menuPare;
	}
    
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuIslink() {
        return menuIslink;
    }

    public void setMenuIslink(String menuIslink) {
        this.menuIslink = menuIslink == null ? null : menuIslink.trim();
    }

    public BigDecimal getMenuSequ() {
        return menuSequ;
    }

    public void setMenuSequ(BigDecimal menuSequ) {
        this.menuSequ = menuSequ;
    }

    public String getMenuPare() {
        return menuPare;
    }

    public void setMenuPare(String menuPare) {
        this.menuPare = menuPare == null ? null : menuPare.trim();
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState == null ? null : menuState.trim();
    }

    public String getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo == null ? null : menuInfo.trim();
    }

    public String getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco == null ? null : menuIco.trim();
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "SysMenu [menuId=" + menuId + ", menuUrl=" + menuUrl + ", menuName=" + menuName + ", menuIslink="
				+ menuIslink + ", menuSequ=" + menuSequ + ", menuPare=" + menuPare + ", menuState=" + menuState
				+ ", menuInfo=" + menuInfo + ", menuIco=" + menuIco + ", userId=" + userId + ", menuList=" + menuList
				+ "]";
	}
}
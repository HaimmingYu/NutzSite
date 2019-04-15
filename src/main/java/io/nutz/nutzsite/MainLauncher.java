package io.nutz.nutzsite;

import io.nutz.nutzsite.common.utils.ShiroUtils;
import io.nutz.nutzsite.common.utils.TreeUtils;
import io.nutz.nutzsite.module.sys.models.Menu;
import io.nutz.nutzsite.module.sys.models.User;
import io.nutz.nutzsite.module.sys.services.MenuService;
import org.nutz.boot.NbApp;
import org.nutz.conf.NutConf;
import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.*;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean(create="init", depose="depose")
@Localization(value="msg/", defaultLocalizationKey="zh-CN")
public class MainLauncher {

    @Inject
    protected PropertiesProxy conf;
    @Inject
    private MenuService menuService;

    @At({"/", "/index"})
    @Ok("re")
    public String index( HttpServletRequest req) {
        User user = ShiroUtils.getSysUser();
        if(Lang.isEmpty(user)){
            return "th:/login.html";
        }
        List<Menu> menuList =menuService.getMenuList(user.getId());
        req.setAttribute("menus", TreeUtils.getChildPerms(menuList,"0"));
        return "th:/index.html";
    }

    /**
     * 系统介绍
     * @return
     */
    @At({"/sys/main"})
    @Ok("th:/main.html")
    public NutMap main()
    {
        return NutMap.NEW().setv("version", "1.0");
    }

    public void init() {
        // NB自身初始化完成后会调用这个方法
//        Daos.createTablesInPackage(dao, "io.nutz.nutzsite.module", false);
    }
    public void depose() {}

    public static void main(String[] args) throws Exception {
        new NbApp().setArgs(args).setPrintProcDoc(true).run();
        NutConf.USE_FASTCLASS = true;
    }

}

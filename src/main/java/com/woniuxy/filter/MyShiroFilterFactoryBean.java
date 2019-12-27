package com.woniuxy.filter;

import com.woniuxy.pojo.MenuPermission;
import com.woniuxy.service.MenuService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {
    private static final String ROLE_STRING="roles[{0}]";
    private String filterChainDefinitions;
    @Autowired
    private MenuService menuService;
    @Override
    public void setFilterChainDefinitions(String definitions) {
        filterChainDefinitions=definitions;
        Ini ini=new Ini();
        ini.load(definitions);
        Ini.Section section=ini.getSection("urls");
        if(CollectionUtils.isEmpty(section)){
            section=ini.getSection("");
        }
        StringBuffer sb=new StringBuffer();
        List<MenuPermission> menuPermissions=this.menuService.getMenuPerms();
        if(menuPermissions!=null){
            for(MenuPermission menuPermission:menuPermissions){
                List<Integer> roleIds=menuPermission.getRoleIds();
                String url=menuPermission.getUrl();
                if(StringUtils.hasLength(menuPermission.getUrl())&&roleIds!=null&&roleIds.size()>0){
                    for (Integer role:roleIds){
                        sb.append(role).append(",");
                        System.out.println(roleIds+"*****");
                    }
                    String str=sb.substring(0,sb.length()-1);
                    System.out.println("************"+MessageFormat.format(ROLE_STRING, sb.toString()));
                    System.out.println("***&&&"+menuPermission.getUrl().substring(0,menuPermission.getUrl().indexOf("?")));
                    section.put("/"+menuPermission.getUrl().substring(0,menuPermission.getUrl().indexOf("?")),MessageFormat.format(ROLE_STRING,str));
                }
            }
        }
        section.put("/**","authc");
        this.setFilterChainDefinitionMap(section);

        Map<String,String> all=this.getFilterChainDefinitionMap();
        Set<Map.Entry<String,String>> es=all.entrySet();
        for(Map.Entry<String,String> e:es){
            System.out.println(e.getKey()+"------>"+e.getValue());
        }

    }
}

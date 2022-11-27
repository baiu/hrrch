package com.baiu.hrrch.groupcatalog;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.catalog.CatalogDto2;
import com.baiu.hrrch.group.Group;
import com.baiu.hrrch.group.GroupDto;

@Component
public class GroupCatalogMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(GroupCatalog.class, GroupCatalogDto.class)
                .byDefault()
                .customize(new CustomMapper<GroupCatalog, GroupCatalogDto>() {
                    @Override
                    public void mapAtoB(GroupCatalog groupCatalog, GroupCatalogDto groupCatalogDto, MappingContext context) {
                        groupCatalogDto.setCatalogDto(map(groupCatalog.getCatalog(), CatalogDto2.class));
                        groupCatalogDto.setGroupDto(map(groupCatalog.getGroup(), GroupDto.class));
                    }
                }).register();

        factory.classMap(GroupCatalogDto.class, GroupCatalog.class)
                .byDefault()
                .customize(new CustomMapper<GroupCatalogDto, GroupCatalog>() {
                    @Override
                    public void mapAtoB(GroupCatalogDto groupCatalogDto, GroupCatalog groupCatalog, MappingContext context) {
                        groupCatalog.setCatalog(map(groupCatalogDto.getCatalogDto(), Catalog.class));
                        groupCatalog.setGroup(map(groupCatalogDto.getGroupDto(), Group.class));
                    }
                }).register();
    }
}


package com.baiu.hrrch.group;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
//todo реализовать при доработке контроллера группы
        factory.classMap(Group.class, GroupDto.class)
                .byDefault()
                .customize(new CustomMapper<Group, GroupDto>() {
                    @Override
                    public void mapAtoB(Group group, GroupDto groupDto, MappingContext context) {
                        groupDto.setPersons(group.getPersons());
                    }
                })
                .register();

        factory.classMap(GroupDto.class, Group.class)
                .byDefault()
                .register();
    }
}

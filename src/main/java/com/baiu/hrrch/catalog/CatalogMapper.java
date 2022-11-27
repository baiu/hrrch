package com.baiu.hrrch.catalog;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CatalogMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Catalog.class, CatalogDto.class)
                .customize(new CustomMapper<Catalog, CatalogDto>() {
                    @Transactional(readOnly = true)
                    @Override
                    public void mapAtoB(Catalog catalog, CatalogDto catalogDto, MappingContext context) {
                        catalogDto.setId(catalog.getId());
                        catalogDto.setName(catalog.getName());
                        catalogDto.setIdx(catalog.getIdx());
                        catalogDto.setChildren(mapAsList(catalog.getChildrenCatalogs(), CatalogDto.class));
                        catalogDto.setAncestorsPath(catalog.getAncestorsPath());
                        catalogDto.setChildCount(catalog.getChildCount());
                    }
                })
                .register();

        factory.classMap(CatalogDto.class, Catalog.class)
                .field("id", "id")
                .field("name", "name")
                .field("idx", "idx")
                .field("ancestorsPath", "ancestorsPath")
                .field("childCount", "childCount")
                .byDefault()
                .customize(new CustomMapper<CatalogDto, Catalog>() {
                    @Transactional(readOnly = true)
                    @Override
                    public void mapAtoB(CatalogDto catalogDto, Catalog catalog, MappingContext context) {
                        catalog.setChildrenCatalogs(mapAsSet(catalogDto.getChildren(), Catalog.class));
                    }
                }).register();

        factory.classMap(Catalog.class, CatalogDto2.class)
                .byDefault()
                .customize(new CustomMapper<Catalog, CatalogDto2>() {
                    @Override
                    public void mapAtoB(Catalog catalog, CatalogDto2 catalogDto2, MappingContext context) {
                        catalogDto2.setParentId(catalog.getParentCatalog() == null ? null : catalog.getParentCatalog().getId());
                    }
                }).register();

        factory.classMap(CatalogDto2.class, Catalog.class)
                .byDefault()
                .customize(new CustomMapper<CatalogDto2, Catalog>() {
                    @Override
                    public void mapAtoB(CatalogDto2 catalogDto2, Catalog catalog, MappingContext context) {
                        catalog.setParentCatalog(catalogDto2.getParentId() == null ? null : new Catalog(catalogDto2.getParentId()));
                    }
                }).register();
    }
}

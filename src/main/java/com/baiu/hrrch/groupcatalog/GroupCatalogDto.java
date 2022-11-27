package com.baiu.hrrch.groupcatalog;

import com.baiu.hrrch.catalog.CatalogDto2;
import com.baiu.hrrch.group.GroupDto;

public class GroupCatalogDto {
    private Long id;
    private GroupDto groupDto;
    private CatalogDto2 catalogDto;
    private boolean canEdit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }

    public CatalogDto2 getCatalogDto() {
        return catalogDto;
    }

    public void setCatalogDto(CatalogDto2 catalogDto) {
        this.catalogDto = catalogDto;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
}

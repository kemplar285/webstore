package com.shop.webstore.data.service;

import java.util.List;

public interface IService<DTO, ID> {
    DTO add(DTO item);
    void delete(ID id);
    DTO findById(ID id);
    DTO edit(ID id, DTO item);
    List<DTO> findAll();
}

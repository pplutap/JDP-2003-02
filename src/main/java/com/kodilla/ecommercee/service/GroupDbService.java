package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupDbService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    public void deleteById(Long id) {
        Group group = groupRepository.findById(id).get();
        for (Product product : group.getProducts()) {
            Group productGroup = product.getGroup();
            if (productGroup != null) {
                product.setGroup(null);
                productRepository.save(product);
            }
        }
        groupRepository.deleteById(id);
    }
}

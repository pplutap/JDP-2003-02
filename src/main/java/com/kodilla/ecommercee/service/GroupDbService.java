package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public Optional<Group> getGroup(Long id) {
        return groupRepository.findById(id);
    }
}

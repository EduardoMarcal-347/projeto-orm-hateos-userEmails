package com.iftm.subscription.services;

import com.iftm.subscription.controllers.GroupController;
import com.iftm.subscription.data.vo.EmailVO;
import com.iftm.subscription.data.vo.GroupVO;
import com.iftm.subscription.mapper.DozerMapper;
import com.iftm.subscription.models.Group;
import com.iftm.subscription.models.User;
import com.iftm.subscription.repositories.GroupRepository;
import com.iftm.subscription.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GroupService {

    @Autowired
    GroupRepository repository;

    @Autowired
    UserRepository userRepository;

    public List<GroupVO> findAll() {
        var groupDbList = repository.findAll();
        var groups = DozerMapper.parseListObject(groupDbList, GroupVO.class);
        groups.stream().forEach(group -> {
            group.add(linkTo(methodOn(GroupController.class).findById(group.getId())).
                    withSelfRel());
        });
        return groups;
    }

    public GroupVO findById(Long id) {
        Optional<Group> groupDb = repository.findById(id);
        if(groupDb.isPresent()) {
            var group = DozerMapper.parseObject(groupDb.get(), GroupVO.class);
            group.add(linkTo(methodOn(GroupController.class).findById(group.getId()))
                    .withSelfRel());
            return group;
        }
        return null;
    }

    public GroupVO findByLink(String link){
        Optional<Group> groupDb = Optional.ofNullable(repository.findByLink(link));
        var group = DozerMapper.parseObject(repository.findByLink(link), GroupVO.class);
        group.add(linkTo(methodOn(GroupController.class).findById(group.getId()))
                .withSelfRel());
        return group;
    }

    public List<GroupVO> findByName(String name){
        Optional<List<Group>> groupDb = Optional.ofNullable(repository.findByName(name));
        if(groupDb.isPresent()) {
            var groups = DozerMapper.parseListObject(repository.findByName(name), GroupVO.class);
            groups.stream().forEach(group -> {
                group.add(linkTo(methodOn(GroupController.class).findById(group.getId()))
                        .withSelfRel());
            });
            return groups;
        }
        return null;
    }

    public GroupVO save(GroupVO groupVO) {
        if (verifyGroup(groupVO)){
            Group group = DozerMapper.parseObject(groupVO, Group.class);
            var groupDb = repository.save(group);
            groupVO = DozerMapper.parseObject(groupDb, GroupVO.class);
            groupVO.add(linkTo(methodOn(GroupController.class).findById(groupVO.getId()))
                    .withSelfRel());
        }
        return null;
    }

    public GroupVO insertUsers(GroupVO groupVO) {

        List<User> users = new ArrayList<>();

        var dbGroupOp = repository.findById(groupVO.getId());
        if(dbGroupOp.isPresent())
        {
            var dbGroup = dbGroupOp.get();
            for(var user : groupVO.getUsers()) {
                Optional<User> managedUserOpt = userRepository.findById(user.getId());
                if (managedUserOpt.isPresent())
                {
                    User managedUser = managedUserOpt.get();
                    users.add(managedUser);
                    managedUser.addGroup(dbGroup); // Use o método helper para sincronizar a relação bidirecional

                }
            }

            dbGroup.setUsers(users);
            dbGroup = repository.save(dbGroup);
            var group = DozerMapper.parseObject(dbGroup, GroupVO.class);
            group.add(linkTo(methodOn(GroupController.class).findById(group.getId()))
                    .withSelfRel());
        }
        return null;
    }

    public GroupVO update(GroupVO groupVO) {
        Optional<Group> groupDb = repository.findById(groupVO.getId());
        if(groupDb.isPresent())
        {
            Group group = DozerMapper.parseObject(groupVO, Group.class);
            group = repository.save(group);
            groupVO = DozerMapper.parseObject(group, GroupVO.class);
            groupVO.add(linkTo(methodOn(GroupController.class).findById(group.getId()))
                    .withSelfRel());
            return groupVO;
        }
        return null;
    }

    public String delete(Long id) {
        var groupDb = repository.findById(id);
        if(groupDb.isPresent()) {
            repository.deleteById(id);
            return "Group with id " + id + " has been deleted!";
        }else
            return "Group ID " + id + " not found!";
    }

    private boolean verifyGroup(GroupVO groupVO) {
        if (!groupVO.getCode().isBlank() && !groupVO.getCode().isEmpty() &&
            !groupVO.getName().isBlank() && !groupVO.getName().isEmpty() &&
            !groupVO.getLink().isBlank() && !groupVO.getLink().isEmpty() &&
            !groupVO.getUsers().isEmpty() ) {
            return true;
        }
        return false;
    }
}

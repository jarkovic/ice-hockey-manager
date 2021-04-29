package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;
import cz.fi.muni.pa165.hockeymanager.service.BeanMappingService;
import cz.fi.muni.pa165.hockeymanager.service.TeamService;
import cz.fi.muni.pa165.hockeymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

public class UserFacadeImpl implements UserFacade {
    @Inject
    UserService userService;

    @Inject
    TeamService teamService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(UserDto user) {
        User mappedUser = beanMappingService.mapTo(user, User.class);

        mappedUser.setName(user.getName());
        mappedUser.setEmail(user.getEmail());
        mappedUser.setPassword(user.getPassword());
        mappedUser.setRole(user.getRole());
        mappedUser.setTeam(teamService.findById(user.getTeam().getId()));
        User newUser = userService.create(mappedUser);
        return newUser.getId();
    }

    @Override
    public void remove(Long id) {
        userService.remove(userService.findById(id));
    }

    @Override
    public UserDto getMatchById(Long id) {
        User user = userService.findById(id);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return beanMappingService.mapTo(userService.findAll(), UserDto.class);
    }
}

package com.paymybuddy.connection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.connection.connectionDTO.ConnectionDTO;
import com.paymybuddy.connection.connectionDTO.CurrentConnectionDTO;
import com.paymybuddy.connection.model.Connection;
import com.paymybuddy.connection.repository.ConnectionRepository;
import com.paymybuddy.security.SecurityService;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;

@Service("connectionService")
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private UserService userService;

	@Override
	public String newConnection(ConnectionDTO connectionMail) {
		String currentUserName = SecurityService.getCurrentUserName();
		User currentUser = userService.getUserByUserName(currentUserName).get();

		Optional<User> connectedUser = userService.getUserByUserName(connectionMail.getMail());

		List<Connection> connectionList = connectionRepository.findByUser1_idOrUser2_id(currentUser.getId(),
				currentUser.getId());

		boolean isAlreadyConnected = false;

		for (int i = 0; i < connectionList.size(); i++) {
			Connection c = connectionList.get(i);
			if (c.getUser1().equals(connectedUser.get()) || c.getUser2().equals(connectedUser.get())) {
				isAlreadyConnected = true;
				break;
			}
		}

		if (currentUserName.equals(connectionMail.getMail()) || (connectedUser.isEmpty()) || isAlreadyConnected) {
			return "Error";
		} else {
			Connection connection = new Connection(currentUser, connectedUser.get());
			connectionRepository.save(connection);
		}
		return "Connection created";
	}

	@Override
	public List<CurrentConnectionDTO> getAllConnection() {
		String currentUserName = SecurityService.getCurrentUserName();
		User currentUser = userService.getUserByUserName(currentUserName).get();
		List<Connection> connectionList = connectionRepository.findByUser1_idOrUser2_id(currentUser.getId(),
				currentUser.getId());
		List<CurrentConnectionDTO> currentConnectionDTOList = new ArrayList<>();
		connectionList.forEach(c -> {
			CurrentConnectionDTO currentConnectionDTO;
			if (c.getUser1().getId() == currentUser.getId()) {
				currentConnectionDTO = new CurrentConnectionDTO(c.getUser2());
			} else {
				currentConnectionDTO = new CurrentConnectionDTO(c.getUser1());
			}
			currentConnectionDTOList.add(currentConnectionDTO);
		});
		return currentConnectionDTOList;
	}

}

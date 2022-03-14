package com.paymybuddy.connection.service;

import java.util.List;

import com.paymybuddy.connection.connectionDTO.ConnectionDTO;
import com.paymybuddy.connection.connectionDTO.CurrentConnectionDTO;

public interface ConnectionService {

	public String newConnection(ConnectionDTO connectionMail);

	public List<CurrentConnectionDTO> getAllConnection();

}

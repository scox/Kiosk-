package com.kiosk.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

@Service
public class EmergencyStopServiceImpl implements EmergencyStopService {

	@Override
	public boolean triggerEmergencyStop() {

		boolean status = false;

		Socket Socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			Socket = new Socket("localhost", 4444);
			out = new PrintWriter(Socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					Socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: localhost");
			System.exit(1);
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to: localhost.");
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String fromServer;

		try {


			while ((fromServer = in.readLine()) != null) {

						
				if (fromServer.equals("4")) {
					System.out.println("Got Alert");
					status = true;
					out.print("STOP");
					out.close();
					in.close();
					stdIn.close();
					Socket.close();
					break;
				} else {
					System.out.println("Client: constructed packet ");
					out.println("4");
				}
			}
		

		}

		catch (Exception e) {
			System.out.println("Couldnt send request");
			status = true;
		}

		return status;

	}

}